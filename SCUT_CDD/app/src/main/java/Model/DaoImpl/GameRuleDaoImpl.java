package Model.DaoImpl;

import Database.LocalDB;
import Model.Dao.GameRuleDao;

public class GameRuleDaoImpl implements GameRuleDao {
    @Override
    public boolean getFirstRoundFlag() {
        return LocalDB.isFirstRoundFlag();
    }

    @Override
    public void setFirstRoundFlag(boolean b) {
        LocalDB.setFirstRoundFlag(b);
    }
}
