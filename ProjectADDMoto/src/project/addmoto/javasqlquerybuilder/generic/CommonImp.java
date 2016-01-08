/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.addmoto.javasqlquerybuilder.generic;

import java.util.List;
import project.addmoto.javasqlquerybuilder.Field;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class CommonImp {

    static String protectTableName(String table) {
        return '`' + table + '`';
    }

    static void joinFieldNames(StringBuilder builder, Field[] fields) {
        for (int i = 0; i < fields.length; i++) {
            builder.append(fields[i].toString());

            if (i != fields.length - 1) {
                builder.append(", ");
            }
        }
    }

    static void joinFieldNames(StringBuilder builder, List<Field> fields) {
        for (int i = 0; i < fields.size(); i++) {
            builder.append(fields.get(i).toString());

            if (i != fields.size() - 1) {
                builder.append(", ");
            }
        }
    }

    static void createPlaceholders(StringBuilder builder, int cnt) {
        for (int i = 0; i < cnt; i++) {
            builder.append('?');

            if (i != cnt - 1) {
                builder.append(", ");
            }
        }
    }
}
