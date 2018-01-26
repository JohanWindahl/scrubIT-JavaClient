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
import java.net.*;
import java.io.*;


import java.awt.event.ActionEvent;

public class Main extends Application {
    Button getButton;
    Button setButton;
    Button exitButton;
    Label label;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("scrub IT");

        exitButton = new Button();
        exitButton.setText("Exit");
        exitButton.setTranslateX(275);
        exitButton.setTranslateY(280);
        exitButton.setOnAction(e -> {
            System.out.println("Closing");
            label.setText("Closing");
            System.exit(1);
        });

        setButton = new Button();
        setButton.setText("Set");
        setButton.setTranslateX(exitButton.getTranslateX()-40);
        setButton.setTranslateY(exitButton.getTranslateY());
        setButton.setOnAction(e -> {
            System.out.println("Setting data...");
            label.setText("Data set.");
            doSetData();
        });

        getButton = new Button();
        getButton.setText("Get");
        getButton.setTranslateX(setButton.getTranslateX()-40);
        getButton.setTranslateY(setButton.getTranslateY());
        getButton.setOnAction(e -> {
            System.out.println("Getting data...");
            label.setText("Getting data...");
            try {
                doGetData();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

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

    private void doSetData() {

    }

    private void doGetData() throws IOException {
        URL url = null;

        try {
            url = new URL("https://scrubit.herokuapp.com/joe");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        BufferedReader in = null;
        try {
            in = new BufferedReader(
                    new InputStreamReader(url.openStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String inputLine;
        if (in != null) {
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
            }
        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }

}
