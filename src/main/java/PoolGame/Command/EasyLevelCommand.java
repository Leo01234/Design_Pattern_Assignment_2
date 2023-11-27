package PoolGame.Command;

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

    public EasyLevelCommand(LevelCommand[] levelCommands, Stage stage, Timeline timeline) {
        super(levelCommands,stage, timeline);

        // parse the file:
        try {
            this.setConfigReader(new ConfigReader("/config_easy.json", true));
        } catch (IOException | ParseException | ConfigReader.ConfigKeyMissingException | IllegalArgumentException e) {
            e.printStackTrace();
            System.err.printf("ERROR: %s\n", e.getMessage());
            System.exit(1);
        }
    }
}
