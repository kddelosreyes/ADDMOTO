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
public interface Insert extends Query {

    /**
     * Sets a database column placeholder.
     *
     * @param field
     * @param placeholder
     * @return This query builder.
     */
    public Insert set(Field field, String placeholder);

    /**
     * Sets the table to insert into. Must be called to make a valid insert
     * statement.
     *
     * @param table
     * @return This query builder.
     */
    public Insert inTable(String table);
}
