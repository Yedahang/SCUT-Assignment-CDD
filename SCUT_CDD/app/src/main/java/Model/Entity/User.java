package Model.Entity;

public class User {
    public String USER_ID;
    public String NICKNAME;

    public User(String USER_ID, String NICKNAME) {
        this.USER_ID = USER_ID;
        this.NICKNAME = NICKNAME;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public String getNICKNAME() {
        return NICKNAME;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public void setNICKNAME(String NICKNAME) {
        this.NICKNAME = NICKNAME;
    }
}
