package model;

import Levels.LevelManager;
import utility.Constants;
import utility.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utility.Constants.PlayConstant.*;

public class Player extends Entity {

    public static boolean hit;
    private int playerAction = IDLE;
    private BufferedImage image;
    private BufferedImage[][] Animation;
    private int aniTick;
    private int aniIndex;
    private final int aniSpeed = 30;
    private boolean up, left, right, down;
    private int xVel;
    private final int yVel = 2;
    private boolean falling, jumping, isFloor, isCeiling, isMoving, isAttacking = false;
    private Rectangle firstBlock;


    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        this.actualWidth = (int) (width * 0.60);
        this.actualHeight = (int) (height * 0.70);
        this.actualX = (int) xPos + 27;
        this.actualY = (int) yPos + 9;
        loadAnimations();
    }

    public void update() {
        updateAnimations();
        setAction();
        movePlayer();
        updateHitBox();
        checkHitBox();
    }

    public void render(Graphics g) {
        g.drawImage(Animation[playerAction][aniIndex], (int) xPos, (int) yPos,
                width, height, null);
//        drawRectangle(g);
    }


    public void loadAnimations() {
        BufferedImage image = LoadSave.getAtlas(LoadSave.PLAYER_ATLAS);
        Animation = new BufferedImage[9][6];

        for (int j = 0; j < Animation.length; j++)
            for (int i = 0; i < Animation[j].length; i++) {
                Animation[j][i] = image.getSubimage(i * 64, j * 40, 64, 40);
            }
    }

    public void updateAnimations() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= getSpriteAmount(playerAction)) {
                aniIndex = 0;
            }
        }
    }


    public void setAttacking(boolean attacking) {
        isAttacking = attacking;
    }

    public void movePlayer() {
        if (right) {
            xPos += 2;
        }
        if (left) {
            xPos -= 2;
        }
        if (down) {
            yPos += 2;
        }
        if (jumping && !falling) {
            jumpStrength -= weight;
            yPos -= jumpStrength;
            System.out.println(jumpStrength);
        }
        else {
            faller();
        }
    }


    public void setAction() {
        int startingAni = playerAction;
        if (isAttacking) {
            playerAction = ATTACK_1;
            if (startingAni == ATTACK_1 && aniIndex == 2) {
                isAttacking = false;
            }
        } else if (falling) {
            playerAction = FALLINGJUMPING;
        } else if (isMoving) {
            playerAction = RUNNING;
        } else if (jumping) {
            playerAction = FALLINGJUMPING;
        } else {
            playerAction = IDLE;
        }

        if (startingAni != playerAction) {
            aniTick = 0;
            aniIndex = 0;
        }
    }

    private void roofHit() {
        jumping = false;
        falling = true;
        isCeiling = true;
        downVelocity = 0;
        System.out.println("roof");
    }

    private void leftHit(int i) {
        this.xPos = i - 27;
        System.out.println("left");
    }

    private void rightHit(int i) {
        this.xPos = i - 32 - 50;
        System.out.println("right");
    }

    private void floorHit(int j) {
        if (actualY != j - actualHeight) {
            yPos = j - actualHeight - 9;
        }

        falling = false;
        jumping = false;
        isFloor = true;
        downVelocity = 0;
        jumpStrength = 4;
        System.out.println("floor");
    }

    protected void faller() {
        if (!testHitBox( actualY + 1)) {
            isFloor = false;
            downVelocity += weight;
            yPos += downVelocity;
        }
    }

    public boolean checkHitBox() {
        String firstHit = null;
        String secondHit = null;
        for (int j = 0; j < 12; j++) {
            for (int i = 0; i < 20; i++) {
                int index = j * 20 + i;
                Rectangle block = new Rectangle(i * 64, j * 64, 64, 64);

                if (hitBox.intersects(block) && Constants.LevelConstant.isHit(LevelManager.getIndex(index))) {

                    boolean leftHit = hitBox.getMinX() <= block.getMaxX() && hitBox.getMaxX() >= block.getMaxX();
                    boolean leftAndRight = hitBox.getMaxX() <= block.getMaxX() && hitBox.getMinX() >= block.getMinX();
                    boolean rightHit = hitBox.getMaxX() >= block.getMinX() && hitBox.getMinX() <= block.getMinX();
                    boolean floorHit = hitBox.getMaxY() <= block.getMaxY();
                    boolean roofHit = (hitBox.getMinY() >= block.getMinY());

                    // 2 corners in same block
                    if (leftHit && roofHit && floorHit) {
                        leftHit((int) block.getMaxX());
                        return false;
                    }
                    if (rightHit && roofHit && floorHit) {
                        rightHit((int) block.getMinX());
                        return false;
                    }
                    if (leftAndRight && floorHit) {
                        floorHit(j * 64);
                        return false;
                    }
                    if (leftAndRight && roofHit) {
                        roofHit();
                        return false;
                    }

                    //check if corner hits, counter for checking if hit box corners hits 2 separate blocks

                    if (leftHit && roofHit) {
                        if (firstHit == null) {
                            firstHit = "top-left";
                            firstBlock = new Rectangle(new Rectangle(i * 64, j * 64, 64, 64));
                        } else {
                            secondHit = "top-left";
                        }
                    }
                    //top right corner
                    if (rightHit && roofHit) {
                        if (firstHit == null) {
                            firstHit = "top-right";
                            firstBlock = new Rectangle(i * 64, j * 64, 64, 64);
                        } else {
                            secondHit = "top-right";
                        }
                    }
                    // bottom left corner
                    if (leftHit && floorHit) {

                        if (firstHit == null) {
                            firstHit = "bottom-left";
                            firstBlock = new Rectangle(i * 64, j * 64, 64, 64);
                        } else {
                            secondHit = "bottom-left";
                        }
                    }
                    //bottom right corner
                    if (rightHit && floorHit) {
                        if (firstHit == null) {
                            firstHit = "bottom-right";
                            firstBlock = new Rectangle(i * 64, j * 64, 64, 64);
                        } else {
                            secondHit = "bottom-right";
                        }
                    }
                }
            }
        }
        if (firstHit != null && secondHit == null) {
            double leftMagnitude = firstBlock.getMaxX() - hitBox.getMinX();
            double rightMagnitude = hitBox.getMaxX() - firstBlock.getMinX();
            double floorMagnitude = hitBox.getMaxY() - firstBlock.getMinY();
            double roofMagnitude = firstBlock.getMaxY() - hitBox.getMinY();

            // determine what corner is
            if (firstHit == "top-left") {
                if (roofMagnitude <= leftMagnitude) {
                    roofHit();
                } else {
                    leftHit((int) firstBlock.getMaxX());
                }
            }
            if (firstHit == "top-right") {
                if (rightMagnitude >= roofMagnitude) {
                    roofHit();
                } else rightHit((int) firstBlock.getMinX());
            }
            if (firstHit == "bottom-left") {
                if (floorMagnitude >= leftMagnitude) {
                    leftHit((int) firstBlock.getMaxX());
                } else floorHit((int) firstBlock.getMinY());
            }
            if (firstHit == "bottom-right") {
                if (rightMagnitude >= floorMagnitude) {
                    floorHit((int) firstBlock.getMinY());
                } else rightHit((int) firstBlock.getMinX());
            }


        } else if (firstHit == "top-left" && secondHit == "bottom-left" || secondHit == "top-left" && firstHit == "bottom-left") {
            leftHit((int) firstBlock.getMaxX());

        } else if (firstHit == "top-right" && secondHit == "bottom-right" || secondHit == "top-right" && firstHit == "bottom-right") {
            rightHit((int) firstBlock.getMinX());

        } else if (firstHit == "top-left" && secondHit == "top-right" || secondHit == "top-left" && firstHit == "top-right") {
            roofHit();

        } else if (firstHit == "bottom-left" && secondHit == "bottom-right" || secondHit == "bottom-left" && firstHit == "bottom-right") {
            floorHit((int) firstBlock.getMinY());
        }
        return true;
    }


    public boolean testHitBox(int y) {
        Rectangle newHitBox = hitBox;
        String firstHit = null;
        String secondHit = null;
        for (int j = 0; j < 12; j++) {
            for (int i = 0; i < 20; i++) {
                int index = j * 20 + i;
                Rectangle block = new Rectangle(i * 64, j * 64, 64, 64);

                if (newHitBox.intersects(block) && Constants.LevelConstant.isHit(LevelManager.getIndex(index))) {

                    boolean leftHit = newHitBox.getMinX() <= block.getMaxX() && newHitBox.getMaxX() >= block.getMaxX();
                    boolean leftAndRight = newHitBox.getMaxX() <= block.getMaxX() && newHitBox.getMinX() >= block.getMinX();
                    boolean rightHit = newHitBox.getMaxX() >= block.getMinX() && newHitBox.getMinX() <= block.getMinX();
                    boolean floorHit = newHitBox.getMaxY() <= block.getMaxY();
                    boolean roofHit = (newHitBox.getMinY() >= block.getMinY());

                    if (leftAndRight && floorHit) {
                        System.out.println(LevelManager.getIndex(index));
                        System.out.println("test4");

                        return true;

                    }

                    //check if corner hits, counter for checking if hit box corners hits 2 separate blocks

                    if (leftHit && roofHit) {
                        if (firstHit == null) {
                            firstHit = "top-left";
                            firstBlock = new Rectangle(new Rectangle(i * 64, j * 64, 64, 64));
                        } else {
                            secondHit = "top-left";
                        }
                    }
                    //top right corner
                    if (rightHit && roofHit) {
                        if (firstHit == null) {
                            firstHit = "top-right";
                            firstBlock = new Rectangle(i * 64, j * 64, 64, 64);
                        } else {
                            secondHit = "top-right";
                        }
                    }
                    // bottom left corner
                    if (leftHit && floorHit) {

                        if (firstHit == null) {
                            firstHit = "bottom-left";
                            firstBlock = new Rectangle(i * 64, j * 64, 64, 64);
                        } else {
                            secondHit = "bottom-left";
                        }
                    }
                    //bottom right corner
                    if (rightHit && floorHit) {
                        if (firstHit == null) {
                            firstHit = "bottom-right";
                            firstBlock = new Rectangle(i * 64, j * 64, 64, 64);
                        } else {
                            secondHit = "bottom-right";
                        }
                    }
                }
            }
        }
        if (firstHit != null && secondHit == null) {
            double leftMagnitude = firstBlock.getMaxX() - newHitBox.getMinX();
            double rightMagnitude = newHitBox.getMaxX() - firstBlock.getMinX();
            double floorMagnitude = newHitBox.getMaxY() - firstBlock.getMinY();

            // determine what corner is
            if (firstHit == "bottom-left") {
                if (floorMagnitude >= leftMagnitude) {

                } else return true;
            }
            if (firstHit == "bottom-right") {
                return rightMagnitude >= floorMagnitude;
            }
        } else if (firstHit == "bottom-left" && secondHit == "bottom-right" || secondHit == "bottom-left" && firstHit == "bottom-right") {
            System.out.println("test1");

            return true;

        }
        return false;
    }

    public void setXPosCharacter(int x) {
        super.xPos += x;
    }

    public void setYPosCharacter(int y) {
        super.yPos += y;
    }

    public float getXPos() {
        return super.xPos;
    }

    public float getYPos() {
        return super.yPos;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setMoving(boolean f) {
        this.isMoving = f;
    }

    public void setFalling(boolean f) {
        this.falling = f;
    }


    public void setJumping(boolean f) {
        this.jumping = f;
    }

}
