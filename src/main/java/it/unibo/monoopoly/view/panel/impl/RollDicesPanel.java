package it.unibo.monoopoly.view.panel.impl;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * this class implement a panel to allow player to throw dices.
 */
public final class RollDicesPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private static final int TEXT_SIZE = 15;

    /**
     * initialize all needed fields.
     * @param actionListener
     * @param mainFrameHeight
     */
    public RollDicesPanel(final ActionListener actionListener, final int mainFrameHeight) {
        final JButton rollDiceButton = new JButton("Lancia dadi");
        rollDiceButton.setFont(new Font("Arial", Font.PLAIN, mainFrameHeight / TEXT_SIZE));
        rollDiceButton.addActionListener(actionListener);
        this.setLayout(new BorderLayout());
        this.add(rollDiceButton, BorderLayout.CENTER);
    }
}
