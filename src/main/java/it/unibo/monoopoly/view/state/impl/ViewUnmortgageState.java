package it.unibo.monoopoly.view.state.impl;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.JOptionPane;

import it.unibo.monoopoly.controller.data.impl.DataBuilderOutputImpl;
import it.unibo.monoopoly.controller.data.impl.DataInput;
import it.unibo.monoopoly.utils.impl.ViewCellGiver;
import it.unibo.monoopoly.view.main.api.MainView;
import it.unibo.monoopoly.view.panel.impl.UnmortgagePanel;
import it.unibo.monoopoly.view.state.api.ViewState;

/**
 * Implementations of {@link ViewState} for the card's phase:
 * to allow the player to decide which property to release the mortgage on.
 */
public class ViewUnmortgageState implements ViewState {
    private final MainView mainView;
    private boolean makeState;

    /**
     * Constructor of the class that sets the field.
     * 
     * @param mainView to set.
     */
    public ViewUnmortgageState(final MainView mainView) {
        this.mainView = mainView;
    }

    /**
     *
     * {@inheritDoc}
     */

    @Override
    public void setMode(Boolean setter) {
        this.makeState = setter;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void visualize(final DataInput dataInput) {
        if (this.makeState) {
            JPanel panel = new UnmortgagePanel(new ViewCellGiver(this.mainView), intToTextCell(dataInput.cellList().get()));
            this.mainView.getMainFrame().add(panel);
        } else {
            JOptionPane.showMessageDialog(this.mainView.getMainFrame(),
                    "Mi spiace non hai proprietà da disipotecare", "Disipoteca", JOptionPane.PLAIN_MESSAGE);
            this.mainView.getMainController().getControllerState().continueState(new DataBuilderOutputImpl().build());
        }
    }

    private List<String> intToTextCell(final List<Integer> cellList) {
        return cellList.stream()
                .map(this.mainView.getNameCells()::get)
                .toList();
    }
}
