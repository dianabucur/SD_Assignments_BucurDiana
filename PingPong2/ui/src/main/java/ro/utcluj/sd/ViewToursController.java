package ro.utcluj.sd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import ro.utcluj.sd.Models.Tournament;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewToursController implements Initializable {

    @FXML
    private TextArea tour;

    @FXML
    private TextArea level;

    @FXML
    private TextArea status;

    @FXML
    private TextArea fee;

    @FXML
    private ComboBox statussel;

    @FXML
    private ComboBox typesel;

    @FXML
    private Button sort;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        StringBuilder st = new StringBuilder();
        TournamentBLL t = new TournamentBLL();
        ArrayList<String> alltours = t.getAllTours();
        for(String tour: alltours){
            st.append(tour);
            st.append('\n');
        }
        tour.setText(st.toString());

        StringBuilder st2 = new StringBuilder();
        ArrayList<String> allstatus = t.getAllTourStatus();
        for(String tour: allstatus){
            st2.append(tour);
            st2.append('\n');
        }
        status.setText(st2.toString());

        StringBuilder st3 = new StringBuilder();
        ArrayList<String> alllevels = t.getAllTourLevel();
        for(String tour: alllevels){
            st3.append(tour);
            st3.append('\n');
        }
        level.setText(st3.toString());

        StringBuilder st4 = new StringBuilder();
        ArrayList<Integer> allfees = t.getAllTourFee();
        for(Integer f: allfees){
            st4.append(Integer.toString(f));
            st4.append('\n');
        }
        fee.setText(st4.toString());

        statussel.getItems().add("-");
        statussel.getItems().add("Open");
        statussel.getItems().add("Finished");

        typesel.getItems().add("-");
        typesel.getItems().add("Free");
        typesel.getItems().add("Paid");

    }

    public void viewTours(ActionEvent event) throws IOException {
        String s = statussel.getSelectionModel().getSelectedItem().toString();
        String tt = typesel.getSelectionModel().getSelectedItem().toString();
        TournamentBLL t = new TournamentBLL();
        if(tt != "-") {
            ArrayList<Tournament> fees = t.getTourByFee(tt);
            StringBuilder st = new StringBuilder();
            StringBuilder st2 = new StringBuilder();
            StringBuilder st3 = new StringBuilder();
            StringBuilder st4 = new StringBuilder();
            for (Tournament tour : fees) {
                st.append(tour.getName());
                st.append('\n');
                st2.append(tour.getStatus());
                st2.append('\n');
                st3.append(tour.getLevel());
                st3.append('\n');
                st4.append(Integer.toString(tour.getFee()));
                st4.append('\n');
            }
            tour.setText(st.toString());
            status.setText(st2.toString());
            level.setText(st3.toString());
            fee.setText(st4.toString());
        }
        if(s != "-") {
            ArrayList<Tournament> fees = t.getTourByStatus(s);
            StringBuilder st = new StringBuilder();
            StringBuilder st2 = new StringBuilder();
            StringBuilder st3 = new StringBuilder();
            StringBuilder st4 = new StringBuilder();
            for (Tournament tour : fees) {
                st.append(tour.getName());
                st.append('\n');
                st2.append(tour.getStatus());
                st2.append('\n');
                st3.append(tour.getLevel());
                st3.append('\n');
                st4.append(Integer.toString(tour.getFee()));
                st4.append('\n');
            }
            tour.setText(st.toString());
            status.setText(st2.toString());
            level.setText(st3.toString());
            fee.setText(st4.toString());
        }

    }

}
