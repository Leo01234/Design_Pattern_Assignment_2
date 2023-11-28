package PoolGame.Items.ControlPanel;

import PoolGame.Drawable;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * @author Leo01234
 * @version 1.0
 */
public class ControlPanel implements Drawable {
    private double[] dim;
    private static final Color COLOR = Color.WHITE;
    private Rectangle shape;

    /**
     * Build the control panel with the provided values
     * @param dimX The dimension of the panel in the x-axis
     * @param dimY The dimension of the panel in the y-axis
     */
    public ControlPanel(double dimX, double dimY) {
        this.init(dimX, dimY);
    }

    private void init(double dimX, double dimY) {
        this.dim = new double[2];
        this.dim[0] = dimX;
        this.dim[1] = dimY;
        this.shape = new Rectangle(this.dim[0], this.dim[1], COLOR);
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

    public Node getNode() {
        return this.shape;
    }

    /**
     * Add the panel and its components to the JavaFX group so they can be drawn.
     * @param groupChildren The list of `Node` obtained from the JavaFX Group.
     */
    @Override
    public void addToGroup(ObservableList<Node> groupChildren) {
        groupChildren.add(this.shape);
    }
}
