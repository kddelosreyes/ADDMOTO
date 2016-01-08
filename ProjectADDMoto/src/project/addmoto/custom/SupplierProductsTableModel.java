/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.addmoto.custom;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import project.addmoto.data.ColorString;
import project.addmoto.data.SupplierProduct;
import project.addmoto.utilities.Formatter;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class SupplierProductsTableModel extends AbstractTableModel {

    private String[] columnNames = {
        "Supplier Code", "ADD Moto Code", "Product Line",
        "Description", "Qty", "Unit Price",
        "Selling Price", "Status"
    };

    private Object[][] data;

    public SupplierProductsTableModel(ArrayList<SupplierProduct> supplierProductsList) {

        data = new Object[supplierProductsList.size()][columnNames.length];

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
        }
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
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
}