package lapr.project.authorization;

import lapr.project.login.User;
import lapr.project.login.UserSession;
import lapr.project.login.UsersRecord;



public class FacadeAuthorization {

    private UserSession m_oSession;

    private UsersRecord usersRecord;
    
    private final UsersRecord m_oUsers = new UsersRecord();

    public FacadeAuthorization() {
        this.usersRecord = new UsersRecord();
        this.m_oSession = new UserSession();
    }

    public boolean registesUserWithRole(String strName, String strEmail, String strRole) {
        User utlz = this.m_oUsers.newUser(strName, strEmail, strRole);
        utlz.setRole(strRole);
        return this.m_oUsers.addUser(utlz);
    }

    public boolean registesUserWithRole(String strName, String strEmail, String strPassword, String role) {
        User user = this.m_oUsers.newUser(strName, strEmail, strPassword);
        user.setRole(role);
        return this.m_oUsers.addUser(user);
    }

    public boolean doLogin(String strId, String strPwd) {
        User user = this.m_oUsers.searchUser(strId);
        if (user == null) {
            return false;
        }

        if (user.hasPassword(strPwd)) {
            this.m_oSession = new UserSession(user);
            return true;
        } else {
            return false;
        }
    }

    public UserSession getCurrentSession() {
        return this.m_oSession;
    }

    public void doLogout() {
        if (this.m_oSession != null) {
            this.m_oSession.doLogout();
        }
    }

}
