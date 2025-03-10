package it.unibo.monoopoly.view.main.impl;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.monoopoly.view.main.api.View;

/**
 * Abstract class that represents a {@link View} used by the application.
 */
public abstract class AbstractView implements View {

    private static final List<Color> COLORS = List.of(Color.BLUE, Color.GREEN, Color.RED, Color.ORANGE);

    private final JFrame mainFrame = new JFrame("MONOOPOLY");

    /**
     * initialize the main frame of a {@link View}.
     */
    public AbstractView() {
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent k) {
                if (k.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    showConfirmToExit();
                }
            }
        });
    }

    private void showConfirmToExit() {
        if (JOptionPane.showConfirmDialog(this.mainFrame, "Siete sicuri di voler chiudere l'applicazione?",
                "Chiudere applicazione", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void display() {
        this.getMainFrame().add(this.getMainPanel());
        this.mainFrame.setVisible(true);
        this.getMainFrame().requestFocusInWindow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Suppressing according to pattern State")
    public final JFrame getMainFrame() {
        return mainFrame;
    }

    /**
     * 
     * @return the list of colors used in the game.
     */
    protected static List<Color> getColors() {
        return COLORS;
    }

}
