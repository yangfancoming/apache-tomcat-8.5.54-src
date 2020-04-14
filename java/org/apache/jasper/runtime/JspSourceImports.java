
package org.apache.jasper.runtime;

import java.util.Set;

/**
 * The EL engine needs access to the imports used in the JSP page to configure
 * the ELContext. The imports are available at compile time but the ELContext
 * is created lazily per page. This interface exposes the imports at runtime so
 * that they may be added to the ELContext when it is created.
 */
public interface JspSourceImports {
    Set<String> getPackageImports();
    Set<String> getClassImports();
}
