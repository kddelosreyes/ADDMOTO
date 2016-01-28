/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.addmoto.view;

import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import org.jdesktop.swingx.JXSearchField;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class NewPurchaseOrder extends javax.swing.JPanel {

    /**
     * Creates new form NewPurchaseOrder
     */
    public NewPurchaseOrder() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        npoSupplierMenu = new javax.swing.JPopupMenu();
        npoAdd = new javax.swing.JMenuItem();
        npoView = new javax.swing.JMenuItem();
        npoProductsMenu = new javax.swing.JPopupMenu();
        npoRemove = new javax.swing.JMenuItem();
        npoChangeQty = new javax.swing.JMenuItem();
        npoPONumber = new javax.swing.JLabel();
        npoTabbedPane = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        npoSupplierProducts = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        npoProductOrders = new javax.swing.JTable();
        npoTotal = new javax.swing.JLabel();
        npoItems = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        npoSuppliers = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        npoDate = new com.toedter.calendar.JDateChooser();
        npoSearch = new org.jdesktop.swingx.JXSearchField();
        npoDone = new javax.swing.JButton();

        npoAdd.setText("Add to Order");
        npoSupplierMenu.add(npoAdd);

        npoView.setText("View Product Details");
        npoSupplierMenu.add(npoView);

        npoRemove.setText("Remove from Order");
        npoProductsMenu.add(npoRemove);

        npoChangeQty.setText("Change Quantity");
        npoProductsMenu.add(npoChangeQty);

        npoPONumber.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        npoPONumber.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/addmoto/icons/purchaseorder.png"))); // NOI18N
        npoPONumber.setText("PO#10000");

        npoTabbedPane.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N

        npoSupplierProducts.setAutoCreateRowSorter(true);
        npoSupplierProducts.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        npoSupplierProducts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Supplier Code", "ADD Moto Code", "Product", "Qty", "Unit Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        npoSupplierProducts.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        npoSupplierProducts.getTableHeader().setResizingAllowed(false);
        npoSupplierProducts.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(npoSupplierProducts);
        if (npoSupplierProducts.getColumnModel().getColumnCount() > 0) {
            npoSupplierProducts.getColumnModel().getColumn(0).setMinWidth(60);
            npoSupplierProducts.getColumnModel().getColumn(0).setPreferredWidth(60);
            npoSupplierProducts.getColumnModel().getColumn(0).setMaxWidth(60);
            npoSupplierProducts.getColumnModel().getColumn(1).setMinWidth(100);
            npoSupplierProducts.getColumnModel().getColumn(1).setPreferredWidth(100);
            npoSupplierProducts.getColumnModel().getColumn(1).setMaxWidth(100);
            npoSupplierProducts.getColumnModel().getColumn(2).setMinWidth(100);
            npoSupplierProducts.getColumnModel().getColumn(2).setPreferredWidth(100);
            npoSupplierProducts.getColumnModel().getColumn(2).setMaxWidth(100);
            npoSupplierProducts.getColumnModel().getColumn(4).setMinWidth(40);
            npoSupplierProducts.getColumnModel().getColumn(4).setPreferredWidth(40);
            npoSupplierProducts.getColumnModel().getColumn(4).setMaxWidth(40);
            npoSupplierProducts.getColumnModel().getColumn(5).setMinWidth(100);
            npoSupplierProducts.getColumnModel().getColumn(5).setPreferredWidth(100);
            npoSupplierProducts.getColumnModel().getColumn(5).setMaxWidth(100);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 801, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                .addContainerGap())
        );

        npoTabbedPane.addTab("Supplier Products", jPanel2);

        npoProductOrders.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        npoProductOrders.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product ID", "Supplier Code", "Product", "Qty", "Unit Price", "Subtotal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        npoProductOrders.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        npoProductOrders.getTableHeader().setResizingAllowed(false);
        npoProductOrders.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(npoProductOrders);
        if (npoProductOrders.getColumnModel().getColumnCount() > 0) {
            npoProductOrders.getColumnModel().getColumn(0).setMinWidth(0);
            npoProductOrders.getColumnModel().getColumn(0).setPreferredWidth(0);
            npoProductOrders.getColumnModel().getColumn(0).setMaxWidth(0);
            npoProductOrders.getColumnModel().getColumn(1).setMinWidth(120);
            npoProductOrders.getColumnModel().getColumn(1).setPreferredWidth(120);
            npoProductOrders.getColumnModel().getColumn(1).setMaxWidth(120);
            npoProductOrders.getColumnModel().getColumn(3).setMinWidth(60);
            npoProductOrders.getColumnModel().getColumn(3).setPreferredWidth(60);
            npoProductOrders.getColumnModel().getColumn(3).setMaxWidth(60);
            npoProductOrders.getColumnModel().getColumn(4).setMinWidth(130);
            npoProductOrders.getColumnModel().getColumn(4).setPreferredWidth(130);
            npoProductOrders.getColumnModel().getColumn(4).setMaxWidth(130);
            npoProductOrders.getColumnModel().getColumn(5).setMinWidth(130);
            npoProductOrders.getColumnModel().getColumn(5).setPreferredWidth(130);
            npoProductOrders.getColumnModel().getColumn(5).setMaxWidth(130);
        }

        npoTotal.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        npoTotal.setForeground(new java.awt.Color(51, 51, 51));
        npoTotal.setText("PhP 0.00");

        npoItems.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        npoItems.setForeground(new java.awt.Color(102, 102, 102));
        npoItems.setText("0 items");
        npoItems.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Total:");
        jLabel6.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 801, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(npoItems)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(npoTotal)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(npoItems, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(npoTotal)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        npoTabbedPane.addTab("Product Orders", jPanel1);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setBackground(new java.awt.Color(0, 102, 153));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("   Purchase Order Details");
        jLabel3.setOpaque(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel1.setText("Supplier Name: ");

        npoSuppliers.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel2.setText("Target Delivery Date:");

        npoDate.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(npoSuppliers, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(npoDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(npoSuppliers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(npoDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        npoSearch.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        npoDone.setBackground(new java.awt.Color(0, 255, 102));
        npoDone.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        npoDone.setForeground(new java.awt.Color(255, 255, 255));
        npoDone.setMnemonic('D');
        npoDone.setText("Place Purchase Order");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(npoPONumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(npoTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(178, 178, 178)
                        .addComponent(npoSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 647, Short.MAX_VALUE)
                        .addComponent(npoDone)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(npoPONumber)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(npoSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(npoTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(npoDone, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenuItem npoAdd;
    private javax.swing.JMenuItem npoChangeQty;
    private com.toedter.calendar.JDateChooser npoDate;
    private javax.swing.JButton npoDone;
    private javax.swing.JLabel npoItems;
    private javax.swing.JLabel npoPONumber;
    private javax.swing.JTable npoProductOrders;
    private javax.swing.JPopupMenu npoProductsMenu;
    private javax.swing.JMenuItem npoRemove;
    private org.jdesktop.swingx.JXSearchField npoSearch;
    private javax.swing.JPopupMenu npoSupplierMenu;
    private javax.swing.JTable npoSupplierProducts;
    private javax.swing.JComboBox<String> npoSuppliers;
    private javax.swing.JTabbedPane npoTabbedPane;
    private javax.swing.JLabel npoTotal;
    private javax.swing.JMenuItem npoView;
    // End of variables declaration//GEN-END:variables

    public JMenuItem getNpoAdd() {
        return npoAdd;
    }

    public JMenuItem getNpoChangeQty() {
        return npoChangeQty;
    }

    public JDateChooser getNpoDate() {
        return npoDate;
    }

    public JLabel getNpoItems() {
        return npoItems;
    }

    public JLabel getNpoPONumber() {
        return npoPONumber;
    }

    public JTable getNpoProductOrders() {
        return npoProductOrders;
    }

    public JPopupMenu getNpoProductsMenu() {
        return npoProductsMenu;
    }

    public JMenuItem getNpoRemove() {
        return npoRemove;
    }

    public JXSearchField getNpoSearch() {
        return npoSearch;
    }

    public JPopupMenu getNpoSupplierMenu() {
        return npoSupplierMenu;
    }

    public JTable getNpoSupplierProducts() {
        return npoSupplierProducts;
    }

    public JComboBox<String> getNpoSuppliers() {
        return npoSuppliers;
    }

    public JLabel getNpoTotal() {
        return npoTotal;
    }

    public JMenuItem getNpoView() {
        return npoView;
    }

    public JTabbedPane getNpoTabbedPane() {
        return npoTabbedPane;
    }

    public JButton getNpoDone() {
        return npoDone;
    }
}