package PoolGame.ControlPane;

import PoolGame.Command.ChangeLevelCommand;
import PoolGame.Command.Command;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leo01234
 * @version 1.0
 */
public class LevelButtons implements OnPaneDrawable {
    private List<LevelButton> levelButtons;
    private List<Command> commands;

    /**
     * Build the level buttons with the provided values
     */
    public LevelButtons() {
        this.levelButtons = new ArrayList<>();

        ToggleGroup group = new ToggleGroup();

        this.levelButtons.add(new LevelButton("0: Default level"));
        this.levelButtons.add(new LevelButton("1: Easy level"));
        this.levelButtons.add(new LevelButton("2: Normal level"));
        this.levelButtons.add(new LevelButton("3: Hard level"));

        for (LevelButton levelButton : this.levelButtons) {
            levelButton.setToggleGroup(group);
        }

        this.levelButtons.get(0).setSelected();
    }

    public void setAndRegisterCommands(List<Command> commands) {
        this.commands = commands;
        this.registerCommands(commands);
    }

    private void registerCommands(List<Command> commands) {
        for (int i = 0; i < this.levelButtons.size(); i++) {
            this.levelButtons.get(i).setAndRegisterCommand(commands.get(i));
        }

    }

    public void registerKeyEvent(Scene scene) {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case DIGIT0:
                        LevelButtons.this.levelButtons.get(0).setSelected();
                        break;
                    case DIGIT1:
                        LevelButtons.this.levelButtons.get(1).setSelected();
                        break;
                    case DIGIT2:
                        LevelButtons.this.levelButtons.get(2).setSelected();
                        break;
                    case DIGIT3:
                        LevelButtons.this.levelButtons.get(3).setSelected();
                        break;
                }
            }
        });
    }

    /**
     * Add the object to the JavaFX pane so they can be drawn.
     * @param paneChildren The list of `Node` obtained from the JavaFX pane.
     */
    @Override
    public void addToPane(ObservableList<Node> paneChildren) {
        for (LevelButton levelButton : this.levelButtons) {
            levelButton.addToPane(paneChildren);
        }
    }

}
