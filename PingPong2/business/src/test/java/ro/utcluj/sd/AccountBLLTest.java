package ro.utcluj.sd;

import junit.framework.TestCase;
import ro.utcluj.sd.Models.User;

public class AccountBLLTest extends TestCase {

    public void testGetUserBalance() {
        AccountBLL a = new AccountBLL();
        User user = new User("v", "v", "v", 10, "Female");
        UserBLL u = new UserBLL();
        int id = u.getIdByMail(user.getMail());
        assertEquals(10, a.getUserBalance(id));
    }
}