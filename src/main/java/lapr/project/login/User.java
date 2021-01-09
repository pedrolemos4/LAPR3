package lapr.project.login;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {

    private String m_strName;
    private String m_strEmail;
    private String m_strPassword;
    private String role;

    public User(String strName, String strEmail, String strPassword) {
        if ((strName == null) || (strEmail == null) || (strPassword == null) ||
                (strName.isEmpty()) || (strEmail.isEmpty()) || (strPassword.isEmpty())) {
            throw new IllegalArgumentException("None of the arguments can be null or empty.");
        }

        this.m_strName = strName;
        this.m_strEmail = strEmail;
        this.m_strPassword = strPassword;
    }

    public String getId() {
        return this.m_strEmail;
    }

    public String getName() {
        return this.m_strName;
    }

    public String getEmail() {
        return this.m_strEmail;
    }

    public boolean hasId(String strId) {
        return this.m_strEmail.equals(strId);
    }

    public boolean hasPassword(String strPwd) {
        return this.m_strPassword.equals(strPwd);
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.m_strEmail);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        // Inspirado em https://www.sitepoint.com/implement-javas-equals-method-correctly/

        // self check
        if (this == o) {
            return true;
        }
        // null check
        if (o == null) {
            return false;
        }
        // type check and cast
        if (getClass() != o.getClass()) {
            return false;
        }
        // field comparison
        User obj = (User) o;
        return Objects.equals(m_strEmail, obj.m_strEmail);
    }

    @Override
    public String toString() {
        return String.format("%s - %s", this.m_strName, this.m_strEmail);
    }
}
