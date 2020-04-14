

package org.apache.jasper.compiler;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the line and file mappings associated with a JSR-045
 * "stratum".
 *
 * @author Jayson Falkner
 * @author Shawn Bayern
 */
public class SmapStratum {

    //*********************************************************************
    // Class for storing LineInfo data

    /**
     * Represents a single LineSection in an SMAP, associated with
     * a particular stratum.
     */
    private static class LineInfo {
        private int inputStartLine = -1;
        private int outputStartLine = -1;
        private int lineFileID = 0;
        private int inputLineCount = 1;
        private int outputLineIncrement = 1;
        private boolean lineFileIDSet = false;

        public void setInputStartLine(int inputStartLine) {
            if (inputStartLine < 0)
                throw new IllegalArgumentException("" + inputStartLine);
            this.inputStartLine = inputStartLine;
        }

        public void setOutputStartLine(int outputStartLine) {
            if (outputStartLine < 0)
                throw new IllegalArgumentException("" + outputStartLine);
            this.outputStartLine = outputStartLine;
        }

        /**
         * Sets lineFileID.  Should be called only when different from
         * that of prior LineInfo object (in any given context) or 0
         * if the current LineInfo has no (logical) predecessor.
         * <code>LineInfo</code> will print this file number no matter what.
         *
         * @param lineFileID The new line file ID
         */
        public void setLineFileID(int lineFileID) {
            if (lineFileID < 0)
                throw new IllegalArgumentException("" + lineFileID);
            this.lineFileID = lineFileID;
            this.lineFileIDSet = true;
        }

        public void setInputLineCount(int inputLineCount) {
            if (inputLineCount < 0)
                throw new IllegalArgumentException("" + inputLineCount);
            this.inputLineCount = inputLineCount;
        }

        public void setOutputLineIncrement(int outputLineIncrement) {
            if (outputLineIncrement < 0)
                throw new IllegalArgumentException("" + outputLineIncrement);
            this.outputLineIncrement = outputLineIncrement;
        }

        /**
         * @return the current LineInfo as a String, print all values only when
         *         appropriate (but LineInfoID if and only if it's been
         *         specified, as its necessity is sensitive to context).
         */
        public String getString() {
            if (inputStartLine == -1 || outputStartLine == -1)
                throw new IllegalStateException();
            StringBuilder out = new StringBuilder();
            out.append(inputStartLine);
            if (lineFileIDSet)
                out.append("#" + lineFileID);
            if (inputLineCount != 1)
                out.append("," + inputLineCount);
            out.append(":" + outputStartLine);
            if (outputLineIncrement != 1)
                out.append("," + outputLineIncrement);
            out.append('\n');
            return out.toString();
        }

        @Override
        public String toString() {
            return getString();
        }
    }

    //*********************************************************************
    // Private state

    private final String stratumName;
    private final List<String> fileNameList;
    private final List<String> filePathList;
    private final List<LineInfo> lineData;
    private int lastFileID;

    //*********************************************************************
    // Constructor

    /**
     * Constructs a new SmapStratum object with the stratum name JSP.
     */
    public SmapStratum() {
        this("JSP");
    }

    /**
     * Constructs a new SmapStratum object for the given stratum name
     * (e.g., JSP).
     *
     * @param stratumName the name of the stratum (e.g., JSP)
     *
     * @deprecated Use the no-arg constructor
     */
    @Deprecated
    public SmapStratum(String stratumName) {
        this.stratumName = stratumName;
        fileNameList = new ArrayList<>();
        filePathList = new ArrayList<>();
        lineData = new ArrayList<>();
        lastFileID = 0;
    }

    //*********************************************************************
    // Methods to add mapping information

    /**
     * Adds record of a new file, by filename.
     *
     * @param filename the filename to add, unqualified by path.
     */
    public void addFile(String filename) {
        addFile(filename, filename);
    }

    /**
     * Adds record of a new file, by filename and path.  The path
     * may be relative to a source compilation path.
     *
     * @param filename the filename to add, unqualified by path
     * @param filePath the path for the filename, potentially relative
     *                 to a source compilation path
     */
    public void addFile(String filename, String filePath) {
        int pathIndex = filePathList.indexOf(filePath);
        if (pathIndex == -1) {
            fileNameList.add(filename);
            filePathList.add(filePath);
        }
    }

