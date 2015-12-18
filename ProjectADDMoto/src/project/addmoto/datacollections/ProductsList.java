package project.addmoto.datacollections;

import java.util.ArrayList;
import project.addmoto.data.Products;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class ProductsList extends ArrayList<Products> {
    public Products getProductWithCode(String addmotoCode) {
        for(Products product : this) {
            if(product.getAddmotoCode().equals(addmotoCode)) {
                return product;
            }
        }
        return null;
    }
}