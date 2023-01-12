package model;

import java.awt.*;

import static java.lang.Math.sqrt;

public class GunShot{
    private int xPos, yPos;
    private final double xSpeed;
    private final double ySpeed;

    public GunShot(int x, int y, int mouseX, int mouseY) {
        this.xPos = x;
        this.yPos = y;

        double xDir = mouseX - xPos;
        double yDir = mouseY - yPos;
        double speed = sqrt((xDir*xDir)+(yDir*yDir));
        this.xSpeed = 5 * xDir / speed;
        this.ySpeed = 5 * yDir / speed;
    }

    public void draw(Graphics g) {
        g.fillOval(xPos, yPos, 10, 10);
    }

    public void update() {
        xPos += xSpeed;
        yPos += ySpeed;
    }
}
