
package org.apache.tomcat.unittest;

import javax.servlet.SessionCookieConfig;

public class TesterSessionCookieConfig implements SessionCookieConfig {

    private String name;
    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setDomain(String domain) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getDomain() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setPath(String path) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getPath() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setComment(String comment) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getComment() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setHttpOnly(boolean httpOnly) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isHttpOnly() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setSecure(boolean secure) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isSecure() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setMaxAge(int MaxAge) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getMaxAge() {
        throw new UnsupportedOperationException();
    }
}
