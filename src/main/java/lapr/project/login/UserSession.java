package lapr.project.login;

import java.io.Serializable;

public class UserSession {

    private static UserSession instance;
    private User m_oUser;

    public UserSession() {
        this.m_oUser = null;
    }

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public UserSession(User oUser) {
        if (oUser == null) {
            throw new IllegalArgumentException("The argument can't be null or empty.");
        }
        this.m_oUser = oUser;
    }

    public void doLogout() {
        this.m_oUser = null;
    }

    public User getUser() {
        return this.m_oUser;
    }
}
