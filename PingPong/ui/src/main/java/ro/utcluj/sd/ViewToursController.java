package ro.utcluj.sd;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;


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

    }

}
