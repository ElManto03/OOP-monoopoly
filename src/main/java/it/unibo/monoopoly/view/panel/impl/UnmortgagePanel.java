package it.unibo.monoopoly.view.panel.impl;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * comment.
 */
public class UnmortgagePanel extends PanelAdapter {
    private ActionListener closeMethod;
    private List<String> cellList;
    private JPanel innerPanel;

    /**
     * comment.
     * 
     * @param closeMethod
     * @param cellList
     */
    public UnmortgagePanel(final ActionListener closeMethod, final List<String> cellList) {
        super();
        this.closeMethod = closeMethod;
        this.cellList = cellList;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    protected void panelInit() {
        this.innerPanel = new JPanel();
        this.setLayout(new BorderLayout());
        this.innerPanel.setLayout(new GridLayout(0, cellList.size()));
        this.add(new JTextArea("Scegli una proprietà su cui vendere una casa"), BorderLayout.NORTH);
        for (final String string : cellList) {
            final JButton j = new JButton(string);
            this.innerPanel.add(j, BorderLayout.CENTER);
            j.addActionListener(closeMethod);
        }
        this.add(innerPanel, BorderLayout.CENTER);
    }

}
