package Model.Service.GameRule;

import Model.Entity.SelectedCardGroup;

public interface CardsComparator {
    public boolean SelectedCardGroupCompare(SelectedCardGroup upperShownCards, SelectedCardGroup bottomShownCards);
}
