package it.unibo.monoopoly.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.monoopoly.common.Event;
import it.unibo.monoopoly.controller.data.impl.DataBuilderOutputImpl;
import it.unibo.monoopoly.model.banker.api.Banker;
import it.unibo.monoopoly.model.gameboard.api.Buildable;
import it.unibo.monoopoly.model.main.api.MainModel;
import it.unibo.monoopoly.model.main.impl.MainModelImpl;
import it.unibo.monoopoly.model.state.impl.ModelBankerState;
import it.unibo.monoopoly.model.state.impl.ModelMovementState;
import it.unibo.monoopoly.model.state.impl.ModelUnmortgageState;

/**
 * Tester of {@link Banker}.
 */
class TestModelBankerState {
    private static final int BUILDABLE_CELL1 = 39;
    private static final int BUILDABLE_CELL2 = 37;
    private static final int START_AMOUNT = 1500;
    private static final int AMOUNT_TO_PAY = 200;
    private static final int AMOUNT_TO_PAY_2 = 100;
    private MainModel model;

    /**
     * Initialization for the test.
     */
    @BeforeEach
    void init() {
        this.model = new MainModelImpl(List.of("Genoveffo"));
    }

    /**
     * 
     */
    @Test
    void testOperationSuccess() {
        model.getGameBoard().getCurrentPlayer().pay(START_AMOUNT);
        final ModelBankerState state = new ModelBankerState(this.model, false);
        assertEquals(false, state.verify());
        state.doAction(new DataBuilderOutputImpl().build());
        assertEquals(0, this.model.getGameBoard().getCurrentPlayer().getMoneyAmount());
        state.closeState();
        assertInstanceOf(ModelUnmortgageState.class, this.model.getState());
    }

    /* */
    @Test
    void testOperationSellHouse() {
        final Buildable property = (Buildable) (this.model.getGameBoard().getCell(BUILDABLE_CELL1));
        property.buildHouse();
        this.model.getGameBoard().getCurrentPlayer().addProperty(property);
        property.buildHouse();
        model.getGameBoard().getCurrentPlayer().pay(START_AMOUNT + AMOUNT_TO_PAY);
        final ModelBankerState state = new ModelBankerState(this.model, false);
        assertEquals(true, state.verify());
        assertEquals(Optional.of(Event.SELL_HOUSE), this.model.getEvent());
        state.doAction(new DataBuilderOutputImpl().selectedCell(BUILDABLE_CELL1).build());
        assertEquals(property.getSellHouseCost() - AMOUNT_TO_PAY,
                this.model.getGameBoard().getCurrentPlayer().getMoneyAmount());
        state.closeState();
        assert (this.model.getState() instanceof ModelBankerState);
        property.buildHouse();
        assertEquals(true, this.model.getState().verify());
        assertEquals(Optional.of(Event.SELL_HOUSE), this.model.getEvent());
        state.doAction(new DataBuilderOutputImpl().selectedCell(BUILDABLE_CELL1).build());
        assertEquals(0, this.model.getGameBoard().getCurrentPlayer().getMoneyAmount());
    }

    /* */
    @Test
    void testOperationMortgage() {
        final Buildable property = (Buildable) (this.model.getGameBoard().getCell(BUILDABLE_CELL1));
        final Buildable property2 = (Buildable) (this.model.getGameBoard().getCell(BUILDABLE_CELL2));
        this.model.getGameBoard().getCurrentPlayer().addProperty(property);
        this.model.getGameBoard().getCurrentPlayer().addProperty(property2);
        model.getGameBoard().getCurrentPlayer().pay(START_AMOUNT + AMOUNT_TO_PAY);
        final ModelBankerState state = new ModelBankerState(this.model, false);
        assertEquals(true, state.verify());
        assertEquals(Optional.of(Event.MORTGAGE_PROPERTY), this.model.getEvent());
        state.doAction(new DataBuilderOutputImpl().selectedCell(BUILDABLE_CELL1).build());
        assertEquals(true, property.isMortgaged());
        assertEquals(property.getMortgageValue() - AMOUNT_TO_PAY,
                this.model.getGameBoard().getCurrentPlayer().getMoneyAmount());
        state.closeState();
        assertInstanceOf(ModelBankerState.class, this.model.getState());
        assertEquals(true, this.model.getState().verify());
        assertEquals(Optional.of(Event.MORTGAGE_PROPERTY), this.model.getEvent());
        state.doAction(new DataBuilderOutputImpl().selectedCell(BUILDABLE_CELL2).build());
        assertEquals(
                START_AMOUNT - (START_AMOUNT + AMOUNT_TO_PAY) + property.getMortgageValue()
                        + property2.getMortgageValue(),
                this.model.getGameBoard().getCurrentPlayer().getMoneyAmount());
    }

    /* */
    @Test
    void testOperationBankrupt() {
        model.getGameBoard().getCurrentPlayer().pay(START_AMOUNT + AMOUNT_TO_PAY_2);
        final ModelBankerState state = new ModelBankerState(this.model, false);
        assertEquals(true, state.verify());
        state.doAction(new DataBuilderOutputImpl().build());
        assertEquals(-AMOUNT_TO_PAY_2, this.model.getGameBoard().getCurrentPlayer().getMoneyAmount());
        assertEquals(Optional.of(Event.BANKRUPT), this.model.getEvent());
    }

    /* */
    @Test
    void testOperationInPrison() {
        model.getGameBoard().getCurrentPlayer().pay(START_AMOUNT);
        final ModelBankerState state = new ModelBankerState(this.model, true);
        assertEquals(false, state.verify());
        state.doAction(new DataBuilderOutputImpl().build());
        assertEquals(0, this.model.getGameBoard().getCurrentPlayer().getMoneyAmount());
        state.closeState();
        assertInstanceOf(ModelMovementState.class, this.model.getState());
    }
}
