
package org.apache.catalina.valves.rewrite;

import java.util.HashMap;
import java.util.Map;

public class TesterRewriteMapA implements RewriteMap {

    private static final Map<String,String> map = new HashMap<>();

    static {
        map.put("a", "aa");
        map.put("aa", "aaaa");
        map.put("b", "bb");
    }

    @Override
    public String setParameters(String params) {
        // NO-OP
        return null;
    }

    @Override
    public String lookup(String key) {
        return map.get(key);
    }
}
