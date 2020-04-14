
package examples;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

public abstract class ExampleTagBase extends BodyTagSupport {

    private static final long serialVersionUID = 1L;

    @Override
    public void setParent(Tag parent) {
        this.parent = parent;
    }

    @Override
    public void setBodyContent(BodyContent bodyOut) {
        this.bodyOut = bodyOut;
    }

    @Override
    public Tag getParent() {
        return this.parent;
    }

    @Override
    public int doStartTag() throws JspException {
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }


    @Override
    public void doInitBody() throws JspException {
        // Default implementations for BodyTag methods as well
        // just in case a tag decides to implement BodyTag.
    }

    @Override
    public int doAfterBody() throws JspException {
        return SKIP_BODY;
    }

    @Override
    public void release() {
        bodyOut = null;
        pageContext = null;
        parent = null;
    }

    protected BodyContent bodyOut;
    protected Tag parent;
}
