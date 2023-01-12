package ui;

import javax.swing.*;

public class GameFrame extends JFrame {


    public GameFrame(GamePanel gamePanel) {

        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Game");
        this.add(gamePanel);
        this.pack();
        this.setVisible(true);
    }
}
