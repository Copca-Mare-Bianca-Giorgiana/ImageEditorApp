package editor;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class ImageEditorFunctions {

    public static BufferedImage blur(BufferedImage src) {
        float[] matrix = new float[9];
        for (int i = 0; i < 9; i++) matrix[i] = 1.0f / 9.0f;
        ConvolveOp op = new ConvolveOp(new Kernel(3, 3, matrix));
        return op.filter(src, null);
    }

    public static BufferedImage rotate(BufferedImage src, double angle) {
        int w = src.getWidth();
        int h = src.getHeight();
        BufferedImage rotated = new BufferedImage(h, w, src.getType());
        Graphics2D g2d = rotated.createGraphics();
        g2d.rotate(Math.toRadians(angle), h / 2.0, h / 2.0);
        g2d.translate((h - w) / 2, (h - w) / 2);
        g2d.drawImage(src, 0, 0, null);
        g2d.dispose();
        return rotated;
    }

    public static BufferedImage resize(BufferedImage src, double scale) {
        int newW = (int) (src.getWidth() * scale);
        int newH = (int) (src.getHeight() * scale);
        Image tmp = src.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(newW, newH, src.getType());
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }

    public static BufferedImage addText(BufferedImage src, String text) {
        BufferedImage copy = Utils.deepCopy(src);
        Graphics2D g2d = copy.createGraphics();
        g2d.setFont(new Font("Arial", Font.BOLD, 30));
        g2d.setColor(Color.RED);
        g2d.drawString(text, 20, 40);
        g2d.dispose();
        return copy;
    }
}