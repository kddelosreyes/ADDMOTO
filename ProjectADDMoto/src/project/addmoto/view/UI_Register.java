/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.addmoto.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import project.addmoto.controller.RegistrationController;
import project.addmoto.data.SellerAccount;
import project.addmoto.database.Query;
/**
 *
 * @author User
 */
public class UI_Register extends javax.swing.JFrame {

    private Query query;
    private JFrame parent;
    private RegistrationController registrationController;

    /**
     * Creates new form UI_Register
     */
    
    private String getFirstName() {
        return UIRegister_firstName.getText();
    }
    
    private String getLastName() {
        return UIRegister_lastName.getText();
    }
    
    private String getUsername() {
        return UIRegister_userName.getText();
    }
    
    private String getPassword() {
        return new String(UIRegister_password.getPassword());
    }
    
    private String getConfirmPassword() {
        return new String(UIRegister_confirmPassword.getPassword());
    }
    
    public UI_Register(JFrame parent, Query query) {
        this.parent = parent;
        this.query = query;
        initComponents();
        this.setLocationRelativeTo(null);
        //PromptSupport.setPrompt(" Username", UIRegi);
    }
    
    public boolean isEmpty(String value) {
        return value.equals("");
    }
    
    public boolean areAllNotEmpty() {
        if(isEmpty(getFirstName()) || isEmpty(getLastName()) || isEmpty(getUsername())
                || isEmpty(getPassword()) || isEmpty(getConfirmPassword())) {
            return false;
        }
        return true;
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
        UIRegister_userName = new javax.swing.JTextField();
        UILogin_jLabel_password = new javax.swing.JLabel();
        UIRegister_password = new javax.swing.JPasswordField();
        UILogin_createButton = new javax.swing.JButton();
        UILogin_jLabel_username1 = new javax.swing.JLabel();
        UIRegister_firstName = new javax.swing.JTextField();
        UILogin_jLabel_username2 = new javax.swing.JLabel();
        UIRegister_lastName = new javax.swing.JTextField();
        UILogin_jLabel_password1 = new javax.swing.JLabel();
        UIRegister_confirmPassword = new javax.swing.JPasswordField();
        jSeparator1 = new javax.swing.JSeparator();
        UILogin_createButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("ADD Moto - Motorcycle Parts and Accessories");
        setResizable(false);

        UILogin_jLabel_username.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        UILogin_jLabel_username.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        UILogin_jLabel_username.setText("Username:");

        UIRegister_userName.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        UIRegister_userName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        UIRegister_userName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UIRegister_userNameActionPerformed(evt);
            }
        });

        UILogin_jLabel_password.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        UILogin_jLabel_password.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        UILogin_jLabel_password.setText("Password:");

        UIRegister_password.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        UIRegister_password.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        UILogin_createButton.setBackground(new java.awt.Color(0, 153, 0));
        UILogin_createButton.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        UILogin_createButton.setForeground(new java.awt.Color(255, 255, 255));
        UILogin_createButton.setMnemonic('C');
        UILogin_createButton.setText("Create User");
        UILogin_createButton.setToolTipText("");
        UILogin_createButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UILogin_createButtonActionPerformed(evt);
            }
        });

        UILogin_jLabel_username1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        UILogin_jLabel_username1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        UILogin_jLabel_username1.setText("First Name:");

        UIRegister_firstName.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        UIRegister_firstName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        UIRegister_firstName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UIRegister_firstNameActionPerformed(evt);
            }
        });

        UILogin_jLabel_username2.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        UILogin_jLabel_username2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        UILogin_jLabel_username2.setText("Last Name:");

        UIRegister_lastName.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        UIRegister_lastName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        UIRegister_lastName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UIRegister_lastNameActionPerformed(evt);
            }
        });

        UILogin_jLabel_password1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        UILogin_jLabel_password1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        UILogin_jLabel_password1.setText("Confirm:");

        UIRegister_confirmPassword.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        UIRegister_confirmPassword.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        UILogin_createButton1.setBackground(new java.awt.Color(204, 0, 0));
        UILogin_createButton1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        UILogin_createButton1.setForeground(new java.awt.Color(255, 255, 255));
        UILogin_createButton1.setMnemonic('A');
        UILogin_createButton1.setText("Cancel");
        UILogin_createButton1.setToolTipText("");
        UILogin_createButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UILogin_createButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Copyright © ADD Moto Motorcycle Parts and Accessories 2015.");
        jLabel3.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(UILogin_jLabel_password, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(UILogin_jLabel_username, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(UILogin_jLabel_username1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(UIRegister_firstName, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                            .addComponent(UIRegister_userName)
                            .addComponent(UIRegister_password))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(UILogin_jLabel_username2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(UILogin_jLabel_password1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(UIRegister_lastName, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(UIRegister_confirmPassword)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1)))
                .addContainerGap())
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 139, Short.MAX_VALUE)
                .addComponent(UILogin_createButton, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(UILogin_createButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(133, 133, 133))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(97, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UILogin_jLabel_username1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UIRegister_firstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UILogin_jLabel_username2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UIRegister_lastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UILogin_jLabel_username, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UIRegister_userName, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UILogin_jLabel_password, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UIRegister_password, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UILogin_jLabel_password1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UIRegister_confirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UILogin_createButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UILogin_createButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void UILogin_createButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UILogin_createButtonActionPerformed
        if(areAllNotEmpty()) {
            registrationController = new RegistrationController(
                    getFirstName(), getLastName(), getUsername(),
                    getPassword(), getConfirmPassword(), query);

            if(registrationController.isValidUser()) {
                if(registrationController.addUserToDatabase()) {
                    JOptionPane.showMessageDialog(rootPane, "You successfully Registered! You can now log in.", "Registration Success", JOptionPane.INFORMATION_MESSAGE);
                    parent.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(UI_Register.this, "Something is wrong! Please restart application.", "Registration Failed", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(UI_Register.this, "Your password does not match or username already exists!", "Registration Failed", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(UI_Register.this, "Some fields are left empty!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_UILogin_createButtonActionPerformed

    private void UIRegister_firstNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UIRegister_firstNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UIRegister_firstNameActionPerformed

    private void UIRegister_userNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UIRegister_userNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UIRegister_userNameActionPerformed

    private void UIRegister_lastNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UIRegister_lastNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UIRegister_lastNameActionPerformed

    private void UILogin_createButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UILogin_createButton1ActionPerformed
        parent.setVisible(true);
        dispose();
    }//GEN-LAST:event_UILogin_createButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton UILogin_createButton;
    private javax.swing.JButton UILogin_createButton1;
    private javax.swing.JLabel UILogin_jLabel_password;
    private javax.swing.JLabel UILogin_jLabel_password1;
    private javax.swing.JLabel UILogin_jLabel_username;
    private javax.swing.JLabel UILogin_jLabel_username1;
    private javax.swing.JLabel UILogin_jLabel_username2;
    private javax.swing.JPasswordField UIRegister_confirmPassword;
    private javax.swing.JTextField UIRegister_firstName;
    private javax.swing.JTextField UIRegister_lastName;
    private javax.swing.JPasswordField UIRegister_password;
    private javax.swing.JTextField UIRegister_userName;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
