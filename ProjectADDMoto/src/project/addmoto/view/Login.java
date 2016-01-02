package project.addmoto.view;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import project.addmoto.data.SellerAccount;
import project.addmoto.database.Query;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class Login extends javax.swing.JFrame {

    private Query query;

    /**
     * Creates new form UI_Login
     */
    public Login(Query query) {
        this.query = query;
        initComponents();
        UILogin_usernameTextField.requestFocusInWindow();
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

    private void loginUser() {
        String username = getUsername();
        String password = getPassword();
        int returnedValue = query.countSellerAccount(username, password);
        SellerAccount sellerAccount = query.getSellerAcount(username, password);

        if (returnedValue == 1 && query.insertLog(sellerAccount) == 1) {
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    Login.this.setVisible(false);
                    JOptionPane.showMessageDialog(Login.this, "<html><span style='font-size:16px'>Welcome " + sellerAccount.getFirstName()
                        + " " + sellerAccount.getLastName() + "!\n"
                        + "Have a good day!");
                    new App(Login.this, sellerAccount).setVisible(true);
                }
            });
        } else {
            JOptionPane.showMessageDialog(Login.this, "Login credentials does not exist.", "Login Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        UILogin_jLabel_username = new javax.swing.JLabel();
        UILogin_jLabel_password = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        UILogin_usernameTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        UILogin_passwordPasswordField = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        UILogin_createUser = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        UILogin_loginButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ADD Moto - Motorcycle Parts and Accessories");
        setBackground(new java.awt.Color(51, 51, 51));
        setResizable(false);

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        UILogin_jLabel_username.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        UILogin_jLabel_username.setForeground(new java.awt.Color(255, 255, 255));
        UILogin_jLabel_username.setText("Username");

        UILogin_jLabel_password.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        UILogin_jLabel_password.setForeground(new java.awt.Color(255, 255, 255));
        UILogin_jLabel_password.setText("Password");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        UILogin_usernameTextField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        UILogin_usernameTextField.setBorder(null);
        UILogin_usernameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UILogin_usernameTextFieldActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setForeground(new java.awt.Color(255, 102, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/addmoto/icons/user7.png"))); // NOI18N
        jLabel2.setMaximumSize(new java.awt.Dimension(34, 33));
        jLabel2.setPreferredSize(new java.awt.Dimension(40, 40));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(UILogin_usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(UILogin_usernameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        UILogin_passwordPasswordField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        UILogin_passwordPasswordField.setBorder(null);
        UILogin_passwordPasswordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UILogin_passwordPasswordFieldActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/addmoto/icons/passkey2.png"))); // NOI18N
        jLabel1.setMaximumSize(new java.awt.Dimension(34, 33));
        jLabel1.setPreferredSize(new java.awt.Dimension(35, 35));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(UILogin_passwordPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UILogin_passwordPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        UILogin_createUser.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        UILogin_createUser.setForeground(new java.awt.Color(255, 255, 255));
        UILogin_createUser.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        UILogin_createUser.setText("Create a new user account?");
        UILogin_createUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UILogin_createUserMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                UILogin_createUserMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                UILogin_createUserMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(UILogin_jLabel_username, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(UILogin_jLabel_password, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 10, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(UILogin_createUser, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UILogin_jLabel_username, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UILogin_jLabel_password))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(UILogin_createUser)
                .addGap(19, 19, 19))
        );

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Login");
        jLabel4.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        UILogin_loginButton.setBackground(new java.awt.Color(0, 153, 0));
        UILogin_loginButton.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        UILogin_loginButton.setForeground(new java.awt.Color(255, 255, 255));
        UILogin_loginButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/addmoto/icons/login15.png"))); // NOI18N
        UILogin_loginButton.setMnemonic('L');
        UILogin_loginButton.setText("Login");
        UILogin_loginButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        UILogin_loginButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        UILogin_loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UILogin_loginButtonActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Copyright © ADD Moto Motorcycle Parts and Accessories 2015.");
        jLabel3.setToolTipText("");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(UILogin_loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(UILogin_loginButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void UILogin_loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UILogin_loginButtonActionPerformed
        loginUser();
    }//GEN-LAST:event_UILogin_loginButtonActionPerformed

    private void UILogin_usernameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UILogin_usernameTextFieldActionPerformed
        loginUser();
    }//GEN-LAST:event_UILogin_usernameTextFieldActionPerformed

    private void UILogin_createUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UILogin_createUserMouseClicked
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
        } catch (Exception exc) {
            exc.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Login.this.setVisible(false);
                new Register(Login.this, query).setVisible(true);
            }
        });
    }//GEN-LAST:event_UILogin_createUserMouseClicked

    private void UILogin_createUserMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UILogin_createUserMouseEntered
        UILogin_createUser.setForeground(Color.RED);
    }//GEN-LAST:event_UILogin_createUserMouseEntered

    private void UILogin_createUserMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UILogin_createUserMouseExited
        UILogin_createUser.setForeground(Color.WHITE);
    }//GEN-LAST:event_UILogin_createUserMouseExited

    private void UILogin_passwordPasswordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UILogin_passwordPasswordFieldActionPerformed
        loginUser();
    }//GEN-LAST:event_UILogin_passwordPasswordFieldActionPerformed
                                                     
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel UILogin_createUser;
    private javax.swing.JLabel UILogin_jLabel_password;
    private javax.swing.JLabel UILogin_jLabel_username;
    private javax.swing.JButton UILogin_loginButton;
    private javax.swing.JPasswordField UILogin_passwordPasswordField;
    private javax.swing.JTextField UILogin_usernameTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
