package PoolGame;

import PoolGame.Command.Command;
import PoolGame.ControlPane.ControlPane;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;

import java.time.Duration;
import java.util.List;

/**
 * Game control utils
 * @author Leo01234
 * @version 1.0
 */
public class GameControlFacade {
    private ControlPane controlPane;

    public GameControlFacade(ControlPane controlPane) {
        this.controlPane = controlPane;
    }

    /** Add all drawable object to the JavaFX group
     * @param root The JavaFX `Group` instance
     */
    public void addDrawables(Group root) {
        ObservableList<Node> groupChildren = root.getChildren();
        this.controlPane.addToGroup(groupChildren);
    }
    public void addComponents() {
        this.controlPane.addComponents();
    }
    public void setControlPaneDims(double dimX, double dimY) {
        this.controlPane.setDims(dimX, dimY);
    }

    // LevelButtons
    public void setCommands(List<Command> commands) {
        this.controlPane.getLevelButtons().setAndRegisterCommands(commands);
    }
    public void registerKeyEvent(Scene scene) {
        this.controlPane.getLevelButtons().registerKeyEvent(scene);
    }

    // Timer
    public void playFromStart() {
        this.controlPane.getTimer().playFromStart();
    }
    public void pause() {
        this.controlPane.getTimer().pause();
    }

    public Duration getDuration() {
        return this.controlPane.getTimer().getDuration();
    }

    public void setDuration(Duration duration) {
        this.controlPane.getTimer().setDuration(duration);
    }

    // ScoreBoard
    public void reset() {
        this.controlPane.getScoreBoard().reset();
    }
    public void addScore(int score) {
        this.controlPane.getScoreBoard().addScore(score);
    }
    public void setScore(int score) {
        this.controlPane.getScoreBoard().setScore(score);
    }
    public int getScore() {
        return this.controlPane.getScoreBoard().getScore();
    }

    // UndoControl
    public void setUndoCommand(Command command) {
        this.controlPane.getUndoControl().setAndRegisterCommand(command);
    }
}
