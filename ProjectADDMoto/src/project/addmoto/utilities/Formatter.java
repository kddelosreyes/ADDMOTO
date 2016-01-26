package project.addmoto.utilities;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class Formatter {
    
    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");
    public static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("MMM dd, yyyy, HH:mm:ss");
    private static SimpleDateFormat dateTimeFormat2 = new SimpleDateFormat("MMM dd, yyyy, hh:mm:ss a");
    private static SimpleDateFormat dFormat = new SimpleDateFormat("yyyy MM dd HH mm ss");
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
    
    public static String format(double value) {
        return decimalFormat.format(value);
    }
    
    public static String makeUpperCase(String value) {
        return value.toUpperCase();
    }
    
    public static String formatDate(Date date) {
        return dateTimeFormat.format(date);
    }
    public static String formatDate2(Date date) {
        return dateTimeFormat2.format(date);
    }
    
    public static String formatReceipt(Date date) {
        return dFormat.format(date);
    }
    
    public static String formatOrderDate(Date date) {
        return dateFormat.format(date);
    }
}