package prog1.kotprog.dontstarve.solution.character.actions;

import prog1.kotprog.dontstarve.java.dontstarve.solution.character.actions.Action;
import prog1.kotprog.dontstarve.java.dontstarve.solution.character.actions.ActionType;

/**
 * Az item begyűjtése akció leírására szolgáló osztály: egy item begyűjtése az aktuális mezőről.
 */
public class ActionCollectItem extends Action {
    /**
     * Az akció létrehozására szolgáló konstruktor.
     */
    public ActionCollectItem() {
        super(ActionType.COLLECT_ITEM);
    }
}
