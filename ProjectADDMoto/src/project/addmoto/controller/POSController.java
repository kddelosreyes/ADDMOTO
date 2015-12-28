package project.addmoto.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import project.addmoto.data.ProductLine;
import project.addmoto.model.POSModel;
import project.addmoto.data.Products;
import project.addmoto.data.SalesItems;
import project.addmoto.datacollections.ProductsList;
import project.addmoto.datacollections.SalesItemsList;
import project.addmoto.utilities.Formatter;
import project.addmoto.utilities.Operations_POS;
import project.addmoto.view.App;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class POSController {

    private ProductsList productList;
    private SalesItemsList salesItemsList;

    private App view;
    private POSModel model;

    private JTextField addProduct;
    private JButton enterProduct;
    private JButton clearSales;
    private JButton creditSales;
    private JButton voidItem;
    private JButton paySales;
    private JTable itemsTable;
    private JLabel subtotalValue;
    private JLabel taxValue;
    private JLabel totalValue;
    private JLabel payValue;
    private JLabel productCode;
    private JLabel productLine;
    private JLabel description;
    private JLabel quantityOnHand;
    private JLabel sellingPrice;
    private JTextField quantityField;
    private JButton addItem;
    private JComboBox filterType;

    private DefaultTableModel tableModel;
    private ListSelectionModel selectionModel;

    private int selectedRow = -1;
    private Products selectedProduct = null;

    public POSController(App view) {
        this.view = view;
        model = new POSModel();

        productList = new ProductsList();
        salesItemsList = new SalesItemsList();

        addProduct = view.getPosAddProduct();
        enterProduct = view.getPosEnter();
        clearSales = view.getPosClear();
        creditSales = view.getPosCredit();
        voidItem = view.getPosVoid();
        paySales = view.getPosPay();
        itemsTable = view.getPosItemsTable();
        subtotalValue = view.getPosSubTotal();
        taxValue = view.getPosTax();
        totalValue = view.getPosTotal();
        payValue = view.getPosAmountDue();
        productCode = view.getProductCode();
        productLine = view.getProductLine();
        description = view.getDescription();
        description.setToolTipText(description.getText());
        quantityOnHand = view.getQuantityOnHand();
        sellingPrice = view.getSellingPrice();
        quantityField = view.getQuantityField();
        addItem = view.getAddItem();
        filterType = view.getFilterType();

        tableModel = (DefaultTableModel) itemsTable.getModel();
        selectionModel = (ListSelectionModel) itemsTable.getSelectionModel();

        setListeners();
    }

    private void setListeners() {
        addProduct.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                addProduct.setText(Formatter.makeUpperCase(getContents()));
            }
        });

        addProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getProduct();
            }
        });

        enterProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getProduct();
            }
        });

        voidItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(view, "Nothing Selected!");
                } else {
                    SalesItems itemSelected = getItem(selectedRow);
                    int quantity = itemSelected.getQuantity();
                    String productCode = itemSelected.getItemCode();
                    int dialogResult = JOptionPane.showConfirmDialog(view, "Are you sure to void item " + productCode + "?",
                            "Verify void", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                    if (dialogResult == JOptionPane.YES_OPTION) {
                        if (quantity > 1) {
                            String result = JOptionPane.showInputDialog(view, "How many would you like to void?",
                                    "Void quantity", JOptionPane.QUESTION_MESSAGE);
                            int quantityValue = -1;
                            if (result.contains(".") || Integer.parseInt(result) > quantity) {
                                quantityValue = -1;
                            } else {
                                quantityValue = Integer.parseInt(result);
                            }
                            if (quantityValue == -1) {
                                JOptionPane.showMessageDialog(view, "Invalid quantity.", "Error",
                                        JOptionPane.ERROR_MESSAGE);
                            } else {
                                if (quantity - quantityValue == 0) {
                                    tableModel.removeRow(selectedRow);
                                    removeItem(selectedRow);
                                } else {
                                    int newQuantity = quantity - quantityValue;
                                    itemSelected.setQuantity(newQuantity);
                                    itemSelected.setExtPrice(newQuantity * itemSelected.getSellingPrice());
                                    updateItem(itemSelected, selectedRow);

                                    tableModel.setValueAt(newQuantity, selectedRow, 2);
                                    tableModel.setValueAt(itemSelected.getExtPrice(), selectedRow, 4);
                                }
                                String xx = "";
                                SalesItemsList sa = getSalesItemsList();
                                for (SalesItems x : sa) {
                                    xx += x.getItemCode() + " " + x.getQuantity() + " " + x.getExtPrice() + "\n";
                                }
                                System.out.println(xx);
                            }
                        } else {
                            tableModel.removeRow(selectedRow);
                            removeItem(selectedRow);
                        }
                        setFields();
                        voidItem.setEnabled(false);
                    }
                    itemsTable.clearSelection();
                }
            }
        });

        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    return;
                }
                int row = itemsTable.getSelectedRow();
                System.out.println(row);
                if (row != -1) {
                    selectedRow = row;
                    voidItem.setEnabled(true);
                }
            }
        });
        
        addItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addItemToTable();
            }
        });
    }
    
    private void getProduct() {
        String productCode = getContents(),
                selectedFilter = (String) filterType.getSelectedItem();
        if (productCode.equals("")) {
            JOptionPane.showMessageDialog(view, "Enter value with respect to selected filter.", "Error", JOptionPane.ERROR_MESSAGE);
            this.productCode.setText("-----");
            this.productLine.setText("-----");
            description.setText("-----");
            description.setToolTipText(description.getText());
            quantityOnHand.setText("NA");
            sellingPrice.setText("NA");
            quantityField.setEnabled(false);
            addItem.setEnabled(false);
            selectedProduct = null;
        } else {
            if(selectedFilter.equalsIgnoreCase("Product Code")) {
                selectedProduct = model.getProduct(productCode);
            } else if(selectedFilter.equalsIgnoreCase("Product Line")) {
                
            } else if(selectedFilter.equalsIgnoreCase("Item Name")) {
                
            } else if(selectedFilter.equalsIgnoreCase("Description")) {
                
            } else if(selectedFilter.equalsIgnoreCase("None")) {
                
            }
            
            if(selectedProduct == null) {
                JOptionPane.showMessageDialog(view, "No product on list.", "Error.", JOptionPane.ERROR_MESSAGE);
            } else {
                quantityField.setEnabled(true);
                addItem.setEnabled(true);
                ProductLine productLine = model.getProductLine(selectedProduct.getProductLineID());
            
                this.productCode.setText(selectedProduct.getAddmotoCode());
                this.productLine.setText(productLine.getProductLineName());
                description.setText((selectedProduct.getCharacteristics() + 
                        " " + selectedProduct.getDescription() +
                        " " + selectedProduct.getMotors()).trim());
                description.setToolTipText(description.getText());
                quantityOnHand.setText(String.valueOf(selectedProduct.getCurrentQuantity()));
                sellingPrice.setText("PhP " + selectedProduct.getSellingPrice());
                quantityField.setEnabled(true);
                addItem.setEnabled(true);
            }
        }
    }

    private void addItemToTable() {
        Products product = selectedProduct;
        if (product == null) {
            selectedProduct = null;
        } else {
            boolean isAlreadyListed = false;
            int itemIdx = -1, i = 0;
            for (SalesItems items : salesItemsList) {
                if (items.getItemCode().equals(product.getAddmotoCode())) {
                    isAlreadyListed = true;
                    itemIdx = i;
                    break;
                }
                i++;
            }
            
            String quantity_ = quantityField.getText();
            if (quantity_.equals("") || quantity_.matches(".*[a-zA-Z]+.*") || hasSpecialCharacter(quantity_.toCharArray())) {
                JOptionPane.showMessageDialog(view, "Invalid number format", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                int quantity = Integer.parseInt(quantityField.getText());

                if (isAlreadyListed) {
                    SalesItems itemUpdate = getItem(itemIdx);
                    int newQuantity = itemUpdate.getQuantity() + quantity;
                    itemUpdate.setQuantity(newQuantity);
                    itemUpdate.setExtPrice(newQuantity * itemUpdate.getSellingPrice());
                    updateItem(itemUpdate, itemIdx);

                    tableModel.setValueAt(itemUpdate.getQuantity(), itemIdx, 2);
                    tableModel.setValueAt(itemUpdate.getExtPrice(), itemIdx, 4);
                } else {
                    ProductLine productLine = model.getProductLine(product.getProductLineID());
                    SalesItems salesItems = new SalesItems(product.getProductID(), product.getAddmotoCode(),
                            productLine.getProductLineName() + " " + product.getDescription() + " " + product.getCharacteristics(), quantity,
                            product.getSellingPrice(), product.getSellingPrice() * quantity);
                    addItem(salesItems);

                    Object[] row = {salesItems.getItemCode(), salesItems.getItemName(), salesItems.getQuantity(),
                        salesItems.getSellingPrice(), Double.parseDouble(Formatter.format(salesItems.getExtPrice()))};
                    tableModel.addRow(row);
                }
                setFields();
                addProduct.setText("");
                quantityField.setText("");
                addProduct.requestFocusInWindow();
                this.productCode.setText("-----");
                this.productLine.setText("-----");
                description.setText("-----");
                description.setToolTipText(description.getText());
                quantityOnHand.setText("NA");
                sellingPrice.setText("NA");
                quantityField.setEnabled(false);
                addItem.setEnabled(false);
                selectedProduct = null;
            }
        }
    }
    
    private boolean hasSpecialCharacter(char[] str) {
        for(char tmp : str) {
            if(Character.isSpaceChar(tmp) || !((tmp >= 'a' && tmp <= 'z') || (tmp >= 'A' && tmp <= 'Z'))) {
                return true;
            }
        }
        return false;
    }

    private String getContents() {
        return addProduct.getText();
    }

    private void setFields() {
        subtotalValue.setText("PhP   " + getSubtotalPrice());
        taxValue.setText("PhP   " + getTaxablePrice());
        totalValue.setText("PhP   " + getTotalPriceString());
    }

    /*
    Products
     */
    public void addProduct(Products product) {
        productList.add(product);
    }

    public Products removeProduct(int index) {
        return productList.remove(index);
    }

    /*
    Sales Items
     */
    public void addItem(SalesItems item) {
        salesItemsList.add(item);
    }

    public SalesItems getItem(int index) {
        return salesItemsList.get(index);
    }

    public SalesItems removeItem(int index) {
        return salesItemsList.remove(index);
    }

    public void updateItem(SalesItems salesItems, int selectedRow) {
        salesItemsList.set(selectedRow, salesItems);
    }

    public SalesItemsList getSalesItemsList() {
        return salesItemsList;
    }

    /*
    For table values display
     */
    public double getTotalPrice() {
        return Operations_POS.getTotalAmount(salesItemsList);
    }

    public String getTaxablePrice() {
        return Operations_POS.getTaxableAmount(getTotalPrice());
    }

    public String getSubtotalPrice() {
        return Operations_POS.getSubtotalAmount(getTotalPrice());
    }

    public String getTotalPriceString() {
        return Operations_POS.getTotalAmountString(getTotalPrice());
    }
}
