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
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import org.jdesktop.swingx.JXSearchField;
import project.addmoto.model.PurchaseOrderModel;
import project.addmoto.mvc.Controller;
import project.addmoto.view.App;

/**
 *
 * @author Kim Howel delos Reyes
 */
public final class PurchaseOrderController extends Controller {
    
    private final App view;
    private PurchaseOrderModel model;
    
    private final JButton poCreate;
    private final JButton poReceive;
    private final JButton poView;
    private final JXSearchField poSearch;
    private final JTable poTable;
    private final JPopupMenu poPopup;
    private final JMenuItem poTogglePaid;
    private final JMenuItem poViewDetails;
    private final JMenuItem poReceiveProducts;
    
    private int selectedRow;
    
    
    public PurchaseOrderController(final App view, final Connection connection) {
        this.view = view;
        model = new PurchaseOrderModel(connection);
        
        poCreate = view.getPoCreate();
        poReceive = view.getPoReceive();
        poView = view.getPoView();
        poSearch = view.getPoSearch();
        poTable = view.getPoTable();
        poPopup = view.getPoPopup();
        poTogglePaid = view.getPoTogglePaid();
        poViewDetails = view.getPoViewDetails();
        poReceiveProducts = view.getPoReceiveProducts();
        
        setListeners();
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
                JOptionPane.showMessageDialog(poTogglePaid, "View Details on Row: " + selectedRow);
            }
        });
        
        poTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(SwingUtilities.isRightMouseButton(e)) {
                    int row = poTable.rowAtPoint(e.getPoint());
                    selectedRow = row;
                    
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