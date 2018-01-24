package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;

public class Main extends Application {
    Button getButton;
    Button setButton;
    Button exitButton;
    Label label;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("scrubIT");




        exitButton = new Button();
        exitButton.setText("Exit");
        exitButton.setOnAction(e -> {
            System.out.println("Closing");
            label.setText("Closing");
            System.exit(1);
        });

        exitButton.setTranslateX(275);
        exitButton.setTranslateY(280);

        setButton = new Button();
        setButton.setText("Set");
        setButton.setAlignment(Pos.BASELINE_LEFT);
        setButton.setOnAction(e -> {
            System.out.println("set");
            label.setText("set");
        });
        setButton.setTranslateX(exitButton.getTranslateX()-40);
        setButton.setTranslateY(exitButton.getTranslateY());

        getButton = new Button();
        getButton.setText("Get");
        getButton.setOnAction(e -> {
            System.out.println("get");
            label.setText("get");
        });

        getButton.setTranslateX(setButton.getTranslateX()-40);
        getButton.setTranslateY(setButton.getTranslateY());

        label = new Label();
        label.setText("label text");
        label.setTranslateY(getButton.getTranslateY());



        StackPane layout = new StackPane();
        layout.getChildren().addAll(getButton, setButton,exitButton,label);
        layout.setAlignment(Pos.CENTER);

        Scene root = new Scene(layout, 600, 600);

        primaryStage.setScene(root);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }




}
