package PoolGame.ControlPane;

import PoolGame.Drawable;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 * @author Leo01234
 * @version 1.0
 */
public class ControlPane implements Drawable {
    private double[] dim;
    private static final Color COLOR = Color.WHITE;
    private GridPane gridPane;

    private LevelChangePane levelChangePane;
    private Timer timer;
    private ScoreBoard scoreBoard;
    private UndoControl undoControl;

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
        this.gridPane = new GridPane();
        this.gridPane.setLayoutX(dimX);
        this.gridPane.setLayoutY(dimY);
        this.gridPane.setBackground(new Background(new BackgroundFill(
                COLOR,
                new CornerRadii(10),
                new Insets(0)
        )));
        this.levelChangePane = new LevelChangePane(0, 0);
        this.timer = new Timer(0, 1);
        this.scoreBoard = new ScoreBoard(0, 2);
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

    public LevelChangePane getLevelChangePane() {
        return levelChangePane;
    }

    public Node getNode() {
        return this.gridPane;
    }

    /**
     * Add the panel and its components to the JavaFX group so they can be drawn.
     * @param groupChildren The list of `Node` obtained from the JavaFX Group.
     */
    @Override
    public void addToGroup(ObservableList<Node> groupChildren) {
        ObservableList<Node> thisPaneChildren = this.gridPane.getChildren();
        this.levelChangePane.addToPane(thisPaneChildren);
        this.timer.addToPane(thisPaneChildren);
        this.scoreBoard.addToPane(thisPaneChildren);

        groupChildren.add(this.gridPane);
    }
}
