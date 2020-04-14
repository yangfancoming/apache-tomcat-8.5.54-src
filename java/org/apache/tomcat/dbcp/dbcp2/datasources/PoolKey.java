

package org.apache.tomcat.dbcp.dbcp2.datasources;

import java.io.Serializable;

/**
 * @since 2.0
 */
class PoolKey implements Serializable {
    private static final long serialVersionUID = 2252771047542484533L;

    private final String dataSourceName;
    private final String userName;

    PoolKey(final String dataSourceName, final String userName) {
        this.dataSourceName = dataSourceName;
        this.userName = userName;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PoolKey other = (PoolKey) obj;
        if (dataSourceName == null) {
            if (other.dataSourceName != null) {
                return false;
            }
        } else if (!dataSourceName.equals(other.dataSourceName)) {
            return false;
        }
        if (userName == null) {
            if (other.userName != null) {
                return false;
            }
        } else if (!userName.equals(other.userName)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dataSourceName == null) ? 0 : dataSourceName.hashCode());
        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer(50);
        sb.append("PoolKey(");
        sb.append(userName).append(", ").append(dataSourceName);
        sb.append(')');
        return sb.toString();
    }
}
