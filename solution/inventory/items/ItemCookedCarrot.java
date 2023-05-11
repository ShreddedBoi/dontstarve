package prog1.kotprog.dontstarve.solution.inventory.items;

import prog1.kotprog.dontstarve.java.dontstarve.solution.inventory.items.AbstractItem;
import prog1.kotprog.dontstarve.java.dontstarve.solution.inventory.items.FoodItem;
import prog1.kotprog.dontstarve.java.dontstarve.solution.inventory.items.ItemType;

/**
 * A főtt répa item leírására szolgáló osztály.
 */
public class ItemCookedCarrot extends FoodItem {
    /**
     * Konstruktor, amellyel a tárgy létrehozható.
     *
     * @param amount az item mennyisége
     */
    public ItemCookedCarrot(int amount) {
        super(ItemType.COOKED_CARROT, amount);
    }

    @Override
    public int getMaxStackSize() {
        return 10;
    }

    @Override
    public AbstractItem clone() {
        return new ItemCookedCarrot(this.getAmount());
    }
}
