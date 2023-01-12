package model;

import java.awt.*;

public class Entity {
    protected static final double weight = 0.05;
    protected static float downVelocity = 0;
    protected static double jumpStrength = 4;
    protected float xPos, yPos;
    protected int width, height;
    protected int actualWidth, actualHeight;
    protected int actualX, actualY;
    protected Rectangle hitBox;
    protected Rectangle firstBlock;

    public Entity(float x, float y, int width, int height) {
        this.xPos = x;
        this.yPos = y;
        this.width = width;
        this.height = height;
        this.actualWidth = (int) (width * 0.60);
        this.actualHeight = (int) (height * 0.70);
        this.actualX = (int) xPos + 27;
        this.actualY = (int) yPos + 9;
        createHitBox();
    }

    private void createHitBox() {
        this.hitBox = new Rectangle((int) xPos + 27, (int) yPos + 9, (int) (width * 0.60), (int) (height * 0.70));
    }

    public void updateHitBox() {
        hitBox.setLocation((int) xPos + 27, (int) yPos + 9);
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public void drawRectangle(Graphics g) {
        g.setColor(Color.RED);
        g.drawRect((int) xPos + 27, (int) yPos + 9, (int) (width * 0.60), (int) (height * 0.70));
    }

}
