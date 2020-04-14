
package javax.el;

public class TesterELContext extends ELContext {

    private final ELResolver resolver;

    public TesterELContext() {
        this(null);
    }

    public TesterELContext(ELResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public ELResolver getELResolver() {
        return resolver;
    }

    @Override
    public FunctionMapper getFunctionMapper() {
        return null;
    }

    @Override
    public VariableMapper getVariableMapper() {
        return null;
    }
}
