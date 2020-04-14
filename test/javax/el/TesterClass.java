
package javax.el;

public class TesterClass {

    public static final String publicStaticString = "publicStaticString";
    public String publicString = "publicString";
    @SuppressWarnings("unused") // Used in TestStaticFieldELResolver
    private static String privateStaticString = "privateStaticString";
    @SuppressWarnings("unused") // Used in TestStaticFieldELResolver
    private String privateString = "privateString";

    public TesterClass() {
    }

    @SuppressWarnings("unused") // Used in TestStaticFieldELResolver
    private TesterClass(String privateString) {
        this.privateString = privateString;
    }

    public static String getPublicStaticString() {
        return publicStaticString;
    }

    public static void printPublicStaticString() {
        System.out.println(publicStaticString);
    }

    public void setPrivateString(String privateString) {
        this.privateString = privateString;
    }
}
