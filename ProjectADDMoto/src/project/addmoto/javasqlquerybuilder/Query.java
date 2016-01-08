/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.addmoto.javasqlquerybuilder;

/**
 *
 * @author Kim Howel delos Reyes
 */
public interface Query {

    /**
     * Gets the created query string that needs to be passed to
     * Connection.prepareStatement(String).
     *
     * @return The query string.
     */
    public String getQueryString();

    /**
     * Gets the index of a placeholder.
     *
     * @param placeholderName - The name of the placeholder value.
     * @return An integer (starting at 1) that can be passed to
     * PreparedStatement::set* functions.
     */
    public int getPlaceholderIndex(String placeholderName);
}
