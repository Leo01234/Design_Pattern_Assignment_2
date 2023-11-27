package PoolGame.Command;

import PoolGame.ConfigReader;
import javafx.animation.Timeline;
import javafx.stage.Stage;

/**
 * @author Leo01234
 * @version 1.0
 */
public class DefaultLevelCommand extends LevelCommand{

    public DefaultLevelCommand(LevelCommand[] levelCommands, Stage stage, Timeline timeline, ConfigReader configReader) {
        super(levelCommands, stage, timeline);
        this.setConfigReader(configReader);
    }
}
