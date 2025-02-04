package it.unibo.monoopoly.model.api.player;

import java.util.Optional;
import java.util.Set;

import it.unibo.monoopoly.model.api.gameboard.Buyable;

/**
 * Represents the player of the game.
 */
public interface Player {

    /**
     * @return the name of the player.
     */
    public Optional<String> getName();

    /**
     * @return the amount of money the player has.
     */
    public int getMoneyAmount();

    /**
     * @return the current position of the player.
     */
    public int getActualPosition();

    /**
     * @return true if the player is in prison, false otherwise.
     */
    public boolean isPrisoned();

    /**
     * @return true if the player can pay, false otherwise.
     */
    public boolean isPayable(int amount);

    /**
     * Deducts the specified amount from the player's money.
     * 
     * @param amount the amount to be deducted.
     */
    public void pay(int amount);

    /**
     * Adds the specified amount to the player's money.
     * 
     * @param amount the amount to be added.
     */
    public void receive(int amount);

    /**
     * Adds a property to the player's list of owned properties.
     * 
     * @param property the property to be added.
     * @return the total amount of money the player possesses, including cash and bank account balance.
     */
    boolean addProperty(Buyable property);

    /**
     * Removes a property from the player's list of owned properties.
     * 
     * @param property the property to be removed.
     * @return the total amount of money the player possesses, including cash and bank account balance.
     */
    boolean removeProperty(Buyable property);

    /**
     * Retrieves the properties owned by the player.
     * 
     * @return the properties owned by the player.
     */
    Set<Buyable> getProperties();

    /**
     * Sets the player as bankrupt.
    */
    void inBankrupt();

    /**
     * Checks if the player is bankrupt.
     * 
     * @return true if the player is bankrupt, false otherwise.
     */
    boolean isBankrupt();

    /**
     * Adds a "Get Out of Jail Free" vard to the player's collection.
     * 
     */
    void addGetOutOfJailCard();

    /**
     * Retrieves the number of "Get Out of Jail Free" cards the player has.
     * 
     * @return the number of "Get Out of Jail Free" cards the player currently has.
     */
    int getFreeJailCards();

    /**
     * Uses a "Get Out of Jail Free" card, decrementin the card count by 1.
     * 
     * @return true if the card was used, false if none was available.
     */
    boolean useGetOutOfJailCard();

    /**
     * Sets the player as in prison.
     */
    void setPrisoned();
}