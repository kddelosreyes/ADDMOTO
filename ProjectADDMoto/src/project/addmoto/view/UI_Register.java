/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.addmoto.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import project.addmoto.data.SellerAccount;
import project.addmoto.database.Query;
/**
 *
 * @author User
 */
public class UI_Register extends javax.swing.JFrame {

    private Query query;
    private JFrame parent;

    /**
     * Creates new form UI_Register
     */
    
    private String getFirstName() {
        return UILogin_firstNameTextField.getText();
    }
    
    private String getLastName() {
        return UILogin_lastNameTextField.getText();
    }
    
    private String getUsername() {
        return UILogin_usernameTextField.getText();
    }
    
    private String getPassword() {
        return new String(UILogin_passwordPasswordField.getPassword());
    }
    
    public UI_Register(JFrame parent, Query query) {
        this.parent = parent;
        this.query = query;
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

        UILogin_jLabel_username = new javax.swing.JLabel();
        UILogin_usernameTextField = new javax.swing.JTextField();
        UILogin_jLabel_password = new javax.swing.JLabel();
        UILogin_passwordPasswordField = new javax.swing.JPasswordField();
        UILogin_createButton = new javax.swing.JButton();
        UILogin_jLabel_username1 = new javax.swing.JLabel();
        UILogin_firstNameTextField = new javax.swing.JTextField();
        UILogin_jLabel_username2 = new javax.swing.JLabel();
        UILogin_lastNameTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        UILogin_jLabel_username.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        UILogin_jLabel_username.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        UILogin_jLabel_username.setText("Username:");

        UILogin_usernameTextField.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        UILogin_usernameTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        UILogin_usernameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UILogin_usernameTextFieldActionPerformed(evt);
            }
        });

        UILogin_jLabel_password.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        UILogin_jLabel_password.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        UILogin_jLabel_password.setText("Password:");

        UILogin_passwordPasswordField.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        UILogin_passwordPasswordField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        UILogin_createButton.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        UILogin_createButton.setText("Create");
        UILogin_createButton.setToolTipText("");
        UILogin_createButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UILogin_createButtonActionPerformed(evt);
            }
        });

        UILogin_jLabel_username1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        UILogin_jLabel_username1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        UILogin_jLabel_username1.setText("First Name:");

        UILogin_firstNameTextField.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        UILogin_firstNameTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        UILogin_firstNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UILogin_firstNameTextFieldActionPerformed(evt);
            }
        });

        UILogin_jLabel_username2.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        UILogin_jLabel_username2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        UILogin_jLabel_username2.setText("Last Name:");

        UILogin_lastNameTextField.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        UILogin_lastNameTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        UILogin_lastNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UILogin_lastNameTextFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(UILogin_createButton, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(UILogin_jLabel_username, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(UILogin_jLabel_password, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(UILogin_jLabel_username1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(UILogin_jLabel_username2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(59, 59, 59)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(UILogin_passwordPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(UILogin_usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(UILogin_firstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(UILogin_lastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UILogin_firstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UILogin_jLabel_username1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UILogin_jLabel_username2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UILogin_lastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UILogin_jLabel_username, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UILogin_usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UILogin_passwordPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UILogin_jLabel_password, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(UILogin_createButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void UILogin_createButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UILogin_createButtonActionPerformed
        // TODO add your handling code here:
        String firstname = getFirstName();
        String lastname = getLastName();
        String username = getUsername();
        String password = getPassword();
        int checkValue = query.createSellerAccount(firstname, lastname, username, password);
        SellerAccount sellerAccount = query.getSellerAcount(username, password);

        if(checkValue == 1){
            JOptionPane.showMessageDialog(rootPane, "You successfully Registered", "Registration", JOptionPane.INFORMATION_MESSAGE);
            parent.setVisible(true);
            dispose();
           
        }
        else{
            JOptionPane.showMessageDialog(UI_Register.this, "Username exist!", "Registration Failed", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_UILogin_createButtonActionPerformed

    private void UILogin_firstNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UILogin_firstNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UILogin_firstNameTextFieldActionPerformed

    private void UILogin_usernameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UILogin_usernameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UILogin_usernameTextFieldActionPerformed

    private void UILogin_lastNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UILogin_lastNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UILogin_lastNameTextFieldActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton UILogin_createButton;
    private javax.swing.JTextField UILogin_firstNameTextField;
    private javax.swing.JLabel UILogin_jLabel_password;
    private javax.swing.JLabel UILogin_jLabel_username;
    private javax.swing.JLabel UILogin_jLabel_username1;
    private javax.swing.JLabel UILogin_jLabel_username2;
    private javax.swing.JTextField UILogin_lastNameTextField;
    private javax.swing.JPasswordField UILogin_passwordPasswordField;
    private javax.swing.JTextField UILogin_usernameTextField;
    // End of variables declaration//GEN-END:variables
}
