package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.*;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.net.*;
import java.io.*;

public class Main extends Application {
    Button getButton;
    Button setButton;
    Button exitButton;
    Label label;
    StackPane layout;
    TableView table;
    ObservableList<Product> data =
            FXCollections.observableArrayList(
                    new Product(1,"Mock Cola",120,12),
                    new Product(2,"Mock Pepsi Max",99,12)
            );

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("scrubITadmin");

        table = new TableView();
        TableView<Product> table = new TableView<>();
        table.setEditable(true);
        table.setTranslateY(-25);
        table.setMaxHeight(550);

        TableColumn id = new TableColumn("ID");
        id.setCellValueFactory(
                new PropertyValueFactory<Product, Integer>("Id"));

        TableColumn name = new TableColumn("Name");
        name.setMinWidth(300);
        name.setCellValueFactory(
                new PropertyValueFactory<Product, String>("Name"));

        TableColumn price = new TableColumn("Price");
        price.setMinWidth(100);
        price.setCellValueFactory(
                new PropertyValueFactory<Product, Integer>("Price"));

        TableColumn stock = new TableColumn("Stock");
        stock.setMinWidth(100);
        stock.setCellValueFactory(
                new PropertyValueFactory<Product, Integer>("Stock"));
        table.getColumns().addAll(id, name, price, stock);
        table.setItems(data);

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
            label.setText("Setting data...");
            doSetData();
        });

        getButton = new Button();
        getButton.setText("Get");
        getButton.setTranslateX(setButton.getTranslateX()-40);
        getButton.setTranslateY(setButton.getTranslateY());
        getButton.setOnAction(e -> {
            System.out.println("Getting data...");
             try {
                doGetData();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        label = new Label();
        label.setTranslateY(getButton.getTranslateY());

        layout = new StackPane();
        layout.getChildren().addAll(getButton, setButton,exitButton,label,table);
        layout.setAlignment(Pos.CENTER);

        Scene root = new Scene(layout, 600, 600);

        primaryStage.setScene(root);
        primaryStage.show();
    }

    private void doSetData() {

    }

    private void doGetData() throws IOException {
        label.setText("Getting data...");

        String urlToJson = "https://jsonplaceholder.typicode.com/todos";
        URL obj = new URL(urlToJson);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();

        if (responseCode==200) {
            data.clear();
            label.setText("Request succeeded");
        }
        else {
            label.setText("Connection not successful, Response: " +  responseCode);
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
            response.append("\n");
        }

        JSONArray jsonObj = new JSONArray(response.toString());


        for (int i=0;i<10;i++) {
        //for (int i=0;i<jsonObj.length();i++) {
            Integer id = (Integer)jsonObj.getJSONObject(i).get("id");
            String name = (String)jsonObj.getJSONObject(i).get("title");
            Integer stock = (Integer)jsonObj.getJSONObject(i).get("userId");
            Integer price = (Integer)jsonObj.getJSONObject(i).get("userId");

            Product test = new Product(id,name,stock,price);
            data.add(test);
        }
        table.setItems(data);
    }
    public static void main(String[] args) {
        launch(args);
    }


    }




