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
    
    public SellerAccount(int sellerID, String firstName, String lastName,
            String userName, String password) {
        this.sellerID = sellerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
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
}
