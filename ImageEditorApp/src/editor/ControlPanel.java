package editor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ControlPanel extends JPanel {
    private final ImageEditorFrame frame;

    public ControlPanel(ImageEditorFrame frame) {
        this.frame = frame;

        JButton uploadBtn = new JButton("Încarcă Imagine");
        uploadBtn.addActionListener(this::uploadImage);
        add(uploadBtn);

        JButton blurBtn = new JButton("Blur");
        blurBtn.addActionListener(this::applyBlur);
        add(blurBtn);

        JButton rotateBtn = new JButton("Rotire 90°");
        rotateBtn.addActionListener(this::rotate);
        add(rotateBtn);

        JButton resizeBtn = new JButton("Redimensionare 50%");
        resizeBtn.addActionListener(this::resize);
        add(resizeBtn);

        JButton textBtn = new JButton("Adaugă Text");
        textBtn.addActionListener(this::addText);
        add(textBtn);

        JButton saveBtn = new JButton("Salvează Imaginea");
        saveBtn.addActionListener(this::save);
        add(saveBtn);
    }

    private void uploadImage(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
            try {
                BufferedImage image = ImageIO.read(chooser.getSelectedFile());
                frame.setImage(image);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Eroare la încărcare imagine.");
            }
        }
    }

    private void applyBlur(ActionEvent e) {
        BufferedImage img = frame.getEditedImage();
        if (img != null) {
            frame.updateImage(ImageEditorFunctions.blur(img));
        }
    }

    private void rotate(ActionEvent e) {
        BufferedImage img = frame.getEditedImage();
        if (img != null) {
            frame.updateImage(ImageEditorFunctions.rotate(img, 90));
        }
    }

    private void resize(ActionEvent e) {
        BufferedImage img = frame.getEditedImage();
        if (img != null) {
            frame.updateImage(ImageEditorFunctions.resize(img, 0.5));
        }
    }

    private void addText(ActionEvent e) {
        BufferedImage img = frame.getEditedImage();
        if (img != null) {
            String text = JOptionPane.showInputDialog("Introdu textul:");
            if (text != null) {
                frame.updateImage(ImageEditorFunctions.addText(img, text));
            }
        }
    }

    private void save(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
            try {
                ImageIO.write(frame.getEditedImage(), "png", chooser.getSelectedFile());
                JOptionPane.showMessageDialog(frame, "Imagine salvată cu succes.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Eroare la salvare imagine.");
            }
        }
    }
}