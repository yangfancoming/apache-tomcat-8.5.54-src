
package examples;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;

/**
 * Log the contents of the body. Could be used to handle errors etc.
 */
public class LogTag extends ExampleTagBase {

    private static final long serialVersionUID = 1L;

    boolean toBrowser = false;

    public void setToBrowser(String value) {
        if (value == null)
            toBrowser = false;
        else if (value.equalsIgnoreCase("true"))
            toBrowser = true;
        else
            toBrowser = false;
    }

    @Override
    public int doStartTag() throws JspException {
        return EVAL_BODY_BUFFERED;
    }

    @Override
    public int doAfterBody() throws JspException {
        try {
            String s = bodyOut.getString();
            System.err.println(s);
            if (toBrowser)
                bodyOut.writeOut(bodyOut.getEnclosingWriter());
            return SKIP_BODY;
        } catch (IOException ex) {
            throw new JspTagException(ex.toString());
        }
    }
}


