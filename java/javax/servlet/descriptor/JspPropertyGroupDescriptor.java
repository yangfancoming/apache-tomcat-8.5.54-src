
package javax.servlet.descriptor;

import java.util.Collection;

/**
 * @since Servlet 3.0
 * TODO SERVLET3 - Add comments
 */
public interface JspPropertyGroupDescriptor {
    public Collection<String> getUrlPatterns();
    public String getElIgnored();
    public String getPageEncoding();
    public String getScriptingInvalid();
    public String getIsXml();
    public Collection<String> getIncludePreludes();
    public Collection<String> getIncludeCodas();
    public String getDeferredSyntaxAllowedAsLiteral();
    public String getTrimDirectiveWhitespaces();
    public String getDefaultContentType();
    public String getBuffer();
    public String getErrorOnUndeclaredNamespace();
}
