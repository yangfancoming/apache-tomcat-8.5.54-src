


package jsp2.examples;

/**
 * Accept and display a value.
 */
public class ValuesBean {
    private String string;
    private double doubleValue;
    private long longValue;

    public String getStringValue() {
        return this.string;
    }

    public void setStringValue(String string) {
        this.string = string;
    }

    public double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(double doubleValue) {
        this.doubleValue = doubleValue;
    }

    public long getLongValue() {
        return longValue;
    }

    public void setLongValue(long longValue) {
        this.longValue = longValue;
    }
}
