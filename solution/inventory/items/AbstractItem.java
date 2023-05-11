package prog1.kotprog.dontstarve.solution.inventory.items;

import prog1.kotprog.dontstarve.java.dontstarve.solution.inventory.items.ItemType;

/**
 * Egy általános itemet leíró osztály.
 */
public abstract class AbstractItem {
    /**
     * Az item típusa.
     *
     * @see prog1.kotprog.dontstarve.java.dontstarve.solution.inventory.items.ItemType
     */
    private final prog1.kotprog.dontstarve.java.dontstarve.solution.inventory.items.ItemType type;

    /**
     * Az item mennyisége.
     */
    private int amount;


    /**
     * Konstruktor, amellyel a tárgy létrehozható.
     *
     * @param type   az item típusa
     * @param amount az item mennyisége
     */
    public AbstractItem(prog1.kotprog.dontstarve.java.dontstarve.solution.inventory.items.ItemType type, int amount) {
        this.type = type;
        this.amount = amount;
    }

    /**
     * A type gettere.
     *
     * @return a tárgy típusa
     */
    public ItemType getType() {
        return type;
    }

    /**
     * Az amount gettere.
     *
     * @return a tárgy mennyisége
     */
    public int getAmount() {
        return amount;
    }

    public abstract int getMaxStackSize();


    public void setAmount(int amount) {
        this.amount = amount;
    }

    public abstract AbstractItem clone();

}
