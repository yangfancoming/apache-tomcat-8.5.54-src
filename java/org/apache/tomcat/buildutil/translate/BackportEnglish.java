
package org.apache.tomcat.buildutil.translate;

import java.io.IOException;

/**
 * Generates a set of English property files to back-port updates to a previous
 * version. Where a key exists in the source and target versions the value is
 * copied from the source to the target, overwriting the value in the target.
 * The expectation is that the changes will be manually reviewed before
 * committing them.
 */
public class BackportEnglish extends BackportBase {

    public static void main(String... args) throws IOException {
        BackportEnglish backport = new BackportEnglish(args);
        backport.execute();
    }


    protected BackportEnglish(String[] args) throws IOException {
        super(args);
    }


    @Override
    protected void execute() throws IOException {
        for (Object key : sourceEnglish.keySet()) {
            if (targetEnglish.containsKey(key)) {
                targetEnglish.put(key, sourceEnglish.get(key));
            }
        }

        Utils.export("", targetEnglish, storageDir);
    }
}
