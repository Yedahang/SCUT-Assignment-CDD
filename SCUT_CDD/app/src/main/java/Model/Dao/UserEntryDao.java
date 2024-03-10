package Model.Dao;

import Model.Entity.User;

public interface UserEntryDao {
    void insertDate(String user_id,String user_nickname);
    User getUser(String user_id);
}
