
package org.apache.catalina.startup;

import java.security.SecureRandom;
import java.util.Random;

public class FastNonSecureRandom extends SecureRandom {

    private static final long serialVersionUID = 1L;

    private final Random random = new Random();

    @Override
    public String getAlgorithm() {
        return "INSECURE";
    }

    @Override
    public synchronized void setSeed(byte[] seed) {
        // Not implemented
    }

    @Override
    public synchronized void setSeed(long seed) {
        // The super class constructor calls this method earlier than our
        // fields are initialized. Ignore the call.
        if (random == null) {
            return;
        }
        random.setSeed(seed);
    }

    @Override
    public synchronized void nextBytes(byte[] bytes) {
        random.nextBytes(bytes);
    }

    @Override
    public byte[] generateSeed(int numBytes) {
        byte[] value = new byte[numBytes];
        nextBytes(value);
        return value;
    }

}