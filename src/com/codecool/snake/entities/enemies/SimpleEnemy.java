package com.codecool.snake.entities.enemies;

import com.codecool.snake.Game;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import java.util.Random;

import com.sun.javafx.geom.Vec2d;
import javafx.geometry.Point2D;



public class SimpleEnemy extends Enemy implements Animatable, Interactable {
    Point2D heading;
    static Random rnd = new Random();

    public SimpleEnemy() {
        this(10);
    }

    public SimpleEnemy(int damage,SnakeHead head) {
        super(damage);

        double spawnPointX = rnd.nextDouble() * Globals.WINDOW_WIDTH;
        double spawnPointY = rnd.nextDouble() * Globals.WINDOW_HEIGHT;
        Vec2d spawnPoint = new Vec2d(spawnPointX, spawnPointY);


        while(spawnPoint.distance(head.getPosition()) < 150){
             spawnPointX = rnd.nextDouble() * Globals.WINDOW_WIDTH;
             spawnPointY = rnd.nextDouble() * Globals.WINDOW_HEIGHT;
             spawnPoint = new Vec2d(spawnPointX, spawnPointY);
        }

        setImage(Globals.getInstance().getImage("SimpleEnemy"));
        setX(spawnPointX);
        setY(spawnPointY);

        double direction = rnd.nextDouble() * 360;
        setRotate(direction);

        int speed = 1;
        heading = Utils.directionToVector(direction, speed);
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();

            new SimpleEnemy();
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof SnakeHead){
            System.out.println(getMessage());
            destroy();
        }
    }

    @Override
    public String getMessage() {
        return (getDamage() + " damage");
    }
}
