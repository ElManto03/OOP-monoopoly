package it.unibo.monoopoly.model.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import it.unibo.monoopoly.model.api.ModelState;
import it.unibo.monoopoly.model.api.gameboard.Buyable;
import it.unibo.monoopoly.model.api.player.Player;
import it.unibo.monoopoly.model.api.player.Turn;

/**
 * Implementation of {@link ModelState} for the unmortgage state,
 * used to to release a mortgage on a property of the actual {@link Player},
 * if it has any.
 */
public class ModelUnmortgageState implements ModelState<Optional<Integer>, Optional<List<Integer>>> {
    private boolean makeState;
    private final Turn turn;

    /**
     * Constructor of the class,
     * that takes the {@link Turn} reference to perform all necessary state
     * operations,
     * according to the State pattern.
     * 
     * @param turn the reference to perform the operations.
     */
    public ModelUnmortgageState(Turn turn) {
        this.turn = turn;
    }

    /**
     * {@inheritDoc}
     * In this specific case,
     * the method verify if the {@link player} has any property to unmortgage,
     * and set the relative field.
     */
    @Override
    public boolean verify() {
        return this.makeState = havePropertyToUnmortgage();
    }

    private boolean havePropertyToUnmortgage() {
        return this.turn.getActualPlayer().getProperties().stream()
                .filter(this::isPayable)
                .anyMatch(c -> c.isMortgaged());
    }

    /**
     * {@inheritDoc}
     * In this specific case,
     * the method release a mortgage to the selected property.
     */
    @Override
    public void doAction(Optional<Integer> selectedCell) {
        if (selectedCell.isPresent()) {
            unmortgageByIndex(selectedCell);
        }
    }

    private void unmortgageByIndex(Optional<Integer> selectedCell) {
        this.turn.getCellsList().stream()
                .filter(c -> c instanceof Buyable)
                .map(c -> (Buyable) c)
                .toList()
                .get(selectedCell.get())
                .removeMortgage();
    }

    /**
     * {@inheritDoc}
     * In this specific case,
     * return if exist the {@link List} of cell mortgageable of the actual
     * {@link Player},
     * by index.
     */
    @Override
    public Optional<List<Integer>> getData() {
        return Optional.of(this.turn.getActualPlayer().getProperties().stream()
                .filter(c -> c.isMortgaged())
                .filter(this::isPayable)
                .map(this.turn.getCellsList()::indexOf)
                .toList());
    }

    private boolean isPayable(Buyable property) {
        BigDecimal multiFactor = new BigDecimal(1.10);
        int toPay = multiFactor.multiply(new BigDecimal(property.getMortgageValue())).intValue();
        return this.turn.getActualPlayer().isPayable(toPay);
    }

    /**
     * {@inheritDoc}
     * In this specific case,
     * set the new {@link ModelState}:
     * {@link PrisonModelState} if the actual {@link Player} does not want or cannot unmortgage houses,
     * {@link ModelUnmortgageState} otherwise to select a new property or stop operation.
     */
    @Override
    public void closeState() {
        if (makeState) {
            this.turn.setState(new ModelUnmortgageState(turn));
        } else {
            this.turn.setState(new ModelPrisonState(this.turn.getActualPlayer()));
        }
    }

}
