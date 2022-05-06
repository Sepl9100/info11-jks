package Games;

import Games.Data.Tetris.ColorChangeManager;
import Games.Data.Tetris.TileArray;
import Games.Data.Tetris.Tiles.Tile;
import Games.Data.Tetris.Tiles.Tilecolors;
import Games.Engine.Game;
import Games.Engine.Keyboard;
import Games.Engine.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Tetris extends Game {

    private TileArray array;
    private Random random;

    private final JPanel start_screen;
    private final JCheckBox wide_mode;
    private final JCheckBox special_mode_cb;
    private final JLabel score_lbl;

    private Tile activetile;
    private boolean started = false;
    private boolean game_over = false;

    private boolean special_mode;

    private int tilesize = 30;
    private int left_offset = 390;
    public int score = 0;

    public Tetris(Window window){
        super(window, "Tetris");
        this.setBackground(Color.black);

        random = new Random();

        // Start Screen UI
        start_screen = new JPanel();
        int width = 400;
        int height = 500;
        start_screen.setLocation(PANELWIDTH/2-width/2, PANELHEIGHT/2-height/2);
        start_screen.setSize(width, height);
        start_screen.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        wide_mode = new JCheckBox("Breiter Modus");     // auswählbare optionen
        start_screen.add(wide_mode, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        special_mode_cb = new JCheckBox("Spezial Modus");
        start_screen.add(special_mode_cb, gbc);

        setLayout(null);
        this.add(start_screen);

        gbc.gridx = 2;
        JButton btn = new JButton("Spiel starten");
        start_screen.add(btn, gbc);
        btn.addActionListener(e -> start_game());

        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        JLabel lbl = new JLabel("Steuerung:");
        lbl.setFont(font2);
        start_screen.add(lbl, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        lbl = new JLabel("A/S/D Bewegen");
        lbl.setFont(font1);
        start_screen.add(lbl, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        lbl = new JLabel("Q/E Drehen");
        lbl.setFont(font1);
        start_screen.add(lbl, gbc);

        score_lbl = new JLabel("Score: ");
        score_lbl.setFont(font1);
        score_lbl.setForeground(Color.white);
        score_lbl.setBounds(10, 10, 100, 100);
        this.add(score_lbl);

        window.pack();
    }

    public void open_game_over_screen(){

    }

    public void start_game(){
        if (special_mode_cb.isSelected()){      // Wenn spezialmodus
            special_mode = true;
            wide_mode.setSelected(true);        // im spezialmodus wird der breite modus aktiviert
        }

        if (wide_mode.isSelected()){        // Wenn breiter Modus an ist -> größeres Array
            array = new TileArray(this,31, 20);    // im breiten Modus ist das TileArray größer als der standard
            left_offset = 75;       // Abstand des Spielfelds von links verkleinern
        }
        else{
            array = new TileArray(this);
        }
        started = true;

        activetile = new Tile(3, -1);   // Erstes falledes Tile erstellen
        this.remove(start_screen);          // Startbildschirm beenden
    }

    public void display_tile(Tile tile){
        // Aktuell fallendes Tile anzeigen
        for (int y_index = 0; y_index < tile.arraysize; y_index++){
            for (int x_index = 0; x_index < tile.arraysize; x_index++){
                int block = tile.array[x_index][y_index];
                if (block != 0){        // wenn hier im array eine farbe ist
                    g.setColor(Tilecolors.get(block, tick));      // Farbe durch Zahl im Array mit Klasse Tilecolors holen
                    g.fillRect((tile.x+x_index)*tilesize + left_offset, (tile.y+y_index)*tilesize, tilesize, tilesize); // Block zeichnen
                    g.setColor(Color.black);
                    g.drawRect((tile.x+x_index)*tilesize + left_offset, (tile.y+y_index)*tilesize, tilesize, tilesize); // Schwarze Ränder zeichnen
                }
            }
        }
    }

    public void display_array(){
        // Array aus gelandeten Tiles anzeigen
        for (int y_index = 0; y_index < array.height; y_index++){
            for (int x_index = 0; x_index < array.width; x_index++){
                int block = array.get_tile(x_index, y_index);
                if (block != 0){        // wenn hier im array eine farbe ist
                    g.setColor(Tilecolors.get(block, tick));      // Farbe durch Zahl im Array mit Klasse Tilecolors holen
                    g.fillRect((x_index)*tilesize + left_offset, (y_index)*tilesize, tilesize, tilesize);       // Block zeichnen
                    g.setColor(Color.black);
                    g.drawRect((x_index)*tilesize + left_offset, (y_index)*tilesize, tilesize, tilesize);       // Schwarze Ränder zeichnen
                }
            }
        }
    }

    @Override
    public void update_loop() {
        if (started) {
            score_lbl.setText("Score: " + score);
            g.setColor(Color.darkGray);
            g.fillRect(left_offset, 0, array.width * tilesize, array.height * tilesize);        // Spielfeld grau färben
            if (activetile != null) display_tile(activetile);  // Aktuell fallendes Tile anzeigen falls vorhanden
            display_array();            // Array aus gelandeten Tiles anzeigen

            if (activetile != null) {               // Wenn momentan ein Tetris block aktiv ist
                // spezialmodus aktionen
                if (special_mode) {
                    if (tick % (random.nextInt(100) + 4) == 0) {
                        activetile.rotateCCW();     // Block zufllig drehen
                        if (array.check_collision(activetile, 0, 0))
                            activetile.rotateCW();        // Bei Kollision zurück drehen
                    }
                }

                // automatische Bewegung
                int tick_delay = 20;    // standardmäßige Zeit für Bewegung nach unten
                if (array.check_collision(activetile, 0, 1)) {   // wenn der Block unten liegt dem
                    tick_delay = 50;                                         // Spieler etwas zeit zum links/rechts bewegen geben
                }
                if (Keyboard.isKeyPressed(KeyEvent.VK_S)) {      // Verkürzung der zeit für die Bewegung nach unten (S Taste)
                    tick_delay = 3;
                }

                if (tick % tick_delay == 0) {       // wenn momentan ein tick ist, der durch tick_delay teilbar ist
                    if (array.check_collision(activetile, 0, 1)) {      // Überprüfen ob unter Tetris block schon ein anderer ist
                        if (array.place_matrix(activetile) == 0) {     // Block in array setzen; wenn nicht gameover ->  neuen Tetris block erstellen
                            activetile = new Tile(3, -1);

                        } else {                                    // wenn gameover
                            System.out.println("GAMEOVER");
                            game_over = true;
                            activetile = null;              // keinen aktiven tetris block setzen
                        }
                    }
                    if (activetile != null) activetile.move(0, 1);          // Block nach unten bewegen falls aktuell eines im spiel ist

                }

                // Key input Bewegung, sheeesh
                if (a_key_bind.update() && !array.check_collision(activetile, -1, 0)) {     // A Taste gedrückt und links kein Tetris block
                    activetile.move(-1, 0);
                }
                if (d_key_bind.update() && !array.check_collision(activetile, 1, 0)) {      // D Taste gedrückt und rechts kein Tetris block
                    activetile.move(1, 0);
                }
                if (e_key_bind.update()) {          // Q Taste gedrückt
                    activetile.rotateCW();          // Im Uhrzeigersinn drehen
                    if (array.check_collision(activetile, 0, 0))
                        activetile.rotateCCW();        // Bei Kollision zurückdrehen
                }
                if (q_key_bind.update()) {          // E Taste gedrückt
                    activetile.rotateCCW();         // Gegen den Uhrzeigersinn drehen
                    if (array.check_collision(activetile, 0, 0))
                        activetile.rotateCW();         // Bei Kollision zurückdrehen
                }

            }
        }
    }
}
