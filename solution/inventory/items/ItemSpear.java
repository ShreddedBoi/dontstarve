package prog1.kotprog.dontstarve.solution.inventory.items;

import prog1.kotprog.dontstarve.java.dontstarve.solution.inventory.items.AbstractItem;
import prog1.kotprog.dontstarve.java.dontstarve.solution.inventory.items.EquippableItem;
import prog1.kotprog.dontstarve.java.dontstarve.solution.inventory.items.ItemType;

/**
 * A lándzsa item leírására szolgáló osztály.
 */
public class ItemSpear extends EquippableItem {

    /**
     * Konstruktor, amellyel a tárgy létrehozható.
     */
    public ItemSpear() {
        super(ItemType.SPEAR);
        durability = 10;
        isHeld = false;
        damage = 19;
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }

    @Override
    public AbstractItem clone() {
        return new ItemSpear();
    }

    @Override
    public int getDamage() {
        return this.getDamage();
    }

    @Override
    public void setDurability(int durability) {
        this.durability = durability;
    }

    @Override
    public int getDurability() {
        return this.durability;
    }
}
