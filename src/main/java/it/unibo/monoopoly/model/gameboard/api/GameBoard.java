package it.unibo.monoopoly.model.gameboard.api;

import java.util.List;

import it.unibo.monoopoly.model.deck.api.Deck;
import it.unibo.monoopoly.model.player.api.Player;

/**
 * This interface rapresents the game board of the game.
 * Save the current player in turn amd check if the game is ended.
 */
public interface GameBoard {

    /**
     * @param index
     * @return cell in index position.
     */
    Cell getCell(int index);

    /**
     * Remove a player from the game.
     */
    void removePlayer();

    /**
     * Control if the game is ended checking the number of player remaining.
     * 
     * @return true if the game is ended.
     */
    boolean isGameEnded();

    /**
     * Set the next player.
     */
    void nextPlayer();

    /**
     * @return current player to play.
     */
    Player getCurrentPlayer();

    /**
     * @return list of players in game.
     */
    List<Player> getPlayersList();

    /**
     * @return list of names of cells
     */
    List<String> getCellsNames();

    /**
     * @return list of cells.
     */
    List<Cell> getCellsList();

    /**
     * @return dices.
     */
    Dices getDices();

    /**
     * @return deck.
     */
    Deck getDeck();

}
