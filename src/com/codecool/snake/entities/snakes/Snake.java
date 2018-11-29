package com.codecool.snake.entities.snakes;

import com.codecool.snake.DelayedModificationList;
import com.codecool.snake.Display;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.eventhandler.InputHandler;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.input.KeyCode;


public class Snake implements Animatable {
    private static final float speed = 2;
    private int health = 100;
    private KeyCode left;
    private KeyCode right;
    public SnakeHead getHead() {
        return head;
    }

    private SnakeHead head;
    private DelayedModificationList<GameEntity> body;

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }


    public Snake(Vec2d position, int snakeNumber) {
        head = new SnakeHead(this, position);
        body = new DelayedModificationList<>();
        if (snakeNumber > 1){
            this.left = KeyCode.A;
            this.right = KeyCode.D;
        } else{
            this.left = KeyCode.LEFT;
            this.right = KeyCode.RIGHT;
        }
        addPart(4);
    }

    public void step() {
        SnakeControl turnDir = getUserInput();
        head.updateRotation(turnDir, speed);

        updateSnakeBodyHistory();
        checkForGameOverConditions();

        body.doPendingModifications();
    }

    private SnakeControl getUserInput() {
        SnakeControl turnDir = SnakeControl.INVALID;
        if(InputHandler.getInstance().isKeyPressed(left)) turnDir = SnakeControl.TURN_LEFT;
        if(InputHandler.getInstance().isKeyPressed(right)) turnDir = SnakeControl.TURN_RIGHT;
        return turnDir;
    }

    public void addPart(int numParts) {
        GameEntity parent = getLastPart();
        Vec2d position = parent.getPosition();

        for (int i = 0; i < numParts; i++) {
            SnakeBody newBodyPart = new SnakeBody(position);
            body.add(newBodyPart);
        }
        Globals.getInstance().display.updateSnakeHeadDrawPosition(head);
    }

    public void changeHealth(int diff) {
        health += diff;
    }

    public void decreaseHealth(int damage) {
        health -= damage;
    }

    private void checkForGameOverConditions() {
        if (head.isOutOfBounds() || health <= 0) {

            health = 0;

            head.destroy();
            for(GameEntity bodypart : body.getList()){
                bodypart.destroy();

            }

        }
    }

    private void updateSnakeBodyHistory() {
        GameEntity prev = head;
        for(GameEntity currentPart : body.getList()) {
            currentPart.setPosition(prev.getPosition());
            prev = currentPart;
        }
    }

    private GameEntity getLastPart() {
        GameEntity result = body.getLast();

        if(result != null) return result;
        return head;
    }
}
