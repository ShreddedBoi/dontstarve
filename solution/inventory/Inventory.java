package prog1.kotprog.dontstarve.solution.inventory;

import prog1.kotprog.dontstarve.java.dontstarve.solution.inventory.BaseInventory;
import prog1.kotprog.dontstarve.solution.inventory.items.*;


public class Inventory implements BaseInventory {
    private static final int MAX_SLOT_COUNT = 10;
    private final AbstractItem[] items;
    private EquippableItem equippedItem;

    /**
     * Inventory konstuktora
     */
    public Inventory() {
        items = new AbstractItem[MAX_SLOT_COUNT];
    }

    /**
     *
     * @param item a hozzáadni kívánt tárgy
     * @return sikeresen hozzáadtam-e a tárgyat, az adott darabszámban
     */
    @Override
    public boolean addItem(AbstractItem item) {
        if (item == null) {     // ellenőrzöm a paraméter nem null-e
            return false;
        }

        if (item.getMaxStackSize() == 1) {      //nem stackelhető itemet kaptam
            for (int i = 0; i < MAX_SLOT_COUNT; i++) {
                if (items[i] == null) {             //ha üres helyet találok behelyezem az adott indexre az itemet
                    items[i] = item;
                    return true;
                }
            }
            return false;       //nem találtam üres helyet, sikertelen elhelyezés
        }
        int itemAmount = item.getAmount();      //belemásolom a paraméterben kapott item db számát
        for (int i = 0; i < MAX_SLOT_COUNT; i++) {
            if (itemAmount == 0) {                  //ha 0 azt jelenti elfogyott elhelyeztem az összeset siker
                return true;
            }
            if (items[i] == null) {     //üres inventory hely
                int fillableSize = Math.min(item.getMaxStackSize(), itemAmount);    //többet kaptam mint amennyi a max férőhely?
                items[i] = item.clone();    //copy consturctorral lemásolom az itemet az inventory i. helyére
                item.setAmount(itemAmount);         //beállítom a kapott item db számát
                items[i].setAmount(fillableSize);   //beállítom az inventoryban az item db számát vagy az item számára vagy a maxra
                itemAmount -= fillableSize;         //levonom az itemszámából amennyit már beraktam

            } else if (items[i].getType() == item.getType() && items[i].getAmount() != items[i].getMaxStackSize()) { //ha olyan helyet találok ahol már van ilyen item de még fér
                int fillableSize = Math.min(item.getMaxStackSize() - items[i].getAmount(), itemAmount); //kivonom a maxból amennyi már van benne és minimumot keresek a betevésre váró itemszámmal
                items[i].setAmount(fillableSize + items[i].getAmount());
                itemAmount -= fillableSize;
                item.setAmount(itemAmount);
            }
        }
        return itemAmount == 0;
    }

    /**
     * Egy tárgy kidobása az inventory-ból. Hatására a tárgy eltűnik az inventory-ból.
     * @param index a slot indexe, amelyen lévő itemet szeretnénk eldobni
     * @return
     */

    @Override
    public AbstractItem dropItem(int index) {
        if (index < 0 || index >= MAX_SLOT_COUNT) {
            return null;
        }
        AbstractItem forReturn = items[index];
        items[index] = null;
        return forReturn;
    }

    /**
     * Megkeressük az itemet az inventory-ban az ItemType alapján
     * Ha több item van mint az amount akkor csökkentjük a db-számot
     * Ha pont annyit törlök eltávolítom az inventoryból, ha többet nem történik semmi, sikertelen (false )
     */


    @Override
    public boolean removeItem(ItemType type, int amount) {
        if (type == null || amount <= 0) {
            return false;
        }

        for (int i = 0; i < MAX_SLOT_COUNT; i++) {
            if (items[i] == null || items[i].getType() != type || amount == 0) {
                continue;
            }

            if (amount < items[i].getAmount()) {
                items[i].setAmount(items[i].getAmount() - amount);
                return true;
            }

            amount -= items[i].getAmount();
            items[i] = null;
        }

        return amount == 0;
    }

    /**
     * Két item pozíciójának megcserélése az inventory-ban. Csak akkor használható, ha mind a két pozíción már van item.
     * @param index1 a slot indexe, amelyen az első item található
     * @param index2 a slot indexe, amelyen a második item található
     * @return
     */
    @Override
    public boolean swapItems(int index1, int index2) { //ellenőrzöm jók-e a megadott indexek és van-e ott item
        if (index1 < 0 || index1 >= MAX_SLOT_COUNT || index2 < 0 ||
                index2 >= MAX_SLOT_COUNT || items[index1] == null || items[index2] == null) {
            return false;
        }
        AbstractItem item = items[index1];
        items[index1] = items[index2];
        items[index2] = item;
        return true;
    }

