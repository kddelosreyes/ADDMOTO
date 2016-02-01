/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.addmoto.controller;

import com.toedter.calendar.JDateChooser;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.JXSearchField;
import project.addmoto.data.InventoryChange;
import project.addmoto.data.Receipt;
import project.addmoto.data.Return;
import project.addmoto.data.ReturnsData;
import project.addmoto.data.SellerAccount;
import project.addmoto.data.SoldItems;
import project.addmoto.model.ReturnsModel;
import project.addmoto.mvc.Controller;
import project.addmoto.utilities.Formatter;
import project.addmoto.view.App;

/**
 *
 * @author Kim Howel delos Reyes
 */
public final class ReturnsController extends Controller {
    
    private final App app;
    private final Connection connection;
    private final ReturnsModel model;
    private final SellerAccount account;
    
    private final JDateChooser rFrom;
    private final JDateChooser rTo;
    private final JTable rReceiptsTable;
    private final JTable rReturnsTable;
    private final DefaultTableModel receiptsModel;
    private final DefaultTableModel returnsModel;
    private final ListSelectionModel receiptsSelection;
    private final JComboBox rDropdown;
    private final JXSearchField rSearch;
    private final JButton rReturn;
    private final JLabel rPrice;
    private final JLabel rNoItems;
    
    private ArrayList<Receipt> receiptList;
    private ArrayList<ReturnsData> rData;
    private ArrayList<SoldItems> list;
    private int orderID;
    
    private boolean isAdd = false;
    
    public ReturnsController(final App app, final Connection connection, final SellerAccount account) {
        this.app = app;
        this.connection = connection;
        this.account = account;
        model = new ReturnsModel(connection);
        
        rFrom = app.getrFrom();
        rTo = app.getrTo();
        rReceiptsTable = app.getrReceiptsTable();
        receiptsModel = (DefaultTableModel) rReceiptsTable.getModel();
        rReturnsTable = app.getrReturnsTable();
        rReceiptsTable.getTableHeader().setPreferredSize(new Dimension(rReceiptsTable.getWidth(), 38));
        receiptsSelection = rReceiptsTable.getSelectionModel();
        rDropdown = app.getrDropdown();
        returnsModel = (DefaultTableModel) rReturnsTable.getModel();
        rReturnsTable.getTableHeader().setPreferredSize(new Dimension(rReturnsTable.getWidth(), 38));
        rSearch = app.getrSearch();
        rReturn = app.getrReturn();
        rPrice = app.getrPrice();
        rNoItems = app.getrNoItems();
        
        populate();
        
        setListeners();
    }
    
    public void populate() {
        _getReceipts();
        _getReturns();
        rReturn.setEnabled(false);
        rDropdown.setEnabled(false);
    }
    
    private void _getReturns() {
        rData = model.getReturns();
        isAdd = true;
        while(rReturnsTable.getRowCount() > 0) {
            returnsModel.removeRow(0);
        }
        isAdd = false;
        
        double sum = 0;
        int qty = 0;
        for(ReturnsData rD : rData) {
            returnsModel.addRow(
                    new Object[] {
                        Formatter.formatOrderDate(new Date(rD.getReturnDate())),
                        rD.getAddmotoCode(),
                        rD.getItemName(),
                        rD.getQuantity(),
                        Formatter.format(rD.getSellingPrice()),
                        Formatter.format(rD.getTotalPrice())
                    }
            );
            sum += rD.getTotalPrice();
            qty += rD.getQuantity();
        }
        
        rPrice.setText("-PhP " + Formatter.format(sum));
        rNoItems.setText("Total of " + qty + " items");
    }
    
    private void _getReceipts() {
        receiptList = model.getReceipts();
        isAdd = true;
        while(rReceiptsTable.getRowCount() > 0) {
            receiptsModel.removeRow(0);
        }
        isAdd = false;
        
        for(Receipt r : receiptList) {
            receiptsModel.addRow(
                    new Object[] {
                        Formatter.formatOrderDate(new Date(r.getTransactionTimeStamp())),
                        r.getReceiptNo(),
                        Formatter.format(r.getTotalPrice()),
                        r.getID()
                    }
            );
        }
    }
    
