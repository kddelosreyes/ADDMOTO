package project.addmoto.model;


import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import project.addmoto.data.InventoryChange;
import project.addmoto.data.ProductLine;
import project.addmoto.data.Products;
import project.addmoto.data.Receipt;
import project.addmoto.data.SalesItems;
import project.addmoto.database.Database;
import project.addmoto.datacollections.SalesItemsList;

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

    public POSModel(final Connection connection) {
        this.connection = connection;

        System.out.println("POS Model is called");
    }

    /**
     *
     * @param productCode
     * @return The product with the productCode
     */
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
    
    /**
     *
     * @param productID
     * @return The product with the productID
     */
    public Products getProduct(int productID) {
        Products product = null;

        try {
            query = "SELECT * FROM " + Database.PRODUCTS_TABLE +
                    " WHERE " + Database.PRODUCT_ID + " = " + productID + ";";

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

    /**
     *
     * @param productLineID
     * @return The product line where product is grouped
     */
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

    /**
     *
     * @param receiptDetails
     * @return The number of updated rows
     */
    public int insertReceipt(Receipt receiptDetails) {
        try {
            query = "INSERT INTO " + Database.RECEIPTS_TABLE + " (" + Database.RECEIPT_NO + ", " +  Database.RECEIPT_TRANSACTION_TIMESTAMP + ", "
                    + Database.RECEIPT_TOTAL_PRICE + ", " + Database.SELLER_ID_FK + ", " + Database.RECEIPT_PRICE_PAID + ", " + Database.RECEIPT_IS_FULLY_PAID + ")"
                    + " VALUES (?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, receiptDetails.getReceiptNo());
            preparedStatement.setString(2, receiptDetails.getTransactionTimeStamp());
            preparedStatement.setDouble(3, receiptDetails.getTotalPrice());
            preparedStatement.setInt(4, receiptDetails.getSellerID());
            preparedStatement.setDouble(5, receiptDetails.getPricePaid());
            preparedStatement.setBoolean(6, receiptDetails.isIsFullyPaid());

            return preparedStatement.executeUpdate();
        } catch(Exception exc) {
            exc.printStackTrace();
        }
        return 0;
    }
    
    /**
     *
     * @param receiptDetails
     * @return The number of updated rows
     */
    public int insertSoldItems(SalesItemsList itemsList, int receiptID) {
        int insertedValue = 0;

        for (SalesItems item : itemsList) {
            try {
                query = "INSERT INTO " + Database.SOLD_ITEMS_TABLE + " (" + Database.SOLD_ITEM_QUANTITY + ", " + Database.SOLD_ITEM_TOTAL_PRICE + ", "
                        + Database.SOLD_ITEM_TOTAL_PROFIT + ", " + Database.PRODUCT_ID_FK + ", " + Database.RECEIPT_ID_FK + ")"
                        + " VALUES (?, ?, ?, ?, ?)";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, item.getQuantity());
                preparedStatement.setDouble(2, item.getExtPrice());
                preparedStatement.setDouble(3, item.getExtPrice() - (item.getQuantity() * item.getUnitPrice()));
                preparedStatement.setInt(4, item.getProductID());
                preparedStatement.setInt(5, receiptID);

                insertedValue += preparedStatement.executeUpdate();
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
        
        return insertedValue;
    }
    
    public int getReceiptID(String receiptNo) {
        try {
            query = "SELECT " + Database.RECEIPT_ID + " FROM " + Database.RECEIPTS_TABLE +
                    " WHERE " + Database.RECEIPT_NO + " = '" + receiptNo + "';";

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                int id = resultSet.getInt(Database.RECEIPT_ID);
                return id;
            }
        } catch(Exception exc) {
            exc.printStackTrace();
        }

        return 0;
    }
    
    /**
     *
     * @param changeList
     * @return The number of updated rows
     */
    public int insertChangeItems(ArrayList<InventoryChange> changeList) {
        int insertedValue = 0;

        for (InventoryChange change : changeList) {
            try {
                query = "INSERT INTO " + Database.INVENTORY_CHANGE_TABLE + " (" + Database.CHANGE_TIMESTAMP + ", " + Database.CHANGE_BEFORE + ", "
                        + Database.CHANGE_AFTER + ", " + Database.CHANGE_QTY + ", " + Database.CHANGE_PRODUCT_ID + ")"
                        + " VALUES (?, ?, ?, ?, ?)";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, change.getTimestamp());
                preparedStatement.setInt(2, change.getChangeBefore());
                preparedStatement.setInt(3, change.getChangeAfter());
                preparedStatement.setInt(4, change.getChangeQty());
                preparedStatement.setInt(5, change.getProductID());

                insertedValue += preparedStatement.executeUpdate();
                System.out.println("-> Inserting\n" + change.toString() + "\nValue: " + insertedValue);
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
        
        return insertedValue;
    }
    
    public int updateChangeItems(SalesItemsList itemsList) {
        int updatedValue = 0;
        
        for(SalesItems items : itemsList){
            Products toUpdate = getProduct(items.getProductID());
            
            try {
                query = "UPDATE " + Database.PRODUCTS_TABLE + " SET " + Database.PRODUCT_CURRENT_QUANTITY +
                        " = " + (toUpdate.getCurrentQuantity() - items.getQuantity()) + " WHERE " +
                        Database.PRODUCT_ID + " = " + items.getProductID() + " ;";
                statement = connection.createStatement();
                System.out.println(query);
                
                updatedValue += statement.executeUpdate(query);
            } catch(Exception exc) {
                exc.printStackTrace();
            }
        }
        
        return updatedValue;
    }
    
    public ArrayList<Products> getProducts() {
        ArrayList<Products> productsList = new ArrayList<>();
        
        try {
            query = "SELECT * FROM " + Database.PRODUCTS_TABLE + ";";
            
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
                
                Products product = new Products(id, addmotoCode, supplierCode,
                        quantity, unitPrice, sellingPrice,
                        profitMargin, threshold, imagePicture,
                        description, characteristics, motors,
                        productLineID, supplierID);
                productsList.add(product);
            }
        } catch(Exception exc) {
            exc.printStackTrace();
        }
        
        return productsList;
    }
}
