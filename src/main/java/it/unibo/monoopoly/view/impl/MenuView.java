package it.unibo.monoopoly.view.impl;

import java.util.List;

import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.Toolkit;

import it.unibo.monoopoly.controller.api.MainController;
import it.unibo.monoopoly.controller.api.MenuController;

/**
 * Initial view shown at the start of application.
 */
public class MenuView extends AbstractView {

    private final PanelAdapter menuPanel;

    /**
     * Construct and initialize thr frame and panels of {@link MenuView}.
     * @param menuController the controller of the application
     */
    public MenuView(final MenuController menuController) {
        super();
        final Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.getMainFrame().setSize((int) screenDimension.getWidth() / 2, (int) screenDimension.getHeight() / 2);
        this.getMainFrame().setExtendedState(JFrame.MAXIMIZED_BOTH);
        menuPanel = new MenuPanel(menuController, super.getColors());
    }

    /**
     * MAIN ONLY FOR TEST.

    public static void main(final String[] args) {
        new MenuView().display();
    } */

    /**
     * {@inheritDoc}
     */
    @Override
    public PanelAdapter getMainPanel() {
        return this.menuPanel;
    }

    @Override
    public MainController getMainController() {
        return null;
    }

    @Override
    public List<String> getNameCells() {
        return null;
    }

}
