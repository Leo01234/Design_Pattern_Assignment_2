package PoolGame.Items;

import PoolGame.Drawable;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

import java.awt.*;

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
        this.iv.setVisible(false);

        this.rotate = new Rotate();
        this.iv.getTransforms().add(this.rotate);
    }

    /**
     * X position of upper-left corner
     * set Rotate pivot too
     */
    public void setXPos(double xPos) {
        this.iv.setX(xPos);
        this.rotate.setPivotX(xPos);
    }

    /**
     * Y position of upper-left corner
     * set Rotate pivot too
     */
    public void setYPos(double yPos) {
        this.iv.setY(yPos);
        this.rotate.setPivotY(yPos);
    }

    /**
     * Computes the angle (in degrees) of the vector . The angle
     * will be in the range 0 (inclusive) to 360 (exclusive) as measured
     * counterclockwise from the positive x-axis.
     */
    private double computeAngleOfVector(Point2D vector) {
        double angle = vector.angle(1.0, 0.0);
        if (vector.getY() > 0) {
            // vector pointing downwards and thus is in the 3rd or 4th quadrant
            return 360.0 - angle;
        }
        // vector pointing upwards and thus is in the 1st or 2nd quadrant
        return angle;
    }

    /** Rotate to align with mouse */
    public void setRotate(double ballXPos, double ballYPos) {
        double imageWidth = this.iv.getBoundsInLocal().getWidth();
        double imageHeight = this.iv.getBoundsInLocal().getHeight();
        Point2D imageVector = new Point2D(imageWidth,imageHeight);

        Point2D mouseBallVector = new Point2D(this.iv.getX()-ballXPos,this.iv.getY()-ballYPos);

        this.rotate.setAngle(computeAngleOfVector(imageVector)-computeAngleOfVector(mouseBallVector));
    }

    /** hide the stick */
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
