
package org.apache.catalina.realm;

import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

import org.apache.tomcat.util.security.ConcurrentMessageDigest;

public class TestMessageDigestCredentialHandler {

    private static final String[] DIGESTS = new String[] {"MD5", "SHA-1", "SHA-512"};

    private static final String PWD = "password";

    static {
        try {
            ConcurrentMessageDigest.init("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Test
    public void testGeneral() throws Exception {
        for (String digest : DIGESTS) {
            for (int saltLength = 0; saltLength < 20; saltLength++) {
                for (int iterations = 1; iterations < 100; iterations += 10)
                doTest(digest, saltLength, iterations);
            }
        }
    }

    private void doTest(String digest, int saltLength, int iterations) throws NoSuchAlgorithmException {
        MessageDigestCredentialHandler mdch = new MessageDigestCredentialHandler();
        MessageDigestCredentialHandler verifier = new MessageDigestCredentialHandler();
        mdch.setAlgorithm(digest);
        mdch.setIterations(iterations);
        mdch.setSaltLength(saltLength);
        verifier.setAlgorithm(digest);
        String storedCredential = mdch.mutate(PWD);
        Assert.assertTrue(verifier.matches(PWD, storedCredential));
    }
}
