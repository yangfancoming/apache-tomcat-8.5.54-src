
package javax.el;

import java.util.ArrayList;
import java.util.List;

public class TesterEvaluationListener extends EvaluationListener {

    private final List<Pair> resolvedProperties = new ArrayList<>();
    private final List<String> beforeEvaluationExpressions = new ArrayList<>();
    private final List<String> afterEvaluationExpressions = new ArrayList<>();


    @Override
    public void propertyResolved(ELContext context, Object base,
            Object property) {
        resolvedProperties.add(new Pair(base, property));
    }


    @Override
    public void beforeEvaluation(ELContext context, String expression) {
        beforeEvaluationExpressions.add(expression);
    }


    @Override
    public void afterEvaluation(ELContext context, String expression) {
        afterEvaluationExpressions.add(expression);
    }


    public List<Pair> getResolvedProperties() {
        return resolvedProperties;
    }


    public List<String> getBeforeEvaluationExpressions() {
        return beforeEvaluationExpressions;
    }


    public List<String> getAfterEvaluationExpressions() {
        return afterEvaluationExpressions;
    }


    public static class Pair {
        private final Object base;
        private final Object property;

        public Pair(Object base, Object property) {
            this.base = base;
            this.property = property;
        }

        public Object getBase() {
            return base;
        }

        public Object getProperty() {
            return property;
        }
    }
}
