package it.unibo.monoopoly.controller.menu.impl;

import java.util.List;

import it.unibo.monoopoly.controller.main.api.MainController;
import it.unibo.monoopoly.controller.main.impl.MainControllerImpl;
import it.unibo.monoopoly.controller.menu.api.MenuController;
import it.unibo.monoopoly.model.main.impl.MainModelImpl;
import it.unibo.monoopoly.view.main.impl.MenuView;

/**
 * Implementation of {@link MenuController} interface.
 */
public class MenuControllerImpl implements MenuController {

    /**
     * Constructor of {@link MenuControllerImpl}; start and display the
     * {@link MenuView}.
     */
    public MenuControllerImpl() {
        new MenuView(this).display();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void goGame(final List<String> namePlayers) {
        final MainController mainController = new MainControllerImpl(new MainModelImpl(namePlayers), namePlayers);
        mainController.startTurn();
    }

}
