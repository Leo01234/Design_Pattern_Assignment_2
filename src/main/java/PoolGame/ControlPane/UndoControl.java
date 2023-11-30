package PoolGame.ControlPane;

import PoolGame.Command.Command;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

/**
 * @author Leo01234
 * @version 1.0
 */
public class UndoControl implements OnPaneDrawable{
    private Button button;
    private Command command;

    public UndoControl() {
        this.button = new Button("Undo");
    }

    public void setAndRegisterCommand(Command command) {
        this.command = command;
        this.registerButtonEvent();
    }

    private void registerButtonEvent() {
        this.button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                UndoControl.this.command.execute();
            }
        });
    }

    /**
     * Add the object to the JavaFX pane so they can be drawn.
     * @param paneChildren The list of `Node` obtained from the JavaFX pane.
     */
    @Override
    public void addToPane(ObservableList<Node> paneChildren) {
        paneChildren.add(this.button);
    }
}
