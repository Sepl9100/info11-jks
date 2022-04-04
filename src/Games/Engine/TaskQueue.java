package Games.Engine;

import Games.Data.Snake.Apple;
import Games.Engine.Kompositum.Queue;

public class TaskQueue {
    public QueueTask[] list;
    public int filled_until;
    public TaskQueue(){
        list = new QueueTask[10000];
    }

    public void add_task(QueueTask task){
        filled_until++;
        list[filled_until-1] = task;
    }

    public void remove_task(QueueTask task){
        boolean removed = false;
        for (int index = 0; index < filled_until; index++) {
            QueueTask element = list[index];
            if (element == task) {
                for (int index2 = index; index2 < filled_until; index2++) {
                    QueueTask next_element = list[index2 + 1]; // sprite list elemente alle 1 in richtung anfang bewegen
                    list[index2] = next_element;
                }
                removed = true;
            }
        }
        filled_until --;
        if (removed){
            list[filled_until+1] = null;
        }


    }
}
