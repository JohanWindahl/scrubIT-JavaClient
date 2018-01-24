package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;

public class Main extends Application {
    Button getButton;
    Button setButton;
    Button exitButton;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("scrubIT");

        getButton = new Button();
        getButton.setText("Get");
        getButton.setOnAction(e -> System.out.println("get"));

        setButton = new Button();
        setButton.setText("Set");
        setButton.setOnAction(e -> System.out.println("set"));

        exitButton = new Button();
        exitButton.setText("Exit");
        exitButton.setOnAction(e -> System.exit(1));




        StackPane layout = new StackPane();
        layout.getChildren().add(getButton);
        layout.getChildren().add(setButton);
        layout.getChildren().add(exitButton);

        Scene root = new Scene(layout, 600, 600);

        primaryStage.setScene(root);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }




}
