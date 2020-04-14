
package org.apache.coyote;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;

@RunWith(Parameterized.class)
public class TestCompressionConfig {

    @Parameterized.Parameters(name = "{index}: accept-encoding[{0}], ETag [{1}], NoCompressionStrongETag[{2}], compress[{3}]")
    public static Collection<Object[]> parameters() {
        List<Object[]> parameterSets = new ArrayList<>();

        parameterSets.add(new Object[] { new String[] {  },              null, Boolean.TRUE,  Boolean.FALSE });
        parameterSets.add(new Object[] { new String[] { "gzip" },        null, Boolean.TRUE,  Boolean.TRUE });
        parameterSets.add(new Object[] { new String[] { "xgzip" },       null, Boolean.TRUE,  Boolean.FALSE });
        parameterSets.add(new Object[] { new String[] { "<>gzip" },      null, Boolean.TRUE,  Boolean.FALSE });
        parameterSets.add(new Object[] { new String[] { "foo", "gzip" }, null, Boolean.TRUE,  Boolean.TRUE });
        parameterSets.add(new Object[] { new String[] { "<>", "gzip" },  null, Boolean.TRUE,  Boolean.TRUE });

        parameterSets.add(new Object[] { new String[] { "gzip" },        null, Boolean.TRUE,  Boolean.TRUE });
        parameterSets.add(new Object[] { new String[] { "gzip" },        "W/", Boolean.TRUE,  Boolean.TRUE });
        parameterSets.add(new Object[] { new String[] { "gzip" },        "XX", Boolean.TRUE,  Boolean.FALSE });

        parameterSets.add(new Object[] { new String[] { "gzip" },        null, Boolean.FALSE, Boolean.TRUE });
        parameterSets.add(new Object[] { new String[] { "gzip" },        "W/", Boolean.FALSE, Boolean.TRUE });
        parameterSets.add(new Object[] { new String[] { "gzip" },        "XX", Boolean.FALSE, Boolean.TRUE });

        return parameterSets;
    }

    @Parameter(0)
    public String[] headers;
    @Parameter(1)
    public String eTag;
    @Parameter(2)
    public Boolean noCompressionStrongETag;
    @Parameter(3)
    public Boolean compress;

    @SuppressWarnings("deprecation")
    @Test
    public void testUseCompression() throws Exception {

        CompressionConfig compressionConfig = new CompressionConfig();
        // Skip length and MIME type checks
        compressionConfig.setCompression("force");
        compressionConfig.setNoCompressionStrongETag(noCompressionStrongETag.booleanValue());

        Request request = new Request();
        Response response = new Response();

        for (String header : headers) {
            request.getMimeHeaders().addValue("accept-encoding").setString(header);
        }

        if (eTag != null) {
            response.getMimeHeaders().addValue("ETag").setString(eTag);
        }


        Assert.assertEquals(compress, Boolean.valueOf(compressionConfig.useCompression(request, response)));
    }
}
