package com.codecool.snake.entities.powerups;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.entities.snakes.SnakeHead;
import java.util.Random;


public class SimplePowerUp extends GameEntity implements Interactable {
    private static Random rnd = new Random();

    private int healthPotionPoints = 5;

    public int getHealthPotionPoints() {
        return healthPotionPoints;
    }

    public SimplePowerUp() {

        setImage(Globals.getInstance().getImage("PowerUpBerry"));

        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
    }

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof SnakeHead){
            Snake snake = new Snake();
            snake.changeHealth(getHealthPotionPoints());
            System.out.println(getMessage());
            destroy();

        }
    }

    @Override
    public String getMessage() {
        return "Got power-up :)";
    }
}
