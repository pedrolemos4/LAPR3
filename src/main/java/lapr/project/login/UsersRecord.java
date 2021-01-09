package lapr.project.login;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UsersRecord implements Serializable {

    private List<User> m_lstUsers;

    public UsersRecord() {
        this.m_lstUsers = new ArrayList<>();
    }

    public User newUser(String strNome, String strEmail, String strPassword) {
        return new User(strNome, strEmail, strPassword);
    }

    public boolean addUser(User user) {
        if (hasUser(user)) {
            return this.m_lstUsers.add(user);
        } else {
            return false;
        }
    }

    public User searchUser(String strId) {
        for (User utlz : this.m_lstUsers) {
            if (utlz.hasId(strId)) {
                return utlz;
            }
        }
        return null;
    }

    public boolean hasUser(User user) {
        boolean flag = true;
        for (User utlz : this.m_lstUsers) {
            if (utlz.getEmail().equals(user.getEmail())) {
                return false;
            }
        }
        return flag;
    }
}
