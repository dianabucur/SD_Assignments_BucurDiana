package ro.utcluj.sd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ManageTourController implements Initializable {
    @FXML
    private Button add;
    @FXML
    private Button delete;
    @FXML
    private Button update;
    @FXML
    private TextField name;
    @FXML
    private ComboBox level;
    @FXML
    private ComboBox tours;
    @FXML
    private Button back;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        level.getItems().addAll("Begginer", "Intermediate", "Advanced");
        TournamentBLL t = new TournamentBLL();
        ArrayList<String> alltours = t.getAllTours();
        for(String tour: alltours){
            tours.getItems().add(tour);
        }

    }

    public void addTour(ActionEvent event) throws IOException {
        String nume = name.getText();
        String lev = level.getSelectionModel().getSelectedItem().toString();

        TournamentBLL t = new TournamentBLL();
        t.addTour(nume, lev);
    }

    public void updateTour(ActionEvent event) throws IOException {
        String nume = name.getText();
        String lev = level.getSelectionModel().getSelectedItem().toString();
        String sel = tours.getSelectionModel().getSelectedItem().toString();

        TournamentBLL t = new TournamentBLL();

        if(!sel.isEmpty()) {
            if (!nume.isEmpty()) {
                t.updateName(nume, sel);
            }
            if (!lev.isEmpty()) {
                t.updateLevel(lev, sel);
            }
        }


    }
    public void deleteTour(ActionEvent event) throws IOException {
        String lev = level.getSelectionModel().getSelectedItem().toString();
        String sel = tours.getSelectionModel().getSelectedItem().toString();
        TournamentBLL t = new TournamentBLL();
        t.deleteTour(sel,lev);
    }

    public void back(javafx.event.ActionEvent event) throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("/adminlogin.fxml"));
        Scene scene = new Scene(root2, 630, 430);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }



}
