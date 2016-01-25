package project.addmoto.controller;

import static java.lang.Integer.*;
import static java.lang.Double.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import java.util.Set;
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
import project.addmoto.view.AddItem;
import project.addmoto.view.App;

/**
 *
 * @author Kim Howel delos Reyes
 */
public final class InventoryController extends Controller {

    private final App view;
    private final InventoryModel model;

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
    private final JTextField iSellingPrice;
    private final JTextField iUnitCost;
    private final JTextField iQtyThreshold;
    private final JButton iAddNew;
    private final JButton iAddNewPLine;

    private String sellingPriceValue;
    private String unitCostValue;
    private String qtyThresholdValue;

    private DefaultTableModel tableModel;
    private ListSelectionModel selectionModel;

    private ArrayList<ProductLine> productLines;
    private ArrayList<Supplier> suppliers;
    private ArrayList<Products> products;
    private HashMap<Integer, String> productLineMap;
    private HashMap<Integer, String> supplierMap;

    public boolean isAddingRows = false;

    private final AddItem addItem;
    private final JComboBox iSupplierList;
    private final JComboBox iProductLineList;
    private final JTextField iSupplierCodeText;
    private final JTextField iUnitPriceText;
    private final JTextField iSellingPriceText;
    private final JTextField iQtyText;
    private final JTextField iThresholdText;
    private final JTextField iCharacteristics;
    private final JTextField iModelText;
    private final JTextField iDescriptionText;

    public InventoryController(App view, final Connection connection) {
        this.view = view;
        model = new InventoryModel(connection);
        addItem = new AddItem();

        iSupplierList = addItem.getiSupplierList();
        iProductLineList = addItem.getiProductLineList();
        iSupplierCodeText = addItem.getiSupplierCodeText();
        iUnitPriceText = addItem.getiUnitPriceText();
        iSellingPriceText = addItem.getiSellingPriceText();
        iQtyText = addItem.getiQtyText();
        iThresholdText = addItem.getiThresholdText();
        iCharacteristics = addItem.getiCharacteristicsText();
        iModelText = addItem.getiModelText();
        iDescriptionText = addItem.getiDescriptionText();
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
        iSellingPrice = view.getiSellingPrice();
        iSellingPrice.setDisabledTextColor(Color.BLACK);
        iUnitCost = view.getiUnitCost();
        iUnitCost.setDisabledTextColor(Color.BLACK);
        iQtyThreshold = view.getiThreshold();
        iQtyThreshold.setDisabledTextColor(Color.BLACK);
        iAddNew = view.getiAddNew();
        iAddNewPLine = view.getiAddNewPLine();

        setDefaultViews();
        populate();
        setListeners();
    }

