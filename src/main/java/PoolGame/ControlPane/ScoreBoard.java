package PoolGame.ControlPane;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;

/**
 * @author Leo01234
 * @version 1.0
 */
public class ScoreBoard implements OnPaneDrawable {
    private Label label;
    private int score = 0;

    /**
     * Build the score board with the provided values
     */
    public ScoreBoard() {
        this.init();
    }

    private void init() {
        this.label = new Label(score + "");
    }

    /**
     * Add the object to the JavaFX pane so they can be drawn.
     *
     * @param paneChildren The list of `Node` obtained from the JavaFX pane.
     */
    @Override
    public void addToPane(ObservableList<Node> paneChildren) {
        paneChildren.add(this.label);
    }
}
