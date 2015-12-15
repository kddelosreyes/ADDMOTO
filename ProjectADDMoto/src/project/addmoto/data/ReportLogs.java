package project.addmoto.data;

import java.io.File;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class ReportLogs {
    
    private int reportID;
    private int sellerID;
    private String reportTimestamp;
    private File reportFile;
    private String reportType;
    
    public ReportLogs(int reportID, int sellerID, String reportTimestamp,
            File reportFile, String reportType) {
        this.reportID = reportID;
        this.sellerID = sellerID;
        this.reportTimestamp = reportTimestamp;
        this.reportFile = reportFile;
        this.reportType = reportType;
    }

    public int getReportID() {
        return reportID;
    }

    public void setReportID(int reportID) {
        this.reportID = reportID;
    }

    public int getSellerID() {
        return sellerID;
    }

    public void setSellerID(int sellerID) {
        this.sellerID = sellerID;
    }

    public String getReportTimestamp() {
        return reportTimestamp;
    }

    public void setReportTimestamp(String reportTimestamp) {
        this.reportTimestamp = reportTimestamp;
    }

    public File getReportFile() {
        return reportFile;
    }

    public void setReportFile(File reportFile) {
        this.reportFile = reportFile;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }
}