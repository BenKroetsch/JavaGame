package Levels;

import ui.Game;
import utility.Constants;
import utility.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static utility.LoadSave.LEVEL1_ATLAS;
import static utility.LoadSave.TESTLEVEL;

public class LevelManager {
    private final Integer subImageSize = 32;
    private static final int[] levelConstant = {11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 36, 37, 37, 38, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 36, 38, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 36, 37, 38, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 32, 11, 11, 11, 11, 11, 11, 11, 39, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 39, 11, 11, 11, 11, 36, 38, 11, 11, 12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12, 11, 11, 11, 11, 11, 11, 11, 11, 36, 37, 37, 37, 38, 11, 11, 11, 11, 11, 0, 17, 11, 36, 33, 37, 37, 37, 2, 11, 11, 11, 15, 11, 11, 11, 11, 11, 11, 11, 28, 14, 11, 11, 15, 13, 13, 13, 14, 11, 11, 11, 15, 11, 11, 11, 11, 11, 11, 11, 28, 14, 11, 11, 15, 13, 13, 13, 14, 11, 11, 11, 15, 11, 11, 11, 11, 11, 11, 11, 28, 14};

    private final Integer screenSize = 64;
    private final Game game;
    private BufferedImage levelSprites;
    private final BufferedImage currentLevel;
    private BufferedImage[] spriteRectangle;
    private static final ArrayList<Integer> levelMapping = new ArrayList<>();


    public LevelManager(Game game) {
        this.game = game;
        initialize();
        Constants.LevelConstant.init();
        currentLevel = LoadSave.getAtlas(TESTLEVEL);
        importOutsideSprite();
    }

    private void importOutsideSprite() {
        levelSprites = LoadSave.getAtlas(LEVEL1_ATLAS);
        spriteRectangle = new BufferedImage[48];
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 12; i++) {
                int index = j * 12 + i;
                spriteRectangle[index] = levelSprites.getSubimage(i * subImageSize, j * subImageSize, subImageSize, subImageSize);
            }
        }
    }



    public void render(Graphics g) {
        for (int j = 0; j < 12; j++) {
            for (int i = 0; i < 20; i++) {
                int index = j * 20 + i;
                int pixel = levelMapping.get(index);
                g.drawImage(spriteRectangle[pixel], i * 64, j * 64, 64, 64, null);
            }
        }
    }


    public void initialize() {
        for (int i = 0; i < 240; i++) {
            levelMapping.add(levelConstant[i]);
        }
    }

    public static int getIndex(int index) {
        return levelMapping.get(index);
    }
}


