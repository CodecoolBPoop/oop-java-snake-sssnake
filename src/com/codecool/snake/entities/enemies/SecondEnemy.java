package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.snakes.SnakeHead;
import java.util.Random;

import javafx.geometry.Point2D;


public class SecondEnemy extends SimpleEnemy{
    public SecondEnemy() {


        setImage(Globals.getInstance().getImage("SecondEnemy"));
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);

        double direction = rnd.nextDouble() * 360;
        setRotate(direction);

        int speed = 1;
        heading = Utils.directionToVector(direction, speed);
    }

    @Override // D: After they reached the edge, create new instances
    public void step() {
        if (isOutOfBounds()){
            destroy();
            new SecondEnemy();
        } else {
            setX(getX() + heading.getX());
            setY(getY() + heading.getY());
        }
    }
}
