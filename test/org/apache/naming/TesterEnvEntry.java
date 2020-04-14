
package org.apache.naming;

public class TesterEnvEntry {

    private static final String VALID = "valid";

    public TesterEnvEntry(String value) {
        if (!VALID.equals(value)) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        return VALID;
    }
}
