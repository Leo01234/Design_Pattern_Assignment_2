package PoolGame.ControlPane;

import PoolGame.Command.LevelCommand;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

/**
 * @author Leo01234
 * @version 1.0
 */
public class LevelButton implements OnPaneDrawable  {
    private RadioButton radioButton;
    private LevelCommand levelCommand;

    /**
     * Build the timer with the provided values
     * @param text The text on the button
     */
    public LevelButton(String text) {
        this.init(text);
    }

    private void init(String text) {
        this.radioButton = new RadioButton(text);
        this.registerButtonEvent();
    }

    public void setToggleGroup(ToggleGroup toggleGroup) {
        this.radioButton.setToggleGroup(toggleGroup);
    }
    public void setSelected() {
        this.radioButton.setSelected(true);
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
