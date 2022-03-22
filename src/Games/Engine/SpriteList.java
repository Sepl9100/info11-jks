package Games.Engine;

public class SpriteList {
    public Sprite[][] list;
    public SpriteList(){
        list = new Sprite[30][1000]; //Sprite[layer][place]
    }

    public void add_sprite(Sprite sprite, int layer){
        int layer_lenght = list[layer].length;
        list[layer][layer_lenght] = sprite;
    }

}
