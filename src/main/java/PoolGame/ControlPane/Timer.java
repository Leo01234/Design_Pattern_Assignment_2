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
    private Label label;
    private Instant start;

    /**
     * Build the timer with the provided values
     */
    public Timer() {
        this.init();
    }

    private void init() {
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