
package org.apache.el;

public class TesterBeanEnum {

    private volatile TesterEnum lastSubmitted = null;

    public void submit(TesterEnum testerEnum) {
        this.lastSubmitted = testerEnum;
    }

    public TesterEnum getLastSubmitted() {
        return lastSubmitted;
    }
}
