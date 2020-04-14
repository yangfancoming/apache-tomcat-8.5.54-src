
package org.apache.tomcat.util.buf;

import java.util.Locale;

import org.apache.tomcat.util.res.StringManager;

public enum EncodedSolidusHandling {
    DECODE("decode"),
    REJECT("reject"),
    PASS_THROUGH("passthrough");

    private static final StringManager sm = StringManager.getManager(EncodedSolidusHandling.class);

    private final String value;

    EncodedSolidusHandling(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static EncodedSolidusHandling fromString(String from) {
        String trimmedLower = from.trim().toLowerCase(Locale.ENGLISH);

        for (EncodedSolidusHandling value : EncodedSolidusHandling.values()) {
            if (value.getValue().equals(trimmedLower)) {
                return value;
            }
        }

        throw new IllegalStateException(sm.getString("encodedSolidusHandling.invalid", from));
    }
}
