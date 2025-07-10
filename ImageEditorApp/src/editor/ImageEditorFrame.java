package editor;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageEditorFrame extends JFrame {
    private BufferedImage originalImage;
    private BufferedImage editedImage;
    private final JLabel imageLabel;

    public ImageEditorFrame() {
        setTitle("Image Editor");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        imageLabel = new JLabel("Încarcă o imagine", SwingConstants.CENTER);
        add(imageLabel, BorderLayout.CENTER);

        ControlPanel controlPanel = new ControlPanel(this);
        add(controlPanel, BorderLayout.SOUTH);
    }

    public void setImage(BufferedImage image) {
        this.originalImage = image;
        this.editedImage = Utils.deepCopy(image);
        displayImage();
    }

    public BufferedImage getEditedImage() {
        return editedImage;
    }

    public void updateImage(BufferedImage image) {
        this.editedImage = image;
        displayImage();
    }

    private void displayImage() {
        if (editedImage != null) {
            imageLabel.setIcon(new ImageIcon(editedImage));
            imageLabel.setText("");
        }
    }
}