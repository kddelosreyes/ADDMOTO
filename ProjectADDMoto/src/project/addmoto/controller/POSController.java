package project.addmoto.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.prompt.PromptSupport;
import project.addmoto.data.InventoryChange;
import project.addmoto.data.ProductLine;
import project.addmoto.model.POSModel;
import project.addmoto.data.Products;
import project.addmoto.data.Receipt;
import project.addmoto.data.SalesItems;
import project.addmoto.datacollections.ProductsList;
import project.addmoto.datacollections.SalesItemsList;
import project.addmoto.mvc.Controller;
import project.addmoto.utilities.Formatter;
import project.addmoto.utilities.Operations_POS;
import project.addmoto.view.App;
import project.addmoto.view.Payment;

/**
 *
 * @author Kim Howel delos Reyes
 */
public final class POSController extends Controller {

    private final String PHP = "PhP   ";

    private final ProductsList productList;
    private final SalesItemsList salesItemsList;

    private final App view;
    private final POSModel model;

    private final JTextField addProduct;
    private final JButton clearSales;
    private final JButton voidItem;
    private final JButton paySales;
    private final JTable itemsTable;
    private final JTable productsTable;
    private final JLabel subtotalValue;
    private final JLabel taxValue;
    private final JLabel totalValue;
    private final JLabel payValue;
    private final JLabel productCode;
    private final JLabel productLine;
    private final JLabel description;
    private final JLabel quantityOnHand;
    private final JLabel sellingPrice;
    private final JButton addItem;

    private ArrayList<Products> allProducts;
    private ArrayList<ProductLine> productLines;

    private final DefaultTableModel tableModel;
    private final DefaultTableModel productsModel;
    private final ListSelectionModel selectionModel;
    private final ListSelectionModel productsSelectionModel;

    private int selectedRow = -1;
    private Products selectedProduct = null;
    private Products toAdd;
    
    private double dueAmount;

    public POSController(App view, final Connection connection) {
        this.view = view;
        model = new POSModel(connection);

        productList = new ProductsList();
        salesItemsList = new SalesItemsList();

        addProduct = view.getPosAddProduct();
        PromptSupport.setPrompt("Enter code / keyword", addProduct);
        clearSales = view.getPosClear();
        voidItem = view.getPosVoid();
        paySales = view.getPosPay();
        itemsTable = view.getPosItemsTable();
        productsTable = view.getPosProducts();
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
        addItem = view.getAddItem();

        tableModel = (DefaultTableModel) itemsTable.getModel();
        productsModel = (DefaultTableModel) productsTable.getModel();
        selectionModel = (ListSelectionModel) itemsTable.getSelectionModel();
        productsSelectionModel = (ListSelectionModel) productsTable.getSelectionModel();

        populate();

        setListeners();
    }

    public void populate() {
        allProducts = model.getProducts();
        productLines = model.getProductLines();
        while (productsModel.getRowCount() > 0) {
            productsModel.removeRow(0);
        }

        for (Products product : allProducts) {
            List<ProductLine> productLine = productLines.stream()
                        .filter((p) -> p.getProductLineID() == product.getProductLineID())
                        .collect(Collectors.toList());
            
            productsModel.addRow(
                    new Object[]{
                        product.getAddmotoCode(),
                        (productLine.get(0).getProductLineName() + " " + product.getDescription() + " " + product.getCharacteristics() + " " + product.getMotors()).replaceAll("\\s+", " ").trim()
                    }
            );
        }
    }
    
    private String getProductLineName(int productLineID) {
        List<ProductLine> productLine = productLines.stream()
                        .filter((p) -> p.getProductLineID() == productLineID)
                        .collect(Collectors.toList());
        return productLine.get(0).getProductLineName();
    }

    public int getSelectedRow() {
        return selectedRow;
    }

    public Products getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedRow(int selectedRow) {
        this.selectedRow = selectedRow;
    }

