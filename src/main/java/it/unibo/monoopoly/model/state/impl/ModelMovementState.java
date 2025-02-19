package it.unibo.monoopoly.model.state.impl;

import java.util.Optional;

import it.unibo.monoopoly.controller.data.impl.DataOutput;
import it.unibo.monoopoly.model.gameboard.api.Dices;
import it.unibo.monoopoly.model.gameboard.api.Dices.Pair;
import it.unibo.monoopoly.model.gameboard.impl.DicesImpl;
import it.unibo.monoopoly.model.main.api.MainModel;
import it.unibo.monoopoly.model.player.api.Player;
import it.unibo.monoopoly.model.state.api.ModelState;

/**
 * Implementation of {@link ModelState} for the Dices state,
 * used to move the actual {@link Player}.
 */
public class ModelMovementState implements ModelState {

    private static final int PASS_GO_REWARD = 200;

    private final MainModel turn;
    private final Dices dices = new DicesImpl();
    private final Optional<Integer> cellIndex;

    /**
     * Creates a new instance of {@link ModelMovementState}.
     * This state is used to move the current {@link Player} based on the dice
     * result.
     * 
     * @param turn      used to associate the dices roll state with the specific
     *                  next state to execute.
     * @param cellIndex
     */
    public ModelMovementState(final MainModel turn, final Optional<Integer> cellIndex) {
        this.turn = turn;
        this.cellIndex = cellIndex;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean verify() {
        if (this.cellIndex.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void doAction(final Optional<DataOutput> data) {
        if (verify()) {
            moveWithDices();
        } else {
            moveWithCards(cellIndex());
        }
    }

    private void moveWithDices() {
        this.dices.rollDices();
        if (hasPassedGo()) {
            getPlayer().receive(PASS_GO_REWARD);
            movePlayer((getPlayerPosition() + diceResult()) % numberOfCells());
        } else {
            movePlayer(getPlayerPosition() + diceResult());
        }
    }

    private void moveWithCards(final int cellIndex) {
        if (cellIndex() >= 0) {
            if (!getPlayer().isPrisoned() && cellIndex() < getPlayer().getActualPosition()) {
                getPlayer().receive(PASS_GO_REWARD);
            }
            movePlayer(cellIndex());
        } else {
            if (getPlayerPosition() + cellIndex() < 0) {
                movePlayer(getPlayerPosition() + cellIndex() + numberOfCells());
            } else {
                movePlayer(getPlayerPosition() + cellIndex());
            }
        }
    }

    private int cellIndex() {
        return this.cellIndex.get();
    }

    private int diceResult() {
        return this.dices.getResult();
    }

    private int numberOfCells() {
        return this.turn.getGameBoard().getCellsList().size();
    }

    private int getPlayerPosition() {
        return getPlayer().getActualPosition();
    }

    private Player getPlayer() {
        return this.turn.getGameBoard().getCurrentPlayer();
    }

    private void movePlayer(final int cellIndex) {
        getPlayer().changePosition(cellIndex());
    }

    private boolean hasPassedGo() {
        return getPlayerPosition() + diceResult() >= numberOfCells();
    }

    /**
     * comment.
     * 
     * @return comment.
     */
    public Pair getData() {
        return this.dices.getDices();
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void closeState() {
        if (getPlayer().isPrisoned()) {
            this.turn.setState(new ModelBankerState(turn, PASS_GO_REWARD)); // new ModelPrisonState(turn, false)
        } else {
            this.turn.setState(new ModelCheckActionState(this.turn));
        }
    }

}
