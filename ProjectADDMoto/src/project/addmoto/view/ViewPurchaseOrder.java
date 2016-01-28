/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.addmoto.view;

import javax.swing.JLabel;
import javax.swing.JTable;
import project.addmoto.custom.RoundedBorder;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class ViewPurchaseOrder extends javax.swing.JPanel {

    /**
     * Creates new form ViewPurchaseOrder
     */
    public ViewPurchaseOrder() {
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

        vpLabel = new javax.swing.JLabel();
        vpExport = new javax.swing.JLabel();
        vpReceived = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        vpPending = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        vpDeliveryDate = new javax.swing.JLabel();
        vpDateIssued = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        vpSupplier = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        vpOrderTable = new javax.swing.JTable();
        vpItems = new javax.swing.JLabel();
        vpTotal = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();

        vpLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        vpLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/addmoto/icons/purchaseorder.png"))); // NOI18N
        vpLabel.setText("PO#10000");

        vpExport.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        vpExport.setForeground(new java.awt.Color(0, 102, 255));
        vpExport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/addmoto/icons/po_export.png"))); // NOI18N
        vpExport.setText("Export");

        vpReceived.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        vpReceived.setForeground(new java.awt.Color(0, 102, 255));
        vpReceived.setText("  Received  ");
        vpReceived.setOpaque(true);

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        vpPending.setBackground(new java.awt.Color(255, 204, 51));
        vpPending.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        vpPending.setForeground(new java.awt.Color(255, 255, 255));
        vpPending.setText("  Pending  ");
        vpPending.setBorder(new RoundedBorder(10));
        vpPending.setOpaque(true);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel7.setText("Date Issued:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel8.setText("Target Delivery Date:");

        vpDeliveryDate.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        vpDeliveryDate.setText("Dec 01, 2016");

        vpDateIssued.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        vpDateIssued.setText("Dec 01, 2016, 23:33 AM");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel11.setText("Supplier:");

        vpSupplier.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        vpSupplier.setText("ADD Moto");

        vpOrderTable.setAutoCreateRowSorter(true);
        vpOrderTable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        vpOrderTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item Name", "Unit Price (PhP)", "Qty", "Subtotal Price (PhP)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        vpOrderTable.setRowHeight(22);
        vpOrderTable.setShowHorizontalLines(false);
        vpOrderTable.setShowVerticalLines(false);
        vpOrderTable.getTableHeader().setResizingAllowed(false);
        vpOrderTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(vpOrderTable);
        if (vpOrderTable.getColumnModel().getColumnCount() > 0) {
            vpOrderTable.getColumnModel().getColumn(1).setMinWidth(130);
            vpOrderTable.getColumnModel().getColumn(1).setPreferredWidth(130);
            vpOrderTable.getColumnModel().getColumn(1).setMaxWidth(130);
            vpOrderTable.getColumnModel().getColumn(2).setMinWidth(40);
            vpOrderTable.getColumnModel().getColumn(2).setPreferredWidth(40);
            vpOrderTable.getColumnModel().getColumn(2).setMaxWidth(40);
            vpOrderTable.getColumnModel().getColumn(3).setMinWidth(130);
            vpOrderTable.getColumnModel().getColumn(3).setPreferredWidth(130);
            vpOrderTable.getColumnModel().getColumn(3).setMaxWidth(130);
        }

        vpItems.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        vpItems.setForeground(new java.awt.Color(102, 102, 102));
        vpItems.setText("n items");

        vpTotal.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        vpTotal.setForeground(new java.awt.Color(102, 102, 102));
        vpTotal.setText("PhP 100.000");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setText("Total:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(vpLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(vpExport)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(vpPending)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(vpReceived))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vpSupplier)
                        .addGap(0, 503, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(vpItems)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vpTotal))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vpDateIssued)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vpDeliveryDate)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(vpLabel)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(vpExport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(vpReceived, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(vpPending, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(vpDeliveryDate)
                    .addComponent(vpDateIssued))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(vpSupplier))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vpItems, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vpTotal)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel vpDateIssued;
    private javax.swing.JLabel vpDeliveryDate;
    private javax.swing.JLabel vpExport;
    private javax.swing.JLabel vpItems;
    private javax.swing.JLabel vpLabel;
    private javax.swing.JTable vpOrderTable;
    private javax.swing.JLabel vpPending;
    private javax.swing.JLabel vpReceived;
    private javax.swing.JLabel vpSupplier;
    private javax.swing.JLabel vpTotal;
    // End of variables declaration//GEN-END:variables

    public JLabel getVpDateIssued() {
        return vpDateIssued;
    }

    public JLabel getVpDeliveryDate() {
        return vpDeliveryDate;
    }

    public JLabel getVpExport() {
        return vpExport;
    }

    public JLabel getVpItems() {
        return vpItems;
    }

    public JLabel getVpLabel() {
        return vpLabel;
    }

    public JTable getVpOrderTable() {
        return vpOrderTable;
    }

    public JLabel getVpPending() {
        return vpPending;
    }

    public JLabel getVpReceived() {
        return vpReceived;
    }

    public JLabel getVpSupplier() {
        return vpSupplier;
    }

    public JLabel getVpTotal() {
        return vpTotal;
    }
}