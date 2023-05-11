package prog1.kotprog.dontstarve.solution.inventory.items;

import prog1.kotprog.dontstarve.java.dontstarve.solution.inventory.items.AbstractItem;
import prog1.kotprog.dontstarve.java.dontstarve.solution.inventory.items.ItemType;

/**
 * A gally item leírására szolgáló osztály.
 */
public class ItemTwig extends AbstractItem {

    /**
     * Konstruktor, amellyel a tárgy létrehozható.
     *
     * @param amount az item mennyisége
     */
    public ItemTwig(int amount) {
        super(ItemType.TWIG, amount);
    }

    @Override
    public int getMaxStackSize() {
        return 20;
    }

    @Override
    public AbstractItem clone() {
        return new ItemTwig(this.getAmount());
    }
}
