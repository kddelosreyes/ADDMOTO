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
public interface Delete extends Query {

    /**
     * Sets the WHERE clause.
     *
     * @return A QbWhere object which is bound to this query.
     */
    public Where where();

    /**
     * Marks all records for deletion. Either this method or where must be
     * called.
     *
     * @return This query builder.
     */
    public Delete all();

    /**
     * The table you want to delete from. Must be called to make a valid delete
     * statement.
     *
     * @param table - A table name without backticks.
     * @return This query builder.
     */
    public Delete from(String table);
}
