package prog1.kotprog.dontstarve.solution.character.actions;

import prog1.kotprog.dontstarve.java.dontstarve.solution.character.actions.Action;
import prog1.kotprog.dontstarve.java.dontstarve.solution.character.actions.ActionType;
import prog1.kotprog.dontstarve.solution.inventory.items.EquippableItem;

/**
 * A támadás akció leírására szolgáló osztály: a legközelebbi karakter megtámadása.
 */
public class ActionAttack extends Action {
    EquippableItem weapon;
    /**
     * Az akció létrehozására szolgáló konstruktor.
     */

    public ActionAttack() {
        super(ActionType.ATTACK);

    }


}
