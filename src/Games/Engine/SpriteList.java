package Games.Engine;

public class SpriteList {
    public Sprite[][] list;
    public SpriteList(){
        list = new Sprite[30][1000]; //Sprite[layer][place]
    }

    public void add_sprite(Sprite sprite, int layer){
        int layer_lenght = list[layer].length;
        list[layer][layer_lenght-1] = sprite;
    }

    public void print(){
        for (Sprite[] layer : list) {
            for (Sprite element : layer) {
                if (element != null){
                    element.print();
                }
            }
        }
    }

}
