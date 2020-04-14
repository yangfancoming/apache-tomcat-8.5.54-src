

package org.apache.jasper.runtime;

import java.util.Map;

/**
 * Interface for tracking the source files dependencies, for the purpose
 * of compiling out of date pages.  This is used for
 * 1) files that are included by page directives
 * 2) files that are included by include-prelude and include-coda in jsp:config
 * 3) files that are tag files and referenced
 * 4) TLDs referenced
 */

public interface JspSourceDependent {

   /**
    * Returns a map of file names and last modified time where the current page
    * has a source dependency on the file.
    * @return the map of dependent resources
    */
    public Map<String,Long> getDependants();

}
