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
}
