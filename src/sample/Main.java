package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.control.cell.TextFieldTableCell;
import org.json.*;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
    TextField tf_id;
    TextField tf_name;
    TextField tf_stock;
    TextField tf_price;
    Button add;
    Button deleteButton;

    ObservableList<Product> data =
            FXCollections.observableArrayList(
                    new Product("1","Mock Cola","120","12"),
                    new Product("2","Mock Pepsi Max","99","21")
            );

    @Override
    public void start(Stage primaryStage) {
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("scrubITadmin");

        table = new TableView();
        TableView<Product> table = new TableView<>();
        table.setTranslateY(-75);
        table.setMaxHeight(550);



        TableColumn<Product, String> tc_name = new TableColumn<>("Name");
        tc_name.setMinWidth(300);
        tc_name.setCellValueFactory(
                new PropertyValueFactory<Product,String>("Name")
        );
        tc_name.setCellFactory(TextFieldTableCell.forTableColumn());

        tc_name.setOnEditCommit( new EventHandler<CellEditEvent<Product,String>>() {
            @Override
            public void handle(CellEditEvent<Product,String> t) {
                ((Product) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setName(t.getNewValue());
            }
        });

        TableColumn<Product, String> tc_id = new TableColumn<>("Id");
        tc_id.setMinWidth(100);
        tc_id.setCellValueFactory(
                new PropertyValueFactory<Product,String>("Id")
        );
        tc_id.setCellFactory(TextFieldTableCell.forTableColumn());

        tc_id.setOnEditCommit( new EventHandler<CellEditEvent<Product,String>>() {
            @Override
            public void handle(CellEditEvent<Product,String> t) {
                ((Product) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setId(t.getNewValue());
            }
        });


        TableColumn<Product, String> tc_price = new TableColumn<>("Price");
        tc_price.setMinWidth(100);
        tc_price.setCellValueFactory(
                new PropertyValueFactory<Product,String>("Price")
        );
        tc_price.setCellFactory(TextFieldTableCell.forTableColumn());

        tc_price.setOnEditCommit( new EventHandler<CellEditEvent<Product,String>>() {
            @Override
            public void handle(CellEditEvent<Product,String> t) {
                ((Product) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setPrice(t.getNewValue());
            }
        });

        TableColumn<Product, String> tc_stock = new TableColumn<>("Stock");
        tc_stock.setMinWidth(100);
        tc_stock.setCellValueFactory(
                new PropertyValueFactory<Product,String>("Stock")
        );
        tc_stock.setCellFactory(TextFieldTableCell.forTableColumn());

        tc_stock.setOnEditCommit( new EventHandler<CellEditEvent<Product,String>>() {
            @Override
            public void handle(CellEditEvent<Product,String> t) {
                ((Product) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setStock(t.getNewValue());
            }
        });

        table.getColumns().addAll(tc_id,tc_name, tc_price, tc_stock);
        table.setItems(data);

        table.setOnMouseClicked(e -> {
            TablePosition cell = table.getFocusModel().getFocusedCell();
            table.setEditable(true);
            table.edit(cell.getRow(),cell.getTableColumn());

        });


        exitButton = new Button();
        exitButton.setText("Exit");
        exitButton.setTranslateX(152);
        exitButton.setTranslateY(245);
        exitButton.setOnAction(e -> {
            label.setText("Closing");
            System.exit(1);
        });

        setButton = new Button();
        setButton.setText("Upload Table");
        setButton.setTranslateX(exitButton.getTranslateX()-65);
        setButton.setTranslateY(exitButton.getTranslateY());
        setButton.setOnAction(e -> {
            label.setText("Setting data...");
            doSetData();
        });

        getButton = new Button();
        getButton.setText("Get Table");
        getButton.setTranslateX(setButton.getTranslateX()-80);
        getButton.setTranslateY(setButton.getTranslateY());
        getButton.setOnAction(e -> {
             try {
                doGetData();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        label = new Label();
        label.setTranslateX(getButton.getTranslateX()-150);
        label.setTranslateY(getButton.getTranslateY());

        tf_id = new TextField();
        tf_id.setMaxWidth(29);
        tf_id.setTranslateY(215);
        tf_id.setTranslateX(-284);
        tf_id.setText("id");

        tf_name = new TextField();
        tf_name.setMaxWidth(298);
        tf_name.setTranslateY(215);
        tf_name.setTranslateX(-117);
        tf_name.setText("name");

        tf_price = new TextField();
        tf_price.setMaxWidth(98);
        tf_price.setTranslateY(215);
        tf_price.setTranslateX(83);
        tf_price.setText("price");

        tf_stock = new TextField();
        tf_stock.setMaxWidth(100);
        tf_stock.setTranslateY(215);
        tf_stock.setTranslateX(184);
        tf_stock.setText("stock");

        add = new Button();
        add.setText("Add row");
        add.setTranslateX(265);
        add.setTranslateY(215);
        add.setOnAction(e -> {
            label.setText("Added row");
            addProduct();
        });

        deleteButton = new Button();
        deleteButton.setText("Remove row");
        deleteButton.setTranslateX(255);
        deleteButton.setTranslateY(setButton.getTranslateY());

        deleteButton.setOnAction(e -> {
            Product selectedItem = table.getSelectionModel().getSelectedItem();
            if (selectedItem==null) {
                label.setText("Choose row");
            }
            else {
                table.getItems().remove(selectedItem);
                label.setText("Removed row");
            }
        });


        layout = new StackPane();
        layout.getChildren().addAll(deleteButton,add,tf_id,tf_name,tf_price,tf_stock,getButton, setButton,exitButton,label,table);
        layout.setAlignment(Pos.CENTER);

        Scene root = new Scene(layout, 600, 700);

        primaryStage.setScene(root);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    private void doSetData() {

    }

    private void addProduct() {
        try {
            String id = tf_id.getText();
            String name = tf_name.getText();
            String stock = tf_stock.getText();
            String price = tf_price.getText();
            Product newProduct = new Product(id,name,stock,price);

            boolean doAdd = true;
            for (int i = 0;i<data.size();i++) {
                if (id.equals(data.get(i).getId())) {
                    label.setText("ID already exists");
                    doAdd = false;
                }
            }
            if (doAdd) {
                data.add(newProduct);
            }

        } catch(Exception e) {
            label.setText("Invalid Format");
        }
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
            String id = jsonObj.getJSONObject(i).get("id").toString();
            String name = jsonObj.getJSONObject(i).get("title").toString();
            String stock = jsonObj.getJSONObject(i).get("userId").toString();
            String price = jsonObj.getJSONObject(i).get("userId").toString();

            Product test = new Product(id,name,stock,price);
            data.add(test);
        }
        table.setItems(data);
    }

    public static void main(String[] args) {
        launch(args);
    }
}




