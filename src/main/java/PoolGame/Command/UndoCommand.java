package PoolGame.Command;

import PoolGame.App;
import PoolGame.ConfigReader;
import PoolGame.HistoryCaretaker;

/**
 * @author Leo01234
 * @version 1.0
 */
public class UndoCommand implements Command{

    private HistoryCaretaker historyCaretaker;

    public UndoCommand(HistoryCaretaker historyCaretaker) {
        this.historyCaretaker = historyCaretaker;
    }

    @Override
    public void execute() {
        this.historyCaretaker.undo();
    }
}
