
package GUI.panier;
    import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.File;
import java.io.IOException;
import java.net.URL;


public class AjoutP {



    private Parent view;

    public AjoutP() throws IOException {
        URL url = new File("C:\\Users\\sarra\\Documents\\NetBeansProjects\\AppMarket\\src\\GUI\\panier\\FXMLPani.fxml").toURI().toURL();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(url);
        this.view = root;
    }

    public Parent getView(){
        return this.view;
    }
}
