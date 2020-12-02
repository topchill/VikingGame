package cegepst.engine;

import java.awt.*;

public class Buffer {

    private Graphics2D graphics;

    public Buffer(Graphics2D graphics) {
        this.graphics = graphics;
    }

    public void drawRectangle(int x, int y, int width, int height, Paint paint) {
        graphics.setPaint(paint);
        graphics.fillRect(x, y, width, height);
    }

    public void drawCircle(int x, int y, int radius, Paint paint) {
        graphics.setPaint(paint);
        graphics.fillOval(x, y, radius * 2, radius * 2);
    }

    public void drawText(String text, int x, int y, Paint paint) {
        graphics.setPaint(paint);
        graphics.drawString(text, x, y);
    }

    public void drawHeart(int x, int y, int width, int height, Paint paint) {
        graphics.setPaint(paint);
        int[] triangleX = {
                x - 2*width/18,
                x + width + 2*width/18,
                (x - 2*width/18 + x + width + 2*width/18)/2};
        int[] triangleY = {
                y + height - 2*height/3,
                y + height - 2*height/3,
                y + height };
        graphics.fillOval(
                x - width/12,
                y,
                width/2 + width/6,
                height/2);
        graphics.fillOval(
                x + width/2 - width/12,
                y,
                width/2 + width/6,
                height/2);
        graphics.fillPolygon(triangleX, triangleY, triangleX.length);
    }

    public void drawImage(Image image, int x, int y) {
        graphics.drawImage(image, x, y, null);
    }
}
