package ro.utcluj.sd;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import ro.utcluj.sd.Models.Game;
import ro.utcluj.sd.Models.Match;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PlayController implements Initializable {
    private String tour;
    private String user;
    private ArrayList<Game> games;
    private Match mm;
    private ObservableList<String>items ;

    @FXML
    private ListView<String>  match1;

    @FXML
    private ListView<String>  match2;

    @FXML
    private ListView<String>  match3;

    @FXML
    private ListView<String>  match4;

    @FXML
    private ListView<String>  match5;

    @FXML
    private ListView<String>  match6;

    @FXML
    private ListView<String>  match7;

    @FXML
    private Button addbutton;

    @FXML
    private Label player1;

    @FXML
    private Label player2;

    @FXML
    private Label set11;

    @FXML
    private Label set12;

    @FXML
    private Label set21;

    @FXML
    private Label set22;

    @FXML
    private Label set31;

    @FXML
    private Label set32;

    @FXML
    private Label set41;

    @FXML
    private Label set42;

    @FXML
    private Label set51;

    @FXML
    private Label set52;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setData(String tour,String user){
        this.user = user;
        this.tour = tour;
        MatchBLL m = new MatchBLL();
        ArrayList<Match> matches = m.getAllMacthes(tour);
        UserBLL u = new UserBLL();

        ObservableList<String> items = FXCollections.observableArrayList (
                u.getNameById(matches.get(0).getP1()), u.getNameById(matches.get(0).getP2()));
        match1.setItems(items);

        items = FXCollections.observableArrayList (
                u.getNameById(matches.get(1).getP1()), u.getNameById(matches.get(1).getP2()));
        match2.setItems(items);

        items = FXCollections.observableArrayList (
                u.getNameById(matches.get(2).getP1()), u.getNameById(matches.get(2).getP2()));
        match3.setItems(items);

        items = FXCollections.observableArrayList (
                u.getNameById(matches.get(3).getP1()), u.getNameById(matches.get(3).getP2()));
        match4.setItems(items);

        if(matches.size() > 4){
            String s = "";
            if(u.getNameById(matches.get(4).getP2()) != null){
                s = u.getNameById(matches.get(4).getP2());
            }
            items = FXCollections.observableArrayList (
                    u.getNameById(matches.get(4).getP1()), s);
            match5.setItems(items);
        }
        if(matches.size() > 5){
            String s = "";
            if(u.getNameById(matches.get(5).getP2()) != null){
                s = u.getNameById(matches.get(5).getP2());
            }
            items = FXCollections.observableArrayList (
                    u.getNameById(matches.get(5).getP1()), s);
            match6.setItems(items);
        }
        if(matches.size() > 6){
            String s = "";
            if(u.getNameById(matches.get(6).getP2()) != null){
                s = u.getNameById(matches.get(6).getP2());
            }
            items = FXCollections.observableArrayList (
                    u.getNameById(matches.get(6).getP1()), s);
            match7.setItems(items);
        }

        mm = m.getMatch(user);
        player1.setText(u.getNameById(mm.getP1()));
        player2.setText(u.getNameById(mm.getP2()));

        GameBLL g = new GameBLL();
        games = g.getGames(mm.getId());

        if(games.size() >= 1) {
            set11.setText(Integer.toString(games.get(0).getScore1()));
            set12.setText(Integer.toString(games.get(0).getScore2()));
        }
        if(games.size() >= 2){
            set21.setText(Integer.toString(games.get(1).getScore1()));
            set22.setText(Integer.toString(games.get(1).getScore2()));
        }
        if(games.size() >= 3){
            set31.setText(Integer.toString(games.get(2).getScore1()));
            set32.setText(Integer.toString(games.get(2).getScore2()));
        }
        if(games.size() >= 4){
            set41.setText(Integer.toString(games.get(3).getScore1()));
            set42.setText(Integer.toString(games.get(3).getScore2()));
        }
        if(games.size() == 5){
            set51.setText(Integer.toString(games.get(4).getScore1()));
            set52.setText(Integer.toString(games.get(4).getScore2()));
        }
    }

    public void addPoint(ActionEvent event) throws IOException {
        GameBLL g = new GameBLL();
        games = g.getGames(mm.getId());
        System.out.println("NR DE GAME-URI:  " + games.size());
        int gameid = games.get(games.size()-1).getId();
        int player1id = mm.getP1();
        int player2id = mm.getP2();
        int matchid = mm.getId();
        int score1 = games.get(games.size()-1).getScore1();
        int score2 = games.get(games.size()-1).getScore2();
        System.out.println("id " + gameid + "  score1  " + score1 + "   score2   " + score2);
        UserBLL u = new UserBLL();
        int userid = u.getIdByMail(this.user);
        if(player1id == userid) {
            if ((score1 <= 11 & score2 <= 11) || ((score1 > 11 || score2 > 11) && (Math.abs(score1 - score2) < 2))) {
                if (games.size() == 1)
                    set11.setText(Integer.toString(score1));
                else if (games.size() == 2)
                    set21.setText(Integer.toString(score1));
                else if (games.size() == 3)
                    set31.setText(Integer.toString(score1));
                else if (games.size() == 4)
                    set41.setText(Integer.toString(score1));
                else if (games.size() == 5)
                    set51.setText(Integer.toString(score1));
                score1 += 1;
            }
        }else {
            if ((score1 <= 11 & score2 <= 11) || ((score1 > 11 || score2 > 11) && (Math.abs(score1 - score2) < 2))) {
                if (games.size() == 1)
                    set12.setText(Integer.toString(score2));
                else if (games.size() == 2)
                    set22.setText(Integer.toString(score2));
                else if (games.size() == 3)
                    set32.setText(Integer.toString(score2));
                else if (games.size() == 4)
                    set42.setText(Integer.toString(score2));
                else if (games.size() == 5)
                    set52.setText(Integer.toString(score2));
            }
            score2 += 1;
        }
        String res = g.updateGame(score1,score2,gameid,matchid, this.tour, player1id, player2id);
        System.out.println(res);
        if (res != null) {
            System.out.println(res);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Finished Match");
            alert.setContentText("You won !");
            alert.showAndWait();
            if (res == "Player1F") {
                items = FXCollections.observableArrayList(
                        "", "");
                items.set(0, u.getNameById(player1id));
            } else if (res == "Player1S")
                items.set(1, u.getNameById(player1id));
            else if (res == "Player2F") {
                items = FXCollections.observableArrayList(
                        "", "");
                items.set(0, u.getNameById(player2id));
            } else if (res == "Player2S")
                items.set(1, u.getNameById(player2id));
            MatchBLL m = new MatchBLL();
            int count = m.getCountMatches(this.tour);
            System.out.println(count);
            if (count == 5) {
                match5.setItems(items);
            } else if (count == 6)
                match6.setItems(items);
            else if (count == 7){
                match7.setItems(items);
            }

        }
        //System.out.println("id " + gameid + "  score1  " + score1 + "   score2   " + score2);
    }



}
