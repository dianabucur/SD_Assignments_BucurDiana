package ro.utcluj.sd;

import junit.framework.TestCase;
import ro.utcluj.sd.Models.Game;
import ro.utcluj.sd.Models.Match;
import ro.utcluj.sd.Models.Tournament;

public class GameBLLTest extends TestCase {

    public void testUpdateGame() {
        Tournament tournament = new Tournament("Cluj Cup", "Pro", "Advanced", 0);
        Match match = new Match(1, 3, 4);
        Game game = new Game(1, 8,5);
        GameBLL gameBLL = new GameBLL();
        assertEquals(null, gameBLL.updateGame(game.getScore1(), game.getScore2(),game.getId(),match.getId(),tournament.getName(),match.getP1(),match.getP2()));
    }
}