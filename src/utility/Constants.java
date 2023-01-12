package utility;

import java.util.ArrayList;

public class Constants {

    public static class Directions {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }

    public static class PlayConstant {
        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int FALLINGJUMPING = 2;
        public static final int GROUND = 4;
        public static final int HIT = 5;
        public static final int ATTACK_1 = 6;
        public static final int ATTACK_JUMP_1 = 7;
        public static final int ATTACK_JUMP_2 = 8;

        public static int getSpriteAmount(int pc) {
            switch (pc) {
                case RUNNING:
                    return 6;
                case IDLE:
                    return 5;
                case GROUND:
                    return 2;
                case HIT:
                    return 4;
                case FALLINGJUMPING:
                case ATTACK_1:
                case ATTACK_JUMP_1:
                case ATTACK_JUMP_2:
                    return 3;
                default:
                    return 1;
            }
        }
    }

        public static class LevelConstant {
            private static final int[] walkable = {0, 1, 2, 3, 6, 8, 7, 9, 10, 30, 31, 33, 36, 37, 38, 39};
            public static ArrayList<Integer> hit = new ArrayList<Integer>();

            public static void init() {
                for (int j : walkable) {
                    hit.add(j);
                }
            }

            public static boolean isHit(int block) {
                return hit.contains(block);
            }
        }
    }
