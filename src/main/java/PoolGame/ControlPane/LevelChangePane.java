package PoolGame.ControlPane;

import PoolGame.Command.LevelCommand;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leo01234
 * @version 1.0
 */
public class LevelChangePane implements OnPaneDrawable {
    private int[] constraints;
    private static final Color COLOR = Color.WHITE;
    private GridPane gridPane;
    private List<LevelButton> levelButtons;

    /**
     * Build the timer with the provided values
     * @param column The column of the timer in the gridPane
     * @param row The row of the timer in the gridPane
     */
    public LevelChangePane(int column, int row) {
        this.init(column, row);
    }

    private void init(int column, int row) {
        this.constraints = new int[2];
        this.constraints[0] = column;
        this.constraints[1] = row;
        this.gridPane = new GridPane();
        GridPane.setConstraints(this.gridPane, this.constraints[0], this.constraints[1]);
        this.gridPane.setBackground(new Background(new BackgroundFill(
                COLOR,
                new CornerRadii(10),
                new Insets(0)
        )));
        this.levelButtons = new ArrayList<>();
    }

    public void setAndRegisterLevelCommands(LevelCommand[] levelCommands, Scene scene) {
        this.initLevelButtons(levelCommands);
        this.registerKeyEvent(levelCommands, scene);
    }

    private void initLevelButtons(LevelCommand[] levelCommands) {
        for (int i = 0; i < levelCommands.length; i++) {
            LevelCommand levelCommand = levelCommands[i];
            LevelButton levelButton = new LevelButton(0, i, levelCommand.getDisplayName());
            levelButton.setLevelCommand(levelCommand);
            this.levelButtons.add(levelButton);
        }
    }

    private void registerKeyEvent(LevelCommand[] levelCommands, Scene scene) {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case DIGIT0:
                        levelCommands[0].execute();
                        break;
                    case DIGIT1:
                        levelCommands[1].execute();
                        break;
                    case DIGIT2:
                        levelCommands[2].execute();
                        break;
                    case DIGIT3:
                        levelCommands[3].execute();
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
        ObservableList<Node> thisPaneChildren = this.gridPane.getChildren();
        for (LevelButton levelButton : this.levelButtons) {
            levelButton.addToPane(thisPaneChildren);
        }
        paneChildren.add(this.gridPane);

    }


}
