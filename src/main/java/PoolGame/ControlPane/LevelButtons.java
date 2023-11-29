package PoolGame.ControlPane;

import PoolGame.Command.LevelCommand;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leo01234
 * @version 1.0
 */
public class LevelButtons implements OnPaneDrawable {
    private List<LevelButton> levelButtons;
    private LevelCommand[] levelCommands;

    /**
     * Build the level buttons with the provided values
     */
    public LevelButtons() {
        this.init();
    }

    private void init() {
        this.levelButtons = new ArrayList<>();
    }

    public void setAndRegisterLevelCommands(LevelCommand[] levelCommands) {
        this.levelCommands = levelCommands;
        this.initLevelButtons(levelCommands);
    }

    private void initLevelButtons(LevelCommand[] levelCommands) {
        ToggleGroup group = new ToggleGroup();
        for (int i = 0; i < levelCommands.length; i++) {
            LevelCommand levelCommand = levelCommands[i];
            LevelButton levelButton = new LevelButton(levelCommand.getDisplayName());
            levelButton.setToggleGroup(group);
            levelButton.setLevelCommand(levelCommand);
            this.levelButtons.add(levelButton);
        }

    }

    public void registerKeyEvent(Scene scene) {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case DIGIT0:
                        LevelButtons.this.levelCommands[0].execute();
                        break;
                    case DIGIT1:
                        LevelButtons.this.levelCommands[1].execute();
                        break;
                    case DIGIT2:
                        LevelButtons.this.levelCommands[2].execute();
                        break;
                    case DIGIT3:
                        LevelButtons.this.levelCommands[3].execute();
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
