package PoolGame.Command;

import PoolGame.App;
import PoolGame.ConfigReader;
import PoolGame.Game;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author Leo01234
 * @version 1.0
 */
public abstract class LevelCommand implements Command{

    /** Reference to all the levelCommands */
    private LevelCommand[] levelCommands;

    /** Need the primary stage to change scene */
    private Stage stage;

    private ConfigReader configReader;
    private Timeline timeline;

    public LevelCommand(LevelCommand[] levelCommands, Stage stage, Timeline timeline) {
        this.levelCommands = levelCommands;
        this.stage = stage;
        this.timeline = timeline;
    }

    @Override
    public void execute() {

        // Initialize game first, to get table size
        Game game = new Game(this.configReader);

        Group root = new Group();
        // Set size of the scene
        Scene scene = new Scene(root, game.getWindowDimX(), game.getWindowDimY());

        this.stage.setScene(scene);

        Canvas canvas = new Canvas(game.getWindowDimX(), game.getWindowDimY());

        /*
         * I can't understand the usage of magic number 4 here.
         * Maybe we should use the size of scene instead of stage.
         * It seems that the size of stage includes decorations.
         * https://stackoverflow.com/questions/40095830/why-is-my-javafx-window-not-the-right-width
         */
        // stage.setWidth(game.getWindowDimX());
        // stage.setHeight(game.getWindowDimY() +
        //                 Pocket.RADIUS +
        //                 PoolTable.POCKET_OFFSET +
        //                 4); // Magic number to get bottom to align

        // Set the size of stage using the size of scene
        this.stage.sizeToScene();

        root.getChildren().add(canvas);
        // GraphicsContext gc = canvas.getGraphicsContext2D();
        game.addDrawables(root);

        registerKeyEvent(scene);

        KeyFrame frame = new KeyFrame(Duration.seconds(App.FRAMETIME), (actionEvent) -> game.tick());

        this.timeline.getKeyFrames().clear();
        this.timeline.getKeyFrames().add(frame);
        this.timeline.playFromStart();

    }

    private void registerKeyEvent(Scene scene) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case DIGIT0:
                        LevelCommand.this.levelCommands[0].execute();
                        break;
                    case DIGIT1:
                        LevelCommand.this.levelCommands[1].execute();
                        break;
                    case DIGIT2:
                        LevelCommand.this.levelCommands[2].execute();
                        break;
                    case DIGIT3:
                        LevelCommand.this.levelCommands[3].execute();
                        break;
                }
            }
        });
    }

    public void setConfigReader(ConfigReader configReader) {
        this.configReader = configReader;
    }
}
