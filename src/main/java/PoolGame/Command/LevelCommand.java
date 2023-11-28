package PoolGame.Command;

import PoolGame.App;
import PoolGame.ConfigReader;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

/**
 * @author Leo01234
 * @version 1.0
 */
public abstract class LevelCommand implements Command{

    private App app;
    private ConfigReader configReader;

    public LevelCommand(App app, ConfigReader configReader) {
        this.app = app;
        this.configReader = configReader;
    }

    @Override
    public void execute() {
        this.app.alterGame(this.configReader);
    }

    abstract public String getDisplayName();
}
