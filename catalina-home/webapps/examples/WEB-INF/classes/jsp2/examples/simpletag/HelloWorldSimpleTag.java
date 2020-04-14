


package jsp2.examples.simpletag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * SimpleTag handler that prints "Hello, world!"
 */
public class HelloWorldSimpleTag extends SimpleTagSupport {
    @Override
    public void doTag() throws JspException, IOException {
        getJspContext().getOut().write( "Hello, world!" );
    }
}
