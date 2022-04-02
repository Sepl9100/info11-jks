package Games;

import Games.Data.Hanoi.Stand;
import Games.Engine.Game;
import Games.Engine.Window;

import javax.swing.*;
import java.awt.*;

public class Hanoi extends Game {

    private JPanel rule_screen;

    private Stand stand1, stand2, stand3;


    public Hanoi(Window window) {
        super(window, "Hanoi");
        this.setBackground(Color.black);

        // Regeleinblendung
        rule_screen = new JPanel();
        int width = 400;
        int height = 500;
        rule_screen.setLocation(PANELWIDTH/2-width/2, PANELHEIGHT/2-height/2);
        rule_screen.setSize(width, height);
        rule_screen.setLayout(new BoxLayout(rule_screen, BoxLayout.Y_AXIS));
        this.add(rule_screen);

        JButton rule_back_btn = new JButton("<");
        rule_back_btn.addActionListener(e -> rule_screen.setVisible(false));
        rule_screen.add(rule_back_btn);

        rule_screen.setVisible(false);

        // Regel Button
        JButton rules_btn = new JButton("Regeln");
        rules_btn.setVisible(true);
        rules_btn.setLocation(990, 10);
        rules_btn.setSize(100, 40);
        rules_btn.addActionListener(e -> rule_screen.setVisible(true));
        this.add(rules_btn);



        stand1 = new Stand(this, 60, 350,12);
        stand2 = new Stand(this, stand1.x + stand1.rec_bottom.width+60, 350, 12);
        stand3 = new Stand(this, stand2.x + stand2.rec_bottom.width+60, 350, 12);

        stand1.init_rings();

        new Thread(() -> {demo();}).start();

        setLayout(null);
        window.pack();
    }

    @Override
    public void update_loop() {

    }

    public void demo() {
        stand2.add_ring(stand1.get_top_ring(true));
        pass();
        while (stand2.moving_ring) {pass();}
        stand2.add_ring(stand1.get_top_ring(true));
        pass();
        while (stand2.moving_ring) {pass();}
        stand2.add_ring(stand1.get_top_ring(true));
        pass();
        while (stand2.moving_ring) {pass();}
        stand2.add_ring(stand1.get_top_ring(true));
        pass();
        while (stand2.moving_ring) {pass();}
        stand2.add_ring(stand1.get_top_ring(true));
        pass();
        while (stand2.moving_ring) {pass();}
        stand2.add_ring(stand1.get_top_ring(true));
    }
}
