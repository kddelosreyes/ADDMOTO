/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.addmoto.controller;

import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.JXSearchField;
import project.addmoto.data.Order;
import project.addmoto.data.ProductOrder;
import project.addmoto.data.SellerAccount;
import project.addmoto.data.Supplier;
import project.addmoto.data.SupplierProduct;
import project.addmoto.model.NewPurchaseOrderModel;
import project.addmoto.mvc.Controller;
import project.addmoto.utilities.Formatter;
import project.addmoto.view.NewPurchaseOrder;

/**
 *
 * @author Kim Howel delos Reyes
 */
public final class NewPurchaseOrderController extends Controller {

    private final NewPurchaseOrder view;
    private final NewPurchaseOrderModel model;
    private final SellerAccount sellerAccount;

    private HashMap<String, Integer> supplierMap;

    private final JComboBox npoSupplier;
    private final JDateChooser npoDate;
    private final JLabel npoPONumber;
    private final JXSearchField npoSearch;
    private final JTable npoSupplierProducts;
    private final JTable npoProductOrders;
    private final JLabel npoItems;
    private final JLabel npoTotal;
    private final JTabbedPane npoTabbedPane;
    private final JButton npoDone;

    private final JPopupMenu supplierMenu;
    private final JMenuItem npoAdd;
    private final JMenuItem npoView;

    private final JPopupMenu productsMenu;
    private final JMenuItem npoRemove;
    private final JMenuItem npoChangeQty;

    private final DefaultTableModel tableModelSP;
    private final DefaultTableModel tableModelPO;

    private int selectedRow;
    private String supplierName;

    private ArrayList<ProductOrder> orderList;

    private ProductOrder selectedOrder;
    private ProductOrder selectedChange;
    
    private boolean isDone = false;

    public NewPurchaseOrderController(final Connection connection, final SellerAccount sellerAccount) {
        this.sellerAccount = sellerAccount;
        view = new NewPurchaseOrder();
        model = new NewPurchaseOrderModel(connection);

        orderList = new ArrayList<>();

        npoSupplier = view.getNpoSuppliers();
        npoDate = view.getNpoDate();
        npoPONumber = view.getNpoPONumber();
        npoSearch = view.getNpoSearch();
        npoSupplierProducts = view.getNpoSupplierProducts();
        npoProductOrders = view.getNpoProductOrders();
        npoItems = view.getNpoItems();
        npoTotal = view.getNpoTotal();
        npoTabbedPane = view.getNpoTabbedPane();
        npoDone = view.getNpoDone();

        npoDate.getJCalendar().setMinSelectableDate(new Date());

        supplierMenu = view.getNpoSupplierMenu();
        npoAdd = view.getNpoAdd();
        npoView = view.getNpoView();
        productsMenu = view.getNpoProductsMenu();
        npoRemove = view.getNpoRemove();
        npoChangeQty = view.getNpoChangeQty();

        npoPONumber.setText("PO#" + (model.getMaxOrderID() + 1));

        tableModelSP = (DefaultTableModel) npoSupplierProducts.getModel();
        tableModelPO = (DefaultTableModel) npoProductOrders.getModel();

        populate();

        setListeners();

        JOptionPane.showOptionDialog(
                null,
                view,
                "ADD Moto - Motorcycle Parts and Accessories",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                new Object[]{},
                null
        );
    }

    private void populate() {
        ArrayList<Supplier> suppliers = model.getSuppliers();
        supplierMap = new HashMap<String, Integer>();

        npoSupplier.addItem("");
        for (Supplier supplier : suppliers) {
            npoSupplier.addItem(supplier.getSupplierName());
            supplierMap.put(supplier.getSupplierName(), supplier.getSupplierID());
        }
    }

    private int mapSupplierID(String supplierName) {
        return supplierMap.get(supplierName);
    }

