package com.codecool.snake;

import com.codecool.snake.resources.Resources;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.Random;

// class for holding all static stuff
public class Globals {
    private static Globals instance = null;


    // D: pokemon enemies
   // public String[] ENEMIES = {"pikachu.png", "bullbasaur.png", "charmander.png", "squirtle.png" };

    public static final double WINDOW_WIDTH = 1000;
    public static final double WINDOW_HEIGHT = 700;

    public Display display;
    public Game game;

    private GameLoop gameLoop;
    private Resources resources;


    public static Globals getInstance() {
        if(instance == null) instance = new Globals();
        return instance;
    }

    public void setGameLoop(GameLoop gameLoop) {
        this.gameLoop = gameLoop;
    }

   /* // D: get random enemies picture
    public String getRandomEnemyImage() {
        Random random = new Random();
        String randomEnemy = ENEMIES[random.nextInt(ENEMIES.length)];
        return randomEnemy;
    }*/

    public void setupResources() {
        resources = new Resources();
        resources.addImage("SnakeHead", new Image("caterpie.png"));
        resources.addImage("SnakeBody", new Image("snake_body.png"));
        resources.addImage("SimpleEnemy", new Image("pikachu.png"));
        resources.addImage("SecondEnemy", new Image("bullbasaur.png"));
        resources.addImage("ThirdEnemy", new Image("squirtle.png"));
        resources.addImage("PowerUpBerry", new Image("hyper-potion.png"));
    }

    public Image getImage(String name) { return resources.getImage(name); }

    public void startGame() { gameLoop.start(); }

    public void stopGame() { gameLoop.stop(); }

    private Globals() {
        // singleton needs the class to have private constructor
    }
}
