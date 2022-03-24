# info11-jks
Informatik Projekt 11/2 2022 GN - Jan, Konstantin, Sebastian 


### Ziele:
- Grundlegende Spiele Engine (nur so komplex wie nötig) 
- Spiele
  - Jan: Graph-Labyrinth, Tic-Tac-Toe
  - Konsti: Tetris, Snake
  - Sebi: Sudoku, Türme von Hanoi


##### Der Fokus liegt auf den fertigen Spielen.

---

### Software:
Programmierung:
- Java (IDE: IntelliJ, BlueJ)
- GitHub

Kommunikation:
- Discord
- Taiga.io (Aufgabenverteilung)
- Nextcloud (Nach Bedarf)


# Game Engine Dokumentation

# Spiel erstellen
```java
package Games;

import Games.Engine.Game;
import Games.Engine.Sprite;
import Games.Engine.Window;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DemoGame extends Game {

    private Sprite[] sprites;
    private Random random;

    public DemoGame(Window window){
        super(window, "DemoGame");
        this.setBackground(Color.orange);
        window.pack();

        BufferedImage test_texture = load_image("/img.png"); // Textur laden

        sprites = new Sprite[5000];
        for (int i = 0; i < 400; i++){
            sprites[i] = new Sprite(this, 0, test_texture);   // 400 Sprites mit der Textur test_textur erstellen
            sprites[i].x = 200;
            sprites[i].y = 200;
        }

    }

    @Override
    public void update_loop() {
        for (Sprite element : sprites){
            if (element != null) {
                element.x += 1        // in jedem tick alle Sprites ein Pixel nach rechts bewegen
            }
        }
    }
}

```
