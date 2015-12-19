package product.addmoto.controller;

import project.addmoto.data.Products;
import project.addmoto.data.SalesItems;
import project.addmoto.datacollections.ProductsList;
import project.addmoto.datacollections.SalesItemsList;
import project.addmoto.utilities.Operations_POS;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class POSController {
    
    private ProductsList productList;
    private SalesItemsList salesItemsList;
    
    public POSController() {
        productList = new ProductsList();
        salesItemsList = new SalesItemsList();
    }
    
    public void addProduct(Products product) {
        productList.add(product);
    }
    
    public void addItem(SalesItems item) {
        salesItemsList.add(item);
    }
    
    public SalesItems getItem(int index) {
        return salesItemsList.get(index);
    }
    
    public boolean removeProduct(Products product) {
        for(Products _product : productList) {
            if(product.getAddmotoCode().equals(_product.getAddmotoCode())) {
                return productList.remove(_product);
            }
        }
        return false;
    }
    
    public boolean removeItem(SalesItems item) {
        for(SalesItems _item : salesItemsList) {
            if(item.getItemCode().equals(_item.getItemCode())) {
                return salesItemsList.remove(_item);
            }
        }
        return false;
    }
    
    public double getTotalPrice() {
        return Operations_POS.getTotalAmount(salesItemsList);
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