package project.addmoto.utilities;

import java.text.DecimalFormat;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class Formatter {
    
    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");
    
    public static String format(double value) {
        return decimalFormat.format(value);
    }
    
    public static String makeUpperCase(String value) {
        return value.toUpperCase();
    }
}