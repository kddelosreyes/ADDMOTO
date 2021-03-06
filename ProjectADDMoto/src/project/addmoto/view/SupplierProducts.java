/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.addmoto.view;

import javax.swing.JLabel;
import javax.swing.JTable;
import org.jdesktop.swingx.JXSearchField;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class SupplierProducts extends javax.swing.JPanel {

    /**
     * Creates new form SupplierProducts
     */
    public SupplierProducts() {
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

        supplierProductsTitle = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        supplierProductsTable = new javax.swing.JTable();
        searchField = new org.jdesktop.swingx.JXSearchField();

        supplierProductsTitle.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        supplierProductsTitle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/addmoto/icons/supplierproducts.png"))); // NOI18N
        supplierProductsTitle.setText("Products - ");

        supplierProductsTable.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        supplierProductsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Supplier Code", "ADD Moto Code", "Product Line", "Description", "Qty", "Unit Price", "Selling Price", "Status", "Product ID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Short.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        supplierProductsTable.setRowHeight(20);
        supplierProductsTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        supplierProductsTable.getTableHeader().setResizingAllowed(false);
        supplierProductsTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(supplierProductsTable);
        if (supplierProductsTable.getColumnModel().getColumnCount() > 0) {
            supplierProductsTable.getColumnModel().getColumn(0).setMinWidth(80);
            supplierProductsTable.getColumnModel().getColumn(0).setPreferredWidth(80);
            supplierProductsTable.getColumnModel().getColumn(0).setMaxWidth(80);
            supplierProductsTable.getColumnModel().getColumn(1).setMinWidth(80);
            supplierProductsTable.getColumnModel().getColumn(1).setPreferredWidth(80);
            supplierProductsTable.getColumnModel().getColumn(1).setMaxWidth(80);
            supplierProductsTable.getColumnModel().getColumn(2).setMinWidth(140);
            supplierProductsTable.getColumnModel().getColumn(2).setPreferredWidth(140);
            supplierProductsTable.getColumnModel().getColumn(2).setMaxWidth(140);
            supplierProductsTable.getColumnModel().getColumn(4).setMinWidth(50);
            supplierProductsTable.getColumnModel().getColumn(4).setPreferredWidth(50);
            supplierProductsTable.getColumnModel().getColumn(4).setMaxWidth(50);
            supplierProductsTable.getColumnModel().getColumn(5).setMinWidth(90);
            supplierProductsTable.getColumnModel().getColumn(5).setPreferredWidth(90);
            supplierProductsTable.getColumnModel().getColumn(5).setMaxWidth(90);
            supplierProductsTable.getColumnModel().getColumn(6).setMinWidth(90);
            supplierProductsTable.getColumnModel().getColumn(6).setPreferredWidth(90);
            supplierProductsTable.getColumnModel().getColumn(6).setMaxWidth(90);
            supplierProductsTable.getColumnModel().getColumn(7).setMinWidth(60);
            supplierProductsTable.getColumnModel().getColumn(7).setPreferredWidth(60);
            supplierProductsTable.getColumnModel().getColumn(7).setMaxWidth(60);
            supplierProductsTable.getColumnModel().getColumn(8).setMinWidth(0);
            supplierProductsTable.getColumnModel().getColumn(8).setPreferredWidth(0);
            supplierProductsTable.getColumnModel().getColumn(8).setMaxWidth(0);
        }

        searchField.setAutoscrolls(false);
        searchField.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(supplierProductsTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 248, Short.MAX_VALUE)
                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(supplierProductsTitle)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    public JXSearchField getSearchField() {
        return searchField;
    }

    public JTable getSupplierProductsTable() {
        return supplierProductsTable;
    }

    public JLabel getSupplierProductsTitle() {
        return supplierProductsTitle;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.swingx.JXSearchField searchField;
    private javax.swing.JTable supplierProductsTable;
    private javax.swing.JLabel supplierProductsTitle;
    // End of variables declaration//GEN-END:variables
}
