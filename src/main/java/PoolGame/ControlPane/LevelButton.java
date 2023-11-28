package PoolGame.ControlPane;

import PoolGame.Command.LevelCommand;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;

/**
 * @author Leo01234
 * @version 1.0
 */
public class LevelButton implements OnPaneDrawable  {
    private int[] constraints;
    private RadioButton radioButton;
    private LevelCommand levelCommand;

    /**
     * Build the timer with the provided values
     * @param column The column of the timer in the gridPane
     * @param row The row of the timer in the gridPane
     */
    public LevelButton(int column, int row, String text) {
        this.init(column, row, text);
    }

    private void init(int column, int row, String text) {
        this.constraints = new int[2];
        this.constraints[0] = column;
        this.constraints[1] = row;
        this.radioButton = new RadioButton(text);
        GridPane.setConstraints(this.radioButton, this.constraints[0], this.constraints[1]);
        this.registerButtonEvent();
    }

    public void setLevelCommand(LevelCommand levelCommand) {
        this.levelCommand = levelCommand;
    }
    private void registerButtonEvent() {
        this.radioButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    LevelButton.this.levelCommand.execute();
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
        paneChildren.add(this.radioButton);
    }
}
