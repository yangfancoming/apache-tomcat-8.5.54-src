
package org.apache.tomcat.jni;

public class LibraryNotFoundError extends UnsatisfiedLinkError {

    private static final long serialVersionUID = 1L;

    private final String libraryNames;

    /**
     *
     * @param libraryNames A list of the file names of the native libraries that
     *                     failed to load
     * @param errors A list of the error messages received when trying to load
     *               each of the libraries
     */
    public LibraryNotFoundError(String libraryNames, String errors){
        super(errors);
        this.libraryNames = libraryNames;
    }

    public String getLibraryNames(){
        return libraryNames;
    }
}
