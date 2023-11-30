package PoolGame;

import java.util.Stack;

/**
 * @author Leo01234
 * @version 1.0
 */
public class HistoryCaretaker {
    private Game game;
    private Stack<Game.Memento> history = new Stack<>();
    public HistoryCaretaker(Game game) {
        this.game = game;
    }
    public void saveHistory() {
        this.history.push(this.game.save());
    }
    public void undo() {
        if (this.history.size() > 1) {
            this.game.restore(this.history.pop());
        } else if (this.history.size() == 1) {
            this.game.restore(this.history.peek());
        }
    }
}
