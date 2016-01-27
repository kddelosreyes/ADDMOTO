package project.addmoto.data;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class Expense {
    
    private int expenseID;
    private double expenseAmount;
    private String expenseTimestamp;
    private String expenseReason;
    private String expenseDetail;
    private int sellerID;
    
    public Expense(int expenseID, double expenseAmount, String expenseTimestamp,
            String expenseReason, String expenseDetail, int sellerID) {
        this.expenseID = expenseID;
        this.expenseAmount = expenseAmount;
        this.expenseTimestamp = expenseTimestamp;
        this.expenseReason = expenseReason;
        this.expenseDetail = expenseDetail;
        this.sellerID = sellerID;
    }
    
    public Expense(double expenseAmount, String expenseTimestamp,
            String expenseReason, String expenseDetail, int sellerID) {
        this.expenseAmount = expenseAmount;
        this.expenseTimestamp = expenseTimestamp;
        this.expenseReason = expenseReason;
        this.expenseDetail = expenseDetail;
        this.sellerID = sellerID;
    }

    public int getExpenseID() {
        return expenseID;
    }

    public void setExpenseID(int expenseID) {
        this.expenseID = expenseID;
    }

    public double getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(double expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public String getExpenseTimestamp() {
        return expenseTimestamp;
    }

    public void setExpenseTimestamp(String expenseTimestamp) {
        this.expenseTimestamp = expenseTimestamp;
    }

    public String getExpenseReason() {
        return expenseReason;
    }

    public void setExpenseReason(String expenseReason) {
        this.expenseReason = expenseReason;
    }

    public String getExpenseDetail() {
        return expenseDetail;
    }

    public void setExpenseDetail(String expenseDetail) {
        this.expenseDetail = expenseDetail;
    }

    public int getSellerID() {
        return sellerID;
    }

    public void setSellerID(int sellerID) {
        this.sellerID = sellerID;
    }
}