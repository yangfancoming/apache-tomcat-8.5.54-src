
package org.apache.jasper.compiler;

import org.apache.jasper.compiler.tagplugin.TagPlugin;
import org.apache.jasper.compiler.tagplugin.TagPluginContext;

/**
 * Plug-in for {@link TesterTag}.
 */
public class TesterTagPlugin implements TagPlugin {

    @Override
    public void doTag(TagPluginContext ctxt) {
        ctxt.generateJavaSource("//Just a comment");
    }
}