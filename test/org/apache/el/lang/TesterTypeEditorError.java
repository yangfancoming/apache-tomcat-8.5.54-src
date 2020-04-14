
package org.apache.el.lang;

public class TesterTypeEditorError extends TesterTypeEditorBase {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        throw new IllegalArgumentException();
    }
}
