package PoolGame.ControlPane;

import PoolGame.App;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
    private Instant startInstant;
    private Timeline timeline;

    /**
     * Build the timer with the provided values
     */
    public Timer() {
        this.label = new Label();

        this.timeline = new Timeline();
        this.timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame frame = new KeyFrame(javafx.util.Duration.seconds(1.0), (actionEvent) -> this.tick());
        this.timeline.getKeyFrames().add(frame);

    }

    public void playFromStart() {
        this.label.setText("00:00:00");
        this.startInstant = Instant.now();

        this.timeline.playFromStart();
    }
    public void pause() {
        this.timeline.pause();
    }
    public void tick() {
        Instant now = Instant.now();
        Duration timeElapsed = Duration.between(this.startInstant, now);
        this.label.setText(String.format("%02d:%02d:%02d",
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