package it.unibo.monoopoly.model.gameboard.api;

import java.util.Optional;

import it.unibo.monoopoly.model.player.api.Player;

/**
 * Represents the buyable cells of the gameboard.
 */
public interface Buyable extends Cell {

    /**
     * method that return if is buyable.
     * 
     * @return true if is buyable
     */
    boolean isAvailable();

    /**
     * 
     * @return true if the property is already mortgaged
     */
    boolean isMortgaged();

    /**
     * method that return the owner of property.
     * 
     * @return the owner of property
     */
    Optional<Player> getOwner();

    /**
     * method that return the cost of property.
     * 
     * @return the cost of property
     */
    int getCost();

    /**
     * method that set the owner of property.
     * 
     * @param ownerPlayer
     */
    void setOwner(Optional<Player> ownerPlayer);

    /**
     * method that return the rent of ther property.
     * 
     * @return the value of rent the property
     */
    int getRentalValue();

    /**
     * 
     * @return the mortgage value
     */
    int getMortgageValue();

    /**
     * method that set the mortgage of property.
     * 
     */
    void setMortgage();

    /**
     * remove the mortgage of property.
     * 
     */
    void removeMortgage();

}
