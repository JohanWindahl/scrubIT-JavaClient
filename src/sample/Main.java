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
import java.util.ArrayList;
import java.util.List;


public class Main extends Application {
    Button getButton;
    Button setButton;
    Button exitButton;
    Label label;
    TableView table;


    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("scrub IT");

        table = new TableView();
        TableView<Product> table = new TableView<>();
        table.setEditable(true);

        TableColumn id = new TableColumn("ID");
        id.setCellValueFactory(
                new PropertyValueFactory<Product, Integer>("Id"));

        TableColumn name = new TableColumn("Name");
        name.setCellValueFactory(
                new PropertyValueFactory<Product, String>("Name"));

        TableColumn price = new TableColumn("Price");
        price.setCellValueFactory(
                new PropertyValueFactory<Product, Integer>("Price"));

        TableColumn stock = new TableColumn("Stock");
        stock.setCellValueFactory(
                new PropertyValueFactory<Product, Integer>("Stock"));

        table.getColumns().addAll(id, name, price, stock);

        name.setMinWidth(300);
        price.setMinWidth(100);
        stock.setMinWidth(100);
        table.setTranslateY(-25);
        table.setMaxHeight(550);

        ObservableList<Product> data =
            FXCollections.observableArrayList(
                    new Product(1,"Cola",120,12),
                    new Product(2,"Pepsi Max",99,12)
            );
        table.setItems(data);

        System.out.println(data);










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
        layout.getChildren().addAll(getButton, setButton,exitButton,label,table);
        layout.setAlignment(Pos.CENTER);

        Scene root = new Scene(layout, 600, 600);

        primaryStage.setScene(root);
        primaryStage.show();
    }

    private void doSetData() {

    }

    private void doGetData() throws IOException {

        /*
        URL url = null;

        try {
            url = new URL("https://jsonplaceholder.typicode.com/posts");

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

        String inputLine2;
        if (in != null) {
            while ((inputLine2 = in.readLine()) != null) {
                //System.out.println(inputLine);
            }
        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject(url);
        System.out.println(jsonObject);

        */
        JSONObject obj2 = new JSONObject("{interests : [{interestKey:Dogs}, {interestKey:Cats}]}");
        System.out.println(obj2);
        List<String> list = new ArrayList<String>();
        JSONArray array = obj2.getJSONArray("interests");
        for (int i = 0; i < array.length(); i++) {
            list.add(array.getJSONObject(i).getString("interestKey"));
        }
        System.out.println(list);
        String url2 = "https://jsonplaceholder.typicode.com/users";
        URL obj = new URL(url2);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        System.out.println("Sending GET to url");
        System.out.println("ResponseCode=" +  responseCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        System.out.print(response);


    }
    public static void main(String[] args) {
        launch(args);
    }

}
