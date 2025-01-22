package it.unibo.monoopoly.model.api;

import java.util.Optional;

import it.unibo.monoopoly.common.Event;
import it.unibo.monoopoly.model.api.gameboard.Buyable;
import it.unibo.monoopoly.model.api.player.Player;

/**
 * Manage the operations of buying properties and pay.
 */
public interface Notary {

    /**
     * Tells how the active {@code Player} is going to interacte with the {@code Buyable} property is on.
     * @param player the player to check
     * @param cell the cell of the property to check
     * @return the type of event if this occurs
     */
    Optional<Event> checkProperty (Player player, Buyable cell);

    /**
     * Set the given player as owner of the property.
     * @param player the player who buys
     * @param cell the property to buy
     */
    void buyProperty(Player player, Buyable cell);

}
