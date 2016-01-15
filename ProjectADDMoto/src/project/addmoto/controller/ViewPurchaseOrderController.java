/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.addmoto.controller;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import project.addmoto.data.OrderLine;
import project.addmoto.data.OrderLineData;
import project.addmoto.model.ViewPurchaseOrderModel;
import project.addmoto.mvc.Controller;
import project.addmoto.utilities.Formatter;
import project.addmoto.view.ViewPurchaseOrder;

/**
 *
 * @author Kim Howel delos Reyes
 */
public final class ViewPurchaseOrderController extends Controller {
    
    private final ViewPurchaseOrderModel model;
    private final ViewPurchaseOrder view;
    private final OrderLineData data;
    
    private final JLabel vpLabel;
    private final JLabel vpExport;
    private final JLabel vpEdit;
    private final JLabel vpDelete;
    private final JLabel vpPending;
    private final JLabel vpReceived;
    private final JLabel vpDateIssued;
    private final JLabel vpDeliveryDate;
    private final JLabel vpSupplier;
    private final JTable vpOrderTable;
    private final JLabel vpItems;
    private final JLabel vpTotal;
    
    private ArrayList<OrderLine> orderProducts;
    private DefaultTableModel tableModel;
    
    public ViewPurchaseOrderController(final Connection connection, final OrderLineData data) {
        this.data = data;
        System.out.println(data);
        model = new ViewPurchaseOrderModel(connection);
        orderProducts = model.getOrderProducts(data.getOrderID());
        view = new ViewPurchaseOrder();
        
        vpLabel = view.getVpLabel();
        vpExport = view.getVpExport();
        vpEdit = view.getVpEdit();
        vpDelete = view.getVpDelete();
        vpPending = view.getVpPending();
        vpReceived = view.getVpReceived();
        vpDateIssued = view.getVpDateIssued();
        vpDeliveryDate = view.getVpDeliveryDate();
        vpSupplier = view.getVpSupplier();
        vpOrderTable = view.getVpOrderTable();
        tableModel = (DefaultTableModel) vpOrderTable.getModel();
        vpItems = view.getVpItems();
        vpTotal = view.getVpTotal();
        
        populateFields();
        setListeners();
        
        Object[] buttons = {"Close"};

        JOptionPane.showOptionDialog(
                view,
                view,
                "ADD Moto - Motorcycle Parts and Accessories",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                buttons,
                buttons[0]
        );
    }
    
    private void populateFields() {
        vpLabel.setText(data.getOrderNo());
        vpDateIssued.setText(data.getTimestamp());
        vpSupplier.setText(data.getSupplier());
        vpItems.setText(data.getTotalQuantity() + " items");
        vpTotal.setText("PhP: " + Formatter.format(data.getOrderTotalPrice()));
        
        if(!data.getStatus().equalsIgnoreCase("Pending")) {
            vpReceived.setForeground(new java.awt.Color(255, 255, 255));
            vpReceived.setBackground(new java.awt.Color(255, 204, 51));
            vpReceived.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
            vpPending.setForeground(new Color(0, 102, 255));
            vpPending.setBackground(new java.awt.Color(240, 240, 240));
            vpPending.setBorder(null);
        }
        
        for(OrderLine orderProduct : orderProducts) {
            tableModel.addRow(
                    new Object[] {
                        orderProduct.getProductName().replaceAll("\\s+", " ").trim(),
                        orderProduct.getUnitPrice(),
                        orderProduct.getOrderQuantity(),
                        orderProduct.getTotalPrice()
                    }
            );
        }
    }

    @Override
    public void setListeners() {
        vpExport.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
                setForeground(vpExport, false);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setForeground(vpExport, true);
            }
        });
        
        vpEdit.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
                setForeground(vpEdit, false);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setForeground(vpEdit, true);
            }
        });
        
        vpDelete.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
                setForeground(vpDelete, false);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setForeground(vpDelete, true);
            }
        });
    }
    
    private void setForeground(JLabel label, boolean isCool) {
        if(isCool) {
            label.setForeground(new Color(0, 102, 255));
        } else {
            label.setForeground(Color.RED);
        }
    }
}