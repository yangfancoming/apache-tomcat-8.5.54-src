
package org.apache.el.parser;

/**
 * @author Jacob Hookom [jacob@hookom.net]
 */
public interface NodeVisitor {
    public void visit(Node node) throws Exception;
}
