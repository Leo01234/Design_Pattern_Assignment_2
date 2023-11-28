package PoolGame.Command;

import PoolGame.App;
import PoolGame.ConfigReader;
import javafx.animation.Timeline;
import javafx.stage.Stage;

/**
 * @author Leo01234
 * @version 1.0
 */
public class DefaultLevelCommand extends LevelCommand{
    private String displayName = "Default level";

    @Override
    public String getDisplayName() {
        return displayName;
    }

    public DefaultLevelCommand(App app, ConfigReader configReader) {
        super(app, configReader);
    }
}
