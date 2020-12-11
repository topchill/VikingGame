package cegepst.engine;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

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

    public BufferedImage rotateImageByDegrees(BufferedImage img, double angle, int imageX, int imageY) {

        double rads = Math.toRadians(0);
        double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
        int w = img.getWidth();
        int h = img.getHeight();
        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);

        BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        AffineTransform at = new AffineTransform();
        at.translate((newWidth - w) / 2, (newHeight - h) / 2);

        int x = w / 2;
        int y = h / 2;

        at.rotate(rads, x, y);
        graphics.setTransform(at);
        graphics.drawImage(img, imageX, imageY, null);
        graphics.drawRect(0, 0, newWidth - 1, newHeight - 1);
        graphics.dispose();

        return rotated;
    }
}
