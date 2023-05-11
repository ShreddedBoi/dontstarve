package prog1.kotprog.dontstarve.solution.level;

import prog1.kotprog.dontstarve.java.dontstarve.solution.level.BaseField;
import prog1.kotprog.dontstarve.solution.inventory.items.AbstractItem;


public class Field implements BaseField {
    final int x;
    final int y;
    boolean walkable = true;
    boolean tree;
    boolean stone;
    boolean twig;
    boolean berry;
    boolean carrot;
    boolean fire;

    public Field(int x, int y, int color) {
        this.x = x;
        this.y = y;
        colorToField(color);
    }

    public void colorToField(int color) {
        switch (color) {
            case 0xFF3264C8:
                setWalkable(false);
                break;
            case 0xFFC86432:
                setTree(true);
                break;
            case 0xFFC8C8C8:
                setStone(true);
                break;
            case 0xFFF0B478:
                setTwig(true);
                break;
            case 0xFFFF0000:
                setBerry(true);
                break;
            case 0xFFFAC800:
                setCarrot(true);
                break;
        }
    }

    @Override
    public boolean isWalkable() {
        return walkable;
    }

    public void setWalkable(boolean walkable) {

        this.walkable = walkable;
    }

    public void setTree(boolean tree) {
        this.tree = tree;
    }

    public void setStone(boolean stone) {
        this.stone = stone;
    }

    public void setTwig(boolean twig) {
        this.twig = twig;
    }

    public void setBerry(boolean berry) {
        this.berry = berry;
    }

    public void setCarrot(boolean carrot) {
        this.carrot = carrot;
    }

    public void setFire(boolean fire) {
        this.fire = fire;
    }

    /**
     * van-e fa az adott mezőn
     * @return true ha van
     */
    @Override
    public boolean hasTree() {
        return tree;
    }

    /**
     * van-e kő az azon mezőn
     * @return true ha van
     */
    @Override
    public boolean hasStone() {
        return stone;
    }

    /**
     * van-e gally a mezőn
     * @return true ha van
     */
    @Override
    public boolean hasTwig() {
        return twig;
    }

    /**
     * van-e bogyó a mezőn
     * @return true ha van
     */
    @Override
    public boolean hasBerry() {
        return berry;
    }

    /**
     * van-e répa a mezőn
     * @return true ha van
     */
    @Override
    public boolean hasCarrot() {
        return carrot;
    }

    /**
     * van-e tűz a mezőn
     * @return true ha van
     */
    @Override
    public boolean hasFire() {
        return fire;
    }

    /**
     * Ezen metódus segítségével a mezőn lévő tárgyak lekérdezhetők.<br>
     * A tömbben az a tárgy jön hamarabb, amelyik korábban került az adott mezőre.<br>
     * A karakter ha felvesz egy tárgyat, akkor a legkorábban a mezőre kerülő tárgyat fogja felvenni.<br>
     * Ha nem sikerül felvenni, akkor a (nem) felvett tárgy a tömb végére kerül.
     * @return a mezőn lévő tárgyak
     */
    @Override
    public AbstractItem[] items() {
        return new AbstractItem[0];
    }


}
