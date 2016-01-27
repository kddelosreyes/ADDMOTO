/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.addmoto.controller;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import project.addmoto.data.Expense;
import project.addmoto.data.MonthAverage;
import project.addmoto.data.SellerAccount;
import project.addmoto.model.ExpenseModel;
import project.addmoto.mvc.Controller;
import project.addmoto.utilities.Formatter;
import project.addmoto.view.App;

/**
 *
 * @author Kim Howel delos Reyes
 */
public final class ExpenseController extends Controller {
    
    private final App app;
    private final Connection connection;
    private final ExpenseModel model;
    
    private final JTable eMonthTable;
    private final JTable eTodayTable;
    private final JLabel eMonthTotal;
    private final JLabel eTodayTotal;
    
    private final JPanel eExpensePanel;
    private final JLabel eAverageMonth;
    private final JLabel eHighestCost;
    private final JLabel eHighestMonth;
    private final JLabel eLowestCost;
    private final JLabel eLowestMonth;
    private final JButton eAdd;
    
    private final DefaultTableModel eMonthModel;
    private final DefaultTableModel eTodayModel;
    
    private final SellerAccount sellerAccount;
    
    private final String[] months = {
        "January", "February", "March",
        "April", "May", "June",
        "July", "August", "September",
        "October", "November", "December"
    };
    
    public ExpenseController(final App app, final Connection connection, final SellerAccount sellerAccount) {
        this.app = app;
        this.connection = connection;
        this.sellerAccount = sellerAccount;
        
        model = new ExpenseModel(connection);
        
        eMonthTable = app.geteMonthlyTable();
        eMonthModel = (DefaultTableModel) eMonthTable.getModel();
        eTodayTable = app.geteTodayTable();
        eTodayModel = (DefaultTableModel) eTodayTable.getModel();
        eMonthTotal = app.geteMonthlyTotal();
        eTodayTotal = app.geteTodayTotal();
        eExpensePanel = app.geteExpensePanel();
        eAverageMonth = app.geteAverageMonthly();
        eHighestCost = app.geteHighestLabel();
        eHighestMonth = app.geteHighestMonthly();
        eLowestCost = app.geteLowestLabel();
        eLowestMonth = app.geteLowestMonthly();
        eAdd = app.geteAdd();
        
        populateFields();
        
        setListeners();
    }

    @Override
    public void setListeners() {
        eAdd.addActionListener((ActionEvent e) -> {
            new AddExpenseController(connection, sellerAccount);
            populateFields();
        });
    }
    
    private void populateFields() {
        _getTodayTotal();
        _getTodayExpenses();
        _getThisMonthTotal();
        _getThisMonthExpenses();
        _getMonthlyAverage();
    }
    
    private void _getTodayTotal() {
        double todayTotal = model.getTotalExpenseToday();
        eTodayTotal.setText("PhP " + Formatter.format(todayTotal));
    }
    
    private void _getTodayExpenses() {
        while(eTodayModel.getRowCount() > 0) {
            eTodayModel.removeRow(0);
        }
        ArrayList<Expense> expenses = model.getExpensesToday();
        for(Expense expense : expenses) {
            eTodayModel.addRow(
                    new Object[] {
                        expense.getExpenseReason(),
                        expense.getExpenseDetail(),
                        "PhP " + Formatter.format(expense.getExpenseAmount()),
                        expense.getExpenseID()
                    }
            );
        }
    }
    
    private void _getThisMonthTotal() {
        double thisMonthTotal = model.getTotalExpenseThisMonth();
        eMonthTotal.setText("PhP " + Formatter.format(thisMonthTotal));
    }
    
    private void _getThisMonthExpenses() {
        while(eMonthModel.getRowCount() > 0) {
            eMonthModel.removeRow(0);
        }
        ArrayList<Expense> expenses = model.getExpensesThisMonth();
        for(Expense expense : expenses) {
            String dateTime = "";
            try {
                dateTime = Formatter.formatDate2(Formatter.dateTimeFormat.parse(expense.getExpenseTimestamp()));
            } catch (Exception e) {
            }
            eMonthModel.addRow(
                    new Object[] {
                        dateTime,
                        expense.getExpenseReason(),
                        "PhP " + Formatter.format(expense.getExpenseAmount()),
                        expense.getExpenseID()
                    }
            );
        }
    }
    
    private void _getMonthlyAverage() {
        MonthAverage[] mA = model.getMonthlyAverage();
        eHighestMonth.setText("PhP " + Formatter.format(mA[0].expense));
        eHighestCost.setText(months[mA[0].month - 1] + " " + mA[0].year);
        eLowestMonth.setText("PhP " + Formatter.format(mA[1].expense));
        eLowestCost.setText(months[mA[1].month - 1] + " " + mA[1].year);
        eAverageMonth.setText("PhP " + Formatter.format(mA[2].expense));
    }
}