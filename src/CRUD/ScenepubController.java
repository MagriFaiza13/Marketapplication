/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUD;

//import java.net.URL;
//import java.util.ResourceBundle;
//import javafx.fxml.Initializable;


import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ScenepubController implements Initializable {
    
    @FXML
    private Button insert_image;

    @FXML
    private ImageView image_view;

     @FXML
    private TextField id_pub;

    @FXML
    private TextField id_user;

    @FXML
    private TextField nom_pub;

    @FXML
    private ComboBox<?> type;

    @FXML
    private Label file_path;

    @FXML
    private Label picture;

    @FXML
    private Button insert;

    @FXML
    private Button update;

    @FXML
    private Button clear;

    @FXML
    private Button delete;

    @FXML
    private TableView<Publication1> table_view;

    @FXML
    private TableColumn<Publication1, Integer> col_id_pub;

    @FXML
    private TableColumn<Publication1, Integer> col_id_user;

    @FXML
    private TableColumn<Publication1, String> col_nom_pub;

    @FXML
    private TableColumn<Publication1, String> col_type;

    @FXML
    private TableColumn<Publication1, String> col_picture;
    private File file ;


    private AnchorPane left_main;
    
    private String[] combotype = {"status", "photo", "video"};
    
    public void comboBox(){
        
        List<String> list = new ArrayList<>();
        
        for(String pub: combotype){
            
            list.add(pub);
            
        }
        
        ObservableList dataList = FXCollections.observableArrayList(list);
        
        type.setItems(dataList);
        
    }
    
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    
    public Connection connectDb(){
        
        try{
            
//            Class.forName("com.mysql.jdbc.Driver");
            
            connect = DriverManager.getConnection("jdbc:mysql://localhost/base1", "root" , "");
            
            
            
        }catch(SQLException e){
        System.out.println(e.getMessage());
        }
        
//        return null;
        return connect;
    }
    
    public ObservableList<Publication1> pubList(){
        
        connect = connectDb();
        
        ObservableList<Publication1> pubList = FXCollections.observableArrayList();
        
        String sql = "SELECT * FROM publication";
        
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            Publication1 pub;
            
            while(result.next()){
                
                pub = new Publication1(result.getInt("id_pub"), result.getInt("id_user"),
                        result.getString("nom_pub"), result.getString("type"),
                        result.getString("picture"));
                
                pubList.add(pub);
                
            }
            
        }catch(SQLException e){
        System.out.println(e.getMessage());
        }
        
        return pubList;
        
    }
    
    public void showPublication1(){
        ObservableList<Publication1> showList = pubList();
        
        col_id_pub.setCellValueFactory(new PropertyValueFactory<>("id_pub"));
        col_id_user.setCellValueFactory(new PropertyValueFactory<>("id_user"));
        col_nom_pub.setCellValueFactory(new PropertyValueFactory<>("nom_pub"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_picture.setCellValueFactory(new PropertyValueFactory<>("picture"));
        
        table_view.setItems(showList);
        
    }
    
    @FXML
    public void insertImage(){
        
        FileChooser open = new FileChooser();
        
        Stage stage = (Stage)left_main.getScene().getWindow();
        
        File file = open.showOpenDialog(stage);
        
        if(file != null){
            
            String path = file.getAbsolutePath();
            
            path = path.replace("\\", "\\\\");
            
            file_path.setText(path);

            Image image = new Image(file.toURI().toString(), 190, 191, false, true);
            
            image_view.setImage(image);
            
        }else{
            
            System.out.println("NO DATA EXIST!");
            
        }

    }
    
    public void insert(){
        
        connect = connectDb();
//        I HAVE 5 COLUMNS
        String sql = "INSERT INTO `publication`(`id_pub`, `id_user`, `nom_pub`, `type`, `picture`)  VALUES (?,?,?,?,?)";
        
        try{
            
            if(id_pub.getText().isEmpty() | id_user.getText().isEmpty()
                    | nom_pub.getText().isEmpty() 
                    | type.getSelectionModel().isEmpty()
                    | image_view.getImage() == null){
                
                Alert alert = new Alert(AlertType.ERROR);
                
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Enter all blank fields!");
                alert.showAndWait();
                
            }else{
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, id_pub.getText());
                prepare.setString(2, id_user.getText());
                prepare.setString(3, nom_pub.getText());
                prepare.setString(4, (String)type.getSelectionModel().getSelectedItem());
                prepare.setString(5, file_path.getText());
                prepare.executeUpdate();
              System.out.println("puplic ajouté");
                showPublication1();
                clear();
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
    }
    
    public void update(){
        
        connect = connectDb();
        
        String path = file_path.getText();
        
        path = path.replace("\\", "\\\\");
        
        String sql = "UPDATE `publication` SET `id_user` = '" 
                + id_user.getText() + "', `nom_pub` = '" 
                + nom_pub.getText() + "', `type` = '" 
                + type.getSelectionModel().getSelectedItem() 
                + "', `picture` = '" + path 
                + "' WHERE id_pub = '" + id_pub.getText() + "'";
        
        try{
            
            if(id_pub.getText().isEmpty() | id_user.getText().isEmpty()
                    | nom_pub.getText().isEmpty() 
                    | type.getSelectionModel().isEmpty()
                    | image_view.getImage() == null){
                
                Alert alert = new Alert(AlertType.ERROR);
                
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Enter all blank fields!");
                alert.showAndWait();
                
            }else{
            
                statement = connect.createStatement();
                statement.executeUpdate(sql);

                Alert alert = new Alert(AlertType.INFORMATION);

                alert.setTitle("MarcoMan Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Update the data!");
                alert.showAndWait();

                showPublication1();
                clear();
                
            }
        }catch(SQLException e){
        System.out.println(e.getMessage());
        }
        
    }
    
    public void delete(){
        
        String sql = "DELETE from publication WHERE `id_pub` = '" + id_pub.getText() + "'";
        
        connect = connectDb();
        
        try{
            
            Alert alert = new Alert(AlertType.CONFIRMATION);
            
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure that you want to delete it?");
            
            Optional<ButtonType> buttonType = alert.showAndWait();
            
            if(buttonType.get() == ButtonType.OK){
            
            statement = connect.createStatement();
            statement.executeUpdate(sql);
                
            }else{
                
                return;
                
            }
            
            showPublication1();
            clear();
            
        }catch(SQLException e){
        System.out.println(e.getMessage());}
        
    }

//    public void print(){
//        
//        connect = connectDb();
//        
//        try{
//            JasperDesign jDesign = JRXmlLoader.load("F:\\ajava\\6 NUMBER\\CRUD\\src\\crud\\report.jrxml");
//        
//            JasperReport jReport = JasperCompileManager.compileReport(jDesign);
//            
//            JasperPrint jPrint = JasperFillManager.fillReport(jReport, null, connect);
//            
//            JasperViewer viewer = new JasperViewer(jPrint, false);
//            
//            viewer.setTitle("MarcoMan Report");
//            viewer.show();
//            
//        }catch(Exception e){}
//    }
    
    public void selectPublication1(){
        
        Publication1 pub = table_view.getSelectionModel().getSelectedItem();
        
        int num = table_view.getSelectionModel().getSelectedIndex();
        
        if((num-1) < -1)
            return;
        
        id_pub.setText(String.valueOf(pub.getId_pub()));
        id_user.setText(String.valueOf(pub.getId_user()));
        nom_pub.setText(pub.getNom_pub());
        type.getSelectionModel().clearSelection();
        
        String picture ="file:" +  pub.getPicture();
        
        Image image = new Image(picture, 190, 191, false, true);
        
        image_view.setImage(image);
        
        String path = pub.getPicture();
        
        file_path.setText(path);
        
    }
    
    public void clear(){
        
        id_pub.setText("");
        id_user.setText("");
        nom_pub.setText("");
        type.getSelectionModel().clearSelection();
        image_view.setImage(null);
        
    }
    
    public void textfieldDesign(){
        
        if(id_pub.isFocused()){
            
            id_pub.setStyle("-fx-border-width:2px; -fx-background-color:#fff");
            id_pub.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
            nom_pub.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
            type.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
            
        }else if(id_user.isFocused()){
            
            id_pub.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
            id_user.setStyle("-fx-border-width:2px; -fx-background-color:#fff");
            nom_pub.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
            type.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
            
        }else if(nom_pub.isFocused()){
            
            id_pub.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
            id_user.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
            nom_pub.setStyle("-fx-border-width:2px; -fx-background-color:#fff");
            type.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
            
        }else if(type.isFocused()){
            
            id_pub.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
            id_user.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
            nom_pub.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
            type.setStyle("-fx-border-width:2px; -fx-background-color:#fff");
            
        }
        
    }
    
    public void defaultId(){
        
        id_pub.setStyle("-fx-border-width:2px; -fx-background-color:#fff");
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
          
        comboBox();
        
        defaultId();
        
        showPublication1();
    
    }
    @FXML
    private void choosefile(javafx.scene.input.MouseEvent event) {
        Node source = (Node) event.getSource();
        Stage dialogStage = (Stage) source.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", ".jpg", ".png"));
        file = fileChooser.showOpenDialog(dialogStage);
         if(file!=null){
            String path = file.getAbsolutePath();
            path = path.replace("\\", "\\\\");
            file_path.setText(path);
            Image image = new Image(file.toURI().toString(),110,110,false,true);
            image_view.setImage(image);
        }else{
            System.out.print("no publication exist !");
    }
    }
}

