

package org.apache.el;

import java.util.List;

public class TesterBeanA {
    private TesterBeanB bean;
    private String name;
    private long valLong;
    private List<?> valList;

    public TesterBeanB getBean() {
        return bean;
    }

    public void setBean(TesterBeanB bean) {
        this.bean = bean;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getValLong() {
        return valLong;
    }

    public void setValLong(long valLong) {
        this.valLong = valLong;
    }

    public List<?> getValList() {
        return valList;
    }

    public void setValList(List<?> valList) {
        this.valList = valList;
    }

    public CharSequence echo1(CharSequence cs) {
        return "A1" + cs;
    }

    public CharSequence echo2(String s) {
        return "A2" + s;
    }
}
