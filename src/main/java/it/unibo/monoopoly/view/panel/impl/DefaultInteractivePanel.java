package it.unibo.monoopoly.view.panel.impl;

import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public final class DefaultInteractivePanel extends JPanel {

    private static final int FONT_SIZE = 50;
    private static final long serialVersionUID = 1L;

    public DefaultInteractivePanel() {
        final JTextArea startText = new JTextArea("Qui appariranno le celle di propria proprietà tra cui scegliere");
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        startText.setFont(new Font("Arial", Font.PLAIN, FONT_SIZE));
        startText.setEnabled(false);
        startText.setLineWrap(true);
        startText.setWrapStyleWord(true);
        add(startText);
    }

}
