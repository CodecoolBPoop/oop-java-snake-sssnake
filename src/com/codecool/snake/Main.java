package com.codecool.snake;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.util.Optional;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Alert multiplayerAlert = new Alert(Alert.AlertType.CONFIRMATION);
        multiplayerAlert.setTitle("Multiplayer");
        multiplayerAlert.setHeaderText(null);
        multiplayerAlert.setContentText("Do you want to play with your buddy?");
        ButtonType yes = new ButtonType("Yes");
        ButtonType no = new ButtonType("No");
        multiplayerAlert.getButtonTypes().setAll(yes,no);
        Optional<ButtonType> result = multiplayerAlert.showAndWait();
        Scene mainScene;
        Game game;
        if(result.get() == yes){
            System.out.println("YES");
            game = new Game(true);
            mainScene = new Scene(game, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT);


        }else{
            System.out.println("No");
            game = new Game(false);
            mainScene = new Scene(game, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT);

        }
        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(mainScene);
        primaryStage.show();

        game.start();


    }

    @Override
    public void stop() throws Exception {
        System.out.println("Exiting..");
    }
}
