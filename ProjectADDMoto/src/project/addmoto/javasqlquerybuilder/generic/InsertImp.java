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
import project.addmoto.javasqlquerybuilder.Insert;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class InsertImp implements Insert {

    public InsertImp() {
    }
    
    private String m_table;
    private Map<String, Integer> m_placeholders;
    private List<Field> m_fields;

    @Override
    public String getQueryString() {
        if (m_fields == null || m_table == null || m_placeholders == null) {
            throw new IllegalStateException("Table name or fields missing");
        }

        StringBuilder builder = new StringBuilder("INSERT INTO ");
        builder.append(CommonImp.protectTableName(m_table));
        builder.append(" (");
        CommonImp.joinFieldNames(builder, m_fields);
        builder.append(") VALUES (");
        CommonImp.createPlaceholders(builder, m_fields.size());
        builder.append(')');

        return builder.toString();
    }

    @Override
    public int getPlaceholderIndex(String placeholderName) {
        if (m_placeholders == null) {
            throw new IllegalArgumentException("Placeholder doesn't exist");
        }

        Integer idx = m_placeholders.get(placeholderName);

        if (idx == null) {
            throw new IllegalArgumentException("Placeholder doesn't exist");
        } else {
            return idx;
        }
    }

    @Override
    public Insert set(Field field, String placeholder) {
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
    public Insert inTable(String table) {
        m_table = table;
        return this;
    }
}
