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
    public static final String LOGS_TABLE = "logs_table";
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
    LOGS_TABLE columns
    */
    public static final String LOG_ID = "log_id";
    public static final String LOG_DATETIME = "log_datetime";
    public static final String LOG_SELLER_ID_FK = "seller_id";
    
    /*
    PRODUCTS_TABLE columns
    */
    public static final String PRODUCT_ID = "product_id";
    public static final String PRODUCT_ADDMOTO_CODE = "product_addmoto_code";
    public static final String PRODUCT_SUPPLIER_CODE = "product_supplier_code";
    public static final String PRODUCT_CURRENT_QUANTITY = "product_current_quantity";
    public static final String PRODUCT_UNIT_PRICE = "product_unit_price";
    public static final String PRODUCT_SELLING_PRICE = "product_selling_price";
    public static final String PRODUCT_PROFIT_MARGIN = "product_profit_margin";
    public static final String PRODUCT_THRESHOLD_COUNT = "product_threshold_count";
    public static final String PRODUCT_IMAGE_PICTURE = "product_image_picture";
    public static final String PRODUCT_DESCRIPTION = "product_description";
    public static final String PRODUCT_CHARACTERISTICS = "product_characteristic";
    public static final String PRODUCT_MOTORS = "product_motors";
    public static final String PRODUCT_LINE_ID_FK = "product_line_id";
    public static final String PRODUCT_SUPPLIER_ID_FK = "supplier_id";
    
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