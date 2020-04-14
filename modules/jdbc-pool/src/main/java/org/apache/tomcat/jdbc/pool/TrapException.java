

package org.apache.tomcat.jdbc.pool;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
/**
 * Interceptor that traps any unhandled exception types and throws an exception that has been declared by the method
 * called, or throw an SQLException if it is declared.
 * If the caught exception is not declared, and the method doesn't throw SQLException, then this interceptor will
 * throw a RuntimeException
 *
 */
public class TrapException extends JdbcInterceptor {


    public TrapException() {
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            return super.invoke(proxy, method, args);
        }catch (Exception t) {
            Throwable exception = t;
            if (t instanceof InvocationTargetException && t.getCause() != null) {
                exception = t.getCause();
                if (exception instanceof Error) {
                    throw exception;
                }
            }
            Class<?> exceptionClass = exception.getClass();
            if (!isDeclaredException(method, exceptionClass)) {
                if (isDeclaredException(method,SQLException.class)) {
                    SQLException sqlx = new SQLException("Uncaught underlying exception.");
                    sqlx.initCause(exception);
                    exception = sqlx;
                } else {
                    RuntimeException rx = new RuntimeException("Uncaught underlying exception.");
                    rx.initCause(exception);
                    exception = rx;
                }
            }
            throw exception;
        }

    }

    public boolean isDeclaredException(Method m, Class<?> clazz) {
        for (Class<?> cl : m.getExceptionTypes()) {
            if (cl.equals(clazz) || cl.isAssignableFrom(clazz)) return true;
        }
        return false;
    }

    /**
     * no-op for this interceptor. no state is stored.
     */
    @Override
    public void reset(ConnectionPool parent, PooledConnection con) {
        // NOOP
    }

}
