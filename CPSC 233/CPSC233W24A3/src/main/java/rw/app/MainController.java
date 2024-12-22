package rw.app;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import rw.battle.Battle;
import rw.battle.Entity;
import rw.battle.Maximal;
import rw.battle.PredaCon;
import rw.enums.WeaponType;
import rw.util.Reader;
import rw.util.Writer;
import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.layout.GridPane;


public class MainController implements Initializable {
    //Store the data of editor
    private Battle battle;

    /**
     * Set up the window state
     */  @FXML
    public void initialize() {

    }
    @FXML
    private TextField delrow;
    @FXML
    private TextField delcol;
    @FXML
    private Button delete;
    @FXML
    private TextArea detail;
    @FXML
    private MenuItem about;
    @FXML
    private MenuItem quit;
    @FXML
    private MenuItem saver;
    @FXML
    private MenuItem load;

    @FXML
    private GridPane mygrid;
    @FXML
    private TextField maxrow;
    @FXML
    private TextField maxcol;
    @FXML
    private TextField predcol;
    @FXML
    private TextField predrow;
    @FXML
    private TextArea mytext;
    @FXML
    private TextField myRows;
    @FXML
    private TextField myColumns;
    @FXML
    private Button create;
    @FXML
    private RadioButton button3;
    @FXML
    private RadioButton button2;
    @FXML
    private TextField name1;
    @FXML
    private TextField name2;
    @FXML
    private TextField symbol1;
    @FXML
    private TextField symbol2;
    @FXML
    private TextField armor2;
    @FXML
    private ChoiceBox<String> weapon1;
    @FXML
    private TextField weapon2;
    private String[] weapons = {"Teeth", "Claw", "Laser"};
    @FXML
    private TextField health1;
    @FXML
    private TextField health2;
    int rows;
    int cols;
    char symbol_1;
    String name_1;
    int health_1;
    String symbol_2;
    String name_2;
    int health_2;
    int armor_2;
    String weapon_1;
    int weapon_2;
    int predRow;
    int predCol;
    int maxCol;
    int maxRow;
    public void detailFloor(Button button){
        button.setOnMouseEntered(e -> {
            detail.setText("Floor");
        });
        // Clear details on mouse exit
        button.setOnMouseExited(e -> detail.clear());
    }

