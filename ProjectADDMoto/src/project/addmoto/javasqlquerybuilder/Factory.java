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
public interface Factory {

    /**
     * Creates an all fields selector.
     *
     * @return QbField
     */
    public Field newAllField();

    /**
     * Creates an all fields selector for the specified table.
     *
     * @param table - A table name without backticks.
     * @return QbField
     */
    public Field newAllField(String table);

    /**
     * Creates a standard database field. Will enclose the field in backticks in
     * the final query.
     *
     * @param name - A table column without backticks.
     * @return QbField
     */
    public Field newStdField(String name);

    /**
     * Creates a qualified database field. Will enclose the field and table name
     * in backticks in the final query.
     *
     * @param table - A table name without backticks.
     * @param name - A table column without backticks.
     * @return QbField
     */
    public Field newQualifiedField(String table, String name);

    /**
     * Creates a SUM aggregate function on the specified field.
     *
     * @param field - Returned by newStdField.
     * @param alias - A string to use with AS. May be null in which case no AS
     * is used.
     * @return QbField
     */
    public Field newSum(Field field, String alias);

    /**
     * Creates a count aggregate function on the specified field.
     *
     * @param field - Returned by newStdField.
     * @param alias - A string to use with AS. May be null in which case no AS
     * is used.
     * @return Field
     */
    public Field newCount(Field field, String alias);

    /**
     * Creates an average aggregate function on the specified field.
     *
     * @param field - Returned by newStdField.
     * @param alias - A string to use with AS. May be null in which case no AS
     * is used.
     * @return QbField
     */
    public Field newAvg(Field field, String alias);

    /**
     * Creates a minimum function on the specified field.
     *
     * @param field - Returned by newStdField.
     * @param alias - A string to use with AS. May be null in which case no AS
     * is used.
     * @return QbField
     */
    public Field newMin(Field field, String alias);

    /**
     * Creates a count aggregate function on the specified field.
     *
     * @param field - Returned by newStdField.
     * @param alias - A string to use with AS. May be null in which case no AS
     * is used.
     * @return QbField
     */
    public Field newMax(Field field, String alias);

    /**
     * Creates a custom field.
     *
     * @param custom - A custom string that will be used as is.
     * @return QbField
     */
    public Field newCustomField(String custom);

    /**
     * Creates a SELECT query.
     */
    public Select newSelectQuery();

    /**
     * Creates an UPDATE query.
     */
    public Update newUpdateQuery();

    /**
     * Creates a DELETE query.
     */
    public Delete newDeleteQuery();

    /**
     * Creates an INSERT query.
     */
    public Insert newInsertQuery();

    /**
     * Creates a query object with the given sql. Only use this if the query
     * builder can not build an appropriate query.
     *
     * @param sql - A custom sql string.
     * @return QbQuery
     */
    public Query newQuery(String sql);
}
