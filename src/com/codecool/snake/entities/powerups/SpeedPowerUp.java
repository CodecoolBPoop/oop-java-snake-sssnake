package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;

import java.util.Random;

public class SpeedPowerUp extends SimplePowerUp {
    private static Random rnd = new Random();

    private final static int plusSpeed = 1;

    public static int getPlusSpeed() {
        return plusSpeed;
    }

    public SpeedPowerUp() {
        setImage(Globals.getInstance().getImage("SpeedUpPotion"));

        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
    }
}
