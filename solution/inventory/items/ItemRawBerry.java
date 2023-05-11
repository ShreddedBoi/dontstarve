package prog1.kotprog.dontstarve.solution.inventory.items;

import prog1.kotprog.dontstarve.java.dontstarve.solution.inventory.items.AbstractItem;
import prog1.kotprog.dontstarve.java.dontstarve.solution.inventory.items.FoodItem;
import prog1.kotprog.dontstarve.java.dontstarve.solution.inventory.items.ItemType;

/**
 * A nyers bogyó item leírására szolgáló osztály.
 */
public class ItemRawBerry extends FoodItem {
    /**
     * Konstruktor, amellyel a tárgy létrehozható.
     *
     * @param amount az item mennyisége
     */
    public ItemRawBerry(int amount) {
        super(ItemType.RAW_BERRY, amount);
    }

    @Override
    public int getMaxStackSize() {
        return 10;
    }

    @Override
    public AbstractItem clone() {
        return new ItemRawBerry(this.getAmount());
    }
}
