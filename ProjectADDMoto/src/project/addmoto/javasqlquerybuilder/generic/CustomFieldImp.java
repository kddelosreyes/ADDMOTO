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
public class CustomFieldImp implements Field {

    private final String m_custom;

    CustomFieldImp(String custom) {
        m_custom = custom;
    }

    @Override
    public String toString() {
        return m_custom;
    }
}
