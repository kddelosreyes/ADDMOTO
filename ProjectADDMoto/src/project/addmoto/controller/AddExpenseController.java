/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.addmoto.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import project.addmoto.data.Expense;
import project.addmoto.data.SellerAccount;
import project.addmoto.model.AddExpenseModel;
import project.addmoto.mvc.Controller;
import project.addmoto.utilities.Formatter;
import project.addmoto.view.AddExpense;

/**
 *
 * @author Kim Howel delos Reyes
 */
public final class AddExpenseController extends Controller {
    
    private final Connection connection;
    private final SellerAccount sellerAccount;
    private final AddExpenseModel model;
    private final AddExpense view;
    
    private final JButton eAdd;
    private final JTextField eAmount;
    private final JTextField eOthers;
    private final JTextArea eDetails;
    private final JComboBox<String> eSelect;
    
    public AddExpenseController(final Connection connection, final SellerAccount sellerAccount) {
        this.connection = connection;
        this.sellerAccount = sellerAccount;
        this.model = new AddExpenseModel(connection);
        this.view = new AddExpense();
        
        this.eAdd = this.view.geteAdd();
        this.eAmount = this.view.geteAmount();
        this.eOthers = this.view.geteOthers();
        this.eDetails = this.view.geteDetails();
        this.eSelect = this.view.geteSelect();
        
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
    
    @Override
    public void setListeners() {
        eAdd.addActionListener((ActionEvent e) -> {
            if(eDetails.getText().trim().equals("") || eAmount.getText().equals("")) {
                JOptionPane.showMessageDialog(
                        null,
                        "You still have empty fields.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            } else {
                String _amount = eAmount.getText();
                if(_amount.charAt(0) == '.') {
                    _amount = "0" + _amount;
                }
                if(_amount.charAt(_amount.length() - 1) == '.') {
                    _amount = _amount + "0";
                }
                if(!_amount.contains(".")) {
                    _amount = _amount + ".0";
                }
                if(eSelect.getSelectedItem().equals("Others") && eOthers.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(
                            null,
                            "You still have empty fields.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                } else {
                    if(_amount.charAt(0) == '.' || _amount.split("\\.")[1].length() > 2) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Wrong number format. Amount must be with most two decimal numbers and leading digit before decimal.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    } else {
                        double amount = Double.parseDouble(_amount);
                        if(amount <= 0) {
                            System.out.println("Amount: " + amount);
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Expense amount must be greater than PhP 0.00",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        } else {
                            String expenditure = (String) eSelect.getSelectedItem();
                            if(expenditure.equals("Others")) {
                                expenditure = expenditure + " - " + eOthers.getText().replaceAll("\\s+", " ").trim();
                            }
                            String timestamp = Formatter.formatDate(new Date());
                            String detail = eDetails.getText().replaceAll("\\s+", " ").trim();
                            Expense expense = new Expense(
                                    amount,
                                    timestamp,
                                    expenditure,
                                    detail,
                                    sellerAccount.getSellerID()
                            );
                            
                            int row = model.insertExpense(expense);
                            if(row == 0) {
                                JOptionPane.showMessageDialog(
                                        null,
                                        "There is a problem recording expense.",
                                        "Error",
                                        JOptionPane.ERROR_MESSAGE
                                );
                            } else {
                                JOptionPane.showMessageDialog(
                                        null,
                                        "Expense successfully recorded.",
                                        "Success",
                                        JOptionPane.INFORMATION_MESSAGE
                                );
                                eAmount.setText("");
                                eSelect.setSelectedIndex(0);
                                eOthers.setEnabled(false);
                                eOthers.setText("");
                                eDetails.setText("");
                            }
                        }
                    }
                }
            }
        });
        
        eSelect.addActionListener((ActionEvent e) -> {
            if(eSelect.getSelectedItem().equals("Others")) {
                eOthers.setEnabled(true);
            } else {
                eOthers.setEnabled(false);
                eOthers.setText("");
            }
        });
        
        eAmount.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();
                if (!((ch >= '0') && (ch <= '9')
                        || (ch == KeyEvent.VK_BACK_SPACE)
                        || (ch == KeyEvent.VK_DELETE)
                        || (ch == '.'))) {
                    tk.beep();
                    e.consume();
                } else if ((ch == '.' && eAdd.getText().contains("."))
                        || (ch == '.' && eAdd.getText().equals(""))) {
                    tk.beep();
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });
    }
}
