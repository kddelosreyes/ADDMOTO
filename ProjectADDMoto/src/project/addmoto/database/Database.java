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
    public static final String INVENTORY_CHANGE_TABLE = "inventory_change_table";
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
    EXPENSE_TABLE columns
    */
    public static final String EXPENSE_ID = "expense_id";
    public static final String EXPENSE_AMOUNT = "expense_amount";
    public static final String EXPENSE_TIMESTAMP = "expense_timestamp";
    public static final String EXPENSE_REASON = "expense_reason";
    public static final String EXPENSE_DETAIL = "expense_detail";
    public static final String EXPENSE_SELLER_ID_FK = "seller_id";
    
    /*
    INVENTORY_CHANGE_TABLE columns
    */
    public static final String CHANGE_ID = "change_id";
    public static final String CHANGE_TIMESTAMP = "change_timestamp";
    public static final String CHANGE_BEFORE = "change_before";
    public static final String CHANGE_AFTER = "change_after";
    public static final String CHANGE_QTY = "change_qty";
    public static final String CHANGE_PRODUCT_ID = "change_product_id";
    
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
    PRODUCT_LINE_TABLE columns
    */
    public static final String PRODUCT_LINE_ID = "product_line_id";
    public static final String PRODUCT_LINE_NAME = "product_line_name";
    
    /*
    RECEIPTS_TABLE columns
    */
    public static final String RECEIPT_ID = "receipt_id";
    public static final String RECEIPT_NO = "receipt_no";
    public static final String RECEIPT_TRANSACTION_TIMESTAMP = "receipt_transaction_timestamp";
    public static final String RECEIPT_TOTAL_PRICE = "receipt_total_price";
    public static final String SELLER_ID_FK = "seller_id";
    public static final String RECEIPT_PRICE_PAID = "receipt_price_paid";
    public static final String RECEIPT_IS_FULLY_PAID = "receipt_is_fully_paid";
    public static final String RECEIPT_DISCOUNT = "receipt_discount";
    
    /*
    SELLER_ACCOUNT_TABLE columns
    */
    public static final String SELLER_ID = "seller_id";
    public static final String SELLER_ACCOUNT_FIRST_NAME = "seller_account_first_name";
    public static final String SELLER_ACCOUNT_LAST_NAME = "seller_account_last_name";
    public static final String SELLER_ACCOUNT_USERNAME = "seller_account_username";
    public static final String SELLER_ACCOUNT_PASSWORD = "seller_account_password";
    public static final String SELLER_USER_TYPE = "seller_user_type";
    
    public static final String USERTYPE_SUPERADMIN = "SuperAdmin";
    public static final String USERTYPE_REPORTMANAGER = "ReportManager";
    public static final String USERTYPE_INVENTORYMANAGER = "InventoryManager";
    public static final String USERTYPE_STORESELLER = "StoreSeller";
    
    /*
    SOLD_ITEMS_TABLE columns
    */
    public static final String SOLD_ID = "sold_id";
    public static final String SOLD_ITEM_QUANTITY = "sold_item_quantity";
    public static final String SOLD_ITEM_TOTAL_PRICE = "sold_item_total_price";
    public static final String SOLD_ITEM_TOTAL_PROFIT = "sold_item_total_profit";
    public static final String PRODUCT_ID_FK = "product_id";
    public static final String RECEIPT_ID_FK = "receipt_id";
    
    /*
    SUPPLIER_TABLE columns
    */
    public static final String SUPPLIER_ID = "supplier_id";
    public static final String SUPPLIER_NAME = "supplier_name";
    public static final String SUPPLIER_CITY = "supplier_city";
    public static final String SUPPLIER_COUNTRY = "supplier_country";
    public static final String SUPPLIER_ADDRESS = "supplier_address";
    public static final String SUPPLIER_POSTAL = "supplier_postal";
    
    /*
    SUPPLIER_CONTACTS_TABLE
    */
    public static final String SUPPLIER_CONTACTS_ID = "supplier_contacts_id";
    public static final String SUPPLIER_CONTACTS_NAME = "supplier_contacts_name";
    public static final String SUPPLIER_CONTACTS_CONTACT_NO = "supplier_contacts_contact_no";
    public static final String SUPPLIER_CONTACTS_EMAIL = "supplier_contacts_email";
    public static final String SUPPLIER_CONTACTS_POSITION = "supplier_contacts_position";
    public static final String SUPPLIER_IS_MAIN = "supplier_is_main";
    public static final String SUPPLIER_SUPPLIER_ID_FK = "supplier_id";
    
    /*
    SUPPLIER SUMMARY fields
    */
    public static final String CONTACT_NAME = "contact_name";
    public static final String CONTACT_NO = "contact_no";
    public static final String CONTACT_EMAIL = "contact_email";
    public static final String CONTACT_POSITION = "contact_position";
    public static final String CONTACT_IS_MAIN = "is_main";
    
    /*
    TOP SELLING fields
    */
    public static final String TQUANTITY = "total_quantity";
    public static final String TITEM_NAME = "item_name";
    
    /*
    ORDERS TABLE fields
    */
    public static final String ORDER_ID = "order_id";
    public static final String ORDER_NO = "order_no";
    public static final String ORDER_TIMESTAMP = "order_timestamp";
    public static final String ORDER_TOTAL_PRICE = "order_total_price";
    public static final String ORDER_SUPPLIER_ID = "supplier_id";
    public static final String ORDER_IS_PAID = "order_is_paid";
    public static final String ORDER_SELLER_ID = "order_seller_id";
    public static final String ORDER_STATUS = "order_status";
    public static final String ORDER_TARGET_DATE = "order_target_date";
    
    /*
    ORDER LINE TABLE fields
    */
    public static final String ORDER_LINE_ID = "order_line_id";
    public static final String ORDER_LINE_PRODUCT_ID = "product_id";
    public static final String ORDER_LINE_ORDER_ID = "order_id";
    public static final String ORDER_LINE_QUANTITY = "order_quantity";
    public static final String ORDER_LINE_UNIT_PRICE = "order_unit_price";
    public static final String ORDER_LINE_TOTAL_PRICE = "order_total_price";
    
    /*
    ORDERS TABLE added fields
    */
    public static final String ORDER_TOTAL_QUANTITY = "total_quantity";
    
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