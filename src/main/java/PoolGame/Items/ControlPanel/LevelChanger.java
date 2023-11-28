package PoolGame.Items.ControlPanel;

import PoolGame.Drawable;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Text;

/**
 * @author Leo01234
 * @version 1.0
 */
public class LevelChanger implements Drawable {
    private double[] dim;
    private RadioButton[] radioButtons;

    /**
     * Build the timer with the provided values
     * @param dimX The dimension of the timer in the x-axis
     * @param dimY The dimension of the timer in the y-axis
     * @param content The content of the timer
     */
    public Timer(double dimX, double dimY, String content) {
        this.init(dimX, dimY, content);
    }

    private void init(double dimX, double dimY, String content) {
        this.dim = new double[2];
        this.dim[0] = dimX;
        this.dim[1] = dimY;
        this.text = new Text(dimX, dimY, content);
    }

    /**
     * Get the x dimension of the timer.
     * @return The dimension of the timer for the x axis.
     */
    public double getDimX() {
        return this.dim[0];
    }

    /**
     * Get the y dimension of the timer.
     * @return The dimension of the timer for the y axis.
     */
    public double getDimY() {
        return this.dim[1];
    }

    public Node getNode() {
        return this.text;
    }

    /**
     * Add the timer and its components to the JavaFX group so they can be drawn.
     * @param groupChildren The list of `Node` obtained from the JavaFX Group.
     */
    @Override
    public void addToGroup(ObservableList<Node> groupChildren) {
        groupChildren.add(this.text);
    }

    public void setContent(String content) {
        this.text.setText(content);
    }
}
