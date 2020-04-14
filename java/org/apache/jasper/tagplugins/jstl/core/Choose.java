


package org.apache.jasper.tagplugins.jstl.core;

import org.apache.jasper.compiler.tagplugin.TagPlugin;
import org.apache.jasper.compiler.tagplugin.TagPluginContext;

public final class Choose implements TagPlugin {

    @Override
    public void doTag(TagPluginContext ctxt) {

        // Not much to do here, much of the work will be done in the
        // containing tags, <c:when> and <c:otherwise>.

        ctxt.generateBody();
        // See comments in When.java for the reason "}" is generated here.
        ctxt.generateJavaSource("}");
    }
}
