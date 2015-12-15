package project.addmoto.data;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class Logs {
    
    private int logID;
    private String logDatetime;
    private int sellerID;
    
    public Logs(int logID, String logDatetime, int sellerID) {
        this.logID = logID;
        this.logDatetime = logDatetime;
        this.sellerID = sellerID;
    }

    public int getLogID() {
        return logID;
    }

    public void setLogID(int logID) {
        this.logID = logID;
    }

    public String getLogDatetime() {
        return logDatetime;
    }

    public void setLogDatetime(String logDatetime) {
        this.logDatetime = logDatetime;
    }

    public int getSellerID() {
        return sellerID;
    }

    public void setSellerID(int sellerID) {
        this.sellerID = sellerID;
    }
}
