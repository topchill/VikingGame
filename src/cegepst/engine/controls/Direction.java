package cegepst.engine.controls;

public enum Direction {
    LEFT(-1, 0),
    RIGHT(1, 0),
    UP(0, -1),
    DOWN(0, 1);

    private final double xMultiplier;
    private final double yMultiplier;

    Direction(double xMultiplier, double yMultiplier) {
        this.xMultiplier = xMultiplier;
        this.yMultiplier = yMultiplier;
    }

    public double getVelocityX(int speed) {
        return xMultiplier * speed;
    }

    public double getVelocityY(int speed) {
        return yMultiplier * speed;
    }
}
