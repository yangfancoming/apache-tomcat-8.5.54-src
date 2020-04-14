
package org.apache.tomcat.buildutil.translate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Properties;

public class Import {

    public static void main(String... args) throws IOException {
        File root = new File(Constants.STORAGE_DIR);

        for (File f : root.listFiles()) {
            // Not robust but good enough
            if (f.isFile() && f.getName().startsWith(Constants.L10N_PREFIX)) {
                processFile(f);
            }
        }
    }


    @SuppressWarnings("null")
    private static void processFile(File f) throws IOException {
        String language = Utils.getLanguage(f.getName());

        // Unlike the master branch, don't skip the original so we can import
        // updates to the English translations
        Properties props = Utils.load(f);
        Object[] objKeys = props.keySet().toArray();
        Arrays.sort(objKeys);

        String currentPkg = null;
        Writer w = null;
        String currentGroup = "zzz";

        for (Object objKey : objKeys) {
            String key = (String) objKey;
            CompositeKey cKey = new CompositeKey(key);

            if (!cKey.pkg.equals(currentPkg)) {
                currentPkg = cKey.pkg;
                if (w != null) {
                    w.close();
                }
                File outFile = new File(currentPkg.replace('.', File.separatorChar), Constants.L10N_PREFIX + language + Constants.L10N_SUFFIX);
                FileOutputStream fos = new FileOutputStream(outFile);
                w = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
                org.apache.tomcat.buildutil.Utils.insertLicense(w);
            }

            if (!currentGroup.equals(cKey.group)) {
                currentGroup = cKey.group;
                w.write(System.lineSeparator());
            }

            w.write(cKey.key + "=" + Utils.formatValue(props.getProperty(key)));
            w.write(System.lineSeparator());
        }
        if (w != null) {
            w.close();
        }
    }


    private static class CompositeKey {

        public final String pkg;
        public final String key;
        public final String group;

        public CompositeKey(String in) {
            int posPkg = in.indexOf(Constants.END_PACKAGE_MARKER);
            pkg = in.substring(0, posPkg);
            key = in.substring(posPkg + Constants.END_PACKAGE_MARKER.length());
            int posGroup = key.indexOf('.');
            if (posGroup == -1) {
                group = "";
            } else {
                group = key.substring(0, posGroup);
            }
        }
    }
}