    public void setSelectedProduct(Products selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    @Override
    public void setListeners() {
        productsSelectionModel.addListSelectionListener((ListSelectionEvent e) -> {
            if (e.getValueIsAdjusting()) {
                return;
            }
            int row = productsTable.getSelectedRow();
            System.out.println(row);
            if (row != -1) {
                String productCode_ = (String) productsTable.getValueAt(row, 0);

                List<Products> productWithID = allProducts.stream()
                        .filter((p) -> p.getAddmotoCode().equals(productCode_))
                        .collect(Collectors.toList());
                toAdd = productWithID.get(0);
                selectedProduct = toAdd;
                getProduct();
            }
        });

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
                String input = addProduct.getText();
                List<Products> productWithID = allProducts.stream()
                        .filter((p) -> (p.getAddmotoCode().toUpperCase().contains(input.toUpperCase())
                                || (getProductLineName(p.getProductLineID()) + " " + p.getDescription() + " " + p.getCharacteristics() + p.getMotors()).replaceAll("\\s+", " ").trim().toUpperCase().contains(input.toUpperCase())))
                        .collect(Collectors.toList());

                while (productsModel.getRowCount() > 0) {
                    productsModel.removeRow(0);
                }
                for (Products pr : productWithID) {
                    List<ProductLine> productLine = productLines.stream()
                        .filter((p) -> p.getProductLineID() == pr.getProductLineID())
                        .collect(Collectors.toList());
                    
                    productsModel.addRow(
                            new Object[]{
                                pr.getAddmotoCode(),
                                (productLine.get(0).getProductLineName() + " " + pr.getDescription() + " " + pr.getCharacteristics() + " " + pr.getMotors()).replaceAll("\\s+", " ").trim()
                            }
                    );
                }
            }
        });

        addProduct.addActionListener((ActionEvent e) -> {
            getProduct();
        });

        voidItem.addActionListener((ActionEvent e) -> {
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(view, "Nothing Selected!");
            } else {
                SalesItems itemSelected = getItem(selectedRow);
                int quantity = itemSelected.getQuantity();
                String productCode1 = itemSelected.getItemCode();
                int dialogResult = JOptionPane.showConfirmDialog(view, "Are you sure to void item " + productCode1 + "?", "Verify void", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    if (quantity > 1) {
                        String result = JOptionPane.showInputDialog(view, "How many would you like to void?",
                                "Void quantity", JOptionPane.QUESTION_MESSAGE);
                        if (result != null) {
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
                                    tableModel.setValueAt(Formatter.format(itemSelected.getExtPrice()), selectedRow, 4);
                                }
                                String xx = "";
                                SalesItemsList sa = getSalesItemsList();
                                for (SalesItems x : sa) {
                                    xx += x.getItemCode() + " " + x.getQuantity() + " " + x.getExtPrice() + "\n";
                                }
                                System.out.println(xx);
                            }
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
        });

        selectionModel.addListSelectionListener((ListSelectionEvent e) -> {
            if (e.getValueIsAdjusting()) {
                return;
            }
            int row = itemsTable.getSelectedRow();
            System.out.println(row);
            if (row != -1) {
                selectedRow = row;
                voidItem.setEnabled(true);
            }
        });

        addItem.addActionListener((ActionEvent e) -> {
            addItemToTable();
        });

        paySales.addActionListener((ActionEvent e) -> {
            int choice = JOptionPane.showConfirmDialog(view, "<html><span style='font-size:10px'>Do you want to proceed with the payment?",
                    "Confirm payment", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (choice == JOptionPane.OK_OPTION) {
                Payment paymentPanel = new Payment();
                JLabel p_sales = paymentPanel.getPaymentSalesTotal();
                JLabel p_balance = paymentPanel.getPaymentBalance();
                JTextField p_payment = paymentPanel.getPayment();
                ButtonGroup paymentGroup = paymentPanel.getPaymentBG();
                JRadioButton paymentN = paymentPanel.getPaymentN();
                JRadioButton paymentP = paymentPanel.getPaymentP();
                JTextField p_discount = paymentPanel.getPaymentDiscount();
                JLabel discountPrice = paymentPanel.getDiscountPrice();

                paymentGroup.add(paymentP);
                paymentGroup.add(paymentN);
                paymentN.setSelected(true);
                p_discount.setText("0.00");

                dueAmount = Double.parseDouble(payValue.getText().split("\\s+")[1]);
                paymentP.addActionListener((ActionEvent e1) -> {
                    dueAmount = Double.parseDouble(payValue.getText().split("\\s+")[1]);
                    p_discount.setText("0");
                    p_balance.setText(PHP + Formatter.format(dueAmount));
                    p_payment.setText("");
                    discountPrice.setText("PhP 0.00");
                });
                paymentN.addActionListener((ActionEvent e1) -> {
                    dueAmount = Double.parseDouble(payValue.getText().split("\\s+")[1]);
                    p_discount.setText("0.00");
                    p_balance.setText(PHP + Formatter.format(dueAmount));
                    p_payment.setText("");
                    discountPrice.setText("PhP 0.00");
                });

                p_sales.setText(PHP + Formatter.format(dueAmount));
                p_balance.setText(PHP + Formatter.format(dueAmount));
                p_payment.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        char ch = e.getKeyChar();
                        if (!((ch >= '0') && (ch <= '9')
                                || (ch == KeyEvent.VK_BACK_SPACE)
                                || (ch == KeyEvent.VK_DELETE)
                                || (ch == '.'))) {
                            tk.beep();
                            e.consume();
                        } else if ((ch == '.' && p_payment.getText().contains("."))
                                || (ch == '.' && p_payment.getText().equals(""))) {
                            tk.beep();
                            e.consume();
                        }
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                        String payValue_ = p_payment.getText();
                        if(paymentN.isSelected()) {
                            if (payValue_.equals("")) {
                                p_balance.setText(PHP + Formatter.format((dueAmount - Double.parseDouble(p_discount.getText()))));
                            } else {
                                double inputPayment = Double.parseDouble(p_payment.getText());
                                p_balance.setText(PHP + Formatter.format((dueAmount - Double.parseDouble(p_discount.getText())) - inputPayment));
                            }
                        } else if(paymentP.isSelected()) {
                            if (payValue_.equals("")) {
                                p_balance.setText(PHP + Formatter.format((dueAmount*((100 - Double.parseDouble(p_discount.getText()))/100.0))));
                            } else {
                                double inputPayment = Double.parseDouble(p_payment.getText());
                                p_balance.setText(PHP + Formatter.format((dueAmount*((100 - Double.parseDouble(p_discount.getText()))/100.0)) - inputPayment));
                            }
                        }
                    }
                });
                p_discount.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        char ch = e.getKeyChar();
                        if (!((ch >= '0') && (ch <= '9')
                                || (ch == KeyEvent.VK_BACK_SPACE)
                                || (ch == KeyEvent.VK_DELETE)
                                || (ch == '.'))) {
                            tk.beep();
                            e.consume();
                        } else if ((ch == '.' && p_discount.getText().contains("."))
                                || (ch == '.' && p_discount.getText().equals(""))) {
                            tk.beep();
                            e.consume();
                        }
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                        String payValue_ = p_discount.getText();
                        if (payValue_.equals("")) {
                            p_balance.setText(PHP + dueAmount);
                        } else if (paymentP.isSelected()) {
                            String discount = p_discount.getText();
                            if(discount.charAt(0) == '.') {
                                discount = "0" + discount;
                            }
                            double inputPayment = Double.parseDouble(discount);
                            p_balance.setText(PHP + Formatter.format(dueAmount * ((100 - inputPayment) / 100.00)));
                            discountPrice.setText("PhP " + Formatter.format(dueAmount * (inputPayment / 100.00)));
                        } else if (paymentN.isSelected()) {
                            String discount = p_discount.getText();
                            if(discount.charAt(0) == '.') {
                                discount = "0" + discount;
                            }
                            double inputPayment = Double.parseDouble(discount);
                            p_balance.setText(PHP + Formatter.format(dueAmount - inputPayment));
                            discountPrice.setText("PhP " + Formatter.format(inputPayment));
                        }
                    }
                });

                int kPress = JOptionPane.showOptionDialog(view, paymentPanel,
                        "Enter payment", JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE, null, null, null);
                if (kPress == JOptionPane.OK_OPTION) {
                    if (p_payment.getText().equals("") || Double.parseDouble(p_balance.getText().split("\\s+")[1]) > 0) {
                        JOptionPane.showMessageDialog(view, "Cannot process payment", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        Date date = new Date();
                        String receiptNo = Formatter.formatReceipt(date).replaceAll(" ", "");
                        
                        double discountValue = 0.0;
                        if(paymentN.isSelected()) {
                            discountValue = Double.parseDouble(p_discount.getText());
                        } else if(paymentP.isSelected()) {
                            discountValue = dueAmount * (100 - Double.parseDouble(p_discount.getText())) / 100.00;
                        }

                        Receipt receipt = new Receipt(receiptNo, Formatter.formatDate(date), dueAmount,
                                view.getSellerAccount().getSellerID(), Double.parseDouble(p_payment.getText()), true, discountValue);
                        int returnValue = model.insertReceipt(receipt);
                        if (returnValue == 0) {
                            JOptionPane.showMessageDialog(view, "Cannot process transaction properly during inserting receipt", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            int receiptID_ = model.getReceiptID(receiptNo);
                            System.out.println("Receipt ID: " + receiptID_);
                            int countInserted = model.insertSoldItems(salesItemsList, receiptID_);
                            if (countInserted == salesItemsList.size()) {
                                JOptionPane.showMessageDialog(view, "<html><span style='font-size:10px'>Transaction processed completely.</span><br><span style='font-size:16px'>Receipt No: " + receiptNo,
                                        "Payment Success", JOptionPane.PLAIN_MESSAGE);

                                ArrayList<InventoryChange> changeList = new ArrayList<>();
                                System.out.println("Size: " + salesItemsList.size());

                                for (SalesItems itemsX : salesItemsList) {
                                    Products productX = model.getProduct(itemsX.getProductID());
                                    InventoryChange change = new InventoryChange(Formatter.formatDate(date),
                                            productX.getCurrentQuantity(), productX.getCurrentQuantity() - itemsX.getQuantity(),
                                            itemsX.getQuantity(), itemsX.getProductID());
                                    System.out.println(change.toString());
                                    changeList.add(change);
                                }

                                int insertChangeLog = model.insertChangeItems(changeList);
                                if (insertChangeLog == salesItemsList.size()) {
                                    System.out.println("Successful insert to update item change log.");
                                } else {
                                    System.out.println("Not successful insert to update item change log.");
                                }

                                int updateChange = model.updateChangeItems(salesItemsList);
                                if (updateChange == salesItemsList.size()) {
                                    System.out.println("Successful update item quantity");
                                } else {
                                    System.out.println("Not successful update item quantity");
                                }

                                itemsTable.clearSelection();
                                while (tableModel.getRowCount() > 0) {
                                    tableModel.removeRow(0);
                                }
                                subtotalValue.setText("Php   0.00");
                                taxValue.setText("PhP   0.00");
                                totalValue.setText("PhP   0.00");
                                payValue.setText("PhP   0.00");
                                paySales.setEnabled(false);
                                voidItem.setEnabled(false);
                                salesItemsList.clear();
                                System.out.println("Size: " + salesItemsList.size());
                                selectedProduct = null;
                                POSController.this.productCode.setText("-----");
                                POSController.this.productLine.setText("-----");
                                description.setText("-----");
                                description.setToolTipText(description.getText());
                                quantityOnHand.setText("NA");
                                sellingPrice.setText("NA");
                                addItem.setEnabled(false);
                                addProduct.setText("");
                            } else {
                                JOptionPane.showMessageDialog(view, "Cannot process transaction properly during inserting sold items", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                }
            }
        });
    }

    private void getProduct() {
        if (selectedProduct == null) {
            JOptionPane.showMessageDialog(view, "No product on list.", "Error.", JOptionPane.ERROR_MESSAGE);
            this.productCode.setText("-----");
            this.productLine.setText("-----");
            description.setText("-----");
            description.setToolTipText(description.getText());
            quantityOnHand.setText("NA");
            sellingPrice.setText("NA");
            addItem.setEnabled(false);
            selectedProduct = null;
        } else {
            addItem.setEnabled(true);
            ProductLine productLine = model.getProductLine(selectedProduct.getProductLineID());

            this.productCode.setText(selectedProduct.getAddmotoCode());
            this.productLine.setText(productLine.getProductLineName());
            description.setText((selectedProduct.getCharacteristics()
                    + " " + selectedProduct.getDescription()
                    + " " + selectedProduct.getMotors()).trim());
            description.setToolTipText(description.getText());
            quantityOnHand.setText(String.valueOf(selectedProduct.getCurrentQuantity()));
            sellingPrice.setText("PhP " + Formatter.format(selectedProduct.getSellingPrice()));
            addItem.setEnabled(true);
        }
    }

    private void addItemToTable() {
        int count = Integer.parseInt(JOptionPane.showInputDialog(null, "Quantity:"));
        
        if (count <= 0) {
            JOptionPane.showMessageDialog(view, "Please enter a valid quantity value", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
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

                int quantity = count;

                if (isAlreadyListed) {
                    SalesItems itemUpdate = getItem(itemIdx);
                    int newQuantity = itemUpdate.getQuantity() + quantity;
                    itemUpdate.setQuantity(newQuantity);
                    itemUpdate.setExtPrice(newQuantity * itemUpdate.getSellingPrice());
                    updateItem(itemUpdate, itemIdx);

                    tableModel.setValueAt(itemUpdate.getQuantity(), itemIdx, 2);
                    tableModel.setValueAt(Formatter.format(itemUpdate.getExtPrice()), itemIdx, 4);
                } else {
                    ProductLine productLine = model.getProductLine(product.getProductLineID());
                    SalesItems salesItems = new SalesItems(product.getProductID(), product.getAddmotoCode(),
                            productLine.getProductLineName() + " " + product.getDescription() + " " + product.getCharacteristics(), quantity,
                            product.getSellingPrice(), product.getSellingPrice() * quantity, product.getUnitPrice());
                    addItem(salesItems);

                    Object[] row = {salesItems.getItemCode(), salesItems.getItemName(), salesItems.getQuantity(),
                        Formatter.format(salesItems.getSellingPrice()), Formatter.format(salesItems.getExtPrice())};
                    tableModel.addRow(row);
                }
                if (salesItemsList.isEmpty()) {
                    paySales.setEnabled(false);
                } else {
                    paySales.setEnabled(true);
                }
                setFields();
                addProduct.setText("");
                addProduct.requestFocusInWindow();
                this.productCode.setText("-----");
                System.out.println("Product Code Set to Nothing");
                this.productLine.setText("-----");
                description.setText("-----");
                description.setToolTipText(description.getText());
                quantityOnHand.setText("NA");
                sellingPrice.setText("NA");
                addItem.setEnabled(false);
                selectedProduct = null;
                populate();
            }
        }
    }

    private String getContents() {
        return addProduct.getText();
    }

    private void setFields() {
        subtotalValue.setText("PhP   " + getSubtotalPrice());
        taxValue.setText("PhP   " + getTaxablePrice());
        totalValue.setText("PhP   " + getTotalPriceString());
        payValue.setText("PhP   " + getTotalPriceString());
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
