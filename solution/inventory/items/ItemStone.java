package prog1.kotprog.dontstarve.solution.inventory.items;

import prog1.kotprog.dontstarve.java.dontstarve.solution.inventory.items.AbstractItem;
import prog1.kotprog.dontstarve.java.dontstarve.solution.inventory.items.ItemType;

/**
 * A kő item leírására szolgáló osztály.
 */
public class ItemStone extends AbstractItem {
    /**
     * Konstruktor, amellyel a tárgy létrehozható.
     *
     * @param amount az item mennyisége
     */
    public ItemStone(int amount) {
        super(ItemType.STONE, amount);
    }

    @Override
    public int getMaxStackSize() {
        return 10;
    }

    @Override
    public AbstractItem clone() {
        return new ItemStone(this.getAmount());
    }

}
