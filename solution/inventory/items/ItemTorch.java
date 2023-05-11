package prog1.kotprog.dontstarve.solution.inventory.items;

import prog1.kotprog.dontstarve.java.dontstarve.solution.inventory.items.AbstractItem;
import prog1.kotprog.dontstarve.java.dontstarve.solution.inventory.items.EquippableItem;
import prog1.kotprog.dontstarve.java.dontstarve.solution.inventory.items.ItemType;

/**
 * A fáklya item leírására szolgáló osztály.
 */
public class ItemTorch extends EquippableItem {
    /**
     * Konstruktor, amellyel a tárgy létrehozható.
     */
    public ItemTorch() {
        super(ItemType.TORCH);
        durability = 20; //TODO nem használat, hanem időegység
        isHeld = false;
        damage = 6;

    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }

    @Override
    public AbstractItem clone() {
        return new ItemTorch();
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
