/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.addmoto.custom;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.table.AbstractTableModel;
import project.addmoto.data.ColorString;
import project.addmoto.data.SupplierProduct;
import project.addmoto.utilities.Formatter;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class SupplierProductsTableModel extends AbstractTableModel {

    private ArrayList<SupplierProduct> supplierProductsList;
    
    private String[] columnNames = {
        "Supplier Code", "ADD Moto Code", "Product Line",
        "Description", "Qty", "Unit Price",
        "Selling Price", "Status"
    };

    //private Object[][] data;

    public SupplierProductsTableModel(ArrayList<SupplierProduct> supplierProductsList) {
        
        this.supplierProductsList = supplierProductsList;

        /*data = new Object[supplierProductsList.size()][columnNames.length];

        for (int i = 0; i < supplierProductsList.size(); i++) {
            SupplierProduct sProduct = supplierProductsList.get(i);

            int threshold = sProduct.getThreshold(), quantity = sProduct.getQuantity();
            ColorString colorString = null;
            if (quantity > threshold * 1.10) {
                colorString = new ColorString(Color.GREEN, "Good");
            } else if ((quantity >= threshold * 0.90) && (quantity <= threshold * 1.10)) {
                colorString = new ColorString(Color.ORANGE, "Warning");
            } else {
                colorString = new ColorString(Color.RED, "Critical");
            }

            data[i] = new Object[]{
                sProduct.getSupplierCode(),
                sProduct.getAddmotoCode(),
                sProduct.getProductLine(),
                sProduct.getDescription(),
                sProduct.getQuantity(),
                "PhP " + Formatter.format(sProduct.getUnitPrice()),
                "PhP " + Formatter.format(sProduct.getSellingPrice()),
                colorString,
                sProduct.getProductID()
            };
        }*/
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return supplierProductsList.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        SupplierProduct supplierProduct = supplierProductsList.get(row);
        Object result = null;
        if(col == 0) {
            result = supplierProduct.getSupplierCode();
        } else if(col == 1) {
            result = supplierProduct.getAddmotoCode();
        } else if(col == 2) {
            result = supplierProduct.getProductLine();
        } else if(col == 3) {
            result = supplierProduct.getDescription();
        } else if(col == 4) {
            result = supplierProduct.getQuantity();
        } else if(col == 5) {
            result = "PhP " + Formatter.format(supplierProduct.getUnitPrice());
        } else if(col == 6) {
            result = "PhP " + Formatter.format(supplierProduct.getSellingPrice());
        } else if(col == 7) {
            int threshold = supplierProduct.getThreshold(), quantity = supplierProduct.getQuantity();
            ColorString colorString = null;
            if (quantity > threshold * 1.10) {
                colorString = new ColorString(Color.GREEN, "Good", Formatter.format(quantity * 1.0 / threshold * 100.0));
            } else if ((quantity >= threshold * 0.90) && (quantity <= threshold * 1.10)) {
                colorString = new ColorString(Color.ORANGE, "Warning", Formatter.format(quantity * 1.0 / threshold * 100.0));
            } else {
                colorString = new ColorString(Color.RED, "Critical", Formatter.format(quantity * 1.0 / threshold * 100.0));
            }
            result = colorString;
        }
        
        return result;
    }

    /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
     */
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        return false;
    }
    
    public void sortByColumn(int columnIndex) {
        System.out.println("This is called.");
        fNumClicks++;
        Comparator<SupplierProduct> comparator = null;
        switch(columnIndex) {
            case 0:
                comparator = SupplierProduct.SUPPLIER_CODE_SORT;
                break;
            case 1:
                comparator = SupplierProduct.ADDMOTO_CODE_SORT;
                break;
            case 2:
                comparator = SupplierProduct.PRODUCT_LINE_SORT;
                break;
            case 3:
                comparator = SupplierProduct.DESCRIPTION_SORT;
                break;
            case 4:
                comparator = SupplierProduct.QUANTITY_SORT;
                break;
            case 5:
                comparator = SupplierProduct.UNIT_PRICE_SORT;
                break;
            case 6:
                comparator = SupplierProduct.SELLING_PRICE_SORT;
                break;
            case 7:
                break;
        }
        Collections.sort(supplierProductsList, comparator);
        if(fNumClicks % 2 == 0) {
            Collections.reverse(supplierProductsList);
        }
        fireTableDataChanged();
    }
    
    private int fNumClicks = 0;
}