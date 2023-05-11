package prog1.kotprog.dontstarve.solution.inventory.items;

import prog1.kotprog.dontstarve.java.dontstarve.solution.inventory.items.AbstractItem;
import prog1.kotprog.dontstarve.java.dontstarve.solution.inventory.items.EquippableItem;
import prog1.kotprog.dontstarve.java.dontstarve.solution.inventory.items.ItemType;

/**
 * A csákány item leírására szolgáló osztály.
 */
public class ItemPickaxe extends EquippableItem {
    /**
     * Konstruktor, amellyel a tárgy létrehozható.
     */
    public ItemPickaxe() {
        super(ItemType.PICKAXE);
        durability = 30;
        isHeld = false;
        damage = 8;
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }

    @Override
    public AbstractItem clone() {
        return new ItemPickaxe();
    }

    @Override
    public int getDamage() {
        return this.damage;
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
