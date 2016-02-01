/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.addmoto.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import project.addmoto.data.InventoryChange;
import project.addmoto.data.Receipt;
import project.addmoto.data.Return;
import project.addmoto.data.ReturnsData;
import project.addmoto.data.SoldItems;
import project.addmoto.database.Database;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class ReturnsModel {
    
    private final Connection connection;
    private String query;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    
    public ReturnsModel(final Connection connection) {
        this.connection = connection;
        
        System.out.println("Returns model is called.");
    }
    
    public ArrayList<Receipt> getReceipts() {
        ArrayList<Receipt> list = new ArrayList<>();
        
        try {
            query = "SELECT * FROM " + Database.RECEIPTS_TABLE + ";";
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            System.out.println("Set: " + resultSet.getFetchSize());
            while(resultSet.next()) {
                list.add(
                        new Receipt(
                                resultSet.getInt(Database.RECEIPT_ID),
                                resultSet.getString(Database.RECEIPT_NO),
                                resultSet.getString(Database.RECEIPT_TRANSACTION_TIMESTAMP),
                                resultSet.getDouble(Database.RECEIPT_TOTAL_PRICE),
                                resultSet.getInt(Database.SELLER_ID),
                                resultSet.getDouble(Database.RECEIPT_PRICE_PAID),
                                resultSet.getBoolean(Database.RECEIPT_IS_FULLY_PAID),
                                resultSet.getDouble(Database.RECEIPT_DISCOUNT)
                        )
                );
            }
        } catch(Exception exc) {
            exc.printStackTrace();
        }
        
        return list;
    }
    
    public ArrayList<SoldItems> getSoldItems(int receiptID) {
        ArrayList<SoldItems> list = new ArrayList<>();
        
        try {
            query = "SELECT si.*, " +
                    "concat(pl.PRODUCT_LINE_NAME, ' ', p.PRODUCT_DESCRIPTION, ' ', p.PRODUCT_CHARACTERISTIC, ' ', p.PRODUCT_MOTORS) as 'PRODUCT_NAME' " +
                    "from sold_items_table as si left join receipts_table as r on r.RECEIPT_ID = si.RECEIPT_ID " +
                    "left join products_table as p on si.PRODUCT_ID = p.PRODUCT_ID " +
                    "left join product_line_table as pl on pl.PRODUCT_LINE_ID = p.PRODUCT_LINE_ID " +
                    "where r.RECEIPT_ID = " + receiptID + " and si.SOLD_ITEM_QUANTITY != 0;";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                list.add(
                        new SoldItems(
                                resultSet.getInt(Database.SOLD_ID),
                                resultSet.getInt(Database.SOLD_ITEM_QUANTITY),
                                resultSet.getDouble(Database.SOLD_ITEM_TOTAL_PRICE),
                                resultSet.getDouble(Database.SOLD_ITEM_TOTAL_PROFIT),
                                resultSet.getInt(Database.PRODUCT_ID),
                                resultSet.getInt(Database.RECEIPT_ID),
                                resultSet.getString("PRODUCT_NAME")
                        )
                );
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return list;
    }
    
    public double[] getAmountPrice(int productID) {
        try {
            query = "SELECT " + Database.PRODUCT_UNIT_PRICE +
                    ", " + Database.PRODUCT_SELLING_PRICE +
                    " FROM " + Database.PRODUCTS_TABLE +
                    " WHERE " + Database.PRODUCT_ID + " = " + productID + ";";
            
            System.out.println(query);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                return new double[] {
                    resultSet.getDouble("PRODUCT_UNIT_PRICE"),
                    resultSet.getDouble("PRODUCT_SELLING_PRICE")
                };
            }
        } catch(Exception e) {
            return new double[] {-1, -1};
        }
        return new double[] {-1, -1};
    }
    
    public int updateSoldItem(SoldItems item) {
        try {
            query = "UPDATE SOLD_ITEMS_TABLE SET SOLD_ITEM_QUANTITY = " + item.getItemQuantity() +
                    ", SOLD_ITEM_TOTAL_PRICE = " + item.getTotalPrice() +
                    ", SOLD_ITEM_TOTAL_PROFIT = " + item.getTotalProfit() +
                    " WHERE SOLD_ID = " + item.getSoldID();
            
            statement = connection.createStatement();
            return statement.executeUpdate(query);
        } catch(Exception e) {
            return 0;
        }
    }
    
    public double getTotalPrice(int receiptID) {
        try {
            query = "select ifnull(sum(sold_item_total_price), 0) as 'TOTAL_PRICE' from sold_items_table where receipt_id = "
                    + receiptID
                    + " group by receipt_id;";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                return resultSet.getDouble("TOTAL_PRICE");
            }
        } catch(Exception e) {
            return 0;
        }
        return 0;
    }
    
    public int updateReceipt(double totalPrice, int receiptID) {
        try {
            query = "update receipts_table set receipt_total_price = " +
                    totalPrice + 
                    " where receipt_id = " +
                    receiptID;
            statement = connection.createStatement();
            return statement.executeUpdate(query);
        } catch(Exception e) {
            return 0;
        }
    }
    
    public int getCurrentQty(int productID) {
        try {
            query = "SELECT PRODUCT_CURRENT_QUANTITY FROM PRODUCTS_TABLE WHERE PRODUCT_ID = " + productID + ";";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                return resultSet.getInt("PRODUCT_CURRENT_QUANTITY");
            }
        } catch(Exception e) {
            return 0;
        }
        return 0;
    }
    
    public int updateProductQty(int qty, int productID) {
        try {
            query = "update products_table set product_current_quantity = " +
                    qty + 
                    " where product_id = " +
                    productID;
            statement = connection.createStatement();
            return statement.executeUpdate(query);
        } catch(Exception e) {
            return 0;
        }
    }
    
    public int insertChange(InventoryChange ic) {
        try {
            query = "INSERT INTO inventory_change_table (change_timestamp, change_before, change_after, change_qty, change_product_id, type) values(?, ?, ?, ?, ?, ?);";
            preparedStatement = connection.prepareStatement(query);
            
            preparedStatement.setString(1, ic.getTimestamp());
            preparedStatement.setInt(2, ic.getChangeBefore());
            preparedStatement.setInt(3, ic.getChangeAfter());
            preparedStatement.setInt(4, ic.getChangeQty());
            preparedStatement.setInt(5, ic.getProductID());
            preparedStatement.setInt(6, 3);
            
            return preparedStatement.executeUpdate();
        } catch(Exception e) {
            return -1;
        }
    }
    
    public int insertReturn(Return r) {
        try {
            query = "INSERT INTO returns_table (return_date, return_product_id, return_total_quantity, return_receipt_id, seller_id) values(?, ?, ?, ?, ?);";
            preparedStatement = connection.prepareStatement(query);
            
            preparedStatement.setString(1, r.getDate());
            preparedStatement.setInt(2, r.getProductID());
            preparedStatement.setInt(3, r.getQty());
            preparedStatement.setInt(4, r.getReceiptID());
            preparedStatement.setInt(5, r.getSellerID());
            
            return preparedStatement.executeUpdate();
        } catch(Exception e) {
            return -1;
        }
    }
    
    public ArrayList<ReturnsData> getReturns() {
        ArrayList<ReturnsData> rList = new ArrayList<>();
        
        try {
            query = "select r.return_date, " +
                    "p.product_addmoto_code, " +
                    "concat(pl.product_line_name, ' ', p.product_description, ' ', p.product_characteristic, ' ', p.product_motors) as 'item_name', " +
                    "r.return_total_quantity, " +
                    "p.product_selling_price, " +
                    "(r.return_total_quantity * p.product_selling_price) as 'total_price' " +
                    "from returns_table as r left join products_table as p on r.return_product_id = p.product_id " +
                    "left join product_line_table as pl on p.product_line_id = pl.product_line_id";
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            
            while(resultSet.next()) {
                rList.add(
                        new ReturnsData(
                                resultSet.getString("return_date"),
                                resultSet.getString("product_addmoto_code"),
                                resultSet.getString("item_name"),
                                resultSet.getInt("return_total_quantity"),
                                resultSet.getDouble("product_selling_price"),
                                resultSet.getDouble("total_price")
                        )
                );
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return rList;
    }
}