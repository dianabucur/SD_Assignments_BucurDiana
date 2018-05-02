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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EnrollUserController implements Initializable {
    private String user;

    @FXML
    private ComboBox tours;

    @FXML
    private Button enroll;

    @FXML
    private Button back;

    @FXML
    private TextField searchtext;

    @FXML
    private Button searchbutton;

    public void setUser(String user){
        this.user = user;
        EnrollBLL e = new EnrollBLL();
        TournamentBLL t = new TournamentBLL();
        ArrayList<String> alltours = t.getAllTours();
        for(String tour: alltours){
            tours.getItems().add(tour);
        }
    }

    public void search(javafx.event.ActionEvent event) throws IOException {
        String text = searchtext.getText();
        TournamentBLL t = new TournamentBLL();
        tours.getItems().clear();
        ArrayList<String> alltours = t.getSearched(text);
        for(String tour: alltours){
            tours.getItems().add(tour);
        }
    }

    public void enroll(javafx.event.ActionEvent event) throws IOException {
        try {
            String tour = tours.getSelectionModel().getSelectedItem().toString();
            EnrollBLL enrollBLL = new EnrollBLL();
            if (enrollBLL.getPlayers(tour).size() < 8) {
                int valid = enrollBLL.enrollPlayer(user, tour);
                if (valid == -1) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Enroll error");
                    alert.setContentText("User already enrolled!");
                    alert.showAndWait();
                }
                else{
                    UserBLL u = new UserBLL();
                    TournamentBLL t = new TournamentBLL();
                    AccountBLL a = new AccountBLL();
                    int userbal = a.getUserBalance(u.getIdByMail(user));
                    int tourfee = t.getFee(tour);
                    if(userbal >= tourfee){
                        a.updateEnroll(user, tour, tourfee);
                    }
                    else{
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("You do not have enough money.");
                        alert.showAndWait();
                    }

                }
            } else if (enrollBLL.getPlayers(tour).size() == 8) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Tournament is full!");
                alert.showAndWait();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Enroll error");
            alert.setContentText("Please select both a player and a tournament !");
            alert.showAndWait();
        }
    }

    public void back(javafx.event.ActionEvent event) throws IOException {
        /*Parent root2 = FXMLLoader.load(getClass().getResource("/playerlogin.fxml"));
        Scene scene = new Scene(root2, 630, 430);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();*/

        FXMLLoader playFrame = new FXMLLoader(getClass().getResource("/playerlogin.fxml"));
        Parent p = (Parent) playFrame.load();
        PlayerController theController = playFrame.getController();
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
