package ro.utcluj.sd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PlayerController implements Initializable {
    private String user;


    private String tour;
    @FXML
    private Button view;
    @FXML
    private Button play;
    @FXML
    private Button enroll;
    @FXML
    private ComboBox select;


    public void setUser(String user){
        this.user = user;
        EnrollBLL e = new EnrollBLL();
        ArrayList<String> tours= e.showTours(this.user);
        for(String player: tours){
            select.getItems().add(player);
        }
    }

    public void viewTours(ActionEvent event) throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("/viewtours.fxml"));
        Scene scene = new Scene(root2, 630, 430);
        Stage window = new Stage();
        window.setScene(scene);
        window.show();
    }

    public void play(ActionEvent event) throws IOException{
        tour = select.getSelectionModel().getSelectedItem().toString();
        EnrollBLL e = new EnrollBLL();
        if(e.getPlayers(tour).size() == 8) {
            FXMLLoader playFrame = new FXMLLoader(getClass().getResource("/play.fxml"));
            Parent p = (Parent) playFrame.load();
            PlayController theController = playFrame.getController();
            theController.setData(tour, user);

            Scene scene = new Scene(p, 871, 567);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Cannot Play");
            alert.setContentText("There are not enough players enrolled at this tournament !");
            alert.showAndWait();
        }
    }

    public void enroll(ActionEvent event) throws IOException {
        FXMLLoader enrollFrame = new FXMLLoader(getClass().getResource("/enroll2user.fxml"));
        Parent p = (Parent) enrollFrame.load();
        EnrollUserController theController = enrollFrame.getController();
        theController.setUser(user);

        Scene scene = new Scene(p, 630, 430);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
