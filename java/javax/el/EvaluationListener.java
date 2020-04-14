
package javax.el;

/**
 * @since EL 3.0
 */
public abstract class EvaluationListener {

    /**
     * Fired before the evaluation of the expression.
     *
     * @param context    The EL context in which the expression will be
     *                   evaluated
     * @param expression The expression that will be evaluated
     */
    public void beforeEvaluation(ELContext context, String expression) {
        // NO-OP
    }

    /**
     * Fired after the evaluation of the expression.
     *
     * @param context    The EL context in which the expression was evaluated
     * @param expression The expression that was evaluated
     */
    public void afterEvaluation(ELContext context, String expression) {
        // NO-OP
    }

    /**
     * Fired after a property has been resolved.
     *
     * @param context  The EL context in which the property was resolved
     * @param base     The base object on which the property was resolved
     * @param property The property that was resolved
     */
    public void propertyResolved(ELContext context, Object base, Object property) {
        // NO-OP
    }
}
