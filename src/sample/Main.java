package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
    String sysPassword;

    private Button getButton;
    private Button setButton;
    private Button exitButton;
    private Button updateSingleButton;
    private Button addButton;
    private Button deleteButton;

    private Label label;
    private StackPane layout;
    private TableView table;
    private TextField tf_name;
    private TextField tf_quantity;
    private TextField tf_price;
    private TextField tf_url;
    private TextField tf_desc;
    private TextField tf_qr;
    private PasswordField tf_password;

    private Integer idWidth = 170;
    private Integer nameWidth = 150;
    private Integer quantityWidth = 60;
    private Integer priceWidth = 50;
    private Integer urlWidth = 300;
    private Integer descWidth = 200;
    private Integer qrWidth = 150;
    private Integer totalWidth = 4+idWidth+nameWidth+quantityWidth+priceWidth+urlWidth+descWidth+qrWidth;

    private ObservableList<Product> data =
            FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {
        sysPassword = "hej";
        primaryStage.setTitle("scrubIT admin");

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
        tc_name.setOnEditCommit(t -> {
            if (checkPassword()) {
                try {
                    (t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setName(t.getNewValue());
                    doUpdateRow(table.getSelectionModel().getSelectedIndex());
                    doGetData();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
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
                new PropertyValueFactory<>("Price")
        );
        tc_price.setCellFactory(TextFieldTableCell.forTableColumn());
        tc_price.setOnEditCommit(t -> {
            try {
                int intTest = Integer.parseInt(t.getNewValue());
                (t.getTableView().getItems().get(t.getTablePosition().getRow())).setQuantity(t.getNewValue());
                if (checkPassword()) {
                    try {
                        (t.getTableView().getItems().get(t.getTablePosition().getRow())).setPrice(t.getNewValue());
                        doUpdateRow(table.getSelectionModel().getSelectedIndex());
                        doGetData();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            } catch (NumberFormatException e) {
                label.setText("Price has to be integer");
            }
        });

        TableColumn<Product, String> tc_quantity = new TableColumn<>("Quantity");
        tc_quantity.setPrefWidth(quantityWidth);
        tc_quantity.setCellValueFactory(
                new PropertyValueFactory<>("Quantity")
        );
        tc_quantity.setCellFactory(TextFieldTableCell.forTableColumn());
        tc_quantity.setOnEditCommit(t -> {
            try {
                int intTest = Integer.parseInt(t.getNewValue());
                (t.getTableView().getItems().get(t.getTablePosition().getRow())
                ).setQuantity(t.getNewValue());
                if (checkPassword()) {
                    try {
                        (t.getTableView().getItems().get(t.getTablePosition().getRow())).setQuantity(t.getNewValue());
                        doUpdateRow(table.getSelectionModel().getSelectedIndex());
                        doGetData();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            } catch (NumberFormatException e) {
                label.setText("Quantity has to be integer");
            }
        });

        TableColumn<Product, String> tc_desc = new TableColumn<>("Desc");
        tc_desc.setPrefWidth(descWidth);
        tc_desc.setCellValueFactory(
                new PropertyValueFactory<>("Desc")
        );
        tc_desc.setCellFactory(TextFieldTableCell.forTableColumn());
        tc_desc.setOnEditCommit(t -> {
            if (checkPassword()) {
                try {
                    (t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setDesc(t.getNewValue());
                    doUpdateRow(table.getSelectionModel().getSelectedIndex());
                    doGetData();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        TableColumn<Product, String> tc_qr = new TableColumn<>("Qr-Code");
        tc_qr.setPrefWidth(qrWidth);
        tc_qr.setCellValueFactory(
                new PropertyValueFactory<>("qr")
        );
        tc_qr.setCellFactory(TextFieldTableCell.forTableColumn());
        tc_qr.setOnEditCommit(t -> {
            try {
                int intTest = Integer.parseInt(t.getNewValue());
                (t.getTableView().getItems().get(t.getTablePosition().getRow())
                ).setQuantity(t.getNewValue());
                if (checkPassword()) {
                    try {
                        (t.getTableView().getItems().get(t.getTablePosition().getRow())).setQr(t.getNewValue());
                        doUpdateRow(table.getSelectionModel().getSelectedIndex());
                        doGetData();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            } catch (NumberFormatException e) {
                label.setText("QR has to be integer");
            }
        });

        TableColumn<Product, String> tc_url = new TableColumn<>("Url");
        tc_url.setPrefWidth(urlWidth);
        tc_url.setCellValueFactory(
                new PropertyValueFactory<>("Url")
        );
        tc_url.setCellFactory(TextFieldTableCell.forTableColumn());
        tc_url.setOnEditCommit(t -> {
            if (checkPassword()) {
                try {
                    (t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setUrl(t.getNewValue());
                    doUpdateRow(table.getSelectionModel().getSelectedIndex());
                    doGetData();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
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
        getButton.setText("Refresh");
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
                System.out.println("none");
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
        tf_password.setTranslateY(setButton.getTranslateY());
        tf_password.setTranslateX(setButton.getTranslateX());
        tf_password.setPromptText("password");

        deleteButton = new Button();
        deleteButton.setText("Remove item");
        deleteButton.setMinWidth(setButton.getMinWidth());
        deleteButton.setTranslateX(exitButton.getTranslateX());
        deleteButton.setTranslateY(setButton.getTranslateY()-26);
        deleteButton.setOnAction(e -> {
            Product selectedItem = table.getSelectionModel().getSelectedItem();
            if (selectedItem==null) {
                label.setText("Choose row");
            }
            else if (checkPassword()) {
                try {
                    doDeleteRow(table.getSelectionModel().getSelectedIndex());
                    doGetData();
                } catch (IOException e1) {
                    e1.printStackTrace();
                };
            }
        });

        addButton = new Button();
        addButton.setText("Add item");
        addButton.setMinWidth(getButton.getMinWidth());
        addButton.setTranslateX(getButton.getTranslateX());
        addButton.setTranslateY(getButton.getTranslateY()-26);
        addButton.setOnAction(e -> {
            if (checkPassword()) {
                try {
                    doInsertOneRow();
                    doGetData();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
          });

        updateSingleButton = new Button();
        updateSingleButton.setText("Update item");
        updateSingleButton.setMinWidth(exitButton.getMinWidth());

        updateSingleButton.setTranslateX(setButton.getTranslateX());
        updateSingleButton.setTranslateY(addButton.getTranslateY());
        updateSingleButton.setOnAction((ActionEvent e) -> {
            Product selectedItem = table.getSelectionModel().getSelectedItem();
            if (selectedItem==null) {
                label.setText("Choose row");
            }
            else if (checkPassword()) {
                 try {
                    doUpdateRow(table.getSelectionModel().getSelectedIndex());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        layout = new StackPane();
        layout.getChildren().addAll(updateSingleButton, deleteButton, addButton,tf_name,tf_price,tf_quantity,tf_desc,tf_url,tf_qr,getButton,exitButton,label,table,tf_password);
        layout.setAlignment(Pos.CENTER);
        Scene root = new Scene(layout, totalWidth, 700);
        primaryStage.setScene(root);
        primaryStage.setResizable(false);
        primaryStage.show();
        try {
            doGetData();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private boolean checkPassword() {
        if (tf_password.getText().equals(sysPassword)) {
            System.out.println("Password accepted");
            return true;
        }
        label.setText("Password incorrect");
        System.out.println("Password rejected");
        return false;
    }

    private void connectToUrlSendObj(String url, String stringToSend,String Method) throws IOException  {
        URL conUrl = new URL(url);
        HttpURLConnection con = (HttpURLConnection) conUrl.openConnection();
        con.setDoOutput(true);
        con.setRequestMethod(Method);
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        OutputStream os = con.getOutputStream();
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(os, "UTF-8"));
        writer.write(stringToSend);
        writer.flush();
        writer.close();
        os.close();
        con.connect();

        int responseCode = con.getResponseCode();
        if (responseCode==200 || responseCode==201) {
            label.setText("Request succeeded");
        }
        else {
            label.setText("Connection unsuccessful, Response: " +  responseCode);
        }
    }

    private void doInsertOneRow() throws IOException {
        try {
            int stockInt = Integer.parseInt(tf_qr.getText());
            int priceInt = Integer.parseInt(tf_price.getText());
            int qrInt = Integer.parseInt(tf_qr.getText());
            String stringToSend = "qr=" + tf_qr.getText() + "&name=" + tf_name.getText() + "&description=" + tf_desc.getText() +
                    "&price=" + tf_price.getText() + "&quantity=" + tf_quantity.getText() + "&url=" + tf_url.getText();
            String url = "http://scrubit.herokuapp.com/api/insert";
            connectToUrlSendObj(url, stringToSend,"POST");
        } catch (NumberFormatException e) {
            label.setText("Price, Stock and Qr has to be integer");
            System.out.println("Price, Stock and Qr has to be integer");
        }
    }

    private void doDeleteRow(Integer rowNumber) throws IOException {
        String stringToSend = "_id=" + data.get(rowNumber).getId();
        String testUrl = "http://ptsv2.com/t/020s2-1519735531/post";
        String url = "http://scrubit.herokuapp.com/api/delete";
        connectToUrlSendObj(url,stringToSend,"POST");
    }

    private void doUpdateRow(Integer rowNumber) throws IOException  {
        String stringToSend = "_id=" + data.get(rowNumber).getId() + "&qr=" + data.get(rowNumber).getQr() + "&name=" + data.get(rowNumber).getName() + "&description=" +
                data.get(rowNumber).getDesc() + "&price=" + data.get(rowNumber).getPrice() + "&quantity=" +
                data.get(rowNumber).getQuantity() + "&url=" + data.get(rowNumber).getUrl();
        String url = "http://scrubit.herokuapp.com/api/update";
        connectToUrlSendObj(url,stringToSend,"POST");
    }

    private void doGetData() throws IOException {
        System.out.println("Fetching from DB");
        String urlToJson = "http://scrubit.herokuapp.com/api/get-all";
        URL obj = new URL(urlToJson);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        if (responseCode==200) {
            data.clear();
            //label.setText("Request succeeded");
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
        JSONArray jsonObj = new JSONArray(response.toString());

        for (int i=0;i<jsonObj.length();i++) {
            try {
                String id = jsonObj.getJSONObject(i).get("_id").toString();
                String name = jsonObj.getJSONObject(i).get("name").toString();
                String stock = jsonObj.getJSONObject(i).get("quantity").toString();
                String price = jsonObj.getJSONObject(i).get("price").toString();
                String qr = jsonObj.getJSONObject(i).get("qr").toString();
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




