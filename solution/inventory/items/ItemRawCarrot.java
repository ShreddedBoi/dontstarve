package prog1.kotprog.dontstarve.solution.inventory.items;

import prog1.kotprog.dontstarve.java.dontstarve.solution.inventory.items.AbstractItem;
import prog1.kotprog.dontstarve.java.dontstarve.solution.inventory.items.FoodItem;
import prog1.kotprog.dontstarve.java.dontstarve.solution.inventory.items.ItemType;

/**
 * A nyers répa item leírására szolgáló osztály.
 */
public class ItemRawCarrot extends FoodItem {
    /**
     * Konstruktor, amellyel a tárgy létrehozható.
     *
     * @param amount az item mennyisége
     */
    public ItemRawCarrot(int amount) {
        super(ItemType.RAW_CARROT, amount);
    }

    @Override
    public int getMaxStackSize() {
        return 10;
    }

    @Override
    public AbstractItem clone() {
        return new ItemRawCarrot(this.getAmount());
    }
}
