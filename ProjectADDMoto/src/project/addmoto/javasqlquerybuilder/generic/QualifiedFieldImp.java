/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.addmoto.javasqlquerybuilder.generic;

import project.addmoto.javasqlquerybuilder.Field;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class QualifiedFieldImp implements Field {

    private final String m_table;
    private final String m_field;

    QualifiedFieldImp(String table, String field) {
        m_table = table;
        m_field = field;
    }

    @Override
    public String toString() {
        return CommonImp.protectTableName(m_table) + ".`" + m_field + '`';
    }
}
