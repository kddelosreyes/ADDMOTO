/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.addmoto.javasqlquerybuilder.generic;

import project.addmoto.javasqlquerybuilder.Delete;
import project.addmoto.javasqlquerybuilder.Factory;
import project.addmoto.javasqlquerybuilder.Field;
import project.addmoto.javasqlquerybuilder.Insert;
import project.addmoto.javasqlquerybuilder.Query;
import project.addmoto.javasqlquerybuilder.Select;
import project.addmoto.javasqlquerybuilder.Update;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class FactoryImp implements Factory {

    private static Field m_allField = new AllFieldImp();

    @Override
    public Field newAllField() {
        return m_allField;
    }

    @Override
    public Field newAllField(String table) {
        return new AllTableFieldImp(table);
    }

    @Override
    public Field newStdField(String name) {
        return new StdFieldImp(name);
    }

    @Override
    public Field newQualifiedField(String table, String name) {
        return new QualifiedFieldImp(table, name);
    }

    @Override
    public Field newSum(Field field, String alias) {
        return new AggregateFieldImp(field, "SUM", alias);
    }

    @Override
    public Field newCount(Field field, String alias) {
        return new AggregateFieldImp(field, "COUNT", alias);
    }

    @Override
    public Field newAvg(Field field, String alias) {
        return new AggregateFieldImp(field, "AVG", alias);
    }

    @Override
    public Field newMin(Field field, String alias) {
        return new AggregateFieldImp(field, "MIN", alias);
    }

    @Override
    public Field newMax(Field field, String alias) {
        return new AggregateFieldImp(field, "MAX", alias);
    }

    @Override
    public Field newCustomField(String custom) {
        return new CustomFieldImp(custom);
    }

    @Override
    public Select newSelectQuery() {
        return new SelectImp();
    }

    @Override
    public Update newUpdateQuery() {
        return new UpdateImp();
    }

    @Override
    public Delete newDeleteQuery() {
        return new DeleteImp();
    }

    @Override
    public Insert newInsertQuery() {
        return new InsertImp();
    }

    @Override
    public Query newQuery(String sql) {
        return new CustomQuery(sql);
    }
}
