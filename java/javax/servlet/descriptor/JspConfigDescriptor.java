
package javax.servlet.descriptor;

import java.util.Collection;

/**
 * @since Servlet 3.0
 * TODO SERVLET3 - Add comments
 */
public interface JspConfigDescriptor {
    public Collection<TaglibDescriptor> getTaglibs();
    public Collection<JspPropertyGroupDescriptor> getJspPropertyGroups();
}
