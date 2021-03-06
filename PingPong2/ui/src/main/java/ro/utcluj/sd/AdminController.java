package ro.utcluj.sd;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminController {

    @FXML
    private Button manageuser;
    @FXML
    private Button managetour;

    public void manageplayers(javafx.event.ActionEvent event) throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("/manageuser.fxml"));
        Scene scene = new Scene(root2, 630, 430);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void enrollplayers(javafx.event.ActionEvent event) throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("/enrollplayer.fxml"));
        Scene scene = new Scene(root2, 630, 430);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void managetours(javafx.event.ActionEvent event) throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("/managetour.fxml"));
        Scene scene = new Scene(root2, 630, 430);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

}
