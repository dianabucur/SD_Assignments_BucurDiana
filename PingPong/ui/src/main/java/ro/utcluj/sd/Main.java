package ro.utcluj.sd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class Main extends Application{

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/display.fxml"));
        primaryStage.setTitle("Ping-Pong");
        primaryStage.setScene(new Scene(root, 630, 430));
        primaryStage.show();
    }
}