package PoolGame.ControlPane;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;

/**
 * @author Leo01234
 * @version 1.0
 */
public class ScoreBoard implements OnPaneDrawable {
    private int[] constraints;
    private Label label;
    private int score = 0;

    /**
     * Build the score board with the provided values
     * @param column The column of the score board in the gridPane
     * @param row The row of the score board in the gridPane
     */
    public ScoreBoard(int column, int row) {
        this.init(column, row);
    }

    private void init(int column, int row) {
        this.constraints = new int[2];
        this.constraints[0] = column;
        this.constraints[1] = row;
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
