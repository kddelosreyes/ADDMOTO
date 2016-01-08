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
public class StdFieldImp implements Field {

    private final String m_fieldName;

    StdFieldImp(String field) {
        m_fieldName = field;
    }

    @Override
    public String toString() {
        return '`' + m_fieldName + '`';
    }
}
