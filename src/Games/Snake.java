package Games;

import Games.Engine.Game;
import Games.Engine.Window;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Snake extends Game {

    private BufferedImage image = null;

    public Snake(Window window){
        super(window, "Snake");
        this.setBackground(Color.green);

        try {
            image = load_image("/img.png");
        }
        catch(Exception e){
            System.out.println("Problem opening file");
        }


        JButton b = new JButton();
        b.setText("SNAKE GAME EXIT");
        b.setVisible(true);

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
            }
        });

        this.add(b);
        window.pack();
    }

    private BufferedImage load_image(String filename) {
        // This time, you can use an InputStream to load
        try {
            // Grab the InputStream for the image.
            InputStream in = getClass().getResourceAsStream(filename);

            // Then read it.
            return ImageIO.read(in);
        } catch (IOException e) {
            System.out.println("The image was not loaded.");
            //System.exit(1);
        }
        return null;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawRect(230,80,10,10);
        g.setColor(Color.BLUE);
        g.fillRect(230,80,10,10);

        for (int i = 0; i < 20; i++){
            g.fillRect(i*22, 10, 20, 20);
        }

        g.fillRoundRect(50, 50, 100, 100, 20, 20);
        g.drawImage(image, 20, 20, null);
    }

}
