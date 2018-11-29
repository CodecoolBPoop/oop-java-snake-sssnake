package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.snakes.SnakeHead;

public class ThirdEnemy extends SimpleEnemy {
    public ThirdEnemy(SnakeHead head) {
        super(head);

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
        } else {
            setX(getX() + heading.getX());
            setY(getY() + heading.getY());
        }
    }
}

