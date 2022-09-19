/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.acceuil;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import static sun.audio.AudioPlayer.player;
import utils.MaConnexion;


public class FXMLacceuilController implements Initializable {

    @FXML
    private Button IDsignin;
    @FXML
    private Label IDlabel;
    @FXML
    private Button IDsignout;
    @FXML
    private Button IDprofil;
    @FXML
    private Button IDacceuil;
    @FXML
    private Button IDbonplans;
    @FXML
    private Button IDevennements;
    @FXML
    private Button IDmateriels;
    @FXML
    private Button IDaboutus;
    @FXML
    private Circle c1;
    @FXML
    private Circle c2;
    @FXML
    private Circle c3;
    @FXML
    private Circle c4;
    @FXML
    private Button idbtnacceuil;
    @FXML
    private StackPane parentContainer;
    @FXML
    private AnchorPane anchorRoot;
    @FXML
    private MediaView mediaview;

    private File file;
    private Media media;
    private MediaPlayer mediaPlayer;
    private static final String mediaurl = "sss.mp4";
    @FXML
    private ImageView idimage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if (MaConnexion.getInstance().getCnx()== null) {
            IDlabel.setVisible(false);
            IDsignin.setVisible(true);
            IDprofil.setVisible(false);
            IDsignout.setVisible(false);

        } else {

            IDsignout.setVisible(true);
            IDaboutus.setVisible(true);
            IDmateriels.setVisible(true);
            IDbonplans.setVisible(true);
            IDprofil.setVisible(true);
            IDsignin.setVisible(false);

            IDlabel.setText("welcome " + MaConnexion.getInstance().getCnx());

        }
        setRotate(c2, true, 360, 10);
        setRotate(c3, true, 180, 18);
        setRotate(c4, true, 145, 24);
        setRotate(c1, true, 360, 10);

        //file = new File("video.mp4");
        //media = new Media(file.toURI().toString());
        //MediaPlayer  = new MediaPlayer(media);
        //MediaPlayer.play();
        mediaPlayer = new MediaPlayer(new Media(this.getClass().getResource(mediaurl).toExternalForm()));
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setVolume(50);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaview.setMediaPlayer(mediaPlayer);

        TranslateTransition translate = new TranslateTransition();
        translate.setNode(idimage);
        translate.setDuration(Duration.millis(5000));
        translate.setCycleCount(TranslateTransition.INDEFINITE);
        translate.setByX(150);
        translate.setAutoReverse(true);
        translate.play();

    }

    @FXML
    private void Signin(ActionEvent event) throws IOException {
        Stage stage = (Stage) IDsignin.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("../../GUI.login/FXMLlogin.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Login ");
        stage.getIcons().add(new Image("GUI.dashboardadmin/Untitled design (2).png"));
        stage.show();

    }

    @FXML
    private void Signout(ActionEvent event) throws IOException {
        MaConnexion.getInstance().getCnx();
        Stage stage = (Stage) IDsignout.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("../../GUI.acceuil/FXMLacceuil.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Acceuil ");
        stage.getIcons().add(new Image("GUI.dashboardadmin/Untitled design (2).png"));

        stage.show();
    }

  

    private void setRotate(Circle c, boolean reverse, int angle, int duration) {
        RotateTransition rt = new RotateTransition(Duration.seconds(duration), c);
        rt.setAutoReverse(reverse);
        rt.setByAngle(angle);
        rt.setDelay(Duration.seconds(0));
        rt.setRate(3);
        rt.setCycleCount(18);
        rt.setAxis(Rotate.Z_AXIS);
        rt.play();
    }

    @FXML
    private void loadnext(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../GUI.acceuil/FXMLscene1.fxml"));
        
        Scene scene = idbtnacceuil.getScene();

        root.translateXProperty().set(scene.getHeight());
        
        parentContainer.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_BOTH);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(event1 -> {
            parentContainer.getChildren().remove(anchorRoot);
        });
        timeline.play();

    }

    @FXML
    private void enterevent(MouseEvent event) {

    }

}
