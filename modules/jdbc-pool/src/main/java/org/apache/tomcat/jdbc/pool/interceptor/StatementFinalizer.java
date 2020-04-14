
package org.apache.tomcat.jdbc.pool.interceptor;

import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.apache.tomcat.jdbc.pool.ConnectionPool;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.apache.tomcat.jdbc.pool.PooledConnection;

/**
 * Keeps track of statements associated with a connection and invokes close upon {@link java.sql.Connection#close()}
 * Useful for applications that dont close the associated statements after being done with a connection.
 *
 */
public class StatementFinalizer extends AbstractCreateStatementInterceptor {
    private static final Log log = LogFactory.getLog(StatementFinalizer.class);

    protected List<StatementEntry> statements = new LinkedList<>();

    private boolean logCreationStack = false;

    @Override
    public Object createStatement(Object proxy, Method method, Object[] args, Object statement, long time) {
        try {
            if (statement instanceof Statement)
                statements.add(new StatementEntry((Statement)statement));
        }catch (ClassCastException x) {
            //ignore this one
        }
        return statement;
    }

    @SuppressWarnings("null") // st is not null when used
    @Override
    public void closeInvoked() {
        while (!statements.isEmpty()) {
            StatementEntry ws = statements.remove(0);
            Statement st = ws.getStatement();
            boolean shallClose = false;
            try {
                shallClose = st!=null && (!st.isClosed());
                if (shallClose) {
                    st.close();
                }
            } catch (Exception ignore) {
                if (log.isDebugEnabled()) {
                    log.debug("Unable to closed statement upon connection close.",ignore);
                }
            } finally {
                if (logCreationStack && shallClose) {
                    log.warn("Statement created, but was not closed at:", ws.getAllocationStack());
                }
            }
        }
    }

    @Override
    public void setProperties(Map<String, PoolProperties.InterceptorProperty> properties) {
        super.setProperties(properties);

        PoolProperties.InterceptorProperty logProperty = properties.get("trace");
        if (null != logProperty) {
            logCreationStack = logProperty.getValueAsBoolean(logCreationStack);
        }
    }

    @Override
    public void reset(ConnectionPool parent, PooledConnection con) {
        statements.clear();
        super.reset(parent, con);
    }

    protected class StatementEntry {
        private WeakReference<Statement> statement;
        private Throwable allocationStack;

        public StatementEntry(Statement statement) {
            this.statement = new WeakReference<>(statement);
            if (logCreationStack) {
                this.allocationStack = new Throwable();
            }
        }

        public Statement getStatement() {
            return statement.get();
        }

        public Throwable getAllocationStack() {
            return allocationStack;
        }
    }


}
