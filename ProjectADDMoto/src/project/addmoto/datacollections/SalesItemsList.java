package project.addmoto.datacollections;

import java.util.ArrayList;
import project.addmoto.data.SalesItems;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class SalesItemsList extends ArrayList<SalesItems> {
    public SalesItems getItemWithCode(String addmotoCode) {
        for(SalesItems product : this) {
            if(product.getItemCode().equals(addmotoCode)) {
                return product;
            }
        }
        return null;
    }
}