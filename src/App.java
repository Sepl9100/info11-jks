import Games.Engine.Window;
import Games.MainMenu;
import Games.Snake;

public class App {
    Window window;

    public App(){
        window = new Window();
        new MainMenu(window);
    }
}
