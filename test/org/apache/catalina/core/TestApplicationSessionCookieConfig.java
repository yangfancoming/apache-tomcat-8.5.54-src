

package org.apache.catalina.core;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.apache.catalina.LifecycleState;

public class TestApplicationSessionCookieConfig {
    private ApplicationSessionCookieConfig applicationSessionCookieConfig;
    private final CustomContext context = new CustomContext();

    @Before
    public void setUp() throws Exception {
        applicationSessionCookieConfig = new ApplicationSessionCookieConfig(
                context);
    }

    @Test
    public void testSetCommentInitPhase() {
        context.setState(LifecycleState.STARTING_PREP);
        applicationSessionCookieConfig.setComment("test");
        Assert.assertTrue(applicationSessionCookieConfig.getComment().equals("test"));
    }

    @Test(expected = IllegalStateException.class)
    public void testSetCommentNotInitPhase() {
        context.setState(LifecycleState.STARTED);
        applicationSessionCookieConfig.setComment("test");
    }

    @Test
    public void testSetDomainInitPhase() {
        context.setState(LifecycleState.STARTING_PREP);
        applicationSessionCookieConfig.setDomain("test");
        Assert.assertTrue(applicationSessionCookieConfig.getDomain().equals("test"));
    }

    @Test(expected = IllegalStateException.class)
    public void testSetDomainNotInitPhase() {
        context.setState(LifecycleState.STARTED);
        applicationSessionCookieConfig.setDomain("test");
    }

    @Test
    public void testSetHttpOnlyInitPhase() {
        context.setState(LifecycleState.STARTING_PREP);
        applicationSessionCookieConfig.setHttpOnly(true);
        Assert.assertTrue(applicationSessionCookieConfig.isHttpOnly());
    }

    @Test(expected = IllegalStateException.class)
    public void testSetHttpOnlyNotInitPhase() {
        context.setState(LifecycleState.STARTED);
        applicationSessionCookieConfig.setHttpOnly(true);
    }

    @Test
    public void testSetMaxAgeInitPhase() {
        context.setState(LifecycleState.STARTING_PREP);
        applicationSessionCookieConfig.setMaxAge(1);
        Assert.assertTrue(applicationSessionCookieConfig.getMaxAge() == 1);
    }

    @Test(expected = IllegalStateException.class)
    public void testSetMaxAgeNotInitPhase() {
        context.setState(LifecycleState.STARTED);
        applicationSessionCookieConfig.setMaxAge(1);
    }

    @Test
    public void testSetNameInitPhase() {
        context.setState(LifecycleState.STARTING_PREP);
        applicationSessionCookieConfig.setName("test");
        Assert.assertTrue(applicationSessionCookieConfig.getName().equals("test"));
    }

    @Test(expected = IllegalStateException.class)
    public void testSetNameNotInitPhase() {
        context.setState(LifecycleState.STARTED);
        applicationSessionCookieConfig.setName("test");
    }

    @Test
    public void testSetPathInitPhase() {
        context.setState(LifecycleState.STARTING_PREP);
        applicationSessionCookieConfig.setPath("test");
        Assert.assertTrue(applicationSessionCookieConfig.getPath().equals("test"));
    }

    @Test(expected = IllegalStateException.class)
    public void testSetPathNotInitPhase() {
        context.setState(LifecycleState.STARTED);
        applicationSessionCookieConfig.setPath("test");
    }

    @Test
    public void testSetSecureInitPhase() {
        context.setState(LifecycleState.STARTING_PREP);
        applicationSessionCookieConfig.setSecure(true);
        Assert.assertTrue(applicationSessionCookieConfig.isSecure());
    }

    @Test(expected = IllegalStateException.class)
    public void testSetSecureNotInitPhase() {
        context.setState(LifecycleState.STARTED);
        applicationSessionCookieConfig.setSecure(true);
    }

    private static class CustomContext extends StandardContext {
        private volatile LifecycleState state;

        @Override
        public LifecycleState getState() {
            return state;
        }

        @Override
        public synchronized void setState(LifecycleState state) {
            this.state = state;
        }
    }
}
