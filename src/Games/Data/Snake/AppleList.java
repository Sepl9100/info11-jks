package Games.Data.Snake;

import Games.Engine.Sprite;

public class AppleList {
    public Apple[] list;
    public int filled_until;
    public AppleList(){
        list = new Apple[100]; //Sprite[layer][place]
    }

    public void add_sprite(Apple sprite){
        filled_until++;
        list[filled_until-1] = sprite;
    }

    public void remove_sprite(Apple apple){
        boolean removed = false;
            for (int index = 0; index < filled_until; index++) {
                Apple element = list[index];
                if (element == apple) {
                    for (int index2 = index; index2 < filled_until; index2++) {
                        Apple next_element = list[index2 + 1]; // sprite list elemente alle 1 in richtung anfang bewegen
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
            for (Apple element : list) {
                if (element != null){
                    element.print();
                }
            }
        }
}
