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
public class EasyLevelCommand extends LevelCommand{
    private String displayName = "Easy level";

    @Override
    public String getDisplayName() {
        return displayName;
    }

    public EasyLevelCommand(App app, ConfigReader configReader) {
        super(app, configReader);
    }
}