    public void  createGrid(int rows ,int cols){
        // creates a grid of buttons
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                Button button = new Button(" . ");
                mygrid.add(button, j, i);
                detailFloor(button);
            }
        }
    }

    // submit cors to create button
    public void submit(ActionEvent event) {
        rows = Integer.parseInt(myRows.getText());
        cols = Integer.parseInt(myColumns.getText());
        System.out.println("1");;
        battle = new Battle(rows,cols);
        String message = battle.battleString();
        mytext.setText(message);
        mygrid.getChildren().clear();
        createGrid(rows,cols);

    }

    // submit 2 cors to 1 radio button
    public void submit2(ActionEvent event) {
        symbol_1 = symbol1.getText().charAt(0);
        name_1 = name1.getText();
        health_1 = Integer.parseInt(health1.getText());
        weapon_1 = weapon1.getValue();
        predCol= Integer.parseInt(predcol.getText());
        predRow= Integer.parseInt(predrow.getText());
        WeaponType weaponType =null;
        System.out.println(symbol_1);
        System.out.println(weapon_1);
        if(weapon_1.equals( "Teeth")){
            weaponType= WeaponType.TEETH;
        }
        if(weapon_1.equals( "Laser")){
            weaponType= WeaponType.LASER;
        }
        if(weapon_1.equals( "Claw")){
            weaponType= WeaponType.CLAWS;
        }
        Entity preda = new PredaCon(symbol_1,name_1,health_1,weaponType);
        battle.addEntity(predRow,predCol,preda);
        String message = this.battle.battleString();
        mytext.setText(message);
        mygrid.getChildren().clear();
        int column =this.battle.getColumns();
        int rowss = this.battle.getRows();
        for (int i = 1; i < rowss+1; i++) {
            for (int j = 1; j < column + 1; j++) {
                // Add placeholder nodes, you can add any type of node you need here
                Entity entity = this.battle.getEntity(i - 1, j - 1);
                if (entity != null) {
                    char symbol = entity.getSymbol();
                    String myString = String.valueOf(symbol);
                    Button button = new Button(myString);
                    mygrid.add(button, j, i);
                    button.setOnMouseEntered(e -> {
                        detail.setText("Symbol: " + myString + "\n" +
                                "Name: " + name_1 + "\n" +
                                "Health: " + health_1 + "\n" +
                                "Weapon: " + weapon_1 + "\n");
                    });
                    // Clear details on mouse exit
                    button.setOnMouseExited(e -> detail.clear());
                } else {
                    Button button = new Button(" . ");
                    mygrid.add(button, j, i);
                    detailFloor(button);
                }
            }
        }

    }

    //submit3 cors to 2 radio button
    public void submit3(ActionEvent event) {
        char symbol_2 = symbol2.getText().charAt(0);
        name_2 = name2.getText();
        health_2 = Integer.parseInt(health2.getText());
        armor_2 = Integer.parseInt(armor2.getText());
        weapon_2 = Integer.parseInt(weapon2.getText());
        maxCol= Integer.parseInt(maxcol.getText());
        maxRow= Integer.parseInt(maxrow.getText());
        Entity maxi = new Maximal(symbol_2,name_2,health_1,weapon_2,armor_2);
        battle.addEntity(maxCol,maxRow,maxi);
        String message = battle.battleString();
        mytext.setText(message);
        mygrid.getChildren().clear();
        int column =this.battle.getColumns();
        int rowss = this.battle.getRows();
        for (int i = 1; i < rowss+1; i++) {
            for (int j = 1; j < column + 1; j++) {
                // Add placeholder nodes, you can add any type of node you need here
                Entity entity = this.battle.getEntity(i - 1, j - 1);
                if (entity != null) {
                    char symbol = entity.getSymbol();
                    String myString = String.valueOf(symbol);
                    Button button = new Button(myString);
                    mygrid.add(button, j, i);
                    button.setOnMouseEntered(e -> {
                        detail.setText("Symbol: " + myString + "\n" +
                                "Name: " + name_2 + "\n" +
                                "Health: " + health_2 + "\n" +
                                "Weapon: " + weapon_2 + "\n" +
                                "Armor: " + armor_2);
                    });
                    // Clear details on mouse exit
                    button.setOnMouseExited(e -> detail.clear());
                } else {
                    Button button = new Button(" . ");
                    mygrid.add(button, j, i);
                    detailFloor(button);
                }

            }
        }

    }
    public void loadFiles(ActionEvent event){
        mygrid.getChildren().clear();
        File file = new FileChooser().showOpenDialog(load.getParentPopup().getScene().getWindow());
        if (file != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
            } catch (Exception e) {
                System.out.println("error opening file");
            }
        }
        Battle battle = Reader.loadBattle(file);
        // setting the loaded battle as this this battle
        this.battle=battle;
        int column =this.battle.getColumns();
        int rowss = battle.getRows();
        String message = battle.battleString();
        mytext.setText(message);
        for (int i = 1; i < rowss + 1; i++) {
            for (int j = 1; j < column + 1; j++) {
                Entity entity = battle.getEntity(i-1, j-1);
                if(entity != null) {
                    char symbol = entity.getSymbol();
                    String myString = String.valueOf(symbol);
                    Button button = new Button(myString);
                    mygrid.add(button, j, i);
                    button.setOnMouseEntered(e -> {
                        detail.setText(myString);
                    });
                    // Clear details on mouse exit
                    button.setOnMouseExited(e -> detail.clear());
                }
                else {
                    Button button = new Button(" . ");
                    mygrid.add(button, j, i);
                    detailFloor(button);
                }
            }
        }


    }
    public void savefile(ActionEvent event){
        File file = new FileChooser().showOpenDialog(load.getParentPopup().getScene().getWindow());
        Writer.createBattle(file,rows,cols, predRow,predCol,maxRow,maxCol,symbol_1,name_1,health_1,weapon_1,symbol_2,name_2,health_1,weapon_2,armor_2);


    }
    // quiting program
    public void quitprogram(ActionEvent event){
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        stage.close();
    }
    public void aboutaction(ActionEvent event){
        Stage aboutStage = new Stage();
        aboutStage.setTitle("About");
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        Label label = new Label("Author : Malik Zaman\n"+
                "Email: zamanshafqat121@gmai.com\n" +
                "Version: v1.0\n" +
                "Program: Robot War map Editor\n");
        vbox.getChildren().add(label);
        Scene scene = new Scene(vbox, 300, 200);
        aboutStage.setScene(scene);
        aboutStage.show();

    }
    public void entitydelete(ActionEvent event){
        int Row = Integer.parseInt(delrow.getText());
        int Col = Integer.parseInt(delcol.getText());
        Entity entityNull = null;
        battle.addEntity(Row,Col,entityNull);
        String message = battle.battleString();
        mytext.setText(message);
        mygrid.getChildren().clear();
        int row123 = battle.getRows();
        int col123 = battle.getColumns();
        for (int i = 1; i < row123+1; i++) {
            for (int j = 1; j < col123+1; j++) {
                Entity entity = this.battle.getEntity(i-1,j-1);
                if(entity!=null) {
                    char symbol = entity.getSymbol();
                    String myString = String.valueOf(symbol);
                    Button button = new Button(myString);
                    mygrid.add(button, j, i);
                    button.setOnMouseEntered(e -> {
                        detail.setText("Symbol: "+myString+"\n"+
                                "Name: " +name_2+"\n"+
                                "Health: "+ health_2+"\n"+
                                "Weapon: "+weapon_2+"\n"+
                                "Armor: "+armor_2);
                    });
                    // Clear details on mouse exit
                    button.setOnMouseExited(e -> detail.clear());
                }
                else{
                    Button button = new Button(" . ");
                    mygrid.add(button, j, i);
                    detailFloor(button);
                }
            }
        }
    }

    /// implementing the choice box
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        weapon1.getItems().addAll(weapons);
        weapon1.setOnAction(this::submit2);
    }


}
