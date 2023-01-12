package model;

public class Obstacle {

//    // pos speed and dimensions
//    private int xPosMoab;
//    private int yPosMob;
//    private final int xMobWidth = randomDimensions();
//    private final int yMobHeight = randomDimensions();
//    private static double xSpeed = 2.5;
//    private static final double ySpeed = 0;
//
//    private String shapeString;
//
////    public Obstacle(float x, float y) {
////        super(x, y);
////    }
//
////    public Obstacle(int x, int y, double xSpeed) {
////        setShape();
////        Obstacle.xSpeed = xSpeed;
////        this.xPosMob = x;
////        this.yPosMob = y - yMobHeight;
////    }
//
//    public void draw(Graphics g) {
//        g.setColor(Color.blue);
//
//        if (Objects.equals(this.shapeString, "circle")) {
//            g.fillOval(xPosMob, yPosMob, xMobWidth, yMobHeight);
//        } else if (Objects.equals(this.shapeString, "rectangle")) {
//            g.fillRect(xPosMob, yPosMob, xMobWidth, yMobHeight);
//        } else
//            g.fillRect(xPosMob, yPosMob, yMobHeight, yMobHeight);
//    }
//
//
//    public void update() {
//        xPosMob -= xSpeed;
//        yPosMob += ySpeed;
//    }
//
//    public boolean outBoundary() {
//        return (xPosMob <= -5);
//    }
//
//    public boolean hit() {
//        return true;
//    }
//
//    public int randomDimensions() {
//        int max = 60;
//        int min = 25;
//
//        int range = max - min;
//
//        return (int) (Math.random() * range) + min;
//    }
//
//
//    public void setShape() {
//        double random;
//        random = java.lang.Math.random();
//
//        if (random <= 0.33) {
//            this.shapeString = "circle";
//        } else if (random <= 0.66) {
//            this.shapeString = "rectangle";
//        } else
//            this.shapeString = "square";
//    }
//
//    public HitBox obstacleHitBox() {
//        return new HitBox(xPosMob, xPosMob + xMobWidth, yPosMob - yMobHeight, yPosMob);
//    }
//}
}


