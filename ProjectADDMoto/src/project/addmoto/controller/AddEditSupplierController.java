/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.addmoto.controller;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import project.addmoto.data.Supplier;
import project.addmoto.model.AddEditSupplierModel;
import project.addmoto.mvc.Controller;
import project.addmoto.view.AddEditSupplier;

/**
 *
 * @author Kim Howel delos Reyes
 */
public final class AddEditSupplierController extends Controller {
    
    private final Connection connection;
    private final AddEditSupplierModel model;
    private final AddEditSupplier view;
    
    private final JTextField supplierName;
    private final JTextField streetAddress;
    private final JTextField city;
    private final JTextField postalCode;
    private final JComboBox country;
    private final JButton button;
    
    private final boolean isAdd;
    private final Supplier supplier;
    
    public AddEditSupplierController(final Connection connection, final boolean isAdd, final Supplier supplier) {
        this.connection = connection;
        this.isAdd = isAdd;
        this.supplier = supplier;
        this.model = new AddEditSupplierModel(connection);
        this.view = new AddEditSupplier();
        
        supplierName = view.getSpAccountName();
        streetAddress = view.getSpAddress();
        city = view.getSpCity();
        country = view.getSpCountry();
        postalCode = view.getSpPostal();
        button = view.getSpAddUpdate();
        
        if (!isAdd) {
            button.setText("Update Supplier");
            supplierName.setText(supplier.getSupplierName());
            streetAddress.setText(supplier.getSupplierAddress());
            city.setText(supplier.getSupplierCity());
            postalCode.setText(String.valueOf(supplier.getSupplierPostal()));
        }
        
        int itemCount = country.getItemCount();
        String supCountry = "";
        if (supplier != null) {
            supCountry = supplier.getSupplierCountry();
            int index = -1;
            for (int i = 0; i < itemCount; i++) {
                String cntry = (String) country.getItemAt(i);
                if (cntry.substring(cntry.indexOf("(") + 1, cntry.indexOf(")")).toUpperCase().equals(supCountry.toUpperCase())) {
                    index = i;
                    break;
                }
            }
            country.setSelectedIndex(index);
        }
        
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
        button.addActionListener((ActionEvent e) -> {
            String sName = supplierName.getText();
            String sAddress = streetAddress.getText();
            String sCity = city.getText();
            String sPostal = postalCode.getText();
            String ctry = (String) country.getSelectedItem();
            
            if (sName.equals("") || sAddress.equals("") || sCity.equals("") || sPostal.equals("")) {
                JOptionPane.showMessageDialog(
                        null,
                        "You still have empty fields.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            } else {
                int option = JOptionPane.showConfirmDialog(
                        null,
                        "Do you want to proceed with this operation?",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );
                
                if (option == JOptionPane.YES_OPTION) {
                    Supplier supplier = new Supplier(
                            sName,
                            sCity,
                            ctry.substring(ctry.indexOf("(") + 1, ctry.indexOf(")")).toUpperCase(),
                            sAddress,
                            Integer.parseInt(sPostal)
                    );
                    if (isAdd) {
                        int row = model.insertSupplier(supplier);
                        if (row != 0) {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "You have added a new supplier on your supplier list successfully.",
                                    "Success",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
                            supplierName.setText("");
                            streetAddress.setText("");
                            city.setText("");
                            postalCode.setText("");
                            country.setSelectedIndex(0);
                        } else {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "There is an error while adding supplier on your supplier list.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                    } else {
                        supplier.setSupplierID(AddEditSupplierController.this.supplier.getSupplierID());
                        int row = model.updateSupplier(supplier);
                        if (row != 0) {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "You have updated this supplier on your supplier list successfully.",
                                    "Success",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
                        } else {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "There is an error while updating supplier on your supplier list.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                    }
                }
            }
        });
    }
}