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

public class Snake extends Game {

    private BufferedImage image = null;

    public Snake(Window window){
        super(window, "Snake");
        this.setBackground(Color.green);

        try {
            image = ImageIO.read(new File("Z:\\Programming\\Programming-Java\\Projects\\Q11\\info11-jks\\src\\img.png"));
        }
        catch(IOException ex){
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
