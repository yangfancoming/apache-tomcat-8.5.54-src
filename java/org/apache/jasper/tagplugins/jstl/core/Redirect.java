


package org.apache.jasper.tagplugins.jstl.core;

import org.apache.jasper.compiler.tagplugin.TagPlugin;
import org.apache.jasper.compiler.tagplugin.TagPluginContext;

public class Redirect implements TagPlugin {

    @Override
    public void doTag(TagPluginContext ctxt) {

        //flag for the existence of the "context"
        boolean hasContext = ctxt.isAttributeSpecified("context");

        //names of the temp variables
        String urlName = ctxt.getTemporaryVariableName();
        String contextName = ctxt.getTemporaryVariableName();
        String baseUrlName = ctxt.getTemporaryVariableName();
        String resultName = ctxt.getTemporaryVariableName();
        String responseName = ctxt.getTemporaryVariableName();

        //get context
        ctxt.generateJavaSource("String " + contextName + " = null;");
        if(hasContext){
            ctxt.generateJavaSource(contextName + " = ");
            ctxt.generateAttribute("context");
            ctxt.generateJavaSource(";");
        }

        //get the url
        ctxt.generateJavaSource("String " + urlName + " = ");
        ctxt.generateAttribute("url");
        ctxt.generateJavaSource(";");

        //get the raw url according to "url" and "context"
        ctxt.generateJavaSource("String " + baseUrlName + " = " +
                "org.apache.jasper.tagplugins.jstl.Util.resolveUrl(" + urlName + ", " + contextName + ", pageContext);");
        ctxt.generateJavaSource("pageContext.setAttribute" +
                "(\"url_without_param\", " + baseUrlName + ");");

        //add params
        ctxt.generateBody();

        ctxt.generateJavaSource("String " + resultName + " = " +
        "(String)pageContext.getAttribute(\"url_without_param\");");
        ctxt.generateJavaSource("pageContext.removeAttribute" +
        "(\"url_without_param\");");

        //get the response object
        ctxt.generateJavaSource("HttpServletResponse " + responseName + " = " +
        "((HttpServletResponse) pageContext.getResponse());");

        //if the url is relative, encode it
        ctxt.generateJavaSource("if(!org.apache.jasper.tagplugins.jstl.Util.isAbsoluteUrl(" + resultName + ")){");
        ctxt.generateJavaSource("    " + resultName + " = "
                + responseName + ".encodeRedirectURL(" + resultName + ");");
        ctxt.generateJavaSource("}");

        //do redirect
        ctxt.generateJavaSource("try{");
        ctxt.generateJavaSource("    " + responseName + ".sendRedirect(" + resultName + ");");
        ctxt.generateJavaSource("}catch(java.io.IOException ex){");
        ctxt.generateJavaSource("    throw new JspTagException(ex.toString(), ex);");
        ctxt.generateJavaSource("}");
    }

}
