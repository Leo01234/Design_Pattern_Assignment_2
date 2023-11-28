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
}
