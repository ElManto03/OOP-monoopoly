package it.unibo.monoopoly.view.impl.banker.state;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JPanel;

import it.unibo.monoopoly.view.api.ViewState;
import it.unibo.monoopoly.view.impl.MainView;

public class ViewBankerState implements ViewState<Boolean, Optional<List<Integer>>> {
    private final MainView mainView;
    private boolean payOrHouse;
    private JPanel panel;

    public ViewBankerState(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void setMode(Boolean mode) {
        this.payOrHouse = mode;
    }

    @Override
    public void visualize(Optional<List<Integer>> cellList) {
        if (cellList.isPresent()) {
            if (payOrHouse) {
                this.panel = new SellHouseView(new CellGiver(), intToTextCell(cellList.get()));
            } else {
                this.panel = new MortgageView(new CellGiver(), intToTextCell(cellList.get()));
            }
        } else {
            if (payOrHouse) {
                this.panel = new SuccessfulPaymentView(new SimpleExit());
            } else {
                this.panel = new BankruptcyView(new SimpleExit());
            }
        }
        this.mainView.getMainFrame().add(panel);
    }

    private List<String> intToTextCell(List<Integer> cellList) {
        return cellList.stream()
                .map(this.mainView.getNameCells()::get)
                .toList();
    }

    public class CellGiver implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            var button = (JButton) e.getSource();
            String cellName = button.getText();
            int cell = mainView.getNameCells().indexOf(cellName);
            mainView.getMainFrame().remove(panel);
            mainView.getMainController().getControllerState().continueState(Optional.of(cell));
        }

    }

    public class SimpleExit implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            mainView.getMainFrame().remove(panel);
            mainView.getMainController().getControllerState().continueState(Optional.empty());
        }

    }

}
