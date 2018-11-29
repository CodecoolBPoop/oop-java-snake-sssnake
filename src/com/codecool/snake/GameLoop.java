package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.Snake;

import java.util.List;
import java.util.Random;

public class GameLoop {
    static final int FRAME = 165;
    private int loopCounter = 0;

    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    private Snake snake;

    public Snake getSnake2() {
        return snake2;
    }

    public void setSnake2(Snake snake2) {
        this.snake2 = snake2;
    }

    private Snake snake2;
    private boolean running = false;
    private Random rnd = new Random();

    public GameLoop(Snake snake, Snake snake1) {
        this.snake = snake;
        this.snake2 = snake1;
    }

    public void start() {
        running = true;
    }

    public void stop() {
        running = false;
    }

    public void step() {
        if(running) {
            if(snake != null) {
                snake.step();
            }
            if (snake2 != null) {
                snake2.step();
            }
            spawnEnemies(loopCounter);
            spawnPowerUps();
            slowSnake(loopCounter);
            stepAnimatableObjects();
            checkCollisions();

        }

        Globals.getInstance().display.frameFinished();
        loopCounter += 1;
    }
    private void stepAnimatableObjects() {
        for (GameEntity gameObject : Globals.getInstance().display.getObjectList()) {
            if (gameObject instanceof Animatable) {
                ((Animatable) gameObject).step();
            }
        }
    }

    private void spawnEnemies(int loopCounter) {
        if (loopCounter % FRAME == 0) {
            Globals.getInstance().game.spawnEnemies(3);
        }

    }

    private void spawnPowerUps() {
        if (rnd.nextInt(10000) < 100) {
            Globals.getInstance().game.spawnPowerUps(1);
        }
    }

    private void slowSnake(int loopCounter) {
        if(loopCounter % FRAME == 0 && snake.getSpeed() > 2) {
            snake.setSpeed(2);
        }
        if(loopCounter % FRAME == 0 && snake2.getSpeed() > 2) {
            snake2.setSpeed(2);
        }

    }

    private void checkCollisions() {
        List<GameEntity> gameObjs = Globals.getInstance().display.getObjectList();
        for (int idxToCheck = 0; idxToCheck < gameObjs.size(); ++idxToCheck) {
            GameEntity objToCheck = gameObjs.get(idxToCheck);
            if (objToCheck instanceof Interactable) {
                for (int otherObjIdx = idxToCheck + 1; otherObjIdx < gameObjs.size(); ++otherObjIdx) {
                    GameEntity otherObj = gameObjs.get(otherObjIdx);
                    if (otherObj instanceof Interactable){
                        if(objToCheck.getBoundsInParent().intersects(otherObj.getBoundsInParent())){
                            ((Interactable) objToCheck).apply(otherObj);
                            ((Interactable) otherObj).apply(objToCheck);
                        }
                    }
                }
            }
        }
    }
}
