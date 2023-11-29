package PoolGame.ControlPane;

import PoolGame.Drawable;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * @author Leo01234
 * @version 1.0
 */
public class ControlPane implements Drawable {
    private double[] dim;
    private static final Color COLOR = Color.RED;
    private VBox vBox;

    private LevelButtons levelButtons;
    private Timer timer;
    private ScoreBoard scoreBoard;
    private UndoControl undoControl;

    /** Set dims later */
    public ControlPane() {
        this.init();
    }
    private void init() {
        this.dim = new double[2];
        this.vBox = new VBox();
        this.vBox.setBackground(new Background(new BackgroundFill(
                COLOR,
                new CornerRadii(0),
                new Insets(0)
        )));

        this.levelButtons = new LevelButtons();
        this.timer = new Timer();
        this.scoreBoard = new ScoreBoard();
//        this.undoControl = new UndoControl();
    }

    /**
     * Build the control panel with the provided values
     * @param dimX The dimension of the panel in the x-axis
     * @param dimY The dimension of the panel in the y-axis
     */
    public ControlPane(double dimX, double dimY) {
        this.init(dimX, dimY);
    }

    private void init(double dimX, double dimY) {
        this.dim = new double[2];
        this.dim[0] = dimX;
        this.dim[1] = dimY;
        this.vBox = new VBox();
        this.vBox.setLayoutX(dimX);
        this.vBox.setLayoutY(dimY);
        this.vBox.setBackground(new Background(new BackgroundFill(
                COLOR,
                new CornerRadii(0),
                new Insets(0)
        )));

        this.levelButtons = new LevelButtons();
        this.timer = new Timer();
        this.scoreBoard = new ScoreBoard();
//        this.undoControl = new UndoControl();
    }

    /**
     * Get the x dimension of the panel.
     * @return The dimension of the panel for the x axis.
     */
    public double getDimX() {
        return this.dim[0];
    }

    /**
     * Get the y dimension of the panel.
     * @return The dimension of the panel for the y axis.
     */
    public double getDimY() {
        return this.dim[1];
    }

    public LevelButtons getLevelButtons() {
        return levelButtons;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setDims(double dimX, double dimY) {
        this.dim[0] = dimX;
        this.dim[1] = dimY;
        this.vBox.setLayoutX(dimX);
        this.vBox.setLayoutY(dimY);
    }

    public Node getNode() {
        return this.vBox;
    }

    /**
     * Add the panel and its components to the JavaFX group so they can be drawn.
     * @param groupChildren The list of `Node` obtained from the JavaFX Group.
     */
    @Override
    public void addToGroup(ObservableList<Node> groupChildren) {

        groupChildren.add(this.vBox);
    }

    public void addComponents() {

        ObservableList<Node> thisPaneChildren = this.vBox.getChildren();
        this.levelButtons.addToPane(thisPaneChildren);
        this.timer.addToPane(thisPaneChildren);
        this.scoreBoard.addToPane(thisPaneChildren);

    }
}
