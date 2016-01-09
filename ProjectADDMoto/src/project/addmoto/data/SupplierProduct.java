/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.addmoto.data;

import java.io.Serializable;
import java.util.Comparator;
import project.addmoto.annotation.Column;
import project.addmoto.annotation.Table;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class SupplierProduct implements Serializable,
        Comparable<SupplierProduct> {
    
    @Column(columnName = "P.productID")
    private int productID;
    
    @Column(columnName = "P.product_supplier_code")
    private String supplierCode;
    
    @Column(columnName = "P.product_addmoto_code")
    private String addmotoCode;
    
    @Column(columnName = "L.product_line_name")
    private String productLine;
    
    @Column(columnName = "P.product_description + P.product_characteristic + p.motors")
    private String description;
    
    @Column(columnName = "P.product_current_quantity")
    private int quantity;
    
    @Column(columnName = "P.product_threshold_count")
    private int threshold;
    
    @Column(columnName = "P.product_unit_price")
    private double unitPrice;
    
    @Column(columnName = "P.product_selling_price")
    private double sellingPrice;
    
    @Table(tableName = "SUPPLIER_TABLE S join PRODUCTS_TABLE P join PRODUCT_LINE_TABLE L")
    public SupplierProduct(
            int productID,
            String supplierCode,
            String addmotoCode,
            String productLine,
            String description,
            int quantity,
            int threshold,
            double unitPrice,
            double sellingPrice) {
        
        this.productID = productID;
        this.supplierCode = supplierCode;
        this.addmotoCode = addmotoCode;
        this.productLine = productLine;
        this.description = description;
        this.quantity = quantity;
        this.threshold = threshold;
        this.unitPrice = unitPrice;
        this.sellingPrice = sellingPrice;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getAddmotoCode() {
        return addmotoCode;
    }

    public void setAddmotoCode(String addmotoCode) {
        this.addmotoCode = addmotoCode;
    }

    public String getProductLine() {
        return productLine;
    }

    public void setProductLine(String productLine) {
        this.productLine = productLine;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    @Override
    public String toString() {
        return "SupplierProduct{" + "productID=" + productID + ", supplierCode=" + supplierCode + ", addmotoCode=" + addmotoCode + ", productLine=" + productLine + ", description=" + description + ", quantity=" + quantity + ", threshold=" + threshold + ", unitPrice=" + unitPrice + ", sellingPrice=" + sellingPrice + '}';
    }
    
    private static final int EQUAL = 0;
    private static final int DESCENDING = -1;
    
    private boolean areEqual(Object o1, Object o2) {
        return o1 == null ? o1 == null : o1.equals(o1);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof SupplierProduct)) return false;
        SupplierProduct that = (SupplierProduct) obj;
        
        return areEqual(this.addmotoCode, that.addmotoCode)
                && areEqual(this.supplierCode, that.supplierCode)
                && areEqual(this.productID, that.productID)
                && areEqual(this.productLine, that.productLine)
                && areEqual(this.description, that.description)
                && areEqual(this.quantity, that.quantity)
                && areEqual(this.threshold, that.threshold)
                && areEqual(this.unitPrice, that.unitPrice)
                && areEqual(this.sellingPrice, that.sellingPrice);
    }

    @Override
    public int compareTo(SupplierProduct that) {
        if(this == that) return EQUAL;
        int comparison = DESCENDING*comparePossiblyNull(this.productID, that.productID);
        if ( comparison != EQUAL ) return comparison;
        return EQUAL;
    }
    
    public static Comparator<SupplierProduct> SUPPLIER_CODE_SORT = new Comparator<SupplierProduct>() {
        @Override
        public int compare(SupplierProduct aThis, SupplierProduct aThat) {
            System.out.println("Supplier Code Comparator");
            if (aThis == aThat) {
                return EQUAL;
            }

            int comparison = aThis.supplierCode.compareTo(aThat.supplierCode);
            if (comparison != EQUAL) {
                return comparison;
            }
            return EQUAL;
        }
    };
    
    public static Comparator<SupplierProduct> ADDMOTO_CODE_SORT = new Comparator<SupplierProduct>() {
        @Override
        public int compare(SupplierProduct aThis, SupplierProduct aThat) {
            System.out.println("ADD Moto Code Comparator");
            if (aThis == aThat) {
                return EQUAL;
            }

            int comparison = aThis.addmotoCode.compareTo(aThat.addmotoCode);
            if (comparison != EQUAL) {
                return comparison;
            }
            return EQUAL;
        }
    };
    
    public static Comparator<SupplierProduct> PRODUCT_LINE_SORT = new Comparator<SupplierProduct>() {
        @Override
        public int compare(SupplierProduct aThis, SupplierProduct aThat) {
            System.out.println("Product Line Sort Comparator");
            if (aThis == aThat) {
                return EQUAL;
            }

            int comparison = aThis.productLine.compareTo(aThat.productLine);
            if (comparison != EQUAL) {
                return comparison;
            }
            return EQUAL;
        }
    };
    
    public static Comparator<SupplierProduct> DESCRIPTION_SORT = new Comparator<SupplierProduct>() {
        @Override
        public int compare(SupplierProduct aThis, SupplierProduct aThat) {
            System.out.println("Description Sort Comparator");
            if (aThis == aThat) {
                return EQUAL;
            }

            int comparison = aThis.description.compareTo(aThat.description);
            if (comparison != EQUAL) {
                return comparison;
            }
            return EQUAL;
        }
    };
    
    public static Comparator<SupplierProduct> QUANTITY_SORT = new Comparator<SupplierProduct>() {
        @Override
        public int compare(SupplierProduct aThis, SupplierProduct aThat) {
            System.out.println("Quantity Sort Comparator");
            if (aThis == aThat) {
                return EQUAL;
            }

            int comparison = aThis.quantity - aThat.quantity;
            if (comparison != EQUAL) {
                return comparison;
            }
            return EQUAL;
        }
    };
    
    public static Comparator<SupplierProduct> UNIT_PRICE_SORT = new Comparator<SupplierProduct>() {
        @Override
        public int compare(SupplierProduct aThis, SupplierProduct aThat) {
            System.out.println("Unit Price Comparator");
            if (aThis == aThat) {
                return EQUAL;
            }

            int comparison = (int)(aThis.unitPrice * 100) - (int)(aThat.unitPrice * 100);
            if (comparison != EQUAL) {
                return comparison;
            }
            return comparison;
        }
    };
    
    public static Comparator<SupplierProduct> SELLING_PRICE_SORT = new Comparator<SupplierProduct>() {
        @Override
        public int compare(SupplierProduct aThis, SupplierProduct aThat) {
            System.out.println("Selling Price Comparator");
            if (aThis == aThat) {
                return EQUAL;
            }

            int comparison = (int)(aThis.sellingPrice * 100) - (int)(aThat.sellingPrice * 100);
            if (comparison != EQUAL) {
                return comparison;
            }
            return comparison;
        }
    };
    
    private static <T extends Comparable<T>> int comparePossiblyNull(T aThis, T aThat) {
        int result = EQUAL;
        int BEFORE = -1;
        int AFTER = 1;

        if (aThis != null && aThat != null) {
            result = aThis.compareTo(aThat);
        } else //at least one reference is null - special handling
        if (aThis == null && aThat == null) {
            //do nothing - they are not distinct 
        } else if (aThis == null && aThat != null) {
            result = BEFORE;
        } else if (aThis != null && aThat == null) {
            result = AFTER;
        }
        return result;
    }
}