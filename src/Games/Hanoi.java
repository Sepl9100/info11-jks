package Games;

import Games.Data.Hanoi.Stand;
import Games.Engine.Game;
import Games.Engine.Window;

import javax.swing.*;
import java.awt.*;

public class Hanoi extends Game {

    private JPanel rule_screen;

    private Stand stand1, stand2, stand3;
    private boolean dest_select;
    private Stand start_stand, dest_stand;


    public Hanoi(Window window) {
        super(window, "Hanoi");
        this.setBackground(Color.black);

        // Regeleinblendung
        rule_screen = new JPanel();
        int width = 300;
        int height = 250;
        rule_screen.setLocation(PANELWIDTH/2-width/2, PANELHEIGHT/2-height/2);
        rule_screen.setSize(width, height);
        rule_screen.setLayout(new BoxLayout(rule_screen, BoxLayout.Y_AXIS));
        this.add(rule_screen);

        JButton rule_back_btn = new JButton("<");
        rule_back_btn.addActionListener(e -> rule_screen.setVisible(false));
        rule_screen.add(rule_back_btn);

        JLabel rule_1 = new JLabel("<html><br><br>1. Der linke Ringturm muss nach Rechts<br><br>" +
                "2. Es darf nur der oberste Ring des Turms bewegt werden<br><br>" +
                "3. Ein größerer Ring darf nicht auf einem kleineren liegen</html>");
        rule_1.setFont(font1);
        rule_screen.add(rule_1);

        rule_screen.setVisible(false);


        // Buttons
        // Regel Button
        JButton rules_btn = new JButton("Regeln");
        rules_btn.setVisible(true);
        rules_btn.setLocation(990, 10);
        rules_btn.setSize(100, 35);
        rules_btn.addActionListener(e -> rule_screen.setVisible(true));
        this.add(rules_btn);

        // Select Buttons
        JButton stand1_btn = new JButton("Stand 1");
        stand1_btn.setVisible(true);
        stand1_btn.setLocation(150, 500);
        stand1_btn.setSize(100, 35);
        stand1_btn.addActionListener(e -> button_click(stand1));
        this.add(stand1_btn);

        JButton stand2_btn = new JButton("Stand 2");
        stand2_btn.setVisible(true);
        stand2_btn.setLocation(500, 500);
        stand2_btn.setSize(100, 35);
        stand2_btn.addActionListener(e -> button_click(stand2));
        this.add(stand2_btn);

        JButton stand3_btn = new JButton("Stand 3");
        stand3_btn.setVisible(true);
        stand3_btn.setLocation(850, 500);
        stand3_btn.setSize(100, 35);
        stand3_btn.addActionListener(e -> button_click(stand3));
        this.add(stand3_btn);


        JButton solve_btn = new JButton("Lösen");
        solve_btn.setVisible(true);
        solve_btn.setLocation(700, 10);
        solve_btn.setSize(100, 35);
        solve_btn.addActionListener(e -> System.out.println(task_queue.filled_until));
        this.add(solve_btn);


        // Stand objects
        stand1 = new Stand(this, 40, 450,13);
        stand2 = new Stand(this, stand1.x + stand1.rec_bottom.width+40, 450, 13);
        stand3 = new Stand(this, stand2.x + stand2.rec_bottom.width+40, 450, 13);

        stand1.init_rings();

        dest_select = false;
        start_stand = stand1;
        dest_stand = stand2;

        //new Thread(() -> {demo();}).start();

        setLayout(null);
        window.pack();
    }

    @Override
    public void update_loop() {
        this.pass();
    }

    public void button_click(Stand stand) {
        if(stand1.moving_ring || stand2.moving_ring || stand3.moving_ring) {
            System.out.println("MOVE BLOCKED");
        } else {
            if (dest_select) {
                dest_stand = stand;
                stand.add_ring(start_stand.get_top_ring(true));
                dest_select = false;
            } else {
                start_stand = stand;
                dest_select = true;
            }
        }
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
