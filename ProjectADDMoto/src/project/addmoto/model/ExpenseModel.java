/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.addmoto.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import project.addmoto.data.Expense;
import project.addmoto.data.MonthAverage;
import project.addmoto.database.Database;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class ExpenseModel {

    private final Connection connection;
    private String query;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public ExpenseModel(final Connection connection) {
        this.connection = connection;

        System.out.println("Expense Model called.");
    }

    public double getTotalExpenseToday() {
        try {
            query = "SELECT IFNULL(SUM(" + Database.EXPENSE_AMOUNT + "),0) FROM " + Database.EXPENSE_TABLE
                    + " WHERE STR_TO_DATE(" + Database.EXPENSE_TIMESTAMP + ", '%b %d, %Y') = CURDATE()"
                    + " GROUP BY STR_TO_DATE(" + Database.EXPENSE_TIMESTAMP + ", '%b %d, %Y');";

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                return resultSet.getDouble(1);
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return 0.00;
    }

    public ArrayList<Expense> getExpensesToday() {
        ArrayList<Expense> expenses = new ArrayList<>();
        try {
            query = "SELECT * FROM " + Database.EXPENSE_TABLE
                    + " WHERE STR_TO_DATE(" + Database.EXPENSE_TIMESTAMP + ", '%b %d, %Y') = CURDATE();";

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            System.out.println("Today: " + resultSet.getFetchSize());
            while (resultSet.next()) {
                expenses.add(
                        new Expense(
                                resultSet.getInt(Database.EXPENSE_ID),
                                resultSet.getDouble(Database.EXPENSE_AMOUNT),
                                resultSet.getString(Database.EXPENSE_TIMESTAMP),
                                resultSet.getString(Database.EXPENSE_REASON),
                                resultSet.getString(Database.EXPENSE_DETAIL),
                                resultSet.getInt(Database.EXPENSE_SELLER_ID_FK)
                        )
                );
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return expenses;
    }

    public double getTotalExpenseThisMonth() {
        try {
            query = "SELECT IFNULL(SUM(" + Database.EXPENSE_AMOUNT + "), 0) FROM "
                    + Database.EXPENSE_TABLE + " WHERE YEAR(STR_TO_DATE(" + Database.EXPENSE_TIMESTAMP + ", '%b %d, %Y')) = "
                    + "YEAR(CURRENT_DATE - INTERVAL 0 MONTH) AND MONTH(STR_TO_DATE(" + Database.EXPENSE_TIMESTAMP + ", '%b %d, %Y')) "
                    + "=  MONTH(CURRENT_DATE - INTERVAL 0 MONTH)";

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                return resultSet.getDouble(1);
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return 0.00;
    }

    public ArrayList<Expense> getExpensesThisMonth() {
        ArrayList<Expense> expenses = new ArrayList<>();
        try {
            query = "SELECT * FROM "
                    + Database.EXPENSE_TABLE + " WHERE YEAR(STR_TO_DATE(" + Database.EXPENSE_TIMESTAMP + ", '%b %d, %Y')) = "
                    + "YEAR(CURRENT_DATE - INTERVAL 0 MONTH) AND MONTH(STR_TO_DATE(" + Database.EXPENSE_TIMESTAMP + ", '%b %d, %Y')) "
                    + "=  MONTH(CURRENT_DATE - INTERVAL 0 MONTH) ORDER BY STR_TO_DATE(" + Database.EXPENSE_TIMESTAMP + ", '%b %d, %Y') DESC";

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            System.out.println("Month: " + resultSet.getFetchSize());
            while (resultSet.next()) {
                expenses.add(
                        new Expense(
                                resultSet.getInt(Database.EXPENSE_ID),
                                resultSet.getDouble(Database.EXPENSE_AMOUNT),
                                resultSet.getString(Database.EXPENSE_TIMESTAMP),
                                resultSet.getString(Database.EXPENSE_REASON),
                                resultSet.getString(Database.EXPENSE_DETAIL),
                                resultSet.getInt(Database.EXPENSE_SELLER_ID_FK)
                        )
                );
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }

        return expenses;
    }
    
    public MonthAverage[] getMonthlyAverage() {
        ArrayList<MonthAverage> list = new ArrayList<>();
        
        try {
            query = "select year(STR_TO_DATE(" + Database.EXPENSE_TIMESTAMP +
                    ", '%b %d, %Y')) as 'year', month(STR_TO_DATE(" + Database.EXPENSE_TIMESTAMP +
                    ", '%b %d, %Y')) as 'month', ifnull(sum(" + Database.EXPENSE_AMOUNT +
                    "),0) as 'sumtotal' from " + Database.EXPENSE_TABLE +
                    " group by month(STR_TO_DATE(" + Database.EXPENSE_TIMESTAMP + ", '%b %d, %Y'))";
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            
            while(resultSet.next()) {
                int year = resultSet.getInt("year");
                int month = resultSet.getInt("month");
                double amount = resultSet.getDouble("sumtotal");
                list.add(
                        new MonthAverage(
                                year,
                                month,
                                amount
                        )
                );
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        MonthAverage[] yA = new MonthAverage[3];
        if(list.isEmpty()) {
            yA[0] = new MonthAverage(0, 0, 0.0);
            yA[1] = new MonthAverage(0, 0, 0.0);
            yA[2] = new MonthAverage(0, 0, 0.0);
        } else {
            double avrg = 0.0;
            MonthAverage high = list.get(0), low = high;
            for (MonthAverage ya : list) {
                avrg += ya.expense;
                if (ya.expense >= high.expense) {
                    high = ya;
                }
                if (ya.expense <= low.expense) {
                    low = ya;
                }
            }
            avrg = ((avrg*1.00) / list.size());
            yA[0] = high;
            yA[1] = low;
            yA[2] = new MonthAverage(0, 0, avrg);
        }
        
        return yA;
    }
    
    public ArrayList<MonthAverage> getAverage() {
        ArrayList<MonthAverage> mAverage = new ArrayList<>();
        
        try {
            query = "select year(STR_TO_DATE(expense_timestamp, '%b %d, %Y')) as 'year', " +
                    "month(STR_TO_DATE(expense_timestamp, '%b %d, %Y')) as 'month', " +
                    "ifnull(sum(expense_amount), 0) as 'sumtotal' " +
                    "from expense_table " +
                    "group by month(STR_TO_DATE(expense_timestamp, '%b %d, %Y')) " +
                    "order by year, month limit 5;";
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                mAverage.add(
                        new MonthAverage(
                                resultSet.getInt("year"),
                                resultSet.getInt("month"),
                                resultSet.getDouble("sumtotal")
                        )
                );
            }
        } catch(Exception exc) {
            exc.printStackTrace();
        }
        
        return mAverage;
    }
}
