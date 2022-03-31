package Games.Engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QueueTask {

    private final int created_on_tick;
    private final int run_at_tick;
    private Game game;
    private ActionListener listener;

    public QueueTask(Game game, int in_ticks, ActionListener listener){
        created_on_tick = game.tick;
        run_at_tick = created_on_tick + in_ticks;
        this.listener = listener;
        this.game = game;
        game.task_queue.add_task(this);

    }

    public void update(){
        if (run_at_tick == game.tick){
            run();
        }
    }

    public void kill(){
        game.task_queue.remove_task(this);
    }

    public void run(){
        listener.actionPerformed(new ActionEvent(this, 0, "RUN"));
        this.kill();
    }
}
