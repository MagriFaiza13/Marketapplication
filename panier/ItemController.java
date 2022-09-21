package GUI.panier;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import Interface.MyListener;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import models.Produit;
import utils.Statics;

public class ItemController {
    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLable;

    @FXML
    private ImageView img;
    @FXML
    private Label ddlabel;
    @FXML
    private Label dflabel;
    @FXML
    private Label idevtitm;
    @FXML
    private Button addpanier;

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(event);
    }

    private Produit event;
    private MyListener myListener;
    
    public void setData(Produit event, MyListener myListener) {
        this.event = event;
        this.myListener = myListener;
        nameLabel.setText(event.getNom_produit());
        priceLable.setText(String.valueOf(event.getReference()));
        idevtitm.setText(String.valueOf(event.getId_produit()));
        ddlabel.setText(String.valueOf(event.getCategorie()));
      dflabel.setText(String.valueOf(event.getQuantite()));
     //dd.setValue(LocalDate.parse(event.getDate_debut().toString()));
        String path = event.getImage();
        Image aa = new Image("file:" + path);
        // System.out.println(image);
        img.setImage(aa);
    }

    @FXML
    private void addpanier(ActionEvent event) {
         
        
    }

}
