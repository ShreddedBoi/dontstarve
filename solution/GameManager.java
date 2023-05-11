package prog1.kotprog.dontstarve.solution;

import prog1.kotprog.dontstarve.solution.character.BaseCharacter;
import prog1.kotprog.dontstarve.solution.character.Character;
import prog1.kotprog.dontstarve.solution.character.actions.*;
import prog1.kotprog.dontstarve.solution.inventory.items.*;
import prog1.kotprog.dontstarve.solution.level.BaseField;
import prog1.kotprog.dontstarve.solution.level.Field;
import prog1.kotprog.dontstarve.solution.level.Level;
import prog1.kotprog.dontstarve.solution.utility.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * A játék működéséért felelős osztály.<br>
 * Az osztály a singleton tervezési mintát valósítja meg.
 */
public final class GameManager {
    /**
     * Az osztályból létrehozott egyetlen példány (nem lehet final).
     */
    private static GameManager instance = new GameManager();
    /**
     * Random objektum, amit a játék során használni lehet.
     */
    private final Random random = new Random();
    List<Character> characters = new ArrayList<>();
    Field[][] fields;
    boolean tutorialMode;
    private Level level;
    private boolean isOn = false;
    private boolean isLoaded = false;
    private int elapsedTime = 0;

    /**
     * Az osztály privát konstruktora.
     */
    private GameManager() {
    }

    /**
     * Az osztályból létrehozott példány elérésére szolgáló metódus.
     *
     * @return az osztályból létrehozott példány.
     */
    public static GameManager getInstance() {
        return instance;
    }


    /**
     * A létrehozott random objektum elérésére szolgáló metódus.
     *
     * @return a létrehozott random objektum
     */
    public Random getRandom() {
        return random;
    }

    /**
     * A játékos pozícióját random generáló metódus.
     * vegigiterál a mezőkön ellenőrzi, nem víz-e, valamint végigmegy a characters listán és egyesével megnézi
     * a már listában lévő elemek pozíciója elég távol van-e az új pozíciótól. ha igen visszatér az új értékkel. Ha nem
     * akkor továbblép
     *
     * @return az új pozíció
     */
    public Position generateNewPosition() {
        float distance = 50;
        boolean valid;
        while (distance >= 5) {
            for (int i = 0; i < level.getWidth(); i++) {
                for (int j = 0; j < level.getHeight(); j++) {
                    if (fields[i][j].isWalkable()) {
                        valid = true;
                        for (Character character : characters) {
                            float charPosX = character.getCurrentPosition().getX();
                            float charPosY = character.getCurrentPosition().getY();
                            Position pos = new Position(charPosX, charPosY);
                            Position newCharPos = new Position(i, j);
                            if (calculateDistance(pos, newCharPos) < distance) {
                                valid = false;
                            }
                        }
                        if (valid) {
                            return new Position(i, j);
                        }
                    }
                }
            }
            distance -= 5;
        }
        return new Position(0, 0);
    }


    /**
     * A két pozíció közötti távolságot kiszámítása.
     *
     * @param position1 első karakter pozíció.
     * @param position2 második.
     * @return távolság.
     */

    private float calculateDistance(Position position1, Position position2) {
        float xDiff = position2.getX() - position1.getX();
        float yDiff = position2.getY() - position1.getY();
        float straightDist = (float) Math.pow(xDiff, 2.0);
        float diagonalDist = (float) Math.pow(yDiff, 2.0);
        return (float) Math.sqrt(straightDist + diagonalDist);
    }

    /**
     * Egy karakter becsatlakozása a játékba.<br>
     * A becsatlakozásnak számos feltétele van:
     * <ul>
     *     <li>A pálya már be lett töltve</li>
     *     <li>A játék még nem kezdődött el</li>
     *     <li>Csak egyetlen emberi játékos lehet, a többi karaktert a gép irányítja</li>
     *     <li>A névnek egyedinek kell lennie</li>
     * </ul>
     *
     * @param name   a csatlakozni kívánt karakter neve
     * @param player igaz, ha emberi játékosról van szó; hamis egyébként
     * @return a karakter pozíciója a pályán, vagy (Integer.MAX_VALUE, Integer.MAX_VALUE) ha nem sikerült hozzáadni
     */

