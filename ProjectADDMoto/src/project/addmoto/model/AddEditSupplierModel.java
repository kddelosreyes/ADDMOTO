/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.addmoto.model;

import java.sql.Connection;
import project.addmoto.javasqlquerybuilder.Factory;
import project.addmoto.javasqlquerybuilder.generic.FactoryImp;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class AddEditSupplierModel {
   
    private final Connection connection;
    private final Factory sqlFactory;
    
    public AddEditSupplierModel(final Connection connection) {
        this.connection = connection;
        sqlFactory = new FactoryImp();
        
        System.out.println("Add Edit Supplier Model called.");
    }
}