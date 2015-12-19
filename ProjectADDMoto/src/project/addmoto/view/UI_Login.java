package project.addmoto.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import project.addmoto.data.SellerAccount;
import project.addmoto.database.Query;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class UI_Login extends javax.swing.JFrame {

    private Query query;
    
    /**
     * Creates new form UI_Login
     */
    public UI_Login(Query query) {
        this.query = query;
        initComponents();
        setLocationRelativeTo(null);
    }
    
    /*
    These methods get the value from views
    */
    private String getUsername() {
        return UILogin_usernameTextField.getText();
    }
    
    private String getPassword() {
        return new String(UILogin_passwordPasswordField.getPassword());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        UILogin_jLabel_username = new javax.swing.JLabel();
        UILogin_jLabel_password = new javax.swing.JLabel();
        UILogin_usernameTextField = new javax.swing.JTextField();
        UILogin_passwordPasswordField = new javax.swing.JPasswordField();
        UILogin_createButton = new javax.swing.JButton();
        UILogin_loginButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ADD Moto - Motorcycle Parts and Accessories");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        UILogin_jLabel_username.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        UILogin_jLabel_username.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        UILogin_jLabel_username.setText("Username:");

        UILogin_jLabel_password.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        UILogin_jLabel_password.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        UILogin_jLabel_password.setText("Password:");

        UILogin_usernameTextField.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        UILogin_usernameTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        UILogin_usernameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UILogin_usernameTextFieldActionPerformed(evt);
            }
        });

        UILogin_passwordPasswordField.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        UILogin_passwordPasswordField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        UILogin_passwordPasswordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UILogin_passwordPasswordFieldActionPerformed(evt);
            }
        });

        UILogin_createButton.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        UILogin_createButton.setText("Create");
        UILogin_createButton.setToolTipText("");
        UILogin_createButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UILogin_createButtonActionPerformed(evt);
            }
        });

        UILogin_loginButton.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        UILogin_loginButton.setText("Login");
        UILogin_loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UILogin_loginButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(UILogin_jLabel_password, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UILogin_jLabel_username, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(UILogin_usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UILogin_passwordPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(UILogin_loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(UILogin_createButton, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UILogin_usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UILogin_jLabel_username, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UILogin_jLabel_password, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UILogin_passwordPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UILogin_createButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UILogin_loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(259, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void UILogin_loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UILogin_loginButtonActionPerformed
        String username = getUsername();
        String password = getPassword();
        int returnedValue = query.countSellerAccount(username, password);
        SellerAccount sellerAccount = query.getSellerAcount(username, password);
        
        if(returnedValue == 1 && query.insertLog(sellerAccount) == 1) {
            JOptionPane.showMessageDialog(UI_Login.this, "Welcome " + sellerAccount.getFirstName() +
                    " " + sellerAccount.getLastName() + "!\n" +
                    "Have a good day!", "Login Successful", JOptionPane.INFORMATION_MESSAGE);
            try {
                UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
            } catch(Exception exc) {
                exc.printStackTrace();
            }
            
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    UI_Login.this.setVisible(false);
                    new UI_Dashboard(UI_Login.this, query).setVisible(true);
                }
            });
        } else {
            JOptionPane.showMessageDialog(UI_Login.this, "Username does not exist!", "Login Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_UILogin_loginButtonActionPerformed

    private void UILogin_usernameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UILogin_usernameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UILogin_usernameTextFieldActionPerformed

    private void UILogin_passwordPasswordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UILogin_passwordPasswordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UILogin_passwordPasswordFieldActionPerformed

    private void UILogin_createButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UILogin_createButtonActionPerformed
        // TODO add your handling code here:
        this.dispose();
        try {
                UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
            } catch(Exception exc) {
                exc.printStackTrace();
            }
            
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    UI_Login.this.setVisible(false);
                    new UI_Register(UI_Login.this, query).setVisible(true);
                }
            });
    }//GEN-LAST:event_UILogin_createButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton UILogin_createButton;
    private javax.swing.JLabel UILogin_jLabel_password;
    private javax.swing.JLabel UILogin_jLabel_username;
    private javax.swing.JButton UILogin_loginButton;
    private javax.swing.JPasswordField UILogin_passwordPasswordField;
    private javax.swing.JTextField UILogin_usernameTextField;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
