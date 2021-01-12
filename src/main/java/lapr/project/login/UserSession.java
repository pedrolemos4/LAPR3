package lapr.project.login;

import lapr.project.model.Utilizador;

public class UserSession {

    private static UserSession instance;
    private Utilizador user;

    public UserSession() {
        this.user = null;
    }

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public void setUser(Utilizador user) {
        this.user = user;
    }

    public void doLogout() {
        this.user = null;
    }

    public Utilizador getUser() {
        return this.user;
    }
}
