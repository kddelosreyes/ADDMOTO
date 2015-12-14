package project.addmoto.database;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class Database {
    
    private Connection connection;
    
    private final String username = "addmotoadmin";
    private final String password = "addmotoadmin";
    
    /*
    All tables from ADDMOTODB database
    */
    public static final String EXPENSE_TABLE = "expense_table";
    public static final String MOTOR_LINE_TABLE = "motor_line_table";
    public static final String ORDERS_TABLE = "orders_table";
    public static final String ORDER_LINE_TABLE = "order_line_table";
    public static final String PRODUCTS_TABLE = "products_table";
    public static final String PRODUCT_LINE_TABLE = "product_line_table";
    public static final String RECEIPTS_TABLE = "receipts_table";
    public static final String REPORT_LOGS_TABLE = "report_logs_table";
    public static final String SELLER_ACCOUNT_TABLE = "seller_account_table";
    public static final String SOLD_ITEMS_TABLE = "sold_items_table";
    public static final String SUPPLIER_CONTACTS_TABLE = "supplier_contacts_table";
    public static final String SUPPLIER_TABLE = "supplier_table";
    
    /*
    SELLER_ACCOUNT_TABLE columns
    */
    public static final String SELLER_ID = "seller_id";
    public static final String SELLER_ACCOUNT_FIRST_NAME = "seller_account_first_name";
    public static final String SELLER_ACCOUNT_LAST_NAME = "seller_account_last_name";
    public static final String SELLER_ACCOUNT_USERNAME = "seller_account_username";
    public static final String SELLER_ACCOUNT_PASSWORD = "seller_account_password";
    
    public Connection getConnection() {
        if(connection == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost/addmotodb", username, password);
            } catch(Exception exc) {
                System.out.println(exc.toString());
            }
        }
        
        return connection;
    }
}