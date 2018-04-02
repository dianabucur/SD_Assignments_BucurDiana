package ro.utcluj.sd;

import junit.framework.TestCase;
import ro.utcluj.sd.Models.Tournament;
import ro.utcluj.sd.Models.User;

public class EnrollBLLTest extends TestCase {

    public void testEnrollPlayer() {
        //check a user that is already enrolled; should return -1
        EnrollBLL enrollBLL = new EnrollBLL();
        Tournament tournament = new Tournament("Sinaia Cup", "Open", "Beginner");
        User user = new User("diana.dbucur@gmail.com", "parola", "Bucur Diana", 21, "Female");
        assertEquals(-1, enrollBLL.enrollPlayer(user.getMail(), tournament.getName()) );
    }
}