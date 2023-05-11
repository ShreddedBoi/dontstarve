package prog1.kotprog.dontstarve.solution.character.actions;

import prog1.kotprog.dontstarve.java.dontstarve.solution.character.actions.Action;
import prog1.kotprog.dontstarve.java.dontstarve.solution.character.actions.ActionType;

/**
 * Az aktuális mezőn lévő tereptárggyal való interakcióba lépés (favágás, kőcsákányozás, gally / bogyó / répa leszedése)
 * leírására szolgáló osztály.
 */
public class ActionInteract extends Action {
    /**
     * Az akció létrehozására szolgáló konstruktor.
     */
    public ActionInteract() {
        super(ActionType.INTERACT);
    }
}
