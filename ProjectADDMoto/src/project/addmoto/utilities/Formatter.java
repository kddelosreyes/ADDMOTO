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
    private static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("MMM dd, yyyy, HH:mm:ss");
    private static SimpleDateFormat dFormat = new SimpleDateFormat("yyyy MM dd HH mm ss");
    
    public static String format(double value) {
        return decimalFormat.format(value);
    }
    
    public static String makeUpperCase(String value) {
        return value.toUpperCase();
    }
    
    public static String formatDate(Date date) {
        return dateTimeFormat.format(date);
    }
    
    public static String formatReceipt(Date date) {
        return dFormat.format(date);
    }
}