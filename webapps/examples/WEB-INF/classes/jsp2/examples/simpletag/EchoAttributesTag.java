


package jsp2.examples.simpletag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * SimpleTag handler that echoes all its attributes
 */
public class EchoAttributesTag
    extends SimpleTagSupport
    implements DynamicAttributes
{
    private final List<String> keys = new ArrayList<>();
    private final List<Object> values = new ArrayList<>();

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        for( int i = 0; i < keys.size(); i++ ) {
            String key = keys.get( i );
            Object value = values.get( i );
            out.println( "<li>" + key + " = " + value + "</li>" );
        }
    }

    @Override
    public void setDynamicAttribute( String uri, String localName,
        Object value )
        throws JspException
    {
        keys.add( localName );
        values.add( value );
    }
}
