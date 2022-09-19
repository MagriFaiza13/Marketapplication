/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import models.Produit;

/**
 * FXML Controller class
 *
 * @author sarra
 */
public class ItemController implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLable;
    @FXML
    private ImageView img;
    @FXML
    private Label quantite;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    private Produit p;
    private Mylistenner myListener;

    @FXML
    private void click(MouseEvent event) {
        myListener.onClickListener(p);
    }

    public void setData(Produit p, Mylistenner myListener) {
        this.p = p;
        this.myListener = myListener;
        nameLabel.setText(p.getNom_Produit());
        priceLable.setText(p.getPrix() + "TND");
        //  quantite.setText(String.valueOf(p.getStock_ticket()));
        String path = p.getImage();
        Image aa = new Image("file:" + path);

        img.setImage(aa);
    }

}
