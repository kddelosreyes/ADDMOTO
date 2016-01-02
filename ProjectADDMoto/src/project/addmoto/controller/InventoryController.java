package project.addmoto.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import org.jdesktop.swingx.JXSearchField;
import org.jdesktop.swingx.JXTable;
import project.addmoto.data.ProductLine;
import project.addmoto.data.Products;
import project.addmoto.data.Supplier;
import project.addmoto.model.InventoryModel;
import project.addmoto.mvc.Controller;
import project.addmoto.utilities.Formatter;
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
    private final JLabel iShowHistory;
    private final JLabel iEditUpdateRSP;
    private final JLabel iEditUpdateUC;
    private final JLabel iEditUpdateQT;
    private final JLabel iCancelRSP;
    private final JLabel iCancelUC;
    private final JLabel iCancelQT;
    private final JLabel iInfo;
    private final JTextField iSellingPrice;
    private final JTextField iUnitCost;
    private final JTextField iQtyThreshold;
    private final JButton iAddNew;
    
    private String sellingPriceValue;
    private String unitCostValue;
    private String qtyThresholdValue;
    
    private DefaultTableModel tableModel;
    private ListSelectionModel selectionModel;
    
    private ArrayList<ProductLine> productLines;
    private ArrayList<Supplier> suppliers;
    private ArrayList<Products> products;
    private HashMap<Integer, String> productLineMap;
    
    public boolean isAddingRows = false;
    
    public InventoryController(App view, final Connection connection) {
        this.view = view;
        model = new InventoryModel(connection);
        
        iProductLineFilter = view.getiProductLineFilter();
        iStatusFilter = view.getiStatusFilter();
        iSupplierFilter = view.getiSupplierFilter();
        iSearchField = view.getiSearchField();
        iProductsTable = view.getiProductsTable();
        tableModel = (DefaultTableModel) iProductsTable.getModel();
        selectionModel = (ListSelectionModel) iProductsTable.getSelectionModel();
        iProductName = view.getiProductName();
        iItemNumber = view.getiItemNo();
        iAddMotoCode = view.getiAddMotoCode();
        iProductLine = view.getiProductLine();
        iItemDescription = view.getiItemDescription();
        iOnHandQuantity = view.getiOnHandQuantity();
        iStatus = view.getiStatus();
        iSupplier = view.getiSupplier();
        iShowHistory = view.getiShowHistory();
        iEditUpdateRSP = view.getiEditUpdateRSP();
        iEditUpdateUC = view.getiEditUpdateUC();
        iEditUpdateQT = view.getiEditUpdateQT();
        iCancelRSP = view.getiCancelRSP();
        iCancelUC = view.getiCancelUC();
        iCancelQT = view.getiCancelQT();
        iInfo = view.getiInfo();
        iSellingPrice = view.getiSellingPrice();
        iSellingPrice.setDisabledTextColor(Color.BLACK);
        iUnitCost = view.getiUnitCost();
        iUnitCost.setDisabledTextColor(Color.BLACK);
        iQtyThreshold = view.getiThreshold();
        iQtyThreshold.setDisabledTextColor(Color.BLACK);
        iAddNew = view.getiAddNew();
        
        setDefaultViews();
        populate();
        setListeners();
    }

    @Override
    public void setListeners() {
        iShowHistory.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(view, "Clicked");
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
                setRedForeground(iShowHistory);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setWhiteForeground(iShowHistory);
            }
        });
        
        iSellingPrice.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                iSellingPrice.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {}
        });
        
        iSellingPrice.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();
                if (!((ch >= '0') && (ch <= '9')
                        || (ch == KeyEvent.VK_BACK_SPACE)
                        || (ch == KeyEvent.VK_DELETE)
                        || (ch == '.'))) {
                    tk.beep();
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        iUnitCost.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                iUnitCost.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {}
        });
        
        iUnitCost.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();
                if (!((ch >= '0') && (ch <= '9')
                        || (ch == KeyEvent.VK_BACK_SPACE)
                        || (ch == KeyEvent.VK_DELETE)
                        || (ch == '.'))) {
                    tk.beep();
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        iQtyThreshold.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                iQtyThreshold.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {}
        });
        
        iQtyThreshold.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();
                if (!((ch >= '0') && (ch <= '9')
                        || (ch == KeyEvent.VK_BACK_SPACE)
                        || (ch == KeyEvent.VK_DELETE)
                        || (ch == '.'))) {
                    tk.beep();
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        iEditUpdateRSP.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(iEditUpdateRSP.getText().equals("Edit")) {
                    sellingPriceValue = iSellingPrice.getText();
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
                    unitCostValue = iUnitCost.getText();
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
                    qtyThresholdValue = iQtyThreshold.getText();
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
                iSellingPrice.setText(sellingPriceValue);
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
                iUnitCost.setText(unitCostValue);
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
                iQtyThreshold.setText(qtyThresholdValue);
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
        
        selectionModel.addListSelectionListener((ListSelectionEvent e) -> {
            if(e.getValueIsAdjusting()) {
                return;
            }
            if(!isAddingRows) {
                int row = iProductsTable.getSelectedRow();
                int ID = Integer.parseInt((String) iProductsTable.getValueAt(row, 0));
                System.out.println("ID -> " + ID);
                iProductsTable.scrollRowToVisible(row);
                showProductDetails(ID);
            }
        });
        
        iInfo.addMouseListener(new MouseListener() {
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
        
        iAddNew.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(view, "Add New button clicked.");
        });
    }
    
    private void showProductDetails(int ID) {
        List<Products> productWithID = products.stream()
                .filter((p) -> p.getProductID() == ID)
                .collect(Collectors.toList());
        List<Supplier> supplierWithID = suppliers.stream()
                .filter((p) -> p.getSupplierID() == p.getSupplierID())
                .collect(Collectors.toList());
        
        Products product = productWithID.get(0);
        Supplier supplier = supplierWithID.get(0);
        
        String productName_ = (productLineMap.get(product.getProductLineID()) + " " +
                product.getDescription() + " " + product.getCharacteristics() + " " +
                product.getMotors()).replaceAll("\\s+", " ");
        String productDescription_ = (product.getDescription() + " " + product.getCharacteristics() +
                " " + product.getMotors()).replaceAll("\\s+", " ");
        
        iProductName.setText(productName_);
        iItemNumber.setText(String.valueOf(product.getProductID()));
        iAddMotoCode.setText(product.getAddmotoCode());
        iProductLine.setText(productLineMap.get(product.getProductLineID()));
        iItemDescription.setText(productDescription_);
        iSellingPrice.setText(String.valueOf(product.getSellingPrice()));
        iUnitCost.setText(String.valueOf(product.getUnitPrice()));
        iOnHandQuantity.setText(String.valueOf(product.getCurrentQuantity()));
        iQtyThreshold.setText(String.valueOf(product.getThresholdCount()));
        iStatus.setText("Not Yet Implemented");
        iSupplier.setText(supplier.getSupplierName());
        iEditUpdateRSP.setEnabled(true);
        iEditUpdateUC.setEnabled(true);
        iEditUpdateQT.setEnabled(true);
    }
    
    public void populate() {
        productLines = model.getProductLines();
        createMap();
        suppliers = model.getSuppliers();
        products = model.getProducts();
        
        iProductLineFilter.addItem(" ");
        for(ProductLine productLine : productLines) {
            iProductLineFilter.addItem(productLine);
        }
        
        iSupplierFilter.addItem(" ");
        for(Supplier supplier : suppliers) {
            iSupplierFilter.addItem(supplier);
        }
        
        populateTable();
    }
    
    private void populateTable() {
        while(tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
        }
        for(Products product : products) {
            tableModel.addRow(
                    new Object[] {
                        String.valueOf(product.getProductID()),
                        product.getAddmotoCode(),
                        productLineMap.get(product.getProductLineID()),
                        (product.getDescription() + " " + product.getCharacteristics() + " " + product.getMotors()).replaceAll("\\s+", " "),
                        "PhP " + Formatter.format(product.getUnitPrice()),
                        "PhP " + Formatter.format(product.getSellingPrice())
            });
        }
    }
    
    public void repopulateTable() {
        populateTable();
        iProductsTable.scrollRowToVisible(0);
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>((DefaultTableModel) iProductsTable.getModel());
        iProductsTable.setRowSorter(sorter);
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();

        int columnIndexToSort = 0;
        sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));

        sorter.setSortKeys(sortKeys);
        sorter.sort();
    }
    
    public void setIndexZero() {
        iProductLineFilter.setSelectedIndex(0);
        iStatusFilter.setSelectedIndex(0);
        iSupplierFilter.setSelectedIndex(0);
    }
    
    public void setDefaultViews() {
        iSearchField.setText("");
        iProductName.setText("");
        iItemNumber.setText("");
        iAddMotoCode.setText("");
        iProductLine.setText("");
        iItemDescription.setText("");
        iOnHandQuantity.setText("");
        iStatus.setText("");
        iSupplier.setText("");
        iSellingPrice.setText("");
        iUnitCost.setText("");
        iQtyThreshold.setText("");
        setToDefault(iSellingPrice, iEditUpdateRSP, iCancelRSP, true);
        setToDefault(iUnitCost, iEditUpdateUC, iCancelUC, true);
        setToDefault(iQtyThreshold, iEditUpdateQT, iCancelQT, true);
    }
    
    private void createMap() {
        productLineMap = new HashMap<Integer, String>();
        for(ProductLine productLine : productLines) {
            productLineMap.put(productLine.getProductLineID(), productLine.getProductLineName());
        }
    }
    
    private void setRedForeground(JLabel label) {
        label.setForeground(Color.RED);
    }
    
    private void setBlackForeground(JLabel label) {
        label.setForeground(Color.BLACK);
    }
    
    private void setWhiteForeground(JLabel label) {
        label.setForeground(Color.WHITE);
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