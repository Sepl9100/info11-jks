package Games;

import Games.Engine.Game;
import Games.Engine.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends Game {

    public MainMenu(Window window){
        super(window, "Hauptmenü");

        this.setBackground(Color.red);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        JPanel line1 = new JPanel();
        line1.setBackground(Color.BLACK);
        this.add(line1, gbc);

        gbc.gridy++;
        JPanel line2 = new JPanel();
        line2.setBackground(Color.BLUE);
        this.add(line2, gbc);

        JButton b = new JButton();
        b.setText("Open snake game");
        b.setVisible(true);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
                new Snake(window);
            }
        });
        line1.add(b);

        JButton e = new JButton();
        e.setText("Open demo game");
        e.setVisible(true);
        e.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
                new DemoGame(window);
            }
        });
        line1.add(e);

        JButton r = new JButton();
        r.setText("Open demo game");
        r.setVisible(true);
        r.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
                new DemoGame(window);
            }
        });
        line2.add(r);

        JButton hanoi = new JButton();
        hanoi.setText("Hanoi Game");
        hanoi.setVisible(true);
        hanoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
                new Hanoi(window);
            }
        });
        line2.add(hanoi);

        window.pack();
    }

    @Override
    public void update_loop() {

    }

    public void exit(){
        window.remove(this);
        window.repaint();
    }
}
