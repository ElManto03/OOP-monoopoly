package it.unibo.monoopoly.view.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.text.ParseException;
import java.util.List;

import javax.swing.GrayFilter;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class SelectionPanel extends PanelAdapter {

    private JPanel namesPanel;
    private JPanel numberPanel;
    private List<Color> colors;
    private Font font = new Font("Arial", Font.BOLD, 15);

    public SelectionPanel() {
        this.colors = List.of(Color.BLUE, Color.GREEN, Color.RED, Color.ORANGE, Color.YELLOW, Color.PINK);
        this.setLayout(new GridBagLayout());
        final JLabel nPlayerTextSelection = new JLabel("Scegli il numero di giocatori");
        nPlayerTextSelection.setFont(this.font);
        final JSpinner spinnerSelection = new JSpinner(new SpinnerNumberModel(4, 2, 6, 1));
        final JButton confirmSelection = new JButton("OK");
        confirmSelection.addActionListener(ev -> {
            try {
                spinnerSelection.commitEdit();
                generateNamesPanel((int) spinnerSelection.getValue());
            } catch (ParseException ex) {
                System.out.print("s");
                JOptionPane.showMessageDialog(this,
                        "Input non valido: " + spinnerSelection.getValue() + " è un numero di giocatori non consentito",
                        "Errore numero giocatori",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        this.numberPanel = new JPanel(new GridBagLayout());
        this.numberPanel.add(nPlayerTextSelection, getLabelConstraints());
        this.numberPanel.add(spinnerSelection, getSpinnerConstraints());
        this.numberPanel.add(confirmSelection, getButtonConstraints());
        this.add(numberPanel, getBasicConstraints());
    }

    private void generateNamesPanel(int players) {
        this.remove(numberPanel);
        this.namesPanel = new JPanel(new GridBagLayout());
        for (int i = 0; i < players; i++) {
            final GridBagConstraints gbc = getBasicConstraints();
            final JTextField nameField = new JTextField(25);
            final JLabel nameLabel = new JLabel("Inserisci il nome del player: ");
            final JPanel color = new JPanel();
            color.setPreferredSize(new Dimension(40, 20));
            color.setBackground(colors.get(i));
            nameLabel.setFont(this.font);
            gbc.fill = GridBagConstraints.NONE;
            gbc.weightx = 0.05;
            this.namesPanel.add(color, gbc);
            gbc.weightx = 0.3;
            this.namesPanel.add(nameLabel, gbc);
            gbc.weightx = 0.65;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            this.namesPanel.add(nameField, gbc);
        }
        final JButton back = new JButton("Indietro");
        final JButton confirm = new JButton("Via!");
        back.setFont(font);
        confirm.setFont(font);
        final GridBagConstraints gbc = getBasicConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        this.namesPanel.add(back,gbc);
        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.EAST;
        this.namesPanel.add(confirm,gbc);
        this.add(namesPanel, getNamesConstraint());
        this.revalidate();
        this.repaint();

        back.addActionListener(e -> {
            this.remove(namesPanel);
            this.add(numberPanel);
            this.revalidate();
            this.repaint();
        });
    }

    private GridBagConstraints getNamesConstraint() {
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weighty = 1;
        return gbc;
    }

    public void display() {
        this.setVisible(true);
    }

    private GridBagConstraints getLabelConstraints() {
        final GridBagConstraints gbc = getBasicConstraints();
        gbc.weightx = 0.4;
        return gbc;
    }

    private GridBagConstraints getSpinnerConstraints() {
        final GridBagConstraints gbc = getBasicConstraints();
        gbc.weightx = 0.3;
        return gbc;
    }

    private GridBagConstraints getButtonConstraints() {
        final GridBagConstraints gbc = getBasicConstraints();
        gbc.weightx = 0.3;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        return gbc;
    }

    private GridBagConstraints getBasicConstraints() {
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        return gbc;
    }

}
