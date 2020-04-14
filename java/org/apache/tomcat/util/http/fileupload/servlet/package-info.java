

/**
 *    <p>
 *      An implementation of
 *      {@link org.apache.tomcat.util.http.fileupload.FileUpload FileUpload}
 *      for use in servlets conforming to JSR 53. This implementation requires
 *      only access to the servlet's current <code>HttpServletRequest</code>
 *      instance, and a suitable
 *      {@link org.apache.tomcat.util.http.fileupload.FileItemFactory FileItemFactory}
 *      implementation, such as
 *      {@link org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory DiskFileItemFactory}.
 *    </p>
 *    <p>
 *      The following code fragment demonstrates typical usage.
 *    </p>
 * <pre>
 *        DiskFileItemFactory factory = new DiskFileItemFactory();
 *        // Configure the factory here, if desired.
 *        ServletFileUpload upload = new ServletFileUpload(factory);
 *        // Configure the uploader here, if desired.
 *        List fileItems = upload.parseRequest(request);
 * </pre>
 *    <p>
 *      Please see the FileUpload
 *      <a href="https://commons.apache.org/fileupload/using.html" target="_top">User Guide</a>
 *      for further details and examples of how to use this package.
 *    </p>
 */
package org.apache.tomcat.util.http.fileupload.servlet;
