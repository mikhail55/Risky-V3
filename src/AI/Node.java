package AI;

/**
 * @author TylerWilson
 */
class Node {

    // node function
    // output = (1/(1 + Math.pow(Math.E, (-1 * output))));

    private float value;
    private boolean isConstant = false;

    Node() {

    }

    void setConstant(boolean bool) {
        isConstant = bool;
        value = 1;
    }

    float getValue() {
        return value;
    }

    void finalizeValue() {
        value = (float) (1/(1 + Math.pow(Math.E, (-1 * value))));
    }

    void updateValue(float newValue) {
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

    public void setValue(float newVal) {
        value = newVal;
    }
}
