package project.addmoto.controller;

import project.addmoto.database.Query;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class RegistrationController {
    
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String confirmPassword;
    private Query query;
    
    public RegistrationController(String firstName, String lastName, String username,
            String password, String confirmPassword, Query query) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.query = query;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }
    
    public boolean isValidUser() {
        return password.equals(confirmPassword) && !query.doesUsernameExists(username);
    }
    
    public boolean addUserToDatabase() {
        return query.createSellerAccount(firstName, lastName, username, password) == 1;
    }
}