package PoolGame.ControlPane;

import PoolGame.App;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.time.Duration;

/**
 * @author Leo01234
 * @version 1.0
 */
public class Timer implements OnPaneDrawable {
    private static final Duration ONESECOND = Duration.ofSeconds(1);
    private Label label;
    private Duration duration;
    private Timeline timeline;

    /**
     * Build the timer with the provided values
     */
    public Timer() {
        this.label = new Label();
        this.label.setFont(Font.font(20.0));

        this.duration = Duration.ZERO;

        this.timeline = new Timeline();
        this.timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame frame = new KeyFrame(javafx.util.Duration.seconds(1.0), (actionEvent) -> this.tick());
        this.timeline.getKeyFrames().add(frame);

    }

    public void playFromStart() {
        this.label.setText("00:00:00");
        this.duration = Duration.ZERO;

        this.timeline.playFromStart();
    }
    public void pause() {
        this.timeline.pause();
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
        this.display();
    }

    public void tick() {
        this.duration = this.duration.plus(ONESECOND);
        this.display();
    }
    private void display() {
        this.label.setText(String.format("%02d:%02d:%02d",
                this.duration.toHours(),
                this.duration.toMinutesPart(),
                this.duration.toSecondsPart()));
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