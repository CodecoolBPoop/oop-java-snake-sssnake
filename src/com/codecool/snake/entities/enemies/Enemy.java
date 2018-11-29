package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.snakes.SnakeHead;

public abstract class Enemy extends GameEntity{
    private final int damage;
    private SnakeHead snakeHead;

    public Enemy(int damage, SnakeHead snakeHead) {
        this.damage = damage;
        this.snakeHead = snakeHead;
    }

    public int getDamage() {
        return damage;
    }
}
