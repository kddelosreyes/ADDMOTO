package project.addmoto.controller;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.jdesktop.swingx.JXSearchField;
import org.jdesktop.swingx.JXTable;
import project.addmoto.model.InventoryModel;
import project.addmoto.mvc.Controller;
import project.addmoto.view.App;

/**
 *
 * @author Kim Howel delos Reyes
 */
public final class InventoryController extends Controller {
    
    private final App view;
    private final InventoryModel model;
    
    private final JComboBox iProductLineFilter;
    private final JComboBox iStatusFilter;
    private final JComboBox iSupplierFilter;
    private final JXSearchField iSearchField;
    private final JXTable iProductsTable;
    private final JTextArea iProductName;
    private final JLabel iItemNumber;
    private final JLabel iAddMotoCode;
    private final JLabel iProductLine;
    private final JLabel iItemDescription;
    private final JLabel iOnHandQuantity;
    private final JLabel iStatus;
    private final JLabel iSupplier;
    private final JLabel iSupplierCode;
    private final JLabel iShowHistory;
    private final JLabel iEditUpdateRSP;
    private final JLabel iEditUpdateUC;
    private final JLabel iEditUpdateQT;
    private final JLabel iCancelRSP;
    private final JLabel iCancelUC;
    private final JLabel iCancelQT;
    private final JTextField iSellingPrice;
    private final JTextField iUnitCost;
    private final JTextField iQtyThreshold;
    
    public InventoryController(App view, final Connection connection) {
        this.view = view;
        model = new InventoryModel(connection);
        
        iProductLineFilter = view.getiProductLineFilter();
        iStatusFilter = view.getiStatusFilter();
        iSupplierFilter = view.getiSupplierFilter();
        iSearchField = view.getiSearchField();
        iProductsTable = view.getiProductsTable();
        iProductName = view.getiProductName();
        iItemNumber = view.getiItemNo();
        iAddMotoCode = view.getiAddMotoCode();
        iProductLine = view.getiProductLine();
        iItemDescription = view.getiItemDescription();
        iOnHandQuantity = view.getiOnHandQuantity();
        iStatus = view.getiStatus();
        iSupplier = view.getiSupplier();
        iSupplierCode = view.getiSupplierCode();
        iShowHistory = view.getiShowHistory();
        iEditUpdateRSP = view.getiEditUpdateRSP();
        iEditUpdateUC = view.getiEditUpdateUC();
        iEditUpdateQT = view.getiEditUpdateQT();
        iCancelRSP = view.getiCancelRSP();
        iCancelUC = view.getiCancelUC();
        iCancelQT = view.getiCancelQT();
        iSellingPrice = view.getiSellingPrice();
        iSellingPrice.setDisabledTextColor(Color.BLACK);
        iUnitCost = view.getiUnitCost();
        iUnitCost.setDisabledTextColor(Color.BLACK);
        iQtyThreshold = view.getiThreshold();
        iQtyThreshold.setDisabledTextColor(Color.BLACK);
        
        setListeners();
    }

    @Override
    public void setListeners() {
        iSellingPrice.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                iSellingPrice.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {}
        });
        
        iEditUpdateRSP.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(iEditUpdateRSP.getText().equals("Edit")) {
                    iSellingPrice.requestFocus();
                    iSellingPrice.setOpaque(true);
                    iSellingPrice.setEnabled(true);
                    iEditUpdateRSP.setText("Update");
                    iCancelRSP.setEnabled(true);
                } else if(iEditUpdateRSP.getText().equals("Update")) {
                    iSellingPrice.setOpaque(false);
                    iSellingPrice.setEnabled(false);
                    iEditUpdateRSP.setText("Edit");
                    iCancelRSP.setEnabled(false);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
                setRedForeground(iEditUpdateRSP);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBlackForeground(iEditUpdateRSP);
            }
        });
        
        iCancelRSP.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                iSellingPrice.setOpaque(false);
                iSellingPrice.setEnabled(false);
                iEditUpdateRSP.setText("Edit");
                iCancelRSP.setEnabled(false);
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
                setRedForeground(iCancelRSP);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBlackForeground(iCancelRSP);
            }
        });
    }
    
    private void setRedForeground(JLabel label) {
        label.setForeground(Color.RED);
    }
    
    private void setBlackForeground(JLabel label) {
        label.setForeground(Color.BLACK);
    }
}