package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.TextAlignment;
import org.json.*;
import org.json.JSONObject;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.net.*;
import java.io.*;

import static javafx.scene.text.TextAlignment.CENTER;

public class Main extends Application {
    Button getButton;
    Button setButton;
    Button exitButton;
    Button updateSingleButton;

    Label label;
    StackPane layout;
    TableView table;
    TextField tf_name;
    TextField tf_quantity;
    TextField tf_price;
    TextField tf_url;
    TextField tf_desc;
    TextField tf_qr;
    PasswordField tf_password;

    Integer idWidth = 170;
    Integer nameWidth = 150;
    Integer quantityWidth = 60;
    Integer priceWidth = 50;
    Integer urlWidth = 300;
    Integer descWidth = 200;
    Integer qrWidth = 150;

    Integer totalWidth = 4+idWidth+nameWidth+quantityWidth+priceWidth+urlWidth+descWidth+qrWidth;

    Button add;
    Button deleteButton;

    ObservableList<Product> data =
            FXCollections.observableArrayList(
                    new Product("MOCK Cola","120","12", "123","www.google.se","coke"),
                    new Product("MOCK Pepsi Max","99","12", "123","www.google.se","bebzi")
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
        tc_name.setPrefWidth(nameWidth);
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

        tc_id.setPrefWidth(idWidth);

        tc_id.setCellValueFactory(
                new PropertyValueFactory<Product,String>("Id")
        );

        TableColumn<Product, String> tc_price = new TableColumn<>("Price");
        tc_price.setPrefWidth(priceWidth);
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

        TableColumn<Product, String> tc_quantity = new TableColumn<>("Quantity");
        tc_quantity.setPrefWidth(quantityWidth);
        tc_quantity.setCellValueFactory(
                new PropertyValueFactory<Product,String>("Quantity")
        );
        tc_quantity.setCellFactory(TextFieldTableCell.forTableColumn());

        tc_quantity.setOnEditCommit( new EventHandler<CellEditEvent<Product,String>>() {
            @Override
            public void handle(CellEditEvent<Product,String> t) {
                ((Product) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setQuantity(t.getNewValue());
            }
        });



        TableColumn<Product, String> tc_desc = new TableColumn<>("Desc");
        tc_desc.setPrefWidth(descWidth);
        tc_desc.setCellValueFactory(
                new PropertyValueFactory<Product,String>("Desc")
        );
        tc_desc.setCellFactory(TextFieldTableCell.forTableColumn());

        tc_desc.setOnEditCommit( new EventHandler<CellEditEvent<Product,String>>() {
            @Override
            public void handle(CellEditEvent<Product,String> t) {
                ((Product) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setDesc(t.getNewValue());
            }
        });


        TableColumn<Product, String> tc_qr = new TableColumn<>("Qr-Code");
        tc_qr.setPrefWidth(qrWidth);
        tc_qr.setCellValueFactory(
                new PropertyValueFactory<Product,String>("qr")
        );
        tc_qr.setCellFactory(TextFieldTableCell.forTableColumn());

        tc_qr.setOnEditCommit( new EventHandler<CellEditEvent<Product,String>>() {
            @Override
            public void handle(CellEditEvent<Product,String> t) {
                ((Product) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setQr(t.getNewValue());
            }
        });

        TableColumn<Product, String> tc_url = new TableColumn<>("Url");
        tc_url.setPrefWidth(urlWidth);
        tc_url.setCellValueFactory(
                new PropertyValueFactory<Product,String>("Url")
        );
        tc_url.setCellFactory(TextFieldTableCell.forTableColumn());

        tc_url.setOnEditCommit( new EventHandler<CellEditEvent<Product,String>>() {
            @Override
            public void handle(CellEditEvent<Product,String> t) {
                ((Product) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setUrl(t.getNewValue());
            }
        });


        table.getColumns().addAll(tc_id, tc_name, tc_price, tc_quantity, tc_desc, tc_url, tc_qr);
        table.setItems(data);

        table.setOnMouseClicked(e -> {
            TablePosition cell = table.getFocusModel().getFocusedCell();
            table.setEditable(true);
            table.edit(cell.getRow(),cell.getTableColumn());
        });

        label = new Label();
        label.setAlignment(Pos.CENTER);

        label.setMinWidth(300);
        label.setTranslateX(-300);
        label.setTranslateY(280);

        getButton = new Button();
        getButton.setMinWidth(100);
        getButton.setText("Fetch DB");
        getButton.setTranslateX(label.getTranslateX()+label.getMinWidth()/2+getButton.getMinWidth()/2);

        getButton.setTranslateY(label.getTranslateY());
        getButton.setOnAction(e -> {
            try {
                doGetData();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });


        setButton = new Button();
        setButton.setMinWidth(100);
        setButton.setText("Update all rows");
        setButton.setMaxWidth(100);
        setButton.setTranslateX(getButton.getTranslateX()+getButton.getMinWidth()/2+setButton.getMinWidth()/2);
        setButton.setTranslateY(label.getTranslateY());
        setButton.setOnAction(e -> {
            label.setText("Sending data...");
            try {
                doSetData();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        exitButton = new Button();
        exitButton.setMinWidth(100);
        exitButton.setText("Exit");
        exitButton.setTranslateX(setButton.getTranslateX()+setButton.getMinWidth()/2+exitButton.getMinWidth()/2);

        exitButton.setTranslateY(label.getTranslateY());
        exitButton.setOnAction(e -> {
            label.setText("Closing");
            System.exit(1);
        });

        tf_name = new TextField();
        tf_name.setMaxWidth(nameWidth);
        tf_name.setTranslateY(215);
        tf_name.setTranslateX((-totalWidth/2+idWidth/2)+idWidth/2+nameWidth/2);
        tf_name.setText("name");

        tf_price = new TextField();
        tf_price.setMaxWidth(priceWidth);
        tf_price.setTranslateY(tf_name.getTranslateY());
        tf_price.setTranslateX(tf_name.getTranslateX()+nameWidth/2+priceWidth/2);
        tf_price.setText("price");

        tf_quantity = new TextField();
        tf_quantity.setMaxWidth(quantityWidth);
        tf_quantity.setTranslateY(tf_price.getTranslateY());
        tf_quantity.setTranslateX(tf_price.getTranslateX()+priceWidth/2+quantityWidth/2);
        tf_quantity.setText("quantity");

        tf_desc = new TextField();
        tf_desc.setMaxWidth(descWidth);
        tf_desc.setTranslateY(tf_quantity.getTranslateY());
        tf_desc.setTranslateX(tf_quantity.getTranslateX()+quantityWidth/2+descWidth/2);
        tf_desc.setText("description");

        tf_url = new TextField();
        tf_url.setMaxWidth(urlWidth);
        tf_url.setTranslateY(tf_desc.getTranslateY());
        tf_url.setTranslateX(tf_desc.getTranslateX()+descWidth/2+urlWidth/2);
        tf_url.setText("url");

        tf_qr = new TextField();
        tf_qr.setMaxWidth(qrWidth);
        tf_qr.setTranslateY(tf_url.getTranslateY());
        tf_qr.setTranslateX(tf_url.getTranslateX()+urlWidth/2+qrWidth/2);
        tf_qr.setText("qr");

        tf_password = new PasswordField();
        tf_password.setMaxWidth(setButton.getMinWidth());
        tf_password.setTranslateY(setButton.getTranslateY()+26);
        tf_password.setTranslateX(setButton.getTranslateX());
        tf_password.setPromptText("password");


        deleteButton = new Button();
        deleteButton.setText("Remove row");
        deleteButton.setMinWidth(setButton.getMinWidth());

        deleteButton.setTranslateX(exitButton.getTranslateX());
        deleteButton.setTranslateY(setButton.getTranslateY()-26);

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


        add = new Button();
        add.setText("Add row");
        add.setMinWidth(getButton.getMinWidth());

        add.setTranslateX(getButton.getTranslateX());
        add.setTranslateY(getButton.getTranslateY()-26);
        add.setOnAction(e -> {
            label.setText("Added row");
            addProduct();
        });



        updateSingleButton = new Button();
        updateSingleButton.setText("Update one row");
        updateSingleButton.setMinWidth(exitButton.getMinWidth());

        updateSingleButton.setTranslateX(setButton.getTranslateX());
        updateSingleButton.setTranslateY(add.getTranslateY());

        updateSingleButton.setOnAction(e -> {
            Product selectedItem = table.getSelectionModel().getSelectedItem();
            if (selectedItem==null) {
                label.setText("Choose row");
            }
            else {
                //table.getItems().remove(selectedItem);
                label.setText("Row updated");
                //TODO
            }
        });

        layout = new StackPane();
        layout.getChildren().addAll(updateSingleButton, deleteButton,add,tf_name,tf_price,tf_quantity,tf_desc,tf_url,tf_qr,getButton,setButton,exitButton,label,table,tf_password);
        layout.setAlignment(Pos.CENTER);

        Scene root = new Scene(layout, totalWidth, 700);

        primaryStage.setScene(root);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void doSetData() throws IOException {
        JSONArray jsonArr = new JSONArray();
        for (int i=0;i<data.size();i++) {
            JSONObject obj = new JSONObject();
            String id = data.get(i).getId();
            String name = data.get(i).getName();
            String price = data.get(i).getPrice();
            String stock = data.get(i).getQuantity();

            obj.put("id",id);
            obj.put("name",name);
            obj.put("price",price);
            obj.put("stock",stock)
            ;
            jsonArr.put(obj);
        }
        System.out.println("Creating JSON: ");
        System.out.println(jsonArr);
        System.out.println("Sending JSON: ");

        String json = jsonArr.toString();

        String url3 = "http://ptsv2.com/t/pnyno-1518085886/post";
        String url2 = "https://scrubit.herokuapp.com/api/get-all";
        String url1 = "https://scrubit.herokuapp.com/api/insert";



        URL obj = new URL(url1);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setDoOutput(true);
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");

        OutputStream os = con.getOutputStream();

        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(os, "UTF-8"));
        writer.write(json);

        writer.flush();
        writer.close();
        os.close();


        int responseCode = con.getResponseCode();

        if (responseCode==200) {
            label.setText("Request succeeded");
        }
        else {
            label.setText("Connection unsuccessful, Response: " +  responseCode);
        }
        System.out.println("Repsonse code: " + responseCode);
    }

    private void addProduct() {
        try {
            String name = tf_name.getText();
            String stock = tf_quantity.getText();
            String price = tf_price.getText();
            String url = tf_url.getText();
            String qr = tf_qr.getText();
            String desc=tf_desc.getText();
            Product newProduct = new Product(name,stock,price,qr,url,desc);

            data.add(newProduct);

        } catch(Exception e) {
            label.setText("Invalid Format");
        }
    }

    private void doGetData() throws IOException {
        label.setText("Downloading data...");

        String urlToJson2 = "https://jsonplaceholder.typicode.com/todos";
        String urlToJson = "https://scrubit.herokuapp.com/api/get-all";

        URL obj = new URL(urlToJson);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();

        if (responseCode==200) {
            data.clear();
            label.setText("Request succeeded");
        }
        else {
            label.setText("Connection unsuccessful, Response: " +  responseCode);
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
            response.append("\n");
        }
        System.out.println(response);
        JSONArray jsonObj = new JSONArray(response.toString());

        for (int i=0;i<jsonObj.length();i++) {
            try {
                String id = jsonObj.getJSONObject(i).get("_id").toString();
                String name = jsonObj.getJSONObject(i).get("name").toString();
                String stock = jsonObj.getJSONObject(i).get("quantity").toString();
                String price = jsonObj.getJSONObject(i).get("price").toString();
                String qr = jsonObj.getJSONObject(i).get("QR").toString();
                String desc = jsonObj.getJSONObject(i).get("description").toString();
                String url = jsonObj.getJSONObject(i).get("url").toString();

                Product newProd = new Product(id,name,stock,price,qr,url,desc);
                data.add(newProd);
            } catch (JSONException e1) {
                System.out.println("Some objects did not have all attributes");
            }


        }
        table.setItems(data);
    }

    public static void main(String[] args) {
        launch(args);
    }
}




