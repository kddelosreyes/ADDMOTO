package project.addmoto.data;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class SellerAccount {
    
    private int sellerID;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String userType;
    
    public SellerAccount(int sellerID, String firstName, String lastName,
            String userName, String password, String userType) {
        this.sellerID = sellerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.userType = userType;
    }

    public int getSellerID() {
        return sellerID;
    }

    public void setSellerID(int sellerID) {
        this.sellerID = sellerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "SellerAccount{" + "sellerID=" + sellerID + ", firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName + ", password=" + password + ", userType=" + userType + '}';
    }
}
