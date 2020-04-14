
package org.apache.tomcat.buildutil.translate;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * Generates a set of translated property files to back-port updates to a
 * previous version. If the source and target use the same value for the English
 * key then any translated value for that key is copied from the source to the
 * target.
 */
public class BackportTranslations extends BackportBase {

    public static void main(String... args) throws IOException {
        BackportTranslations backport = new BackportTranslations(args);
        backport.execute();
    }

    protected BackportTranslations(String[] args) throws IOException {
        super(args);
    }


    @Override
    protected void execute() throws IOException {
        for (String langauge : targetTranslations.keySet()) {
            // Skip source
            if (langauge.length() == 0) {
                continue;
            }

            Properties sourceTranslated = sourceTranslations.get(langauge);
            Properties targetTranslated = targetTranslations.get(langauge);
            if (targetTranslated == null) {
                targetTranslated = new Properties();
                targetTranslations.put(langauge, targetTranslated);
            }

            for (Object key : targetEnglish.keySet()) {
                if (sourceTranslated.containsKey(key) &&
                        targetEnglish.get(key).equals(sourceEnglish.get(key))) {

                    targetTranslated.put(key, sourceTranslated.get(key));
                }
            }

            // Remove translated values for keys that have been removed
            Iterator<Map.Entry<Object,Object>> iter = targetTranslated.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry<Object,Object> entry = iter.next();
                if (!targetEnglish.containsKey(entry.getKey())) {
                    iter.remove();
                }
            }
            Utils.export(langauge, targetTranslated, storageDir);
        }
    }
}
