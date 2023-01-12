package ui;

import inputs.Keyboard;
import inputs.Mouse;

import javax.swing.*;
import java.awt.*;

import static ui.Game.GAME_HEIGHT;
import static ui.Game.GAME_WIDTH;

public class GamePanel extends JPanel {

    private Mouse mouseInputs;
    private final Game game;
    private Keyboard keyboard;


    public GamePanel(Game game) {
        this.game = game;
        initializeKeyBoard();
        initializeMouseInputs();

        setSize();
    }

    public void initializeKeyBoard() {
        keyboard = new Keyboard(this);
        addKeyListener(keyboard);
    }


    public void initializeMouseInputs() {
        mouseInputs = new Mouse(this);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }


    public void setSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        this.setPreferredSize(size);
    }

    public void updateGame() {

    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g);
    }

    public Game getGame() {
        return game;
    }
}
