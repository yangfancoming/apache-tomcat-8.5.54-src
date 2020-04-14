
package org.apache.catalina.realm;

import java.security.Principal;

import org.junit.Assert;
import org.junit.Test;

public class TestMemoryRealm {

    /**
     * Unknown user triggers NPE.
     */
    @Test
    public void testBug56246() {
        MemoryRealm memoryRealm = new MemoryRealm();
        memoryRealm.setCredentialHandler(new MessageDigestCredentialHandler());

        Principal p = memoryRealm.authenticate("foo", "bar");

        Assert.assertNull(p);
    }
}
