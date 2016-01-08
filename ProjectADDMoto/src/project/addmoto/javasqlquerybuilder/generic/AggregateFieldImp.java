package project.addmoto.javasqlquerybuilder.generic;

import project.addmoto.javasqlquerybuilder.Field;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Kim Howel delos Reyes
 */
public class AggregateFieldImp implements Field {

    private final Field m_child;
    private final String m_func;
    private final String m_alias;

    AggregateFieldImp(Field field, String func, String alias) {
        m_child = field;
        m_func = func;
        m_alias = alias;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(m_func);
        builder.append('(');
        builder.append(m_child.toString());
        builder.append(')');

        if (m_alias != null) {
            builder.append(" AS ");
            builder.append(m_alias);
        }
        return builder.toString();
    }
}