    @Override
    public void setListeners() {
        receiptsSelection.addListSelectionListener((ListSelectionEvent e) -> {
            if (e.getValueIsAdjusting()) {
                return;
            }
            while(rDropdown.getItemCount() > 0) {
                rDropdown.removeItemAt(0);
            }
            if(!isAdd) {
                orderID = (int) rReceiptsTable.getValueAt(rReceiptsTable.getSelectedRow(), 3);
                rReturn.setEnabled(true);

                list = model.getSoldItems(orderID);
                for(SoldItems s : list) {
                    rDropdown.addItem(s.getItemName().replaceAll("\\s+", " ").trim());
                }
                rDropdown.setEnabled(true);
            }
        });
        
        rReturn.addActionListener((ActionEvent e) -> {
            int rQty = list.get(rDropdown.getSelectedIndex()).getItemQuantity();
            int qty = 0;
            
            try {
                qty = Integer.parseInt(JOptionPane.showInputDialog(
                        null,
                        "There are currently " + rQty
                            + " on the receipt.\nHow many products are returned?",
                        "Return quantity",
                        JOptionPane.QUESTION_MESSAGE    
                ));
                if(qty <= 0) {
                    JOptionPane.showMessageDialog(
                                null,
                                "Quantity cannot be less than or equal to 0.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                } else {
                    if(qty > rQty) {
                        JOptionPane.showMessageDialog(
                                null,
                                "There are more than to return than there is on the receipt. Cannot return item.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    } else {
                        int productID = list.get(rDropdown.getSelectedIndex()).getProductID();
                        double[] prices = model.getAmountPrice(productID);
                        System.out.println(Arrays.toString(prices));
                        SoldItems s = new SoldItems(
                                list.get(rDropdown.getSelectedIndex()).getSoldID(),
                                rQty - qty,
                                prices[1] * (rQty - qty),
                                (prices[1] - prices[0]) * (rQty - qty),
                                productID,
                                list.get(rDropdown.getSelectedIndex()).getReceiptID()
                        );
                        int rows = model.updateSoldItem(s);
                        double totalPrice = model.getTotalPrice(list.get(rDropdown.getSelectedIndex()).getReceiptID());
                        int rows_ = model.updateReceipt(totalPrice, list.get(rDropdown.getSelectedIndex()).getReceiptID());
                        
                        int currentQty = model.getCurrentQty(productID);
                        int row2 = model.updateProductQty(currentQty + qty, productID);
                        Date date = new Date();
                        InventoryChange ic = new InventoryChange(
                                Formatter.formatDate(date),
                                currentQty,
                                currentQty + qty,
                                qty,
                                productID
                        );
                        int row3 = model.insertChange(ic);
                        
                        if(rows != 0) {
                            System.out.println("Updating sold item successful!");
                        } else {
                            System.out.println("Updating sold item unsuccessful!");
                        }
                        
                        if(rows_ != 0) {
                            System.out.println("Updating receipt successful!");
                        } else {
                            System.out.println("Updating receipt unsuccessful!");
                        }
                        
                        if(row2 != 0) {
                            System.out.println("Updating product quantity successful!");
                        } else {
                            System.out.println("Updating product quantity unsuccessful!");
                        }
                        
                        if(row3 != 0) {
                            System.out.println("Inserting new change successful");
                        } else {
                            System.out.println("Inserting new change unsuccessful");
                        }
                        
                        Return return_ = new Return(
                                Formatter.formatDate(date),
                                productID,
                                qty,
                                list.get(rDropdown.getSelectedIndex()).getReceiptID(),
                                account.getSellerID()
                        );
                        
                        int rowR = model.insertReturn(return_);
                        if(rowR != 0) {
                            System.out.println("Inserting new return successful");
                        } else {
                            System.out.println("Inserting new return unsuccessful");
                        }
                        
                        if(rows != 0 && rows_ != 0) {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Item returned successfully. Inventory is updated and return is recorded.",
                                    "Success",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
                        } else {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "There is something wrong while recording return of products. Please see log details.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                    }
                }
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(
                        null,
                        "You have an error on your number format.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
            populate();
            resetD();
        });
    }
    
    public void resetD() {
        while (rDropdown.getItemCount() > 0) {
            rDropdown.removeItemAt(0);
        }
    }
}
