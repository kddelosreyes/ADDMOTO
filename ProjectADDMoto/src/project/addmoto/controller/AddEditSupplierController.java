/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.addmoto.controller;

import java.sql.Connection;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
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
    
    public AddEditSupplierController(final Connection connection) {
        this.connection = connection;
        this.model = new AddEditSupplierModel(connection);
        this.view = new AddEditSupplier();
        
        supplierName = view.getSpAccountName();
        streetAddress = view.getSpAddress();
        city = view.getSpCity();
        country = view.getSpCountry();
        postalCode = view.getSpPostal();
        button = view.getSpAddUpdate();
        
        setListeners();
    }
    
    @Override
    public void setListeners() {
        
    }
}