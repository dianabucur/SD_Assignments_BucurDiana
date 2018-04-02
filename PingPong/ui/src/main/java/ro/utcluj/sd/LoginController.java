package ro.utcluj.sd;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    private String player;
    @FXML
    private TextField mail;

    @FXML
    private PasswordField password;

    @FXML
    private Button login;

    public void adminlogin(javafx.event.ActionEvent event) throws IOException {
        player = mail.getText();
        String pass = password.getText();
        UserBLL userbll = new UserBLL();
        if (userbll.validateUser(player, pass) == 1) {
            //Parent root2 = FXMLLoader.load(getClass().getResource("/adminlogin.fxml"));

            FXMLLoader playFrame = new FXMLLoader(getClass().getResource("/adminlogin.fxml"));
            Parent p = (Parent) playFrame.load();
            Scene scene = new Scene(p, 630, 430);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } else if (userbll.validateUser(player, pass) == 0) {
            FXMLLoader playFrame = new FXMLLoader(getClass().getResource("/playerlogin.fxml"));
            Parent p = (Parent) playFrame.load();
            PlayerController theController = playFrame.getController();
            theController.setUser(player);

            Scene scene = new Scene(p, 630, 430);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Log in failed");
            alert.setContentText("Mail or password incorrect. Try again !");
            alert.showAndWait();
        }

    }

}
