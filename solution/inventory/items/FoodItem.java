package prog1.kotprog.dontstarve.solution.inventory.items;

import prog1.kotprog.dontstarve.java.dontstarve.solution.inventory.items.AbstractItem;
import prog1.kotprog.dontstarve.java.dontstarve.solution.inventory.items.ItemType;

public abstract class FoodItem extends AbstractItem {
    /**
     * Konstruktor, amellyel a tárgy létrehozható.
     *
     * @param type   az item típusa
     * @param amount az item mennyisége
     */
    public FoodItem(ItemType type, int amount) {
        super(type, amount);
    }

}
