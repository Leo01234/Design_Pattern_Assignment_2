package PoolGame.Command;

import PoolGame.App;
import PoolGame.ConfigReader;

/**
 * @author Leo01234
 * @version 1.0
 */
public class ChangeLevelCommand implements Command{

    private App app;
    private ConfigReader configReader;

    public ChangeLevelCommand(App app, ConfigReader configReader) {
        this.app = app;
        this.configReader = configReader;
    }

    @Override
    public void execute() {
        this.app.alterGame(this.configReader);
    }
}