    private void populateTable(int supplierID) {
        while (tableModelSP.getRowCount() > 0) {
            tableModelSP.removeRow(0);
        }

        ArrayList<SupplierProduct> productList = model.getSupplierProducts(supplierID);

        if (productList.isEmpty()) {
            JOptionPane.showMessageDialog(
                    null,
                    "Supplier has no products on your database.",
                    "Warning",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            for (SupplierProduct sProduct : productList) {
                tableModelSP.addRow(
                        new Object[]{
                            sProduct.getProductID(),
                            sProduct.getSupplierCode(),
                            sProduct.getAddmotoCode(),
                            (sProduct.getProductLine() + " " + sProduct.getDescription()).replaceAll("\\s+", " ").trim(),
                            sProduct.getQuantity(),
                            "PhP " + Formatter.format(sProduct.getUnitPrice())
                        }
                );
            }
        }
    }

    @Override
    public void setListeners() {
        npoDone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tableModelPO.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(
                            null,
                            "You may not have added a product on your order list.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    boolean isValid = true;
                    try {
                        DateFormat.getDateInstance().format(npoDate.getDate());
                    } catch (Exception ex) {
                        isValid = false;
                    }

                    if (isValid) {
                        System.out.println("ORDER LINES");
                        for (ProductOrder pOrder : orderList) {
                            pOrder.setOrderID(Integer.parseInt(npoPONumber.getText().substring(3)));
                            System.out.println(pOrder.getProductID() + ", "
                                    + pOrder.getOrderID() + ", "
                                    + pOrder.getQuantity() + ", "
                                    + pOrder.getUnitPrice() + ", "
                                    + pOrder.getSubtotal());
                        }
                        System.out.println("ORDER DETAILS");
                        Order order = new Order(
                                npoPONumber.getText(),
                                Formatter.formatDate(new Date()),
                                Double.parseDouble(npoTotal.getText().split(" ")[1]),
                                mapSupplierID((String) npoSupplier.getSelectedItem()),
                                false,
                                sellerAccount.getSellerID(),
                                "PENDING",
                                Formatter.formatOrderDate(npoDate.getDate())
                        );
                        System.out.println(order);
                        
                        int chosen = JOptionPane.showConfirmDialog(
                                null,
                                "Do you want to finalize purchase order?",
                                "Confirm",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE
                        );
                        
                        if(chosen == JOptionPane.YES_OPTION) {
                            int rowOrder = model.insertOrder(order);
                            if(rowOrder == 0) {
                                System.out.println("There is some kind of error while inserting order.");
                            }
                            int rowOrderLine = model.insertProductOrders(orderList);
                            if(rowOrderLine == 0) {
                                System.out.println("There is some kind of error while inserting products.");
                            }
                            
                            if(rowOrder != 0 && rowOrderLine != 0) {
                                JOptionPane.showMessageDialog(
                                        null,
                                        "New Purchase Order recorded.",
                                        "Success",
                                        JOptionPane.INFORMATION_MESSAGE
                                );
                                isDone = true;
                                npoPONumber.setText("PO#" + model.getMaxOrderID());
                                npoDate.setDate(null);
                                npoSupplier.setSelectedIndex(0);
                                isDone = false;
                                while(tableModelSP.getRowCount() > 0) {
                                    tableModelSP.removeRow(0);
                                }
                                while(tableModelPO.getRowCount() > 0) {
                                    tableModelPO.removeRow(0);
                                }
                                npoItems.setText("0 items");
                                npoTotal.setText("PhP 0.00");
                                npoTabbedPane.setSelectedIndex(0);
                            } else {
                                JOptionPane.showMessageDialog(
                                        null,
                                        "There must be some kind of error.",
                                        "Error",
                                        JOptionPane.ERROR_MESSAGE
                                );
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(
                                null,
                                "Please set your target order delivery date.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        npoAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isIncluded = false;
                for (ProductOrder po : orderList) {
                    if (po.getProductID() == selectedOrder.getProductID()) {
                        isIncluded = true;
                        break;
                    }
                }

                if (isIncluded) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Chosen item is already on your order list.",
                            "Warning",
                            JOptionPane.WARNING_MESSAGE
                    );
                } else {
                    String input = JOptionPane.showInputDialog(
                            null,
                            "How many would you like to order?",
                            "Quantity",
                            JOptionPane.QUESTION_MESSAGE);
                    boolean isValid = true;
                    try {
                        Integer.parseInt(input);
                    } catch (Exception ex) {
                        isValid = false;
                    }
                    if (isValid) {
                        int value = Integer.parseInt(input);
                        if (value < 1) {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Quantity must not be less than 1.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        } else {
                            selectedOrder.setQuantity(value);
                            selectedOrder.setSubtotal(selectedOrder.getUnitPrice() * value);
                            orderList.add(selectedOrder);
                            System.out.println(orderList.toString());
                            tableModelPO.addRow(
                                    new Object[]{
                                        selectedOrder.getProductID(),
                                        selectedOrder.getSupplierCode(),
                                        selectedOrder.getProduct(),
                                        selectedOrder.getQuantity(),
                                        "PhP " + Formatter.format(selectedOrder.getUnitPrice()),
                                        "PhP " + Formatter.format(selectedOrder.getSubtotal())
                                    }
                            );
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Order list updated with new product added.",
                                    "",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
                            npoTabbedPane.setSelectedIndex(1);
                            setValues();
                        }
                    } else {
                        JOptionPane.showMessageDialog(
                                null,
                                "You have entered an invalid number format.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        npoView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        npoSupplier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tableModelPO.getRowCount() != 0) {
                    if (!NewPurchaseOrderController.this.supplierName.equalsIgnoreCase((String) npoSupplier.getSelectedItem()) && !isDone) {
                        int option = JOptionPane.showConfirmDialog(
                                null,
                                "Changing Supplier will remove all added items on your order list. Do you wish you continue?",
                                "Confirm",
                                JOptionPane.YES_NO_OPTION
                        );
                        if (option == JOptionPane.YES_OPTION) {
                            orderList.clear();
                            System.out.println(orderList.toString());
                            while (tableModelPO.getRowCount() > 0) {
                                tableModelPO.removeRow(0);
                            }
                            setValues();
                        }
                    }
                }
                String supplierName = (String) npoSupplier.getSelectedItem();
                NewPurchaseOrderController.this.supplierName = supplierName;
                if (!supplierName.trim().equals("")) {
                    populateTable(mapSupplierID(supplierName));
                } else {
                    while (tableModelSP.getRowCount() > 0) {
                        tableModelSP.removeRow(0);
                    }
                }
            }
        });

        npoSupplierProducts.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    int row = npoSupplierProducts.rowAtPoint(e.getPoint());
                    selectedRow = row;

                    selectedOrder = new ProductOrder(
                            (int) tableModelSP.getValueAt(row, 0),
                            (String) tableModelSP.getValueAt(row, 1),
                            (String) tableModelSP.getValueAt(row, 3),
                            Double.parseDouble((((String) tableModelSP.getValueAt(row, 5)).split(" ")[1])));

                    npoSupplierProducts.clearSelection();
                    npoSupplierProducts.addRowSelectionInterval(row, row);
                    supplierMenu.show(npoSupplierProducts, (int) e.getPoint().getX(), (int) e.getPoint().getY());
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
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        npoProductOrders.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    int row = npoProductOrders.rowAtPoint(e.getPoint());

                    int productID_ = (int) tableModelPO.getValueAt(row, 0);

                    List<ProductOrder> productWithID = orderList.stream()
                            .filter((p) -> p.getProductID() == productID_)
                            .collect(Collectors.toList());
                    selectedChange = productWithID.get(0);
                    System.out.println("Item: " + selectedChange.toString());

                    npoProductOrders.clearSelection();
                    npoProductOrders.addRowSelectionInterval(row, row);
                    productsMenu.show(npoProductOrders, (int) e.getPoint().getX(), (int) e.getPoint().getY());
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
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        npoRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure you want to remove this item from your orders?",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

                if (option == JOptionPane.YES_OPTION) {
                    for (ProductOrder po : orderList) {
                        if (po.getProductID() == selectedChange.getProductID()) {
                            orderList.remove(po);
                            break;
                        }
                    }

                    while (tableModelPO.getRowCount() > 0) {
                        tableModelPO.removeRow(0);
                    }
                    for (ProductOrder po : orderList) {
                        tableModelPO.addRow(
                                new Object[]{
                                    po.getProductID(),
                                    po.getSupplierCode(),
                                    po.getProduct(),
                                    po.getQuantity(),
                                    "PhP " + Formatter.format(po.getUnitPrice()),
                                    "PhP " + Formatter.format(po.getSubtotal())
                                }
                        );
                    }
                    setValues();
                    System.out.println("Order List after change:\n" + orderList.toString());
                }
            }
        });

        npoChangeQty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(
                        null,
                        "Enter new quantity for item:",
                        "Change Quantity",
                        JOptionPane.QUESTION_MESSAGE);
                boolean isValid = true;
                try {
                    Integer.parseInt(input);
                } catch (Exception ex) {
                    isValid = false;
                }

                if (isValid) {
                    int value = Integer.parseInt(input);
                    if (value < 1) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Quantity must not be less than 1.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        for (ProductOrder po : orderList) {
                            if (po.getProductID() == selectedChange.getProductID()) {
                                po.setQuantity(value);
                                po.setSubtotal(value * po.getUnitPrice());
                                break;
                            }
                        }

                        while (tableModelPO.getRowCount() > 0) {
                            tableModelPO.removeRow(0);
                        }
                        for (ProductOrder po : orderList) {
                            tableModelPO.addRow(
                                    new Object[]{
                                        po.getProductID(),
                                        po.getSupplierCode(),
                                        po.getProduct(),
                                        po.getQuantity(),
                                        "PhP " + Formatter.format(po.getUnitPrice()),
                                        "PhP " + Formatter.format(po.getSubtotal())
                                    }
                            );
                        }
                        setValues();
                    }
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "You have entered an invalid number format.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void setValues() {
        int totalQuantity = 0;
        double totalPrice = 0.0f;
        for (ProductOrder productOrder : orderList) {
            totalQuantity += productOrder.getQuantity();
            totalPrice += productOrder.getSubtotal();
        }
        npoItems.setText(totalQuantity + " items");
        npoTotal.setText("PhP " + Formatter.format(totalPrice));
    }
}
