
package javax.el;

public class TesterBean {

    private String name;
    private Integer[] valueC;

    public TesterBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getNameVarargs(@SuppressWarnings("unused") Integer... someNumbers) {
        return name;
    }

    public String getNameVarargs(@SuppressWarnings("unused") Boolean someBoolean,
            @SuppressWarnings("unused") Integer... someNumbers) {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }

    public String getValueA() throws Exception {
        throw new Exception();
    }

    public int[] getValueB() {
        return new int[] {1,2,3,4,5};
    }

    public void setValueC(Integer[] values) {
        valueC = values;
    }

    public Integer[] getValueC() {
        return valueC;
    }
}
