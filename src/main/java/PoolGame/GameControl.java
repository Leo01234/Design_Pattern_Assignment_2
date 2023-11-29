package PoolGame;

import PoolGame.Command.ChangeLevelCommand;
import PoolGame.Command.Command;
import PoolGame.ControlPane.ControlPane;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;

import java.util.List;

/**
 * Game control utils
 * @author Leo01234
 * @version 1.0
 */
public class GameControl {
    private ControlPane controlPane;

    public GameControl(ControlPane controlPane) {
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

    public void setCommands(List<Command> commands) {
        this.controlPane.getLevelButtons().setAndRegisterCommands(commands);
    }
    public void registerKeyEvent(Scene scene) {
        this.controlPane.getLevelButtons().registerKeyEvent(scene);
    }
    public void setControlPaneDims(double dimX, double dimY) {
        this.controlPane.setDims(dimX, dimY);
    }
}
