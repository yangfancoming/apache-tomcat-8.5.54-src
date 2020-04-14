

/**
 *    <p>
 *      A disk-based implementation of the
 *      {@link org.apache.tomcat.util.http.fileupload.FileItem FileItem}
 *      interface. This implementation retains smaller items in memory, while
 *      writing larger ones to disk. The threshold between these two is
 *      configurable, as is the location of files that are written to disk.
 *    </p>
 *    <p>
 *      In typical usage, an instance of
 *      {@link org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory DiskFileItemFactory}
 *      would be created, configured, and then passed to a
 *      {@link org.apache.tomcat.util.http.fileupload.FileUpload FileUpload}
 *      implementation such as
 *      {@link org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload ServletFileUpload}.
 *    </p>
 *    <p>
 *      The following code fragment demonstrates this usage.
 *    </p>
 * <pre>
 *        DiskFileItemFactory factory = new DiskFileItemFactory();
 *        // maximum size that will be stored in memory
 *        factory.setSizeThreshold(4096);
 *        // the location for saving data that is larger than getSizeThreshold()
 *        factory.setRepository(new File("/tmp"));
 *
 *        ServletFileUpload upload = new ServletFileUpload(factory);
 * </pre>
 *    <p>
 *      Please see the FileUpload
 *      <a href="https://commons.apache.org/fileupload/using.html" target="_top">User Guide</a>
 *      for further details and examples of how to use this package.
 *    </p>
 */
package org.apache.tomcat.util.http.fileupload.disk;
