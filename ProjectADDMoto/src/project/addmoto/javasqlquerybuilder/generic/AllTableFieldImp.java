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
public class AllTableFieldImp implements Field {

    private final String m_table;

    AllTableFieldImp(String table) {
        m_table = table;
    }

    @Override
    public String toString() {
        return CommonImp.protectTableName(m_table) + ".*";
    }
}
