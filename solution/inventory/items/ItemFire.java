package prog1.kotprog.dontstarve.solution.inventory.items;

import prog1.kotprog.dontstarve.java.dontstarve.solution.inventory.items.AbstractItem;
import prog1.kotprog.dontstarve.java.dontstarve.solution.inventory.items.ItemType;

/**
 * A fire item leírására szolgáló osztály.
 */
public class ItemFire extends AbstractItem {

    /**
     * Konstruktor, amellyel a tárgy létrehozható.
     */
    public ItemFire() {
        super(ItemType.FIRE, 1);
    }

    @Override
    public int getMaxStackSize() {
        return 0;
    }

    @Override
    public AbstractItem clone() {
        return new ItemFire();
    }
}
