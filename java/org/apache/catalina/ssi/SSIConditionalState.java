
package org.apache.catalina.ssi;


/**
 * This class is used by SSIMediator and SSIConditional to keep track of state
 * information necessary to process the nested conditional commands ( if, elif,
 * else, endif ).
 *
 * @author Dan Sandberg
 * @author Paul Speed
 */
class SSIConditionalState {
    /**
     * Set to true if the current conditional has already been completed, i.e.:
     * a branch was taken.
     */
    boolean branchTaken = false;
    /**
     * Counts the number of nested false branches.
     */
    int nestingCount = 0;
    /**
     * Set to true if only conditional commands ( if, elif, else, endif )
     * should be processed.
     */
    boolean processConditionalCommandsOnly = false;
}