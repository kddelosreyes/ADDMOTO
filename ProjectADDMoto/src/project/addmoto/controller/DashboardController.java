package project.addmoto.controller;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import project.addmoto.model.DashboardModel;
import project.addmoto.mvc.Controller;
import project.addmoto.utilities.Formatter;
import project.addmoto.view.App;

/**
 *
 * @author Kim Howel delos Reyes
 */
public final class DashboardController extends Controller {
    
    private final App view;
    private final DashboardModel model;
    
    private final JLabel dTotalOrdersAmount;
    private final JLabel dTotalGoodCost;
    private final JLabel dNumberTransactions;
    private final JLabel dAverageTransactions;
    private final JComboBox dFilterType;
    private final JDateChooser dFromDate;
    private final JDateChooser dToDate;
    private final JButton dGoFilter;
    private final JLabel dTransPct;
    private final JLabel dSalesPct;
    private final JLabel dExpensePct;
    private final JTable dIndicatorTable;
    private final JLabel dBestMonth;
    private final JLabel dHighestMonthlySales;
    private final JLabel dInformation;
    
    private final String FILTER_TODAY = "Today";
    private final String FILTER_THIS_WEEK = "This Week";
    private final String FILTER_LAST_WEEK = "Last Week";
    private final String FILTER_THIS_MONTH = "This Month";
    private final String FILTER_LAST_MONTH = "Last Month";
    private final String FILTER_CUSTOM = "Custom";
    
    public DashboardController(App view, final Connection connection) {
        this.view = view;
        model = new DashboardModel(connection);
        
        dTotalOrdersAmount = view.getdTotalOrdersAmount();
        dTotalGoodCost = view.getdTotalGoodCost();
        dNumberTransactions = view.getdNumberTransactions();
        dAverageTransactions = view.getdAverageTransaction();
        dFilterType = view.getdFilterDateType();
        dFromDate = view.getdFromDate();
        dToDate = view.getdToDate();
        dGoFilter = view.getdGoFilter();
        dTransPct = view.getdTransPct();
        dSalesPct = view.getdSalesPct();
        dExpensePct = view.getdExpensePct();
        dIndicatorTable = view.getdIndicatorTable();
        dBestMonth = view.getdBestMonth();
        dHighestMonthlySales = view.getdHighestMonthlySales();
        dInformation = view.getdInformation();
        
        setDefaultViews();
        setListeners();
    }
    
    public void setDefaultViews() {
        double totalAmountSold = model.getTotalAmountToday();
        double goodCostSold = model.getGoodCostToday();
        int noOfTransactions = model.countTransactionsToday();
        double averageSold = model.getAmountAverageToday();
        
        double lastMonthSales = model.getTotalSalesLastMonth();
        double thisMonthSales = model.getTotalSalesThisMonth();
        int lastMonthTransactions = model.getTotalTransactionsLastMonth();
        int thisMonthTransactions = model.getTotalTransactionsThisMonth();
        double lastMonthExpenses = model.getTotalExpensesLastMonth();
        double thisMonthExpenses = model.getTotalExpensesThisMonth();
        
        DefaultTableModel tableModel = (DefaultTableModel) dIndicatorTable.getModel();
        
        while (tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
        }
        
        tableModel.addRow(new Object[]{"Sales", "This Month vs. Last Month", "Php " + thisMonthSales, "Php " + lastMonthSales});
        tableModel.addRow(new Object[]{"Transactions", "This Month vs. Last Month", thisMonthTransactions, lastMonthTransactions});
        tableModel.addRow(new Object[]{"Expenses", "This Month vs. Last Month", "Php " + thisMonthExpenses, "Php " + lastMonthExpenses});
        
        if(lastMonthSales == 0) {
            dSalesPct.setText(thisMonthSales + "%");
        } else {
            if(thisMonthSales > lastMonthSales) {
                dSalesPct.setForeground(new Color(0, 153, 0));
            } else {
                dSalesPct.setForeground(new Color(153, 0, 0));
            }
            double diff = (Math.abs(thisMonthSales - lastMonthSales) / lastMonthSales) * 100.00;
            dSalesPct.setText(Formatter.format(diff) + "%");
        }
        
        if(lastMonthTransactions == 0) {
            dTransPct.setText(thisMonthTransactions + ".00%");
        } else {
            if(thisMonthTransactions > lastMonthTransactions) {
                dTransPct.setForeground(new Color(0, 153, 0));
            } else {
                dTransPct.setForeground(new Color(153, 0, 0));
            }
            double diff = (Math.abs(thisMonthTransactions - lastMonthTransactions) / lastMonthTransactions) * 100.00;
            dTransPct.setText(Formatter.format(diff) + "%");
        }
        
        if(lastMonthExpenses == 0) {
            dExpensePct.setText(thisMonthExpenses + "%");
        } else {
            if(thisMonthExpenses > lastMonthExpenses) {
                dExpensePct.setForeground(new Color(0, 153, 0));
            } else {
                dExpensePct.setForeground(new Color(153, 0, 0));
            }
            double diff = (Math.abs(thisMonthExpenses - lastMonthExpenses) / lastMonthExpenses) * 100.00;
            dExpensePct.setText(Formatter.format(diff) + "%");
        }
        
        dTotalOrdersAmount.setText("PhP " + Formatter.format(totalAmountSold));
        dTotalGoodCost.setText("PhP " + Formatter.format(goodCostSold));
        dNumberTransactions.setText(String.valueOf(noOfTransactions));
        dAverageTransactions.setText("PhP " + Formatter.format(averageSold));
    }
    
    @Override
    public void setListeners() {
        dFilterType.addItemListener((ItemEvent e) -> {
            if(e.getStateChange() == ItemEvent.SELECTED) {
                String selected = e.getItem().toString();
                switch(selected) {
                    case FILTER_TODAY:
                        setFalse();
                        setDefaultViews();
                        break;
                    case FILTER_THIS_WEEK:
                        setFalse();
                        break;
                    case FILTER_LAST_WEEK:
                        setFalse();
                        break;
                    case FILTER_THIS_MONTH:
                        setFalse();
                        break;
                    case FILTER_LAST_MONTH:
                        setFalse();
                        break;
                    case FILTER_CUSTOM:
                        setTrue();
                        break;
                }
            }
        });
        
        dInformation.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(view, "Information");
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }
   
    private void setTrue() {
        dFromDate.setEnabled(true);
        dToDate.setEnabled(true);
        dGoFilter.setEnabled(true);
    }

    private void setFalse() {
        dFromDate.setEnabled(false);
        dToDate.setEnabled(false);
        dGoFilter.setEnabled(false);
    }
}