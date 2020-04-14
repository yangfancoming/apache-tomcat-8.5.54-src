

package javax.servlet.jsp.el;


/**
 * Represents a parsing error encountered while parsing an EL expression.
 *
 * @since 2.0
 * @deprecated As of JSP 2.1, replaced by javax.el.ELException
 */
@SuppressWarnings("dep-ann") // TCK signature test fails with annotation
public class ELParseException extends ELException {

 private static final long serialVersionUID = 1L;

//-------------------------------------
  /**
   * Creates an ELParseException with no detail message.
   */
  public ELParseException ()
  {
    super ();
  }

  //-------------------------------------
  /**
   * Creates an ELParseException with the provided detail message.
   *
   * @param pMessage the detail message
   **/
  public ELParseException (String pMessage)
  {
    super (pMessage);
  }

  //-------------------------------------
}
