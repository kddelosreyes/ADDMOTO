/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.addmoto.javasqlquerybuilder.generic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import project.addmoto.javasqlquerybuilder.Field;
import project.addmoto.javasqlquerybuilder.Update;
import project.addmoto.javasqlquerybuilder.Where;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class UpdateImp implements Update {

    private String m_table;
    private Map<String, Integer> m_placeholders;
    private List<Field> m_fields;
    private boolean m_all;
    private Where m_where;

    public UpdateImp() {
    }

    @Override
    public String getQueryString() {
        if (m_fields == null || m_table == null || m_placeholders == null) {
            throw new IllegalStateException("Table name or fields missing");
        }

        if (m_where == null && m_all == false) {
            throw new IllegalStateException("Must call all() to update all records");
        }

        StringBuilder builder = new StringBuilder("UPDATE ");
        builder.append(CommonImp.protectTableName(m_table));
        builder.append(" SET ");

        int fieldCnt = 0;
        for (Field field : m_fields) {
            builder.append(field.toString());
            builder.append(" = ?");

            if (fieldCnt != m_fields.size() - 1) {
                builder.append(", ");
            }
            fieldCnt++;
        }

        if (m_where != null) {
            builder.append(m_where.toString());
        }

        return builder.toString();
    }

    @Override
    public int getPlaceholderIndex(String placeholderName) {
        if (m_placeholders == null) {
            throw new IllegalArgumentException("Placeholder doesn't exist");
        }

        Integer idx = m_placeholders.get(placeholderName);

        if (idx == null) {
            idx = m_where.getPlaceholderIndex(placeholderName);
            if (idx == 0) {
                throw new IllegalArgumentException("Placeholder doesn't exist");
            }
        }
        return idx;
    }

    @Override
    public Update set(Field field, String placeholder) {
        if (m_fields == null) {
            m_fields = new ArrayList<Field>();
        }
        if (m_placeholders == null) {
            m_placeholders = new HashMap<String, Integer>();
        }

        if (m_placeholders.containsKey(placeholder)) {
            throw new IllegalArgumentException("Duplicate placeholder");
        }

        m_fields.add(field);
        m_placeholders.put(placeholder, m_placeholders.size() + 1);
        return this;
    }

    @Override
    public Where where() {
        m_where = new WhereImp(false, m_placeholders == null ? 1 : m_placeholders.size() + 1);
        return m_where;
    }

    @Override
    public Update all() {
        m_all = true;
        return this;
    }

    @Override
    public Update inTable(String table) {
        m_table = table;
        return this;
    }
}
