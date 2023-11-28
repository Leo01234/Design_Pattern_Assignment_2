package PoolGame.ControlPane;

import javafx.collections.ObservableList;
import javafx.scene.Node;

/**
 * @author Leo01234
 * @version 1.0
 */
public interface OnPaneDrawable {
    /**
     * Add the object to the JavaFX pane so they can be drawn.
     * @param paneChildren The list of `Node` obtained from the JavaFX pane.
     */
    public void addToPane(ObservableList<Node> paneChildren);
}
