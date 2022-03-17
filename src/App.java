import Games.Engine.Window;

public class App {
    Window window;

    public App(){
        window = new Window();

        MainMenu menu = new MainMenu(window);
    }
}
