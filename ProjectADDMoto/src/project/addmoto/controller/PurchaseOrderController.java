/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.addmoto.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.JXSearchField;
import project.addmoto.data.InventoryChange;
import project.addmoto.data.OrderLine;
import project.addmoto.data.OrderLineData;
import project.addmoto.data.Products;
import project.addmoto.data.SellerAccount;
import project.addmoto.model.PurchaseOrderModel;
import project.addmoto.mvc.Controller;
import project.addmoto.utilities.Formatter;
import project.addmoto.view.App;

/**
 *
 * @author Kim Howel delos Reyes
 */
public final class PurchaseOrderController extends Controller {
    
    private final App view;
    private final PurchaseOrderModel model;
    private final Connection connection;
    private final SellerAccount sellerAccount;
    
    private final JButton poCreate;
    private final JXSearchField poSearch;
    private final JTable poTable;
    private final JPopupMenu poPopup;
    private final JMenuItem poTogglePaid;
    private final JMenuItem poViewDetails;
    private final JMenuItem poReceiveProducts;
    private final DefaultTableModel tableModel;
    
    private int selectedRow;
    private int selectedPON;
    
    private ArrayList<OrderLineData> orderLineList;
    private OrderLineData selectedOrder;
    
    public PurchaseOrderController(final App view, final Connection connection, final SellerAccount sellerAccount) {
        this.sellerAccount = sellerAccount;
        this.view = view;
        this.connection = connection;
        model = new PurchaseOrderModel(connection);
        
        poCreate = view.getPoCreate();
        poSearch = view.getPoSearch();
        poTable = view.getPoTable();
        poPopup = view.getPoPopup();
        poTogglePaid = view.getPoTogglePaid();
        poViewDetails = view.getPoViewDetails();
        poReceiveProducts = view.getPoReceiveProducts();
        tableModel = (DefaultTableModel) poTable.getModel();
        
        populate();
        
        setListeners();
    }
    
    private void populate() {
        System.out.println("LOG: POPULATE IS RUNNING");
        orderLineList = model.getOrderLineList();
        
        while(tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
        }
        for(OrderLineData oLData : orderLineList) {
            tableModel.addRow(
                    new Object[] {
                        oLData.getOrderID(),
                        oLData.getOrderNo(),
                        oLData.getTimestamp(),
                        oLData.getSupplier(),
                        Double.parseDouble(Formatter.format(oLData.getOrderTotalPrice())),
                        oLData.getTotalQuantity(),
                        oLData.isIsPaid() ? "YES" : "NO",
                        oLData.getStatus(),
                        oLData.getTargetDate(),
                        oLData.getSellerID()
                    }
            );
        }
    }

    @Override
    public void setListeners() {
        poCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NewPurchaseOrderController(
                        connection,
                        sellerAccount
                );
                populate();
            }
        });
        
        poTogglePaid.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(((String)tableModel.getValueAt(selectedRow, 6)).equalsIgnoreCase("YES")) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Purchase Order is already paid. Cannot toggle payment.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                } else {
                    int option = JOptionPane.showConfirmDialog(
                            null,
                            "Are you sure you want to toggle payment as paid?",
                            "Confirm",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE
                    );
                    if(option == JOptionPane.YES_OPTION) {
                        int row = model.updatePaid(selectedPON, true);
                        if(row == 0) {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "There is a problem while toggling purchase order payment.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        } else {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Purchase Order now set as PAID",
                                    "Success",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
                            populate();
                        }
                    }
                }
            }
        });

        poReceiveProducts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(((String)tableModel.getValueAt(selectedRow, 7)).equalsIgnoreCase("RECEIVED")) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Purchase Order is already received. Cannot toggle receiving.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                } else {
                    if(((String)tableModel.getValueAt(selectedRow, 6)).equalsIgnoreCase("NO")) {
                            JOptionPane.showMessageDialog(
                                null,
                                "Pay first Purchase Order before receiving.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    } else {
                        int option = JOptionPane.showConfirmDialog(
                                null,
                                "Are you sure you want to toggle status as received?",
                                "Confirm",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE
                        );
                        if(option == JOptionPane.YES_OPTION) {
                            int row = model.updateReceived(selectedPON, "RECEIVED");
                            if(row == 0) {
                                JOptionPane.showMessageDialog(
                                        null,
                                        "There is a problem while toggling purchase order receive.",
                                        "Error",
                                        JOptionPane.ERROR_MESSAGE
                                );
                            } else {
                                JOptionPane.showMessageDialog(
                                        null,
                                        "Purchase Order now set as RECEIVED",
                                        "Success",
                                        JOptionPane.INFORMATION_MESSAGE
                                );
                                
                                ArrayList<OrderLine> oLine = model.getOrderProducts(selectedPON);
                                ArrayList<InventoryChange> iChange = new ArrayList<>();

                                String currDate = Formatter.formatDate(new Date());

                                for(OrderLine o : oLine) {
                                    Products p = model.getProduct(o.getProductID());
                                    int currQty = p.getCurrentQuantity();
                                    int change = o.getOrderQuantity();
                                    iChange.add(
                                            new InventoryChange(
                                                    currDate,
                                                    currQty,
                                                    currQty + change,
                                                    change,
                                                    o.getProductID()
                                            )
                                    );
                                }
                                System.out.println(iChange);
                                int rowCount = model.insertChangeItems(iChange);
                                if(rowCount == oLine.size()) {
                                    JOptionPane.showMessageDialog(
                                            null,
                                            "Inventory logs updated due to product item quantity changes.",
                                            "Success",
                                            JOptionPane.INFORMATION_MESSAGE
                                    );
                                } else {
                                    JOptionPane.showMessageDialog(
                                            null,
                                            "There is some kind of error.",
                                            "Error",
                                            JOptionPane.INFORMATION_MESSAGE
                                    );
                                }
                                populate();
                            }
                        }
                    }
                }
            }
        });

        poViewDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewPurchaseOrderController(connection, selectedOrder);
            }
        });
        
        poTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(SwingUtilities.isRightMouseButton(e)) {
                    int row = poTable.rowAtPoint(e.getPoint());
                    selectedRow = row;
                    selectedPON = (int) tableModel.getValueAt(row, 0);
                    
                    selectedOrder = new OrderLineData(
                            (int) tableModel.getValueAt(row, 0),
                            (String) tableModel.getValueAt(row, 1),
                            (String) tableModel.getValueAt(row, 2),
                            (String) tableModel.getValueAt(row, 3),
                            (double) tableModel.getValueAt(row, 4),
                            (int) tableModel.getValueAt(row, 5),
                            ((((String) tableModel.getValueAt(row, 6)).equals("YES"))),
                            (String) tableModel.getValueAt(row, 7),
                            (String) tableModel.getValueAt(row, 8),
                            (int) tableModel.getValueAt(row, 9));
                    
                    poTable.clearSelection();
                    poTable.addRowSelectionInterval(row, row);
                    poPopup.show(poTable, (int)e.getPoint().getX(), (int)e.getPoint().getY());
                }
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
}