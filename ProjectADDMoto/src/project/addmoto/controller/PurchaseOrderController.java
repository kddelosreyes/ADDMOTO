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
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.JXSearchField;
import project.addmoto.custom.OvalBorder;
import project.addmoto.custom.RoundedBorder;
import project.addmoto.data.OrderLineData;
import project.addmoto.model.PurchaseOrderModel;
import project.addmoto.mvc.Controller;
import project.addmoto.view.App;

/**
 *
 * @author Kim Howel delos Reyes
 */
public final class PurchaseOrderController extends Controller {
    
    private final App view;
    private final PurchaseOrderModel model;
    private final Connection connection;
    
    private final JButton poCreate;
    private final JXSearchField poSearch;
    private final JTable poTable;
    private final JPopupMenu poPopup;
    private final JMenuItem poTogglePaid;
    private final JMenuItem poViewDetails;
    private final JMenuItem poReceiveProducts;
    private final DefaultTableModel tableModel;
    
    private int selectedRow;
    
    private ArrayList<OrderLineData> orderLineList;
    private OrderLineData selectedOrder;
    
    public PurchaseOrderController(final App view, final Connection connection) {
        this.view = view;
        this.connection = connection;
        model = new PurchaseOrderModel(connection);
        
        poCreate = view.getPoCreate();
        poCreate.setBorder(new RoundedBorder(12));
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
                        oLData.getOrderTotalPrice(),
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
        poTogglePaid.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(poTogglePaid, "Toggle Paid on Row: " + selectedRow);
            }
        });

        poReceiveProducts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(poTogglePaid, "Receive Products on Row: " + selectedRow);
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