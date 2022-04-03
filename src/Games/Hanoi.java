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


        // Buttons
        // Regel Button
        JButton rules_btn = new JButton("Regeln");
        rules_btn.setVisible(true);
        rules_btn.setLocation(990, 10);
        rules_btn.setSize(100, 40);
        rules_btn.addActionListener(e -> rule_screen.setVisible(true));
        this.add(rules_btn);

        // Select Buttons
        JButton stand1_btn = new JButton("Stand 1");
        stand1_btn.setVisible(true);
        stand1_btn.setLocation(150, 500);
        stand1_btn.setSize(100, 35);
        this.add(stand1_btn);

        JButton stand2_btn = new JButton("Stand 2");
        stand2_btn.setVisible(true);
        stand2_btn.setLocation(500, 500);
        stand2_btn.setSize(100, 35);
        this.add(stand2_btn);

        JButton stand3_btn = new JButton("Stand 3");
        stand3_btn.setVisible(true);
        stand3_btn.setLocation(850, 500);
        stand3_btn.setSize(100, 35);
        this.add(stand3_btn);


        JButton solve_btn = new JButton("Lösen");
        solve_btn.setVisible(true);
        solve_btn.setLocation(700, 10);
        solve_btn.setSize(100, 35);
        this.add(solve_btn);


        // Stand objects
        stand1 = new Stand(this, 40, 450,13);
        stand2 = new Stand(this, stand1.x + stand1.rec_bottom.width+40, 450, 13);
        stand3 = new Stand(this, stand2.x + stand2.rec_bottom.width+40, 450, 13);

        stand1.init_rings();

        new Thread(() -> {demo();}).start();

        setLayout(null);
        window.pack();
    }

    @Override
    public void update_loop() {

    }

    public void demo() {
        for(int i = 0; i < 6; i++) {
            stand2.add_ring(stand1.get_top_ring(true));
            pass();
            while (stand2.moving_ring) {
                pass();
            }
        }
    }
}
