package Games.Data.Snake;

public class SnakeSpriteList {
    public SnakeGameSprite[] list;
    public int filled_until;
    public SnakeSpriteList(){
        list = new SnakeGameSprite[1000]; //Sprite[layer][place]
    }

    public void add_sprite(SnakeGameSprite sprite){
        filled_until++;
        list[filled_until-1] = sprite;
    }

    public void remove_sprite(SnakeGameSprite apple){
        boolean removed = false;
            for (int index = 0; index < filled_until; index++) {
                SnakeGameSprite element = list[index];
                if (element == apple) {
                    for (int index2 = index; index2 < filled_until; index2++) {
                        SnakeGameSprite next_element = list[index2 + 1]; // sprite list elemente alle 1 in richtung anfang bewegen
                        list[index2] = next_element;
                    }
                    removed = true;
                }
            }
            if (removed){
                list[filled_until+1] = null;
            }

    }

    public void print(){
            for (SnakeGameSprite element : list) {
                if (element != null){
                    element.print();
                }
            }
        }
}
