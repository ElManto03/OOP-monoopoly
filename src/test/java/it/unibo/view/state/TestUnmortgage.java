package it.unibo.view.state;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.swing.JFrame;

import it.unibo.monoopoly.controller.api.MainController;
import it.unibo.monoopoly.controller.impl.MainControllerImpl;
import it.unibo.monoopoly.model.impl.player.TurnImpl;
import it.unibo.monoopoly.view.impl.MainView;
import it.unibo.monoopoly.view.impl.unmortgage.state.ViewUnmortgageState;

public class TestUnmortgage extends JFrame{

    public static void main(String[] strg) {
        ViewUnmortgageState state;
        List<String> lista = new ArrayList<>();
        lista.add("ciao");
        lista.add("lol");
        List<String> lis = new ArrayList<>();
        lis.add("Franco");
        lis.add("Luca");
        List<Integer> cells = new LinkedList<>();
        cells.add(1);
        cells.add(0);
        final MainController controller = new MainControllerImpl(new TurnImpl(lista), lis);        
        state = new ViewUnmortgageState(new MainView(controller, lista, lis));
        state.setMode(false);
        state.visualize(Optional.of(cells));
    }

}
