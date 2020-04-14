
package org.apache.catalina.tribes;

import org.apache.catalina.tribes.group.interceptors.DomainFilterInterceptor;
import org.apache.catalina.tribes.util.UUIDGenerator;

/**
 * Utility methods for use by multiple tests.
 */
public class TesterUtil {

    private TesterUtil() {
        // Hide default constructor
    }


    /*
     * Configures a set of channels to use a random domain. Use to ensure that
     * multiple instance of the test suite do not interfere when running on the
     * same machine. This may happen in a CI system or when a developer is
     * running tests for multiple branches in parallel.
     */
    public static void addRandomDomain(ManagedChannel[] channels) {
        if (channels == null) {
            return;
        }

        byte[] domain = UUIDGenerator.randomUUID(false);

        for (ManagedChannel channel : channels) {
            channel.getMembershipService().setDomain(domain);
            DomainFilterInterceptor filter = new DomainFilterInterceptor();
            filter.setDomain(domain);
            channel.addInterceptor(filter);
        }
    }
}
