package it.unibo.monoopoly.view.impl.banker.state;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import it.unibo.monoopoly.view.impl.PanelAdapter;

public class MortgageView extends PanelAdapter{
    ActionListener closeMethod;
    List<String> cellList;
    JPanel innerPanel;

    public MortgageView(ActionListener closeMethod, List<String> cellList) {
        super();
        this.closeMethod = closeMethod;
        this.cellList = cellList;
    }

    @Override
    protected void panelInit() {
        this.innerPanel = new JPanel();
        this.setLayout(new BorderLayout());
        this.innerPanel.setLayout(new GridLayout(0, cellList.size()));
        this.add(new JTextArea("Scegli una proprietà da ipotecare"), BorderLayout.NORTH);
        for (String string : cellList) {
            JButton j = new JButton(string);
            this.innerPanel.add(j, BorderLayout.CENTER);
            j.addActionListener(closeMethod);
        }
        this.add(innerPanel, BorderLayout.CENTER);
    }
}