    public Position joinCharacter(String name, boolean player) {
        Position forRet = new Position(Integer.MAX_VALUE, Integer.MAX_VALUE); //sikertelenség esetén a visszatérési érték

        for (Character character : this.characters) {
            if (character.getName().equals(name)) {     //ha van már ilyen nevű karakter
                return forRet;
            }
        }
        if (player) {
            for (Character character : this.characters) { //ha van már 1 játékos
                if (character.isPlayer()) {
                    return forRet;
                }
            }
        }
        if (level == null || isOn) {        // ha nincs még betöltve a pálya vagy elkezdődött már a játék
            return forRet;
        }
        Character newPlayer = new Character(name, player);      //új charakter objektum létrehozás
        Position newPosition = generateNewPosition();           //új pozíció generálás
        newPlayer.setPosition(newPosition);                     //karakterhez rendelem az új pozíciót
        characters.add(newPlayer);                              //csatlakozott karakterek listájához adom
        setItemsOnCreation(newPlayer);                          //random inventory elkészül
        return newPlayer.getCurrentPosition();
    }

    /**
     * Inventoryhoz 4 random item hozzárendelése
     *
     * @param character beállítja az adott karakternek a kezdő inventoriját
     */

    public void setItemsOnCreation(Character character) {
        AbstractItem[] itemsOnCreation = {new ItemRawBerry(1), new ItemTwig(1), new ItemStone(1), new ItemRawCarrot(1), new ItemLog(1)};
        for (int i = 0; i < 4; i++) {
            AbstractItem item = itemsOnCreation[random.nextInt(4)];
            character.getInventory().addItem(item);
        }
    }

    /**
     * Egy adott nevű karakter lekérésére szolgáló metódus.<br>
     *
     * @param name A lekérdezni kívánt karakter neve
     * @return Az adott nevű karakter objektum, vagy null, ha már a karakter meghalt vagy nem is létezett
     */
    public BaseCharacter getCharacter(String name) {
        BaseCharacter forReturn = null;
        for (BaseCharacter character : characters) {
            if (character.getName().equals(name)) {
                forReturn = character;
            }
        }
        return forReturn;
    }

    /**
     * Ezen metódus segítségével lekérdezhető, hogy hány karakter van még életben.
     *
     * @return Az életben lévő karakterek száma
     */
    public int remainingCharacters() {
        int remainingChars = 0;

        for (BaseCharacter character : characters) {
            if (character.getHp() > 0) {
                remainingChars++;
            }
        }
        return remainingChars;
    }

