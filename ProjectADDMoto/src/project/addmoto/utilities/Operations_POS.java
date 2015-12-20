package project.addmoto.utilities;

import java.text.DecimalFormat;
import java.util.ArrayList;
import project.addmoto.data.SalesItems;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class Operations_POS {
    
    public static double getTotalAmount(ArrayList<SalesItems> itemList) {
        double total = 0;
        for(SalesItems items : itemList) {
            total += items.getExtPrice();
        }
        return total;
    }
    
    public static String getTaxableAmount(double totalAmount) {
        double taxableAmount = totalAmount * 0.12f;
        return Formatter.format(taxableAmount);
    }
    
    public static String getSubtotalAmount(double totalAmount) {
        double subtotalAmount = totalAmount * 0.88f;
        return Formatter.format(subtotalAmount);
    }
    
    public static String getTotalAmountString(double totalAmount) {
        return Formatter.format(totalAmount);
    }
}
