package PoolGame.ControlPane;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;

import java.time.Duration;
import java.time.Instant;

/**
 * @author Leo01234
 * @version 1.0
 */
public class Timer implements OnPaneDrawable {
    private int[] constraints;
    private Label label;
    private Instant start;

    /**
     * Build the timer with the provided values
     * @param column The column of the timer in the gridPane
     * @param row The row of the timer in the gridPane
     */
    public Timer(int column, int row) {
        this.init(column, row);
    }

    private void init(int column, int row) {
        this.constraints = new int[2];
        this.constraints[0] = column;
        this.constraints[1] = row;
        this.label = new Label();
    }

    public void tick() {
        if (this.start == null) {
            this.start = Instant.now();
        }
        Instant now = Instant.now();
        Duration timeElapsed = Duration.between(this.start, now);
        this.label.setText(String.format("%d:%02d:%02d",
                timeElapsed.toHours(),
                timeElapsed.toMinutesPart(),
                timeElapsed.toSecondsPart()));
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