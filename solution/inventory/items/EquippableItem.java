package prog1.kotprog.dontstarve.solution.inventory.items;

import prog1.kotprog.dontstarve.java.dontstarve.solution.exceptions.NotImplementedException;
import prog1.kotprog.dontstarve.java.dontstarve.solution.inventory.items.AbstractItem;
import prog1.kotprog.dontstarve.java.dontstarve.solution.inventory.items.ItemType;
import prog1.kotprog.dontstarve.solution.exceptions.NotImplementedException;

/**
 * Felvehető / kézbe vehető item leírására szolgáló osztály.
 */
public abstract class EquippableItem extends AbstractItem {
    boolean isHeld;
    int durability;
    int damage;
    /**
     * Konstruktor, amellyel a tárgy létrehozható.
     *
     * @param type   az item típusa
     */
    public EquippableItem(ItemType type) {
        super(type, 1);
    }

    /**
     * Megadja, hogy milyen állapotban van a tárgy.
     * @return a tárgy használatlansága, %-ban (100%: tökéletes állapot)
     */
    public float percentage() {
        throw new NotImplementedException();
    }

    /**
     * megadja az adott fegyver sebzését
     * @return fegyversebzés
     */
    public abstract int getDamage();

    /**
     * beállítja a fegyver tartosságát
     * @param durability új tartosság
     */

    public abstract void setDurability(int durability);

    /**
     * visszaadja a jelenlegi tartósságot
     * @return tartósság
     */

    public abstract int getDurability();

}
