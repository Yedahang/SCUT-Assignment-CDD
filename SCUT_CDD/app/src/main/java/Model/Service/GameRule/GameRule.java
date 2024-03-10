package Model.Service.GameRule;

import com.development.scut_cdd.ServerLayer.GameRoomData;

import Model.Entity.SelectedCardGroup;

public interface GameRule {
    public boolean validate(SelectedCardGroup scg);
    boolean validate(GameRoomData gameRoomData, SelectedCardGroup scg);
}
