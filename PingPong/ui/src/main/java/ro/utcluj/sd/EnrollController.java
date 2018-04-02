package ro.utcluj.sd;

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

public class EnrollController implements Initializable{

    @FXML
    private ComboBox players;

    @FXML
    private ComboBox tours;

    @FXML
    private Button enroll;

    @FXML
    private Button back;

    public void enrollplayers(javafx.event.ActionEvent event) throws Exception {
        try {
            String mail = players.getSelectionModel().getSelectedItem().toString();
            String tour = tours.getSelectionModel().getSelectedItem().toString();
            EnrollBLL enrollBLL = new EnrollBLL();
            if (enrollBLL.getPlayers(tour).size() < 8) {
                int valid = enrollBLL.enrollPlayer(mail, tour);
                if (valid == -1) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Enroll error");
                    alert.setContentText("User already enrolled!");
                    alert.showAndWait();
                }
            }
            else if(enrollBLL.getPlayers(tour).size() == 8 ){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Tournament is full!");
                alert.showAndWait();
            }
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Enroll error");
            alert.setContentText("Please select both a player and a tournament !");
            alert.showAndWait();
        }
    }

    public void back(javafx.event.ActionEvent event) throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("/adminlogin.fxml"));
        Scene scene = new Scene(root2, 630, 430);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserBLL b = new UserBLL();
        ArrayList<String> allusers = b.getAllPlayers();
        for(String player: allusers){
            players.getItems().add(player);
        }
        TournamentBLL t = new TournamentBLL();
        ArrayList<String> alltours = t.getAllTours();
        for(String tour: alltours){
            tours.getItems().add(tour);
        }
    }
}
