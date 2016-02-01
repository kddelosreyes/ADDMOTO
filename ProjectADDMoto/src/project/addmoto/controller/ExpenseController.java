/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.addmoto.controller;

import java.awt.BasicStroke;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DefaultDrawingSupplier;
import org.jfree.chart.plot.DrawingSupplier;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
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
    
    private final String[] months2 = {
        "Jan", "Feb", "Mar",
        "Apr", "May", "Jun",
        "Jul", "Aug", "Sep",
        "Oct", "Nov", "Dec"
    };
    
    public ExpenseController(final App app, final Connection connection, final SellerAccount sellerAccount) {
        this.app = app;
        app.pack();
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
        _getDataSet();
    }
    
    private void _getDataSet() {
        System.out.println("Inside get data set");
        final CategoryDataset dataset = createDataset();
        final JFreeChart chart = createChart(dataset);
        
        BufferedImage image = chart.createBufferedImage(452, 218);
        JLabel label = new JLabel(new ImageIcon(image));
        
        eExpensePanel.add(label);
        eExpensePanel.validate();
    }
    
    private JFreeChart createChart(final CategoryDataset dataset) {
        System.out.println("Inside create Chart");
        JFreeChart lineChart = ChartFactory.createLineChart(
                null,
                "Years",
                "Expense",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);
        
        final Shape[] shapes = new Shape[2];
        int[] xpoints;
        int[] ypoints;
        xpoints = new int[] {-3, 3, -3};
        ypoints = new int[] {-3, 0, 3};
        shapes[0] = new Polygon(xpoints, ypoints, 3);
        
        shapes[1] = new Rectangle2D.Double(-2, -3, 3, 6);
        
        final DrawingSupplier supplier = new DefaultDrawingSupplier(
            DefaultDrawingSupplier.DEFAULT_PAINT_SEQUENCE,
            DefaultDrawingSupplier.DEFAULT_OUTLINE_PAINT_SEQUENCE,
            DefaultDrawingSupplier.DEFAULT_STROKE_SEQUENCE,
            DefaultDrawingSupplier.DEFAULT_OUTLINE_STROKE_SEQUENCE,
            shapes
        );
        final CategoryPlot plot = lineChart.getCategoryPlot();
        plot.setDrawingSupplier(supplier);
        
        plot.getRenderer().setSeriesStroke(
            0, 
            new BasicStroke(
                2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 
                1.0f, new float[] {10.0f, 6.0f}, 0.0f
            )
        );
        plot.getRenderer().setSeriesStroke(
            1, 
            new BasicStroke(
                2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
                1.0f, new float[] {6.0f, 6.0f}, 0.0f
            )
        );
        
        final LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setItemLabelsVisible(true);
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setAutoRangeIncludesZero(false);
        rangeAxis.setUpperMargin(0.12);
        
        return lineChart;
    }
    
    private DefaultCategoryDataset createDataset() {
        System.out.println("Inside create data set");
        ArrayList<MonthAverage> mAverage = model.getAverage();
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
        
        double average = 0.0;
        for(MonthAverage mA : mAverage) {
            average += mA.expense;
        }
        average = average / (1.0 * mAverage.size());
        System.out.println("Average : " + average);
        
        for(MonthAverage mA : mAverage) {
            dataset.addValue(mA.expense, "Expense", months2[mA.month - 1] + mA.year);
        }
        
        for(MonthAverage mA : mAverage) {
            dataset.addValue(average, "Average", months2[mA.month - 1] + mA.year);
        }
        
        return dataset;
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