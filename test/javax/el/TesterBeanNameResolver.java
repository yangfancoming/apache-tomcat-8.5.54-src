
package javax.el;

import java.util.HashMap;
import java.util.Map;

public class TesterBeanNameResolver extends BeanNameResolver {

    public static final String EXCEPTION_TRIGGER_NAME = "exception";
    public static final String THROWABLE_TRIGGER_NAME = "throwable";
    public static final String READ_ONLY_NAME = "readonly";

    private Map<String,Object> beans = new HashMap<>();
    private boolean allowCreate = true;


    public TesterBeanNameResolver() {
        beans.put(EXCEPTION_TRIGGER_NAME, new Object());
        beans.put(THROWABLE_TRIGGER_NAME, new Object());
        beans.put(READ_ONLY_NAME, new Object());
    }

    @Override
    public void setBeanValue(String beanName, Object value)
            throws PropertyNotWritableException {
        checkTriggers(beanName);
        if (allowCreate || beans.containsKey(beanName)) {
            beans.put(beanName, value);
        }
    }

    @Override
    public boolean isNameResolved(String beanName) {
        return beans.containsKey(beanName);
    }

    @Override
    public Object getBean(String beanName) {
        checkTriggers(beanName);
        return beans.get(beanName);
    }

    @Override
    public boolean canCreateBean(String beanName) {
        checkTriggers(beanName);
        return allowCreate;
    }


    public void setAllowCreate(boolean allowCreate) {
        this.allowCreate = allowCreate;
    }

    @Override
    public boolean isReadOnly(String beanName) {
        checkTriggers(beanName);
        return READ_ONLY_NAME.equals(beanName);
    }

    private void checkTriggers(String beanName) {
        if (EXCEPTION_TRIGGER_NAME.equals(beanName)) {
            throw new RuntimeException();
        }
        if (THROWABLE_TRIGGER_NAME.equals(beanName)) {
            throw new Error();
        }
    }
}