    @Override
    public void setListeners() {
        iSearchField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                isAddingRows = true;
                iSearchField.setText(Formatter.makeUpperCase(iSearchField.getText()));
                String input = iSearchField.getText();
                List<Products> productWithID = products.stream()
                        .filter((p) -> (p.getAddmotoCode().toUpperCase().contains(input.toUpperCase())
                                || (p.getDescription() + " " + p.getCharacteristics() + p.getMotors()).replaceAll("\\s+", " ").trim().toUpperCase().contains(input.toUpperCase()))
                                || getProductLineName(p.getProductLineID()).toUpperCase().contains(input.toUpperCase())
                        )
                        .collect(Collectors.toList());

                while (tableModel.getRowCount() > 0) {
                    tableModel.removeRow(0);
                }
                for (Products product : productWithID) {
                    tableModel.addRow(
                            new Object[]{
                                String.valueOf(product.getProductID()),
                                product.getAddmotoCode(),
                                productLineMap.get(product.getProductLineID()),
                                (product.getDescription() + " " + product.getCharacteristics() + " " + product.getMotors()).replaceAll("\\s+", " ").trim(),
                                "PhP " + Formatter.format(product.getUnitPrice()),
                                "PhP " + Formatter.format(product.getSellingPrice())
                            });
                }
                isAddingRows = false;
            }
        });

        iAddNewPLine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newProductLine = JOptionPane.showInputDialog(
                        null,
                        "Product Line Name:",
                        "New Product Line",
                        JOptionPane.QUESTION_MESSAGE);

                try {
                    if (newProductLine.equals("")) {
                        JOptionPane.showMessageDialog(
                                null,
                                "You cannot create an empty product line name.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    } else {
                        int rows = model.insertNewProductLine(newProductLine);
                        if (rows != 0) {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Product Line list updated.",
                                    "Success",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
                            populate();
                        } else {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Product Line may be existing already!",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                    }
                } catch (Exception ex) {

                }
            }
        });

        iShowHistory.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(view, "Clicked");
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

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
            public void focusLost(FocusEvent e) {
            }
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
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        iUnitCost.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                iUnitCost.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
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
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        iQtyThreshold.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                iQtyThreshold.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
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
                } else if ((ch == '.' && iSellingPrice.getText().contains("."))
                        || (ch == '.' && iSellingPrice.getText().equals(""))) {
                    tk.beep();
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        
        iQtyThreshold.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();
                if (!((ch >= '0') && (ch <= '9')
                        || (ch == KeyEvent.VK_BACK_SPACE)
                        || (ch == KeyEvent.VK_DELETE))) {
                    tk.beep();
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        iEditUpdateRSP.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!iAddMotoCode.getText().equals("")) {
                    if (iEditUpdateRSP.getText().equals("Edit")) {
                        sellingPriceValue = iSellingPrice.getText();
                        setToDefault(iSellingPrice, iEditUpdateRSP, iCancelRSP, false);
                    } else if (iEditUpdateRSP.getText().equals("Update")) {
                        if(iSellingPrice.getText().equals("") || iUnitCost.getText().equals("") ||
                                iSellingPrice.getText().split("\\.")[1].length() > 2 || iUnitCost.getText().split("\\.")[1].length() > 2) {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "There are empty fields or number format is invalid",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                            iSellingPrice.setText(String.valueOf(sellingPriceValue));
                        } else {
                            double sellingPrice = Double.parseDouble(iSellingPrice.getText());
                            double unitPrice = Double.parseDouble(iUnitCost.getText());

                            if(sellingPrice >= unitPrice * 1.30) {
                                int option = JOptionPane.showConfirmDialog(
                                        null,
                                        "Are you sure you want to proceed with the update?",
                                        "Confirm",
                                        JOptionPane.YES_NO_OPTION,
                                        JOptionPane.QUESTION_MESSAGE
                                );
                                if(option == JOptionPane.YES_OPTION) {
                                    int itemID = Integer.parseInt(iItemNumber.getText());
                                    int updatedRow = model.updateSellingPrice(itemID, sellingPrice);
                                    if(updatedRow != 0) {
                                        JOptionPane.showMessageDialog(
                                                null,
                                                "Product selling price updated!",
                                                "Success",
                                                JOptionPane.INFORMATION_MESSAGE
                                        );
                                        setIndexZero();
                                        setDefaultViews();
                                        isAddingRows = true;
                                        populate();
                                        isAddingRows = false;
                                    } else {
                                        JOptionPane.showMessageDialog(
                                                null,
                                                "There is an error while updating item selling price.",
                                                "Error",
                                                JOptionPane.ERROR_MESSAGE
                                        );
                                    }
                                }
                            } else {
                                JOptionPane.showMessageDialog(
                                        null,
                                        "You must have atleast 30% profit for this item.",
                                        "Error",
                                        JOptionPane.ERROR_MESSAGE
                                );
                            }
                        }
                        setToDefault(iSellingPrice, iEditUpdateRSP, iCancelRSP, true);
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setRedForeground(iEditUpdateRSP);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBlackForeground(iEditUpdateRSP);
            }
        });
        
        iUnitCost.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();
                if (!((ch >= '0') && (ch <= '9')
                        || (ch == KeyEvent.VK_BACK_SPACE)
                        || (ch == KeyEvent.VK_DELETE))) {
                    tk.beep();
                    e.consume();
                } else if ((ch == '.' && iUnitCost.getText().contains("."))
                        || (ch == '.' && iUnitCost.getText().equals(""))) {
                    tk.beep();
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        iEditUpdateUC.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!iAddMotoCode.getText().equals("")) {
                    if (iEditUpdateUC.getText().equals("Edit")) {
                        unitCostValue = iUnitCost.getText();
                        setToDefault(iUnitCost, iEditUpdateUC, iCancelUC, false);
                    } else if (iEditUpdateUC.getText().equals("Update")) {
                        if(iSellingPrice.getText().equals("") || iUnitCost.getText().equals("") ||
                                iSellingPrice.getText().split("\\.")[1].length() > 2 || iUnitCost.getText().split("\\.")[1].length() > 2) {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "There are empty fields or number format is invalid",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                            iUnitCost.setText(String.valueOf(unitCostValue));
                        } else {
                            double sellingPrice = Double.parseDouble(iSellingPrice.getText());
                            double unitPrice = Double.parseDouble(iUnitCost.getText());

                            if(sellingPrice >= unitPrice * 1.30) {
                                int option = JOptionPane.showConfirmDialog(
                                        null,
                                        "Are you sure you want to proceed with the update?",
                                        "Confirm",
                                        JOptionPane.YES_NO_OPTION,
                                        JOptionPane.QUESTION_MESSAGE
                                );
                                if(option == JOptionPane.YES_OPTION) {
                                    int itemID = Integer.parseInt(iItemNumber.getText());
                                    int updatedRow = model.updateUnitPrice(itemID, unitPrice);
                                    if(updatedRow != 0) {
                                        JOptionPane.showMessageDialog(
                                                null,
                                                "Product unit price updated!",
                                                "Success",
                                                JOptionPane.INFORMATION_MESSAGE
                                        );
                                        setIndexZero();
                                        setDefaultViews();
                                        isAddingRows = true;
                                        populate();
                                        isAddingRows = false;
                                    } else {
                                        JOptionPane.showMessageDialog(
                                                null,
                                                "There is an error while updating item unit price.",
                                                "Error",
                                                JOptionPane.ERROR_MESSAGE
                                        );
                                    }
                                }
                            } else {
                                JOptionPane.showMessageDialog(
                                        null,
                                        "You must have atleast 30% profit for this item.",
                                        "Error",
                                        JOptionPane.ERROR_MESSAGE
                                );
                            }
                        }
                        setToDefault(iUnitCost, iEditUpdateUC, iCancelUC, true);
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setRedForeground(iEditUpdateUC);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBlackForeground(iEditUpdateUC);
            }
        });
        
        iQtyThreshold.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();
                if (!((ch >= '0') && (ch <= '9')
                        || (ch == KeyEvent.VK_BACK_SPACE)
                        || (ch == KeyEvent.VK_DELETE))) {
                    tk.beep();
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        iEditUpdateQT.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!iAddMotoCode.getText().equals("")) {
                    if (iEditUpdateQT.getText().equals("Edit")) {
                        qtyThresholdValue = iQtyThreshold.getText();
                        setToDefault(iQtyThreshold, iEditUpdateQT, iCancelQT, false);
                    } else if (iEditUpdateQT.getText().equals("Update")) {
                        if(iQtyThreshold.getText().equals("")) {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "There are empty fields or number format is invalid",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                            iQtyThreshold.setText(String.valueOf(qtyThresholdValue));
                        } else {
                            int threshold = Integer.parseInt(iQtyThreshold.getText());
                            int option = JOptionPane.showConfirmDialog(
                                    null,
                                    "Are you sure you want to proceed with the update?",
                                    "Confirm",
                                    JOptionPane.YES_NO_OPTION,
                                    JOptionPane.QUESTION_MESSAGE
                            );
                            if (option == JOptionPane.YES_OPTION) {
                                int itemID = Integer.parseInt(iItemNumber.getText());
                                int updatedRow = model.updateThreshold(itemID, threshold);
                                if (updatedRow != 0) {
                                    JOptionPane.showMessageDialog(
                                            null,
                                            "Product threshold count updated!",
                                            "Success",
                                            JOptionPane.INFORMATION_MESSAGE
                                    );
                                    setIndexZero();
                                    setDefaultViews();
                                    isAddingRows = true;
                                    populate();
                                    isAddingRows = false;
                                } else {
                                    JOptionPane.showMessageDialog(
                                            null,
                                            "There is an error while updating item threshold count.",
                                            "Error",
                                            JOptionPane.ERROR_MESSAGE
                                    );
                                }
                            }
                        }
                        setToDefault(iQtyThreshold, iEditUpdateQT, iCancelQT, true);
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

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
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

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
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

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
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

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
            if (e.getValueIsAdjusting()) {
                return;
            }
            if (!isAddingRows) {
                int row = iProductsTable.getSelectedRow();
                int ID = Integer.parseInt((String) iProductsTable.getValueAt(row, 0));
                System.out.println("ID -> " + ID);
                iProductsTable.scrollRowToVisible(row);
                showProductDetails(ID);
            }
        });
        
        iSellingPriceText.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();
                if (!((ch >= '0') && (ch <= '9')
                        || (ch == KeyEvent.VK_BACK_SPACE)
                        || (ch == KeyEvent.VK_DELETE)
                        || (ch == '.'))) {
                    tk.beep();
                    e.consume();
                } else if ((ch == '.' && iSellingPriceText.getText().contains("."))
                        || (ch == '.' && iSellingPriceText.getText().equals(""))) {
                    tk.beep();
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        iUnitPriceText.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();
                if (!((ch >= '0') && (ch <= '9')
                        || (ch == KeyEvent.VK_BACK_SPACE)
                        || (ch == KeyEvent.VK_DELETE)
                        || (ch == '.'))) {
                    tk.beep();
                    e.consume();
                } else if ((ch == '.' && iUnitPriceText.getText().contains("."))
                        || (ch == '.' && iUnitPriceText.getText().equals(""))) {
                    tk.beep();
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        iQtyText.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();
                if (!((ch >= '0') && (ch <= '9')
                        || (ch == KeyEvent.VK_BACK_SPACE)
                        || (ch == KeyEvent.VK_DELETE))) {
                    tk.beep();
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        iThresholdText.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();
                if (!((ch >= '0') && (ch <= '9')
                        || (ch == KeyEvent.VK_BACK_SPACE)
                        || (ch == KeyEvent.VK_DELETE))) {
                    tk.beep();
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        iAddNew.addActionListener((ActionEvent e) -> {
            int choice = JOptionPane.showOptionDialog(view, addItem, "ADD Moto - Motorcycle Parts and Accessories", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
            if (choice == JOptionPane.OK_OPTION) {
                String supplierName = getFormattedString((String) iSupplierList.getSelectedItem());
                String productLine = getFormattedString((String) iProductLineList.getSelectedItem());
                String supplierCode = getFormattedString(iSupplierCodeText.getText());
                String unitPrice = getFormattedString(iUnitPriceText.getText());
                String sellingPrice = getFormattedString(iSellingPriceText.getText());
                String qtyOnHand = getFormattedString(iQtyText.getText());
                String threshold = getFormattedString(iThresholdText.getText());
                String characteristics = getFormattedString(iCharacteristics.getText());
                String _model = getFormattedString(iModelText.getText());
                String description = getFormattedString(iDescriptionText.getText());

                if (supplierName.isEmpty() || productLine.isEmpty() || supplierCode.isEmpty()
                        || unitPrice.isEmpty() || sellingPrice.isEmpty() || qtyOnHand.isEmpty()
                        || threshold.isEmpty() || characteristics.isEmpty() || _model.isEmpty()
                        || description.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "All fields must not be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    Products product = new Products(
                            productLine.toUpperCase().substring(0, 2) + "AD" + supplierCode,
                            supplierCode,
                            parseInt(qtyOnHand),
                            parseDouble(unitPrice),
                            parseDouble(sellingPrice),
                            parseDouble(sellingPrice) - parseDouble(unitPrice),
                            parseInt(threshold),
                            null,
                            description,
                            characteristics,
                            _model,
                            getKeybyValue_p(productLine),
                            getKeybyValue_s(supplierName)
                    );
                    System.out.println(product.toString());
                    int returnValue = model.addNewProduct(product);
                    if (returnValue == 0) {
                        JOptionPane.showMessageDialog(view, "There must be some kind of error.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(view, "Successfully added new product!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        setIndexZero();
                        setDefaultViews();
                        isAddingRows = true;
                        repopulateTable();
                        isAddingRows = false;
                    }
                }
            }
        });
    }

    private String getProductLineName(int productLineID) {
        List<ProductLine> productLine = productLines.stream()
                .filter((p) -> p.getProductLineID() == productLineID)
                .collect(Collectors.toList());
        return productLine.get(0).getProductLineName();
    }

    private int getKeybyValue_p(String productLine) {
        Set<Integer> keys = productLineMap.keySet();
        for (Integer key : keys) {
            if (productLineMap.get(key).equals(productLine)) {
                return key;
            }
        }
        return -1;
    }

    private int getKeybyValue_s(String supplierName) {
        Set<Integer> keys = supplierMap.keySet();
        for (Integer key : keys) {
            if (supplierMap.get(key).equals(supplierName)) {
                return key;
            }
        }
        return -1;
    }

    private String getFormattedString(String value) {
        return value.replaceAll("\\s+", " ").trim();
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

        String productName_ = (productLineMap.get(product.getProductLineID()) + " "
                + product.getDescription() + " " + product.getCharacteristics() + " "
                + product.getMotors()).replaceAll("\\s+", " ");
        String productDescription_ = (product.getDescription() + " " + product.getCharacteristics()
                + " " + product.getMotors()).replaceAll("\\s+", " ");

        iProductName.setText(productName_);
        iItemNumber.setText(String.valueOf(product.getProductID()));
        iAddMotoCode.setText(product.getAddmotoCode());
        iProductLine.setText(productLineMap.get(product.getProductLineID()));
        iItemDescription.setText(productDescription_);
        iSellingPrice.setText(Formatter.format(product.getSellingPrice()));
        iUnitCost.setText(Formatter.format(product.getUnitPrice()));
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
        suppliers = model.getSuppliers();
        createMap();
        products = model.getProducts();

        if (iProductLineList.getItemCount() != 0) {
            iProductLineList.removeAllItems();
        }

        iProductLineList.addItem(" ");
        for (ProductLine productLine : productLines) {
            iProductLineList.addItem(productLine.getProductLineName());
        }

        if (iSupplierList.getItemCount() != 0) {
            iSupplierList.removeAllItems();
        }

        iSupplierList.addItem(" ");
        for (Supplier supplier : suppliers) {
            iSupplierList.addItem(supplier.getSupplierName());
        }

        populateTable();
    }

    private void populateTable() {
        while (tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
        }
        products = model.getProducts();
        for (Products product : products) {
            tableModel.addRow(
                    new Object[]{
                        String.valueOf(product.getProductID()),
                        product.getAddmotoCode(),
                        productLineMap.get(product.getProductLineID()),
                        (product.getDescription() + " " + product.getCharacteristics() + " " + product.getMotors()).replaceAll("\\s+", " ").trim(),
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
        iSearchField.setText("");
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
        supplierMap = new HashMap<Integer, String>();
        for (ProductLine productLine : productLines) {
            productLineMap.put(productLine.getProductLineID(), productLine.getProductLineName());
        }
        for (Supplier supplier : suppliers) {
            supplierMap.put(supplier.getSupplierID(), supplier.getSupplierName());
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
