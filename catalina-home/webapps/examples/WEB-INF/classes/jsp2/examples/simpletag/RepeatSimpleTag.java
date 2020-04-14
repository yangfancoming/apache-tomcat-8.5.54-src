


package jsp2.examples.simpletag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * SimpleTag handler that accepts a num attribute and
 * invokes its body 'num' times.
 */
public class RepeatSimpleTag extends SimpleTagSupport {
    private int num;

    @Override
    public void doTag() throws JspException, IOException {
        for (int i=0; i<num; i++) {
            getJspContext().setAttribute("count", String.valueOf( i + 1 ) );
            getJspBody().invoke(null);
        }
    }

    public void setNum(int num) {
        this.num = num;
    }
}
