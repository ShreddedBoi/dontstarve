package prog1.kotprog.dontstarve.solution.inventory.items;

import prog1.kotprog.dontstarve.java.dontstarve.solution.inventory.items.AbstractItem;
import prog1.kotprog.dontstarve.java.dontstarve.solution.inventory.items.ItemType;

/**
 * A fa item leírására szolgáló osztály.
 */
public class ItemLog extends AbstractItem {
    /**
     * Konstruktor, amellyel a tárgy létrehozható.
     *
     * @param amount az item mennyisége
     */
    public ItemLog(int amount) {
        super(ItemType.LOG, amount);
    }

    @Override
    public int getMaxStackSize() {
        return 15;
    }

    @Override
    public AbstractItem clone() {
        return new ItemLog(this.getAmount());
    }
}
