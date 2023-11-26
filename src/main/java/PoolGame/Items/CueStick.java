package PoolGame.Items;

import PoolGame.Drawable;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

/**
 * The cue stick image
 * @author Leo01234
 * @version 1.0
 */
public class CueStick implements Drawable {
    /** Location of image */
    private static final String LOCATION = "/cue_stick.png";
    /** FitWidth of imageView */
    private static final double WIDTH = 200.0;
    /** JavaFx ImageView */
    private ImageView iv;
    /** Control the rotation */
    private Rotate rotate;
    public CueStick() {
        Image image = new Image(LOCATION);
        this.iv = new ImageView();
        this.iv.setImage(image);
        this.iv.setFitWidth(WIDTH);
        this.iv.setPreserveRatio(true);
        this.iv.setSmooth(true);
        this.iv.setCache(true);
//        this.iv.setVisible(false);

        this.rotate = new Rotate();
        this.iv.getTransforms().add(this.rotate);
    }

    /** X position of upper-left corner */
    public void setXPos(double xPos) {
        this.iv.setX(xPos);
    }

    /** Y position of upper-left corner */
    public void setYPos(double yPos) {
        this.iv.setY(yPos);
    }

    /** do not show the stick */
    public void disable() {
        this.iv.setVisible(false);
    }
    /** show the stick */
    public void enable() {
        this.iv.setVisible(true);
    }
    @Override
    public Node getNode() {
        return this.iv;
    }

    @Override
    public void addToGroup(ObservableList<Node> groupChildren) {
        groupChildren.add(this.iv);
    }
}
