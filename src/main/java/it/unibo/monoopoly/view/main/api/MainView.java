package it.unibo.monoopoly.view.main.api;

import java.util.List;

import javax.swing.JPanel;

import it.unibo.monoopoly.controller.main.api.MainController;
import it.unibo.monoopoly.view.state.api.ViewState;

/**
 * Represents the main view, contains the frame of the entire game.
 */
public interface MainView extends View {

    /**
     * 
     * @return comment.
     */
    MainController getMainController();

    /**
     * 
     * @return comment.
     */
    List<String> getNameCells();

    /**
     * change the actual state of the view.
     */
    void setState(ViewState state);

    void setInteractivePanel(JPanel panel);

    /**
     * 
     * @return the actual {@link ViewState}
     */
    ViewState getViewState();

    /**
     * Update the {@link View}
     */
    void update();

}
