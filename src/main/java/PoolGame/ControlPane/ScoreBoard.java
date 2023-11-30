package PoolGame.ControlPane;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

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
        this.label = new Label();
        this.label.setFont(Font.font(30.0));
        this.display();
    }

    public void reset() {
        this.score = 0;
        this.display();
    }

    public void addScore(int score) {
        this.score += score;
        this.display();
    }

    public void setScore(int score) {
        this.score = score;
        this.display();
    }

    private void display() {
        this.label.setText("Score: " + this.score);
    }
    public int getScore() {
        return score;
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
