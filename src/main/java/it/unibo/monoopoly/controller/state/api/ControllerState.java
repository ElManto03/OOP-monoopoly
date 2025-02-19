package it.unibo.monoopoly.controller.state.api;

import it.unibo.monoopoly.controller.data.impl.DataOutput;

/**
 * The model of the pattern State for the controller.
 */
public interface ControllerState {
    /**
     * Launch the state, calling all the other state method in the right order.
     */
    void startState();

    /**
     * Continue the state until the end, calling all the remaining state method in
     * the right order.
     * 
     * @param dataOutput a possible data to process.
     */
    void continueState(DataOutput dataOutput);
}
