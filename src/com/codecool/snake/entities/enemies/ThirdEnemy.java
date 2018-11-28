package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;

public class ThirdEnemy extends SimpleEnemy {
    public ThirdEnemy() {
        super(5);

        setImage(Globals.getInstance().getImage("ThirdEnemy"));
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);

        double direction = rnd.nextDouble() * 360;
        setRotate(direction);

        int speed = 2;
        heading = Utils.directionToVector(direction, speed);
    }

    @Override // D: After they reached the edge, create new instances
    public void step() {
        if (isOutOfBounds()){
            destroy();
            new ThirdEnemy();
        } else {
            setX(getX() + heading.getX());
            setY(getY() + heading.getY());
        }
    }
}

