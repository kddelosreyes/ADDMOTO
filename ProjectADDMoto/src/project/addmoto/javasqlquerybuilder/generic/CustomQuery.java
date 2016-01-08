/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.addmoto.javasqlquerybuilder.generic;

import project.addmoto.javasqlquerybuilder.Query;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class CustomQuery implements Query {

    private final String m_sql;

    CustomQuery(String sql) {
        m_sql = sql;
    }

    @Override
    public String getQueryString() {
        return m_sql;
    }

    @Override
    public int getPlaceholderIndex(String placeholderName) {
        throw new IllegalArgumentException("Placeholder doesn't exist");
    }
}
