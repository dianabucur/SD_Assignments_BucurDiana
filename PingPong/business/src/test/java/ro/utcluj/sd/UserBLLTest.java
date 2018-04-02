package ro.utcluj.sd;

import junit.framework.TestCase;
import ro.utcluj.sd.Models.User;

public class UserBLLTest extends TestCase {

    public void testValidateUser() {
        //check if user is admin
        User user = new User("diana.dbucur@gmail.com", "parola", "Bucur Diana", 21, "Female");
        UserBLL userBLL = new UserBLL();
        assertEquals(1, userBLL.validateUser(user.getMail(), user.getPassword()));

        //check if user is player
        User user2 = new User("q", "q", "Bucur Diana", 21, "Female");
        UserBLL userBLL2 = new UserBLL();
        assertEquals(0, userBLL2.validateUser(user2.getMail(), user2.getPassword()));
    }

    public void testAddUser() {
        //try adding a user already existing
        User user = new User("diana.dbucur@gmail.com", "parola", "Bucur Diana", 21, "Female");
        UserBLL userBLL = new UserBLL();
        assertEquals(-1, userBLL.addUser(user.getMail(),user.getPassword(),user.getName(),user.getAge(),user.getGender()));
    }
}