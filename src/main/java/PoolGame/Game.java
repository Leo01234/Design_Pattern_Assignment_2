package PoolGame;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import PoolGame.Builder.BallBuilderDirector;
import PoolGame.Config.BallConfig;
import PoolGame.Config.PocketConfig;
import PoolGame.Config.PocketsConfig;
import PoolGame.Items.Ball;
import PoolGame.Items.Pocket;
import PoolGame.Items.PoolTable;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.text.Text;

/** The game class that runs the game */
public class Game {
    private PoolTable table;
    private GameControlFacade gameControlFacade;
    private HistoryCaretaker historyCaretaker;
    private List<Boolean> cueBallHasStopped;
    private boolean shownWonText = false;
    private final Text winText = new Text(50, 50, "Win and Bye");

    /**
     * Initialise the game with the provided config
     * @param config The config parser to load the config from
     */
    public Game(ConfigReader config) {
        this.setup(config);
    }

    private void setup(ConfigReader config) {
        this.table = new PoolTable(config.getConfig().getTableConfig());
        List<BallConfig> ballsConf = config.getConfig().getBallsConfig().getBallConfigs();
        List<Ball> balls = new ArrayList<>();
        BallBuilderDirector builder = new BallBuilderDirector();
        builder.registerDefault();
        for (BallConfig ballConf: ballsConf) {
            Ball ball = builder.construct(ballConf);
            if (ball == null) {
                System.err.println("WARNING: Unknown ball, skipping...");
            } else {
                balls.add(ball);
            }
        }
        this.table.setupBalls(balls);

        // Custom pockets
        PocketsConfig pocketsConfig = config.getConfig().getTableConfig().getPocketsConfig();
        if (pocketsConfig != null) {
            List<PocketConfig> pocketConfigs = pocketsConfig.getPocketConfigs();
            List<Pocket> pockets = new ArrayList<>();
            for (PocketConfig pocketConfig : pocketConfigs) {
                Pocket pocket = new Pocket(pocketConfig);
                pockets.add(pocket);
            }
            this.table.reconfigPockets(pockets);
        }

        this.cueBallHasStopped = new ArrayList<>();
        for (Ball ball : balls) {
            if (ball.getBallType() == Ball.BallType.CUEBALL) {
                this.cueBallHasStopped.add(ball.hasStopped());
            }
        }

        this.winText.setVisible(false);
        this.winText.setX(table.getDimX() / 2);
        this.winText.setY(table.getDimY() / 2);
    }

    /**
     * Get the window dimension in the x-axis
     * @return The x-axis size of the window dimension
     */
    public double getWindowDimX() {
        return this.table.getDimX();
    }

    /**
     * Get the window dimension in the y-axis
     * @return The y-axis size of the window dimension
     */
    public double getWindowDimY() {
        return this.table.getDimY();
    }

    /**
     * Get the pool table associated with the game
     * @return The pool table instance of the game
     */
    public PoolTable getPoolTable() {
        return this.table;
    }

    /** Add all drawable object to the JavaFX group
     * @param root The JavaFX `Group` instance
    */
    public void addDrawables(Group root) {
        ObservableList<Node> groupChildren = root.getChildren();
        table.addToGroup(groupChildren);
        groupChildren.add(this.winText);
    }

    public void setGameControl(GameControlFacade gameControlFacade) {
        this.gameControlFacade = gameControlFacade;
    }

    public void setHistoryCaretaker(HistoryCaretaker historyCaretaker) {
        this.historyCaretaker = historyCaretaker;
    }

    /** Reset the game */
    public void reset() {
        this.winText.setVisible(false);
        this.shownWonText = false;
        this.table.reset();

        this.gameControlFacade.playFromStart();
        this.gameControlFacade.reset();
    }

    public void addScore(int score) {
        this.gameControlFacade.addScore(score);
    }
    /** Code to execute every tick. */
    public void tick() {
        if (table.hasWon() && !this.shownWonText) {

            this.gameControlFacade.pause();

            System.out.println(this.winText.getText());
            this.winText.setVisible(true);
            this.shownWonText = true;
        }
        table.checkPocket(this);
        table.handleCollision();
        this.table.applyFrictionToBalls();
        for (Ball ball : this.table.getBalls()) {
            ball.move();
        }

        int i = 0;
        for (Ball ball : this.table.getBalls()) {
            if (ball.getBallType() == Ball.BallType.CUEBALL) {
                if (!this.cueBallHasStopped.get(i) && ball.hasStopped()) {
                    this.historyCaretaker.saveHistory();
                }
                this.cueBallHasStopped.set(i, ball.hasStopped());
                i++;
            }
        }
    }

    public class Memento {
        private static class BallState {
            private double xPos;
            private double yPos;
            private double xVel;
            private double yVel;

            private boolean disabled;
            private int fallCounter;

            private BallState(double xPos, double yPos, double xVel, double yVel, boolean disabled, int fallCounter) {
                this.xPos = xPos;
                this.yPos = yPos;
                this.xVel = xVel;
                this.yVel = yVel;
                this.disabled = disabled;
                this.fallCounter = fallCounter;
            }
        }
        private Duration duration;
        private int score;
        private List<BallState> ballStates;

        private Memento(Duration duration, int score, List<BallState> ballStates) {
            this.duration = duration;
            this.score = score;
            this.ballStates = ballStates;
        }

        public Duration getDuration() {
            return duration;
        }

        public int getScore() {
            return score;
        }

        public List<BallState> getBallStates() {
            return ballStates;
        }
    }
    public Memento save() {
        List<Memento.BallState> ballStates = new ArrayList<>();
        for (Ball ball : this.getPoolTable().getBalls()) {
            ballStates.add(new Memento.BallState(
                    ball.getXPos(),
                    ball.getYPos(),
                    ball.getXVel(),
                    ball.getYVel(),
                    ball.isDisabled(),
                    ball.getFallCounter()
            ));
        }
        return new Memento(this.gameControlFacade.getDuration(),
                this.gameControlFacade.getScore(),
                ballStates);
    }
    public void restore(Memento memento) {
        this.gameControlFacade.setDuration(memento.getDuration());
        this.gameControlFacade.setScore(memento.getScore());

        Iterator<Ball> ballIterator = this.getPoolTable().getBalls().iterator();
        Iterator<Memento.BallState> ballStateIterator = memento.getBallStates().iterator();
        while (ballIterator.hasNext() && ballStateIterator.hasNext()) {
            Ball ball = ballIterator.next();
            Memento.BallState ballState = ballStateIterator.next();

            ball.setXPos(ballState.xPos);
            ball.setYPos(ballState.yPos);
            ball.setXVel(ballState.xVel);
            ball.setYVel(ballState.yVel);
            if (ballState.disabled) {
                ball.disable();
            } else {
                ball.enable();
            }
            ball.setFallCounter(ballState.fallCounter);
        }
    }
}
