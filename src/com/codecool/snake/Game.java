package com.codecool.snake;

import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.SimplePowerUp;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.eventhandler.InputHandler;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;


public class Game extends Pane {
    private Snake snake = null;
    private Snake snake1 = null;
    private GameTimer gameTimer = new GameTimer();
    private static boolean multiplayer = false;

    public Game(boolean multi) {
        Globals.getInstance().game = this;
        Globals.getInstance().display = new Display(this);
        Globals.getInstance().setupResources();

        multiplayer = multi;
        init();

    }

    public void init() {
        spawnSnake();
        spawnEnemies(4);
        spawnPowerUps(4);

        GameLoop gameLoop = new GameLoop(snake,snake1);
        Globals.getInstance().setGameLoop(gameLoop);
        gameTimer.setup(gameLoop::step);
        gameTimer.play();
    }

    public void start() {
        setupInputHandling();
        Globals.getInstance().startGame();
    }

    private void spawnSnake() {
        if(multiplayer) {
            snake = new Snake(new Vec2d(500, 500));
            snake1 = new Snake(new Vec2d(250,250));
        }else{
            snake = new Snake(new Vec2d(500, 500));
        }
    }

    private void spawnEnemies(int numberOfEnemies) {
        for (int i = 0; i < numberOfEnemies; ++i) new SimpleEnemy(snake.getHead());
    }

    private void spawnPowerUps(int numberOfPowerUps) {
        for (int i = 0; i < numberOfPowerUps; ++i) new SimplePowerUp();
    }

    private void setupInputHandling() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> InputHandler.getInstance().setKeyPressed(event.getCode()));
        scene.setOnKeyReleased(event -> InputHandler.getInstance().setKeyReleased(event.getCode()));
    }
}
