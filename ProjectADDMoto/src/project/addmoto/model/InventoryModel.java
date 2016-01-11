package project.addmoto.model;

import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import project.addmoto.data.ProductLine;
import project.addmoto.data.Products;
import project.addmoto.data.Supplier;
import project.addmoto.database.Database;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class InventoryModel {
    
    private final Connection connection;
    private String query;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    
    public InventoryModel(final Connection connection) {
        this.connection = connection;
        
        System.out.println("Inventory model is called.");
    }
    
    public ArrayList<ProductLine> getProductLines() {
        ArrayList<ProductLine> productLines = new ArrayList<>();
        
        try {
            query = "SELECT * FROM " + Database.PRODUCT_LINE_TABLE + ";";
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                int productLineID = resultSet.getInt(Database.PRODUCT_LINE_ID);
                String productLineName = resultSet.getString(Database.PRODUCT_LINE_NAME);
                
                productLines.add(
                        new ProductLine(productLineID, productLineName));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return productLines;
    }
    
    public ArrayList<Supplier> getSuppliers() {
        ArrayList<Supplier> suppliers = new ArrayList<>();
        
        try {
            query = "SELECT * FROM " + Database.SUPPLIER_TABLE + ";";
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                int supplierID = resultSet.getInt(Database.SUPPLIER_ID);
                String supplierName = resultSet.getString(Database.SUPPLIER_NAME);
                String supplierCity = resultSet.getString(Database.SUPPLIER_CITY);
                String supplierCountry = resultSet.getString(Database.SUPPLIER_COUNTRY);
                String supplierAddress = resultSet.getString(Database.SUPPLIER_ADDRESS);
                int supplierPostal = resultSet.getInt(Database.SUPPLIER_POSTAL);
                
                suppliers.add(
                        new Supplier(supplierID, supplierName, supplierCity,
                                supplierCountry, supplierAddress, supplierPostal));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return suppliers;
    }
    
    public ArrayList<Products> getProducts() {
        ArrayList<Products> products = new ArrayList<>();
        
        try {
            query = "SELECT * FROM " + Database.PRODUCTS_TABLE + ";";
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                int productID = resultSet.getInt(Database.PRODUCT_ID);
                String addMotoCode = resultSet.getString(Database.PRODUCT_ADDMOTO_CODE);
                String supplierCode = resultSet.getString(Database.PRODUCT_SUPPLIER_CODE);
                int quantity = resultSet.getInt(Database.PRODUCT_CURRENT_QUANTITY);
                double unitPrice = resultSet.getDouble(Database.PRODUCT_UNIT_PRICE);
                double sellingPrice = resultSet.getDouble(Database.PRODUCT_SELLING_PRICE);
                double profitMargin = resultSet.getDouble(Database.PRODUCT_PROFIT_MARGIN);
                int threshold = resultSet.getInt(Database.PRODUCT_THRESHOLD_COUNT);
                Image image = null;
                String description = resultSet.getString(Database.PRODUCT_DESCRIPTION);
                String characteristics = resultSet.getString(Database.PRODUCT_CHARACTERISTICS);
                String models = resultSet.getString(Database.PRODUCT_MOTORS);
                int productLineID = resultSet.getInt(Database.PRODUCT_LINE_ID_FK);
                int supplierID = resultSet.getInt(Database.SUPPLIER_SUPPLIER_ID_FK);
                
                products.add(
                        new Products(productID, addMotoCode, supplierCode,
                                quantity, unitPrice, sellingPrice,
                                profitMargin, threshold, image,
                                description, characteristics, models,
                                productLineID, supplierID));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return products;
    }
    
    public int addNewProducts(String prodSupplierCode, Double prodUnitPrice, Double prodSellingPrice, Integer prodCurrentQty, Integer prodThresCount, String prodCharacteristics, String prodMotors, String prodDescription) {
        try {
            query = "INSERT INTO " + Database.PRODUCTS_TABLE + " (" + Database.PRODUCT_SUPPLIER_CODE + ", "+ Database.PRODUCT_UNIT_PRICE + ", " + Database.PRODUCT_SELLING_PRICE + ", " 
                    + Database.PRODUCT_CURRENT_QUANTITY + ", " +  Database.PRODUCT_THRESHOLD_COUNT + ", " +  Database.PRODUCT_CHARACTERISTICS + ", " +  Database.PRODUCT_MOTORS + ", " 
                    +  Database.PRODUCT_DESCRIPTION + ")"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, prodSupplierCode);
            preparedStatement.setDouble(2, prodUnitPrice);
            preparedStatement.setDouble(3, prodSellingPrice);
            preparedStatement.setInt(4, prodCurrentQty);
            preparedStatement.setInt(5, prodThresCount);
            preparedStatement.setString(6, prodCharacteristics);
            preparedStatement.setString(7, prodMotors);
            preparedStatement.setString(8, prodDescription);
            return preparedStatement.executeUpdate();
        } catch(Exception exc) {
            exc.printStackTrace();
        }
        return 0;
    }
}