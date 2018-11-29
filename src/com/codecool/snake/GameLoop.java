package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.Snake;

import java.util.List;

public class GameLoop {
    static final int FRAME = 165;
    private int loopCounter = 0;
    private Snake snake;
    private boolean running = false;

    public GameLoop(Snake snake) { this.snake = snake; }

    public void start() {
        running = true;
    }

    public void stop() {
        running = false;
    }

    public void step() {
        if(running) {
            snake.step();
            spawnEnemies(loopCounter);
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
