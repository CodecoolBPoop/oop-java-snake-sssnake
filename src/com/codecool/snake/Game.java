package com.codecool.snake;

import com.codecool.snake.entities.enemies.SecondEnemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.enemies.ThirdEnemy;

import com.codecool.snake.entities.powerups.SimplePowerUp;
import com.codecool.snake.entities.powerups.SpeedPowerUp;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.eventhandler.InputHandler;

import com.sun.javafx.geom.Vec2d;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;

import java.awt.*;

public class Game extends Pane {
    private Snake snake = null;
    private Snake snake2 = null;
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
        spawnEnemies(3);
        spawnPowerUps(4);

        GameLoop gameLoop = new GameLoop(snake,snake2);
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
            snake = new Snake(new Vec2d(500, 500),1);
            snake2 = new Snake(new Vec2d(250,250),2);
        }else{
            snake = new Snake(new Vec2d(500, 500),1);
        }
    }

    public void spawnEnemies(int numberOfEnemies) {
        for(int i = 0; i < numberOfEnemies; ++i) new SimpleEnemy(snake.getHead());
        for(int i = 0; i < numberOfEnemies; ++i) new SecondEnemy(snake.getHead());
        for(int i = 0; i < numberOfEnemies; ++i) new ThirdEnemy(snake.getHead());

    }

    public void spawnPowerUps(int numberOfPowerUps) {

        for(int i = 0; i < numberOfPowerUps; ++i) new SimplePowerUp();
        for(int i = 0; i < numberOfPowerUps; ++i) new SpeedPowerUp();
    }

    private void setupInputHandling() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> InputHandler.getInstance().setKeyPressed(event.getCode()));
        scene.setOnKeyReleased(event -> InputHandler.getInstance().setKeyReleased(event.getCode()));
    }

    public void gameOver() {
        int highScoreSnake = snake.getBody().getList().size();
        int highScoreSnake2 = 0;
        if(multiplayer) {
            highScoreSnake2 = snake2.getBody().getList().size();
        }
        Globals.getInstance().stopGame();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Game over! You died!");
        alert.setHeaderText("Look, snake1 score is" + " " + highScoreSnake + "\n" + "Look, snake2 score is"+ " " + highScoreSnake2);

        ButtonType buttonRestart = new ButtonType("Restart");
        ButtonType buttonExit = new ButtonType("Exit", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonRestart, buttonExit);
        alert.setOnHidden(e -> {
            if (alert.getResult() == buttonRestart)
                Globals.getInstance().display.restart();

            if (alert.getResult() == buttonExit)
                Platform.exit();
        });
        alert.show();


    }
}
