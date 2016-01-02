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
        
        iUnitCost.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                iUnitCost.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {}
        });
        
        iQtyThreshold.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                iQtyThreshold.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {}
        });
        
        iEditUpdateRSP.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(iEditUpdateRSP.getText().equals("Edit")) {
                    setToDefault(iSellingPrice, iEditUpdateRSP, iCancelRSP, false);
                } else if(iEditUpdateRSP.getText().equals("Update")) {
                    setToDefault(iSellingPrice, iEditUpdateRSP, iCancelRSP, true);
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
        
        iEditUpdateUC.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(iEditUpdateUC.getText().equals("Edit")) {
                    setToDefault(iUnitCost, iEditUpdateUC, iCancelUC, false);
                } else if(iEditUpdateUC.getText().equals("Update")) {
                    setToDefault(iUnitCost, iEditUpdateUC, iCancelUC, true);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
                setRedForeground(iEditUpdateUC);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBlackForeground(iEditUpdateUC);
            }
        });
        
        iEditUpdateQT.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(iEditUpdateQT.getText().equals("Edit")) {
                    setToDefault(iQtyThreshold, iEditUpdateQT, iCancelQT, false);
                } else if(iEditUpdateQT.getText().equals("Update")) {
                    setToDefault(iQtyThreshold, iEditUpdateQT, iCancelQT, true);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
                setRedForeground(iEditUpdateQT);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBlackForeground(iEditUpdateQT);
            }
        });
        
        iCancelRSP.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setToDefault(iSellingPrice, iEditUpdateRSP, iCancelRSP, true);
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
        
        iCancelUC.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setToDefault(iUnitCost, iEditUpdateUC, iCancelUC, true);
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
                setRedForeground(iCancelUC);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBlackForeground(iCancelUC);
            }
        });
        
        iCancelQT.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setToDefault(iQtyThreshold, iEditUpdateQT, iCancelQT, true);
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
                setRedForeground(iCancelQT);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBlackForeground(iCancelQT);
            }
        });
    }
    
    private void setRedForeground(JLabel label) {
        label.setForeground(Color.RED);
    }
    
    private void setBlackForeground(JLabel label) {
        label.setForeground(Color.BLACK);
    }
    
    private void setToDefault(JTextField lFunction, JLabel edit, JLabel cancel, boolean isToDefault) {
        if (!isToDefault) {
            lFunction.requestFocus();
            lFunction.setOpaque(true);
            lFunction.setEnabled(true);
            edit.setText("Update");
            cancel.setEnabled(true);
        } else {
            lFunction.setOpaque(false);
            lFunction.setEnabled(false);
            edit.setText("Edit");
            cancel.setEnabled(false);
        }
    }
}