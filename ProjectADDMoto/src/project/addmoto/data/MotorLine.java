package project.addmoto.data;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class MotorLine {
    
    private int motorLineID;
    private String motorLineName;
    
    public MotorLine(int motorLineID, String motorLineName) {
        this.motorLineID = motorLineID;
        this.motorLineName = motorLineName;
    }

    public int getMotorLineID() {
        return motorLineID;
    }

    public void setMotorLineID(int motorLineID) {
        this.motorLineID = motorLineID;
    }

    public String getMotorLineName() {
        return motorLineName;
    }

    public void setMotorLineName(String motorLineName) {
        this.motorLineName = motorLineName;
    }
}