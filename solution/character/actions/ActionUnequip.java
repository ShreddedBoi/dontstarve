package prog1.kotprog.dontstarve.solution.character.actions;

import prog1.kotprog.dontstarve.java.dontstarve.solution.character.actions.Action;
import prog1.kotprog.dontstarve.java.dontstarve.solution.character.actions.ActionType;

/**
 * A tárgy levétele akció leírására szolgáló osztály: az aktuálisan kézben lévő item visszarakása az inventory-ba.
 */
public class ActionUnequip extends Action {
    /**
     * Az akció létrehozására szolgáló konstruktor.
     */
    public ActionUnequip() {
        super(ActionType.UNEQUIP);
    }
}
