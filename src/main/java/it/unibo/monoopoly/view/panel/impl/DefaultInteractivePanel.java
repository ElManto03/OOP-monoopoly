package it.unibo.monoopoly.view.panel.impl;

import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public final class DefaultInteractivePanel extends JPanel {

    private static final int FONT_SIZE = 28;
    private static final long serialVersionUID = 1L;

    public DefaultInteractivePanel() {
        final JTextArea startText = new JTextArea(
                "Qui appariranno le celle di propria proprietà tra cui scegliere.\n" +
                "\t*************************\n" +
                "Se si vuole chiudere l'applicazione:\n" + 
                "-> continuare il gioco fino ad arrivare a una di quelle scelte, poi premere esc e selezionare si.\n" +
                "\t*************************\n" +
                "Ora una piccola spiegazione della gameBoard:\n" +
                "-> Lo spostamento di un giocatore è visualizzato tramite lo spostamento del pallino col relativo colore nelle celle.\n" +
                "-> Se una proprietà appartiene a un giocatore sarà visibile un pallino del colore corrispondente al di sopra.\n" +
                "-> Se un proprietà è in bancarotta sarà visibile un pallino grigio al di sopra.\n" +
                "-> Nel momento in cui si costruirà una casa su una proprietà comparira un numero sulla relativa banda colorata a identificare il numero di case costruite.\n" +
                "\t*************************\n" +
                "Infine i messaggi di notifica o le scelte semplici compariranno in una finestrella al centro del pannello.");
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        startText.setFont(new Font("Arial", Font.PLAIN, FONT_SIZE));
        startText.setEnabled(false);
        startText.setLineWrap(true);
        startText.setWrapStyleWord(true);
        add(startText);
    }

}
