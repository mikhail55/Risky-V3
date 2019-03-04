package AI;

/**
 * @author TylerWilson
 */
class Node {

    // node function
    // output = (1/(1 + Math.pow(Math.E, (-1 * output))));

    private double value;
    private boolean isConstant = false;

    Node() {

    }

    void setConstant(boolean bool) {
        isConstant = bool;
        value = 1;
    }

    double getValue() {
        return value;
    }

    void finalizeValue() {
        value = (1/(1 + Math.pow(Math.E, (-1 * value))));
    }

    void updateValue(double newValue) {
        if(!isConstant) {
            value += newValue;
        }
    }

    void resetValue() {
        value = 0;

        if(isConstant) {
            value = 1;
        }
    }

    public void setValue(double newVal) {
        value = newVal;
    }
}
