package Games;

import Games.Data.Hanoi.Ring;
import Games.Data.Hanoi.Stand;
import Games.Engine.Game;
import Games.Engine.Window;

import javax.swing.*;
import java.awt.*;

public class Hanoi extends Game {

    private JPanel rule_screen;

    private Stand stand1, stand2, stand3;
    private boolean dest_select, solved;
    private Stand select_stand;
    private JLabel info_label;
    private int ring_quantity;

    public Hanoi(Window window) {
        super(window, "Hanoi");
        this.setBackground(Color.black);

        // Regeleinblendung
        rule_screen = new JPanel();
        int width = 300;
        int height = 300;
        rule_screen.setLocation(PANELWIDTH/2-width/2, PANELHEIGHT/2-height/2);
        rule_screen.setSize(width, height);
        rule_screen.setLayout(new BoxLayout(rule_screen, BoxLayout.Y_AXIS));
        this.add(rule_screen);

        JButton rule_back_btn = new JButton("<");
        rule_back_btn.addActionListener(e -> rule_screen.setVisible(false));
        rule_screen.add(rule_back_btn);

        JLabel rule_1 = new JLabel("<html><br><br>1. Der linke Ringturm muss nach Rechts<br><br>" +
                "2. Es darf nur der oberste Ring des Turms bewegt werden<br><br>" +
                "3. Ein größerer Ring darf nicht auf einem kleineren liegen<br><br>" +
                "4. Der Reset Button setzt die Auswahl zurück</html>");
        rule_1.setFont(font1);
        rule_screen.add(rule_1);

        rule_screen.setVisible(false);


        // Buttons

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

        // reset Button
        JButton reset_btn = new JButton("Reset");
        reset_btn.setVisible(true);
        reset_btn.setLocation(680, 10);
        reset_btn.setSize(100, 35);
        reset_btn.addActionListener(e -> {
            if(solved) {reset(); solved=false;}
            else {dest_select=false;}});
        this.add(reset_btn);


        // solve Button
        JButton solve_btn = new JButton("Lösen");
        solve_btn.setVisible(true);
        solve_btn.setLocation(830, 10);
        solve_btn.setSize(100, 35);
        solve_btn.addActionListener(e -> solve());
        this.add(solve_btn);

        // Regel Button
        JButton rules_btn = new JButton("Regeln");
        rules_btn.setVisible(true);
        rules_btn.setLocation(980, 10);
        rules_btn.setSize(100, 35);
        rules_btn.addActionListener(e -> rule_screen.setVisible(true));
        this.add(rules_btn);

        // info Label
        info_label = new JLabel("INFO TEXT-----------------");
        info_label.setFont(font2);
        info_label.setForeground(Color.white);
        info_label.setSize(info_label.getPreferredSize());
        info_label.setLocation(160, 10);
        this.add(info_label);
        
        // Stand objects
        stand1 = new Stand(this, 40, 450,13, 1);
        stand2 = new Stand(this, stand1.x + stand1.rec_bottom.width+40, 450, 13,2);
        stand3 = new Stand(this, stand2.x + stand2.rec_bottom.width+40, 450, 13, 3);

        ring_quantity = 6;
        stand1.init_rings(6);

        dest_select = false;
        solved = false;
        select_stand = stand1;

        setLayout(null);
        window.pack();
    }

    @Override
    public void update_loop() {
        if(tick%10 == 0) {      // every 10 ticks.
            if (stand1.moving_ring || stand2.moving_ring || stand3.moving_ring) {
                info_label.setText("Auswahl blockiert");
                info_label.setForeground(Color.red);
                info_label.setSize(info_label.getPreferredSize());
            } else if (dest_select) {
                info_label.setText("Start: Stand" + select_stand.number + " - Bitte Ziel wählen");
                info_label.setForeground(Color.white);
                info_label.setSize(info_label.getPreferredSize());
            } else {
                info_label.setText("Bitte Start wählen");
                info_label.setForeground(Color.white);
                info_label.setSize(info_label.getPreferredSize());
            }
            if(stand3.stack.count_nodes() == ring_quantity) {
                info_label.setText("Gelöst! Reset zum Neustarten ->");
                info_label.setForeground(Color.green);
                info_label.setSize(info_label.getPreferredSize());
                solved = true;
            }
        }
    }

    public void button_click(Stand stand) {
        if(stand1.moving_ring || stand2.moving_ring || stand3.moving_ring) {
            dest_select = false;
        } else {
            if (dest_select) {
                Ring select = select_stand.get_top_ring(false);
                if(select == null){
                    System.out.println("MOVE BLOCKED-resetting");
                    dest_select = false;
                }else{
                    stand.add_ring(select_stand.get_top_ring(true));
                    dest_select = false;
                }
            } else {
                select_stand = stand;
                dest_select = true;
            }
        }
    }

    public void solve() {
        if(stand2.stack.count_nodes() > 0 || stand3.stack.count_nodes() > 0) {reset();}
        new Thread(() -> {solve_thread(stand1.stack.count_nodes(), stand1, stand2, stand3);}).start();
    }

    public void solve_thread(int n, Stand start, Stand helper, Stand dest) {
        if(n == 0) {return;}
        else {
            pass();
            while (stand1.moving_ring || stand2.moving_ring || stand3.moving_ring) {pass();};
            solve_thread(n-1, start, dest, helper);
            pass();


            while (stand1.moving_ring || stand2.moving_ring || stand3.moving_ring) {pass();};
            dest.add_ring(start.get_top_ring(true));
            pass();

            while (stand1.moving_ring || stand2.moving_ring || stand3.moving_ring) {pass();};
            solve_thread(n-1, helper, start, dest);
        }
    }

    public void reset() {
        stand1.clear();
        stand2.clear();
        stand3.clear();
        stand1.init_rings(ring_quantity);
    }
}
