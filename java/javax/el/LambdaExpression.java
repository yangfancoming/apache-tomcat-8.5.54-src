
package javax.el;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class LambdaExpression {

    private final List<String> formalParameters;
    private final ValueExpression expression;
    private final Map<String,Object> nestedArguments = new HashMap<>();
    private ELContext context = null;

    public LambdaExpression(List<String> formalParameters,
            ValueExpression expression) {
        this.formalParameters = formalParameters;
        this.expression = expression;

    }

    public void setELContext(ELContext context) {
        this.context = context;
    }

    @SuppressWarnings("null") // args[i] can't be null due to earlier checks
    public Object invoke(ELContext context, Object... args)
            throws ELException {

        Objects.requireNonNull(context);

        int formalParamCount = 0;
        if (formalParameters != null) {
            formalParamCount = formalParameters.size();
        }

        int argCount = 0;
        if (args != null) {
            argCount = args.length;
        }

        if (formalParamCount > argCount) {
            throw new ELException(Util.message(context,
                    "lambdaExpression.tooFewArgs",
                    Integer.valueOf(argCount),
                    Integer.valueOf(formalParamCount)));
        }

        // Build the argument map
        // Start with the arguments from any outer expressions so if there is
        // any overlap the local arguments have priority
        Map<String,Object> lambdaArguments = new HashMap<>();
        lambdaArguments.putAll(nestedArguments);
        for (int i = 0; i < formalParamCount; i++) {
            lambdaArguments.put(formalParameters.get(i), args[i]);
        }

        context.enterLambdaScope(lambdaArguments);

        try {
            Object result = expression.getValue(context);
            // Make arguments from this expression available to any nested
            // expression
            if (result instanceof LambdaExpression) {
                ((LambdaExpression) result).nestedArguments.putAll(
                        lambdaArguments);
            }
            return result;
        } finally {
            context.exitLambdaScope();
        }
    }

    public java.lang.Object invoke(Object... args) {
        return invoke (context, args);
    }
}
