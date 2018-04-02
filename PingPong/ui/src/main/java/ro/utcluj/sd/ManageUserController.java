package ro.utcluj.sd;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManageUserController implements Initializable {
    @FXML
    private Button adduser;
    @FXML
    private Button deleteuser;
    @FXML
    private Button updateuser;
    @FXML
    private Button back;
    @FXML
    private TextField name;
    @FXML
    private TextField mail;
    @FXML
    private TextField password;
    @FXML
    private TextField age;
    @FXML
    private ComboBox gender;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gender.getItems().addAll("Female", "Male");
    }

    public void addplayer(ActionEvent event) throws IOException {
        try{
            String nume = name.getText();
            String email = mail.getText();
            String pass = password.getText();
            int a = Integer.valueOf(age.getText());
            String g = gender.getSelectionModel().getSelectedItem().toString();

            UserBLL u = new UserBLL();
            int valid = u.addUser(email, pass,nume,a,g);
            if(valid == -1){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Cannot add player!");
                alert.setContentText("Player already registered in the database!");
                alert.showAndWait();
            }
        } catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Cannot add player!");
            alert.setContentText("Please fill in all the fields!");
            alert.showAndWait();
        }
    }

    public void updatePlayer(ActionEvent event) throws IOException{
        String nume = name.getText();
        String email = mail.getText();
        String pass = password.getText();
        String a = age.getText();

        UserBLL u = new UserBLL();
        if(!email.isEmpty()){
            if(!nume.isEmpty())
                u.updateName(email,nume);
            if(!a.isEmpty())
                u.updateAge(email, Integer.valueOf(a));
            if(!pass.isEmpty())
                u.updatePass(email,pass);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Cannot update player!");
            alert.setContentText("Please fill in the e-mail field!");
            alert.showAndWait();
        }

    }

    public void deletePlayer(ActionEvent event) throws IOException{
        String email = mail.getText();
        UserBLL u = new UserBLL();
        if(!email.isEmpty()){
            u.deleteUser(email);
        }
    }

    public void back(javafx.event.ActionEvent event) throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("/adminlogin.fxml"));
        Scene scene = new Scene(root2, 630, 430);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

}


