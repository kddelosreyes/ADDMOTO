/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.addmoto.javasqlquerybuilder;

/**
 *
 * @author Kim Howel delos Reyes
 */
public interface Where {

    /**
     * The where operator.
     */
    public enum WhereOperator {
        EQUALS("="),
        NOT_EQUALS("<>"),
        LESS_THAN("<"),
        GREATER_THAN(">"),
        LESS_THAN_EQUALS("<="),
        GREATER_THAN_EQUALS(">="),
        LIKE("LIKE"),
        NOT_LIKE("NOT LIKE");

        private final String value;

        WhereOperator(String val) {
            value = val;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    /**
     * Constructs a where clause. If there are already where clauses in the
     * query, its added as a AND WHERE.
     *
     * @param field
     * @param placeholder
     * @return This where builder.
     */
    public Where where(Field field, String placeholder);

    /**
     * Similar to {@link #where(QbField, String) where} but allows you to
     * specify a where operator.
     *
     * @param field
     * @param op
     * @param placeholder
     * @return This where builder.
     */
    public Where where(Field field, WhereOperator op, String placeholder);

    /**
     * Similar to {@link #where(QbField, String) where} but is joined by an OR
     * WHERE.
     *
     * @param field
     * @param placeholder
     * @return This where builder.
     */
    public Where orWhere(Field field, String placeholder);

    /**
     * Similar to {@link #orWhere(QbField, String) orWhere} but allows you to
     * specify a where operator.
     *
     * @param field
     * @param op
     * @param placeholder
     * @return This where builder.
     */
    public Where orWhere(Field field, WhereOperator op, String placeholder);

    /**
     * Allows you to specify a custom where clause. Not recommended for use
     * unless an appropriate clause can't be constructed with other methods.
     *
     * @param custom
     * @return This where builder.
     */
    public Where where(String custom);

    /**
     * Generates an IN clause.
     *
     * @param field
     * @param placeholder - Using this placeholder will return the index of the
     * first placeholder.
     * @param count - The number of placeholders to place in the query.
     * @return This where builder.
     */
    public Where whereIn(Field field, String placeholder, int count);

    /**
     * Similar to {@link #whereIn(QbField, String, int) whereIn} but joined with
     * an OR.
     *
     * @param field
     * @param placeholder
     * @param count
     * @return This where builder.
     */
    public Where orWhereIn(Field field, String placeholder, int count);

    /**
     * Similar to {@link #whereIn(QbField, String, int) whereIn} but generates a
     * NOT IN clause.
     *
     * @param field
     * @param placeholder
     * @param count
     * @return This where builder.
     */
    public Where whereNotIn(Field field, String placeholder, int count);

    /**
     * Similar to {@link #whereNotIn(QbField, String, int) whereIn} but joined
     * with an OR.
     *
     * @param field
     * @param placeholder
     * @param count
     * @return This where builder.
     */
    public Where orWhereNotIn(Field field, String placeholder, int count);

    /**
     * Adds an opening bracket.
     *
     * @return This where builder.
     */
    public Where startBracket();

    /**
     * Adds an ending bracket.
     *
     * @return This where builder.
     */
    public Where endBracket();

    /**
     * Gets the placeholder index. Usually you don't call this directly and get
     * the placeholder index from the QbQuery which will check its own
     * placeholders as well as those from any where clause.
     *
     * @param placeholderName
     * @return The placeholder index.
     */
    public int getPlaceholderIndex(String placeholderName);

    /**
     * Gets the number of placeholders.
     */
    public int getPlaceholderCount();
}
