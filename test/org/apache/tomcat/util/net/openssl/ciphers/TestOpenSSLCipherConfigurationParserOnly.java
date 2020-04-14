
package org.apache.tomcat.util.net.openssl.ciphers;

import java.util.LinkedHashSet;

import org.junit.Assert;
import org.junit.Test;


/*
 * The unit test is independent of OpenSSL version and does not require OpenSSL
 * to be present.
 */
public class TestOpenSSLCipherConfigurationParserOnly {

    @Test
    public void testDefaultSort01() throws Exception {
        // Reproducing a failure observed on Gump with OpenSSL 1.1.x

        // Everything else being equal, AES is preferred
        LinkedHashSet<Cipher> input = new LinkedHashSet<>();
        input.add(Cipher.TLS_ECDHE_RSA_WITH_CAMELLIA_256_CBC_SHA384);
        input.add(Cipher.TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_CBC_SHA384);
        input.add(Cipher.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384);
        input.add(Cipher.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384);
        LinkedHashSet<Cipher> result = OpenSSLCipherConfigurationParser.defaultSort(input);

        LinkedHashSet<Cipher> expected = new LinkedHashSet<>();
        expected.add(Cipher.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384);
        expected.add(Cipher.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384);
        expected.add(Cipher.TLS_ECDHE_RSA_WITH_CAMELLIA_256_CBC_SHA384);
        expected.add(Cipher.TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_CBC_SHA384);

        Assert.assertEquals(expected.toString(), result.toString());
    }

    @Test
    public void testDefaultSort02() throws Exception {
        // Reproducing a failure observed on Gump with OpenSSL 1.1.x

        // ECHDE should beat AES
        LinkedHashSet<Cipher> input = new LinkedHashSet<>();
        input.add(Cipher.TLS_RSA_WITH_AES_256_CBC_SHA);
        input.add(Cipher.TLS_ECDHE_RSA_WITH_CAMELLIA_256_CBC_SHA384);
        LinkedHashSet<Cipher> result = OpenSSLCipherConfigurationParser.defaultSort(input);

        LinkedHashSet<Cipher> expected = new LinkedHashSet<>();
        expected.add(Cipher.TLS_ECDHE_RSA_WITH_CAMELLIA_256_CBC_SHA384);
        expected.add(Cipher.TLS_RSA_WITH_AES_256_CBC_SHA);

        Assert.assertEquals(expected.toString(), result.toString());
    }

    @Test
    public void testDefaultSort03() throws Exception {
        // Reproducing a failure observed on Gump with OpenSSL 1.1.x

        // AES should beat CAMELLIA
        LinkedHashSet<Cipher> input = new LinkedHashSet<>();
        input.add(Cipher.TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_CBC_SHA384);
        input.add(Cipher.TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA384);
        LinkedHashSet<Cipher> result = OpenSSLCipherConfigurationParser.defaultSort(input);

        LinkedHashSet<Cipher> expected = new LinkedHashSet<>();
        expected.add(Cipher.TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA384);
        expected.add(Cipher.TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_CBC_SHA384);

        Assert.assertEquals(expected.toString(), result.toString());
    }

    @Test
    public void testRename01() throws Exception {
        // EDH -> DHE
        LinkedHashSet<Cipher> result =
                OpenSSLCipherConfigurationParser.parse("EXP-EDH-DSS-DES-CBC-SHA");
        LinkedHashSet<Cipher> expected = new LinkedHashSet<>();
        expected.add(Cipher.TLS_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void testCustomOrdering() throws Exception {
        // https://bz.apache.org/bugzilla/show_bug.cgi?id=59081
        LinkedHashSet<Cipher> result = OpenSSLCipherConfigurationParser.parse(
                "ECDHE-RSA-AES256-SHA384:ECDHE-RSA-AES256-SHA:ECDHE-RSA-DES-CBC3-SHA:" +
                "DHE-RSA-AES256-SHA:DHE-RSA-AES128-SHA:DES-CBC3-SHA");
        LinkedHashSet<Cipher> expected = new LinkedHashSet<>();
        expected.add(Cipher.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384);
        expected.add(Cipher.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA);
        expected.add(Cipher.TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA);
        expected.add(Cipher.TLS_DHE_RSA_WITH_AES_256_CBC_SHA);
        expected.add(Cipher.TLS_DHE_RSA_WITH_AES_128_CBC_SHA);
        expected.add(Cipher.TLS_RSA_WITH_3DES_EDE_CBC_SHA);

        Assert.assertEquals(expected.toString(), result.toString());
    }
}
