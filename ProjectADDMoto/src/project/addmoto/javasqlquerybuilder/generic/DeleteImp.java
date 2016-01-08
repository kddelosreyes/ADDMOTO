/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.addmoto.javasqlquerybuilder.generic;

import project.addmoto.javasqlquerybuilder.Delete;
import project.addmoto.javasqlquerybuilder.Where;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class DeleteImp implements Delete {

    private String m_table;
    private boolean m_all;
    private Where m_where;

    @Override
    public String getQueryString() {
        if (m_table == null) {
            throw new IllegalStateException("Must specify a table");
        }

        if (m_all == false && m_where == null) {
            throw new IllegalStateException("Must call all() to delete all records");
        }

        StringBuilder builder = new StringBuilder("DELETE FROM ");
        builder.append(CommonImp.protectTableName(m_table));

        if (m_where != null) {
            builder.append(m_where.toString());
        }

        return builder.toString();
    }

    @Override
    public int getPlaceholderIndex(String placeholderName) {
        if (m_where != null) {
            return m_where.getPlaceholderIndex(placeholderName);
        } else {
            throw new IllegalArgumentException("Placeholder doesn't exist");
        }
    }

    @Override
    public Where where() {
        m_where = new WhereImp(false, 1);
        return m_where;
    }

    @Override
    public Delete all() {
        m_all = true;
        return this;
    }

    @Override
    public Delete from(String table) {
        m_table = table;
        return this;
    }
}
