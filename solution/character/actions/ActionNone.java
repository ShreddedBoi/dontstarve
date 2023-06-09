package prog1.kotprog.dontstarve.solution.character.actions;

import prog1.kotprog.dontstarve.java.dontstarve.solution.character.actions.Action;
import prog1.kotprog.dontstarve.java.dontstarve.solution.character.actions.ActionType;

/**
 * A várakozás akció leírására szolgáló osztály: a karakter nem végez cselekvést az aktuális körben.
 */
public class ActionNone extends Action {
    /**
     * Az akció létrehozására szolgáló konstruktor.
     */
    public ActionNone() {
        super(ActionType.NONE);
    }
}
