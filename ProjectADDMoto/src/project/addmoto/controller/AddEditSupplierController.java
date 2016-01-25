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
        
        if(!isAdd) {
            button.setText("Update Supplier");
            supplierName.setText(supplier.getSupplierName());
            streetAddress.setText(supplier.getSupplierAddress());
            city.setText(supplier.getSupplierCity());
            postalCode.setText(String.valueOf(supplier.getSupplierPostal()));
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
            if(isAdd) {
                System.out.println("IS ADD");
            } else {
                System.out.println("IS EDIT");
            }
        });
    }
}