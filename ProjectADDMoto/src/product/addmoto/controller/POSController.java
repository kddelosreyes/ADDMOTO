package product.addmoto.controller;

import project.addmoto.data.Products;
import project.addmoto.datacollections.ProductsList;
import project.addmoto.utilities.Operations_POS;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class POSController {
    
    private ProductsList productList;
    
    public POSController() {
        productList = new ProductsList();
    }
    
    public void addProduct(Products product) {
        productList.add(product);
    }
    
    public boolean removeProduct(Products product) {
        for(Products _product : productList) {
            if(product.getAddmotoCode().equals(_product.getAddmotoCode())) {
                return productList.remove(_product);
            }
        }
        return false;
    }
    
    public double getTotalPrice() {
        return Operations_POS.getTotalAmount(productList);
    }
    
    public String getTaxablePrice() {
        return Operations_POS.getTaxableAmount(getTotalPrice());
    }
    
    public String getSubtotalPrice() {
        return Operations_POS.getSubtotalAmount(getTotalPrice());
    }
    
    public String getTotalPriceString() {
        return Operations_POS.getTotalAmountString(getTotalPrice());
    }
}