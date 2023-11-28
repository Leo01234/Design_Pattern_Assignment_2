package PoolGame.Command;

import PoolGame.App;
import PoolGame.ConfigReader;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

import java.io.IOException;

/**
 * @author Leo01234
 * @version 1.0
 */
public class NormalLevelCommand extends LevelCommand{
    private String displayName = "Normal level";

    @Override
    public String getDisplayName() {
        return displayName;
    }

    public NormalLevelCommand(App app, ConfigReader configReader) {
        super(app, configReader);
    }
}
