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
import project.addmoto.database.Query;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class POSModel {
    
    private final Connection connection;
    private String query;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    
    public POSModel() {
        connection = new Database().getConnection();
        
        System.out.println("POS Model is called");
    }
    
    public Products getProduct(String productCode) {
        Products product = null;
        
        try {
            query = "SELECT * FROM " + Database.PRODUCTS_TABLE + 
                    " WHERE " + Database.PRODUCT_ADDMOTO_CODE + " = '" + productCode + "';";
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                int id = resultSet.getInt(Database.PRODUCT_ID);
                String addmotoCode = resultSet.getString(Database.PRODUCT_ADDMOTO_CODE);
                String supplierCode = resultSet.getString(Database.PRODUCT_SUPPLIER_CODE);
                int quantity = resultSet.getInt(Database.PRODUCT_CURRENT_QUANTITY);
                double unitPrice = resultSet.getDouble(Database.PRODUCT_UNIT_PRICE);
                double sellingPrice = resultSet.getDouble(Database.PRODUCT_SELLING_PRICE);
                double profitMargin = resultSet.getDouble(Database.PRODUCT_PROFIT_MARGIN);
                int threshold = resultSet.getInt(Database.PRODUCT_THRESHOLD_COUNT);
                Image imagePicture = null;
                String description = resultSet.getString(Database.PRODUCT_DESCRIPTION);
                String characteristics = resultSet.getString(Database.PRODUCT_CHARACTERISTICS);
                String motors = resultSet.getString(Database.PRODUCT_MOTORS);
                int productLineID = resultSet.getInt(Database.PRODUCT_LINE_ID_FK);
                int supplierID = resultSet.getInt(Database.PRODUCT_SUPPLIER_ID_FK);
                
                product = new Products(id, addmotoCode, supplierCode,
                        quantity, unitPrice, sellingPrice,
                        profitMargin, threshold, imagePicture,
                        description, characteristics, motors,
                        productLineID, supplierID);
            }
        } catch(Exception exc) {
            exc.printStackTrace();
        }
        
        return product;
    }
    
    public ProductLine getProductLine(int productLineID) {
        ProductLine productLine = null;
        
        try {
            query = "SELECT * FROM " + Database.PRODUCT_LINE_TABLE + 
                    " WHERE " + Database.PRODUCT_LINE_ID + " = " + productLineID + ";";
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                int id = resultSet.getInt(Database.PRODUCT_LINE_ID);
                String productLineName = resultSet.getString(Database.PRODUCT_LINE_NAME);
                
                productLine = new ProductLine(id, productLineName);
            }
        } catch(Exception exc) {
            exc.printStackTrace();
        }
        
        return productLine;
    }
    
    public int insertReceipt(String receiptNo, String timestamp, double totalPrice,
            int sellerID, double pricePaid, int isFulyPaid) {
        return 0;
    }
}