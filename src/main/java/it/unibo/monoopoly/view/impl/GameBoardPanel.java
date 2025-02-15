package it.unibo.monoopoly.view.impl;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import it.unibo.monoopoly.controller.api.MainController;

public class GameBoardPanel extends PanelAdapter{

    private final MainController mainController;
    private final int mainFrameHeight;
    private final int mainFrameWidth;

    public GameBoardPanel(final MainController mainController, final int mainFrameHeight, final int mainFrameWidth) {
        this.mainController = mainController;
        this.mainFrameHeight = mainFrameHeight;
        this.mainFrameWidth = mainFrameWidth;
    }

    @Override
    protected void panelInit() {
        setPreferredSize(new Dimension(this.mainFrameHeight, this.mainFrameHeight));
        /*final URL imgURL = ClassLoader.getSystemResource("/images/gameBoard.jpg");
        final ImageIcon icon = new ImageIcon(imgURL);
        JLabel gameBoardLabel = new JLabel(icon);
        add(gameBoardLabel);*/
        add(new JLabel("SPERO VADA BENE MONOPOLY"));
    }

}