    /**
     * Egy item átmozgatása az inventory egy másik pozíciójára. Csak akkor használható,
     * ha az eredeti indexen van tárgy, az új indexen viszont nincs.
     * @param index a mozgatni kívánt item pozíciója az inventory-ban
     * @param newIndex az új pozíció, ahova mozgatni szeretnénk az itemet
     * @return
     */
    @Override // index ellenőrzés után áthelyezem az indexről a newIndexre az itemet
    public boolean moveItem(int index, int newIndex) {
        if (index < 0 || index >= MAX_SLOT_COUNT || newIndex < 0 ||
                newIndex >= MAX_SLOT_COUNT || items[index] == null || items[newIndex] != null) {
            return false;
        }

        items[newIndex] = items[index];
        items[index] = null;

        return true;
    }

    /**
     * Két azonos típusú tárgy egyesítése. Csak stackelhető tárgyakra használható.
     * Ha a két stack méretének összege a maximális stack méreten belül van, akkor az egyesítés az első pozíción fog megtörténni.
     * Ha nem, akkor az első pozíción lévő stack maximálisra töltődik, a másikon pedig a maradék marad.
     * @param index1 első item pozíciója az inventory-ban
     * @param index2 második item pozíciója az inventory-ban
     * @return
     */
    @Override
    public boolean combineItems(int index1, int index2) {
        if (index1 < 0 || index1 >= MAX_SLOT_COUNT || index2 < 0 ||
                index2 >= MAX_SLOT_COUNT || items[index1] == null || items[index2] == null) {
            return false;
        }
        if ((items[index1].getMaxStackSize() <= 0) || items[index1].getType() != items[index2].getType()) {
            return false;
        }
        int bothAmounts = items[index1].getAmount() + items[index2].getAmount();
        if (bothAmounts <= items[index1].getMaxStackSize()) {
            items[index1].setAmount(bothAmounts);
            items[index2] = null;
        } else {
            items[index1].setAmount(items[index1].getMaxStackSize());
            items[index2].setAmount(bothAmounts - items[index1].getMaxStackSize());
        }
        return true;
    }


    @Override
    public boolean equipItem(int index) {
        if (index < 0 || index >= MAX_SLOT_COUNT || !(items[index] instanceof EquippableItem)) {
            return false;
        }

        if (equippedItem != null) {
            for (int i = 0; i < MAX_SLOT_COUNT; i++) {
                if (items[i] != null) {
                    items[i] = equippedItem.clone();
                    equippedItem = (EquippableItem) items[index].clone();
                    items[index] = null;
                    return true;
                }
            }
        }
        equippedItem = (EquippableItem) items[index].clone();
        items[index] = null;
        return true;

    }

    @Override
    public EquippableItem unequipItem() {
        if (equippedItem == null) {
            return null;
        } else {
            for (int i = 0; i < MAX_SLOT_COUNT; i++) {
                if (items[i] == null) {
                    items[i] = equippedItem;
                    equippedItem = null;
                    return null;
                }
            }
            EquippableItem forReturn = equippedItem;
            equippedItem = null;
            return forReturn;
        }
    }

    @Override
    public ItemType cookItem(int index) { //tickben ellenőrizni kell hogy tűzon állunk-e és 1 tickig tart amig megfő az item
        if (index < 0 || index >= MAX_SLOT_COUNT || (!(items[index] instanceof ItemRawBerry) && !(items[index] instanceof ItemRawCarrot))) {
            return null;
        }

        FoodItem tempItem = (FoodItem) items[index];
        boolean check = removeItem(items[index].getType(), 1);
        if (check) {
            if (tempItem instanceof ItemRawBerry) {
                addItem(new ItemCookedBerry(1));
            } else if (tempItem instanceof ItemRawCarrot) {
                addItem(new ItemCookedCarrot(1));
            }
        }
        return tempItem.getType();
    }

    @Override
    public ItemType eatItem(int index) {
        if (index < 0 || index >= MAX_SLOT_COUNT || !(items[index] instanceof FoodItem)) {
            return null;
        }

        items[index].setAmount(items[index].getAmount() - 1);
        ItemType tempItem = items[index].getType();
        if (items[index].getAmount() == 0) {
            items[index] = null;
        }

        return tempItem;
    }


    @Override
    public int emptySlots() {
        int emptySlotCount = 0;
        for (int i = 0; i < MAX_SLOT_COUNT; i++) {
            if (items[i] == null) {
                emptySlotCount++;
            }
        }
        return emptySlotCount;
    }


    @Override
    public EquippableItem equippedItem() {
        return equippedItem;
    }

    @Override
    public AbstractItem getItem(int index) {
        if (index < 0 || index >= MAX_SLOT_COUNT) {
            return null;
        }
        return items[index];
    }

}