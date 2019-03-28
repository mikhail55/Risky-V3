package AI;

/**
 * @author TylerWilson
 */
class Node {

    // This function converts the value in a node to a value between 0 and 1
    // It's standard for neural networks

    // node function
    // output = (1/(1 + Math.pow(Math.E, (-1 * output))));

    private float value;

    // Some nodes have a constant value of 1 so that the next layer can have a constant influence
    private boolean isConstant = false;

    // Nodes dont need a proper constructor because they only have functionality, not stored data
    Node() {

    }

    /**
     * Set the node to be a constant
     * @param bool Whether or not its constant
     */
    void setConstant(boolean bool) {
        isConstant = bool;
        value = 1;
    }

    /**
     * @return the node's value
     */
    float getValue() {
        return value;
    }

    /**
     * Convert the node's value to between 0 and 1
     */
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
