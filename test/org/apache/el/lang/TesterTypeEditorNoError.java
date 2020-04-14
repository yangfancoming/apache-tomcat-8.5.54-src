
package org.apache.el.lang;

public class TesterTypeEditorNoError extends TesterTypeEditorBase {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        type = new TesterType(text);
    }
}
