package com.ciphers.enddec;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            Image icon = new Image("file:./src/main/java/img/hackman.png");
            primaryStage.getIcons().add(icon);
            primaryStage.show();



        }catch (Exception e)
        {

            e.printStackTrace();

        }

    }

    public static void main(String[] args) {
        launch();
    }
}