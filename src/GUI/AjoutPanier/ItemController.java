
package GUI.AjoutPanier;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import models.Produit;


public class ItemController {
        @FXML
    private Label nameLabel;

    @FXML
    private Label priceLable;

    @FXML
    private ImageView img;

    @FXML
    private Label quantite;
    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(fruit);
    }
    private Produit fruit;
    private MyListener myListener;

    public void setData(Produit fruit, MyListener myListener) {
        this.fruit = fruit;
        this.myListener = myListener;
        nameLabel.setText(fruit.getNom_produit());
        priceLable.setText(fruit.getPrix()+main.CURRENCY);
        quantite.setText(String.valueOf(fruit.getQuantite()));
        String path = fruit.getImage();
        Image aa = new Image("file:" + path);

        img.setImage(aa);
    }
  
}
