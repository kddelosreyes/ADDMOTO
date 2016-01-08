/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.addmoto.javasqlquerybuilder.generic;

import java.util.ArrayList;
import java.util.List;
import project.addmoto.javasqlquerybuilder.Field;
import project.addmoto.javasqlquerybuilder.Select;
import project.addmoto.javasqlquerybuilder.Where;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class SelectImp implements Select {

    private int m_offset;
    private int m_limit;
    private boolean m_haveLimit;
    private Field[] m_orderBy;
    private OrderBy m_orderByOrder;
    private Where m_havingClause;
    private Where m_where;
    private Field[] m_groupBy;

    public SelectImp() {
    }

    private class JoinInfo {

        final Field leftSide;
        final Field rightSide;
        final JoinType joinType;
        final String table;

        JoinInfo(Field left, Field right, String tabl, JoinType type) {
            leftSide = left;
            rightSide = right;
            joinType = type;
            table = tabl;
        }
    }

    private List<JoinInfo> m_joinList;
    private Field[] m_selectFields;
    private boolean m_distinct;
    private String m_table;

    private String joinTypeToString(JoinType joinType) {
        switch (joinType) {
            case DEFAULT:
                return "";
            case LEFT_OUTER:
                return "LEFT OUTER";
            case RIGHT_OUTER:
                return "RIGHT OUTER";
            case INNER:
            case OUTER:
            case LEFT:
            case RIGHT:
            default:
                return joinType.toString();
        }
    }

    @Override
    public String getQueryString() {
        StringBuilder builder = new StringBuilder("SELECT ");

        if (m_table == null) {
            throw new IllegalStateException("Must specify table");
        }

        if (m_selectFields == null) {
            throw new IllegalStateException("Must specify some fields");
        }

        if (m_distinct) {
            builder.append("DISTINCT ");
        }

        CommonImp.joinFieldNames(builder, m_selectFields);
        builder.append(" FROM ");
        builder.append(CommonImp.protectTableName(m_table));
        builder.append(' ');

        if (m_joinList != null) {
            for (JoinInfo join : m_joinList) {
                builder.append(joinTypeToString(join.joinType));
                builder.append(" JOIN ");
                builder.append(CommonImp.protectTableName(join.table));
                builder.append(" ON ");
                builder.append(join.leftSide.toString());
                builder.append(" = ");
                builder.append(join.rightSide.toString());
            }
        }

        if (m_where != null) {
            builder.append(m_where.toString());
        }

        if (m_groupBy != null) {
            builder.append(" GROUP BY ");
            CommonImp.joinFieldNames(builder, m_groupBy);
        }

        if (m_havingClause != null) {
            builder.append(m_havingClause.toString());
        }

        if (m_orderBy != null) {
            builder.append(" ORDER BY ");
            CommonImp.joinFieldNames(builder, m_orderBy);
            builder.append(' ');
            builder.append(m_orderByOrder.toString());
        }

        if (m_haveLimit) {
            builder.append(" LIMIT ");
            builder.append(m_offset);
            builder.append(", ");
            builder.append(m_limit);
        }

        return builder.toString();
    }

    @Override
    public int getPlaceholderIndex(String placeholderName) {
        int idx = 0;
        if (m_havingClause != null) {
            idx = m_havingClause.getPlaceholderIndex(placeholderName);
        }

        if (idx == 0 && m_where != null) {
            idx = m_where.getPlaceholderIndex(placeholderName);
        }

        if (idx == 0) {
            throw new IllegalArgumentException("Placeholder doesn't exist");
        }

        return idx;
    }

    @Override
    public Select select(Field... fields) {
        m_selectFields = fields;
        return this;
    }

    @Override
    public Select from(String table) {
        m_table = table;
        return this;
    }

    @Override
    public Where where() {
        m_where = new WhereImp(false, 1);
        return m_where;
    }

    @Override
    public Select distinct() {
        m_distinct = true;
        return this;
    }

    @Override
    public Select join(String table, Field field1, Field field2,
            JoinType joinType) {
        if (m_joinList == null) {
            m_joinList = new ArrayList<JoinInfo>(2);
        }

        m_joinList.add(new JoinInfo(field1, field2, table, joinType));
        return this;
    }

    @Override
    public Select join(String table, Field field1, Field field2) {
        if (m_joinList == null) {
            m_joinList = new ArrayList<JoinInfo>(2);
        }

        m_joinList.add(new JoinInfo(field1, field2, table, JoinType.DEFAULT));
        return this;
    }

    @Override
    public Select groupBy(Field... fields) {
        m_groupBy = fields;
        return this;
    }

    @Override
    public Where having() {
        m_havingClause = new WhereImp(true, m_where == null ? 1 : m_where.getPlaceholderCount() + 1);
        return m_havingClause;
    }

    @Override
    public Select orderBy(OrderBy order, Field... fields) {
        m_orderBy = fields;
        m_orderByOrder = order;
        return this;
    }

    @Override
    public Select limit(int offset, int count) {
        m_offset = offset;
        m_limit = count;
        m_haveLimit = true;
        return this;
    }
}