    /**
     * Ezen metódus segítségével történhet meg a pálya betöltése.<br>
     * A pálya betöltésének azelőtt kell megtörténnie, hogy akár 1 karakter is csatlakozott volna a játékhoz.<br>
     * A pálya egyetlen alkalommal tölthető be, később nem módosítható.
     *
     * @param level a fájlból betöltött pálya
     */
    public void loadLevel(Level level) {
        this.level = level;
        if (characters.size() > 0 || isLoaded) return;
        int width = level.getWidth();
        int height = level.getHeight();
        fields = new Field[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                fields[x][y] = new Field(x, y, level.getColor(x, y));
            }
        }
        isLoaded = true;
    }

    /**
     * A pálya egy adott pozícióján lévő mező lekérdezésére szolgáló metódus.
     *
     * @param x a vízszintes (x) irányú koordináta
     * @param y a függőleges (y) irányú koordináta
     * @return az adott koordinátán lévő mező
     */
    public BaseField getField(int x, int y) {
        return fields[x][y];
    }

    /**
     * A játék megkezdésére szolgáló metódus.<br>
     * A játék csak akkor kezdhető el, ha legalább 2 karakter már a pályán van,
     * és közülük pontosan az egyik az emberi játékos által irányított karakter.
     *
     * @return igaz, ha sikerült elkezdeni a játékot; hamis egyébként
     */
    public boolean startGame() {
        int counter = 0;
        if (characters.size() >= 2) {
            for (Character c : characters) {
                if (c.isPlayer()) {
                    counter++;
                }
            }
        }
        if (counter == 1) {
            isOn = true;
        }
        return isOn;
    }

    /**
     * Ez a metódus jelzi, hogy 1 időegység eltelt.<br>
     * A metódus először lekezeli a felhasználói inputot, majd a gépi ellenfelek cselekvését végzi el,
     * végül eltelik egy időegység.<br>
     * Csak akkor csinál bármit is, ha a játék már elkezdődött, de még nem fejeződött be.
     *
     * @param action az emberi játékos által végrehajtani kívánt akció
     */
    public void tick(Action action) {
        for (Character character : characters) {
            if (character.isPlayer()) {
                if (action instanceof ActionStep) {
                    Position newPos = character.actionStep(((ActionStep) action).getDirection());
                    int newX = (int) newPos.getX();
                    int newY = (int) newPos.getY();
                    if ((newX >= 0 && newX < level.getWidth()) && (newY >= 0 && newY < level.getHeight()) && fields[newX][newY].isWalkable()) {
                        character.setPosition(newPos);
                    }
                } else if (action instanceof ActionNone) {
                    break;
                } else if (action instanceof ActionDropItem) {
                    character.getInventory().dropItem(((ActionDropItem) action).getIndex());
                } else if (action instanceof ActionUnequip) {
                    character.getInventory().unequipItem();
                } else if (action instanceof ActionSwapItems) {
                    character.getInventory().swapItems(((ActionSwapItems) action).getIndex1(), ((ActionSwapItems) action).getIndex2());
                } else if (action instanceof ActionCombineItems) {
                    character.getInventory().combineItems(((ActionCombineItems) action).getIndex1(), ((ActionCombineItems) action).getIndex2());
                } else if (action instanceof ActionCook) {
                    character.getInventory().cookItem(((ActionCook) action).getIndex());
                } else if (action instanceof ActionEquip) {
                    character.getInventory().equipItem(((ActionEquip) action).getIndex());
                } else if (action instanceof ActionMoveItem) {
                    character.getInventory().moveItem(((ActionMoveItem) action).getOldIndex(), ((ActionMoveItem) action).getNewIndex());
                } else if (action instanceof ActionAttack) {
                    for (Character c : characters) {
                        if (!c.isPlayer() && calculateDistance(character.getCurrentPosition(), c.getCurrentPosition()) <= 2) {
                            if(character.getInventory().equippedItem() != null) {
                                c.setHp(c.getHp() - character.getInventory().equippedItem().getDamage());
                                character.getInventory().equippedItem().setDurability(character.getInventory().equippedItem().getDurability() - 1);
                                break;
                            }else if(character.getInventory().equippedItem() == null){
                                c.setHp(c.getHp()-4);
                                break;
                            }
                        }
                    }
                }
            }
        }
        elapsedTime++;
        for (Character character : characters) {
            character.setHunger(character.getHunger() - 0.4f);
        }
    }

    /**
     * Ezen metódus segítségével lekérdezhető az aktuális időpillanat.<br>
     * A játék kezdetekor ez az érték 0 (tehát a legelső időpillanatban az idő 0),
     * majd minden eltelt időpillanat után 1-gyel növelődik.
     *
     * @return az aktuális időpillanat
     */
    public int time() {
        return elapsedTime;
    }

    /**
     * Ezen metódus segítségével lekérdezhetjük a játék győztesét.<br>
     * Amennyiben a játéknak még nincs vége (vagy esetleg nincs győztes), akkor null-t ad vissza.
     *
     * @return a győztes karakter vagy null
     */
    public BaseCharacter getWinner() {
        int counter = 0;
        Character winner = null;
        for (Character c : characters) {
            if (c.getHp() > 0) {
                counter++;
                winner = c;
            }
        }
        if (counter != 1) {
            winner = null;
        }
        return winner;

    }

    /**
     * Ezen metódus segítségével lekérdezhetjük, hogy a játék elkezdődött-e már.
     *
     * @return igaz, ha a játék már elkezdődött; hamis egyébként
     */
    public boolean isGameStarted() {
        return isOn;
    }

    /**
     * Ezen metódus segítségével lekérdezhetjük, hogy a játék befejeződött-e már.
     *
     * @return igaz, ha a játék már befejeződött; hamis egyébként
     */
    public boolean isGameEnded() {
        return getWinner() != null;
    }

    /**
     * Ezen metódus segítségével beállítható, hogy a játékot tutorial módban szeretnénk-e elindítani.<br>
     * Alapértelmezetten (ha nem mondunk semmit) nem tutorial módban indul el a játék.<br>
     * Tutorial módban a gépi karakterek nem végeznek cselekvést, csak egy helyben állnak.<br>
     * A tutorial mód beállítása még a karakterek csatlakozása előtt történik.
     *
     * @param tutorial igaz, amennyiben tutorial módot szeretnénk; hamis egyébként
     */
    public void setTutorial(boolean tutorial) {
        tutorialMode = tutorial;
    }

}