    /**
     * Combines consecutive LineInfos wherever possible
     */
    public void optimizeLineSection() {

/* Some debugging code
        for (int i = 0; i < lineData.size(); i++) {
            LineInfo li = (LineInfo)lineData.get(i);
            System.out.print(li.toString());
        }
*/
        //Incorporate each LineInfo into the previous LineInfo's
        //outputLineIncrement, if possible
        int i = 0;
        while (i < lineData.size() - 1) {
            LineInfo li = lineData.get(i);
            LineInfo liNext = lineData.get(i + 1);
            if (!liNext.lineFileIDSet
                && liNext.inputStartLine == li.inputStartLine
                && liNext.inputLineCount == 1
                && li.inputLineCount == 1
                && liNext.outputStartLine
                    == li.outputStartLine
                        + li.inputLineCount * li.outputLineIncrement) {
                li.setOutputLineIncrement(
                    liNext.outputStartLine
                        - li.outputStartLine
                        + liNext.outputLineIncrement);
                lineData.remove(i + 1);
            } else {
                i++;
            }
        }

        //Incorporate each LineInfo into the previous LineInfo's
        //inputLineCount, if possible
        i = 0;
        while (i < lineData.size() - 1) {
            LineInfo li = lineData.get(i);
            LineInfo liNext = lineData.get(i + 1);
            if (!liNext.lineFileIDSet
                && liNext.inputStartLine == li.inputStartLine + li.inputLineCount
                && liNext.outputLineIncrement == li.outputLineIncrement
                && liNext.outputStartLine
                    == li.outputStartLine
                        + li.inputLineCount * li.outputLineIncrement) {
                li.setInputLineCount(li.inputLineCount + liNext.inputLineCount);
                lineData.remove(i + 1);
            } else {
                i++;
            }
        }
    }

    /**
     * Adds complete information about a simple line mapping.  Specify
     * all the fields in this method; the back-end machinery takes care
     * of printing only those that are necessary in the final SMAP.
     * (My view is that fields are optional primarily for spatial efficiency,
     * not for programmer convenience.  Could always add utility methods
     * later.)
     *
     * @param inputStartLine starting line in the source file
     *        (SMAP <code>InputStartLine</code>)
     * @param inputFileName the filepath (or name) from which the input comes
     *        (yields SMAP <code>LineFileID</code>)  Use unqualified names
     *        carefully, and only when they uniquely identify a file.
     * @param inputLineCount the number of lines in the input to map
     *        (SMAP <code>LineFileCount</code>)
     * @param outputStartLine starting line in the output file
     *        (SMAP <code>OutputStartLine</code>)
     * @param outputLineIncrement number of output lines to map to each
     *        input line (SMAP <code>OutputLineIncrement</code>).  <i>Given the
     *        fact that the name starts with "output", I continuously have
     *        the subconscious urge to call this field
     *        <code>OutputLineExcrement</code>.</i>
     */
    public void addLineData(
        int inputStartLine,
        String inputFileName,
        int inputLineCount,
        int outputStartLine,
        int outputLineIncrement) {
        // check the input - what are you doing here??
        int fileIndex = filePathList.indexOf(inputFileName);
        if (fileIndex == -1) // still
            throw new IllegalArgumentException(
                "inputFileName: " + inputFileName);

        //Jasper incorrectly SMAPs certain Nodes, giving them an
        //outputStartLine of 0.  This can cause a fatal error in
        //optimizeLineSection, making it impossible for Jasper to
        //compile the JSP.  Until we can fix the underlying
        //SMAPping problem, we simply ignore the flawed SMAP entries.
        if (outputStartLine == 0)
            return;

        // build the LineInfo
        LineInfo li = new LineInfo();
        li.setInputStartLine(inputStartLine);
        li.setInputLineCount(inputLineCount);
        li.setOutputStartLine(outputStartLine);
        li.setOutputLineIncrement(outputLineIncrement);
        if (fileIndex != lastFileID)
            li.setLineFileID(fileIndex);
        lastFileID = fileIndex;

        // save it
        lineData.add(li);
    }

    //*********************************************************************
    // Methods to retrieve information

    /**
     * @return the name of the stratum.
     *
     * @deprecated Unused. This will be removed in Tomcat 9.0.x
     */
    @Deprecated
    public String getStratumName() {
        return stratumName;
    }

    /**
     * @return the given stratum as a String:  a StratumSection,
     * followed by at least one FileSection and at least one LineSection.
     */
    public String getString() {
        // check state and initialize buffer
        if (fileNameList.size() == 0 || lineData.size() == 0)
            return null;

        StringBuilder out = new StringBuilder();

        // print StratumSection
        out.append("*S " + stratumName + "\n");

        // print FileSection
        out.append("*F\n");
        int bound = fileNameList.size();
        for (int i = 0; i < bound; i++) {
            if (filePathList.get(i) != null) {
                out.append("+ " + i + " " + fileNameList.get(i) + "\n");
                // Source paths must be relative, not absolute, so we
                // remove the leading "/", if one exists.
                String filePath = filePathList.get(i);
                if (filePath.startsWith("/")) {
                    filePath = filePath.substring(1);
                }
                out.append(filePath + "\n");
            } else {
                out.append(i + " " + fileNameList.get(i) + "\n");
            }
        }

        // print LineSection
        out.append("*L\n");
        bound = lineData.size();
        for (int i = 0; i < bound; i++) {
            LineInfo li = lineData.get(i);
            out.append(li.getString());
        }

        return out.toString();
    }

    @Override
    public String toString() {
        return getString();
    }

}
