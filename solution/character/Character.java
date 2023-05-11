package prog1.kotprog.dontstarve.solution.character;

import prog1.kotprog.dontstarve.java.dontstarve.solution.character.BaseCharacter;
import prog1.kotprog.dontstarve.solution.character.actions.Action;
import prog1.kotprog.dontstarve.solution.inventory.BaseInventory;
import prog1.kotprog.dontstarve.solution.inventory.Inventory;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemAxe;
import prog1.kotprog.dontstarve.solution.utility.Direction;
import prog1.kotprog.dontstarve.solution.utility.Position;

public class Character implements BaseCharacter {
    final String name;
    final boolean player;
    float speed;
    float hunger;
    float hp;
    BaseInventory inventory;
    Position position;
    Action lastAction;

    /**
     * karakter konstruktor
     *
     * @param name   karakter neve
     * @param player játékos-e
     */
    public Character(String name, boolean player) {
        this.speed = 1;
        this.hunger = 100;
        this.hp = 100;
        this.inventory = new Inventory();
        this.name = name;
        this.player = player;
    }

    /**
     * beállítja egy karakter helyzetét
     *
     * @param position karakter új pozíciója
     */
    public void setPosition(Position position) {
        this.position = new Position(position.getX(), position.getY());
    }

    @Override
    public float getSpeed() {
        return this.speed;
    }

    /**
     * karakter sebességének beállítása új értékre
     *
     * @param speed új sebesség
     */
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    @Override
    public float getHunger() {
        return this.hunger;
    }

    /**
     * éhség beállítása új értékre
     *
     * @param hunger új éhség
     */
    public void setHunger(float hunger) {
        this.hunger = hunger;
    }

    @Override
    public float getHp() {
        return this.hp;
    }

    /**
     * életerő beállítása
     *
     * @param hp új életerő
     */
    public void setHp(float hp) {
        this.hp = hp;
    }

    @Override
    public BaseInventory getInventory() {
        return this.inventory;
    }


    @Override
    public Position getCurrentPosition() {
        return this.position;
    }

    @Override
    public Action getLastAction() {
        return this.lastAction;
    }


    @Override
    public String getName() {
        return this.name;
    }

    public boolean isPlayer() {
        return this.player;
    }

    /**
     * lépés akció - nem működik
     * pályán belül kell maradnia és nem léphet olyan mezőre amin víz van
     *
     * @param direction irány megadása
     * @return új pozíció
     */
    public Position actionStep(Direction direction) {
        float multiplyAccHunger = 1;
        float multyilyAccHealth = 1;
        if (this.getHunger() < 50 && this.getHunger() >= 20) {
            multiplyAccHunger = 0.9f;
        } else if (this.getHunger() > 0 && this.getHunger() < 20) {
            multiplyAccHunger = 0.8f;
        } else if (this.getHunger() == 0) {
            multiplyAccHunger = 0.5f;
        }
        if (this.getHp() >= 30 && this.getHp() < 50) {
            multyilyAccHealth = 0.9f;
        } else if (this.getHp() >= 10 && this.getHp() < 30) {
            multyilyAccHealth = 0.75f;
        } else if (this.getHp() >= 0 && this.getHp() < 10) {
            multyilyAccHealth = 0.6f;
        }
        float multiplyer = multiplyAccHunger * multyilyAccHealth;
        setSpeed(multiplyer * 1);
        Position pos = this.getCurrentPosition();
        Position newPos = null;
        switch (direction) {
            case UP:
                newPos = new Position(this.getCurrentPosition().getX(), this.getCurrentPosition().getY() - this.speed);
                break;
            case DOWN:
                newPos = new Position(this.getCurrentPosition().getX(), this.getCurrentPosition().getY() + this.speed);
                break;
            case LEFT:
                newPos = new Position(this.getCurrentPosition().getX() - this.speed, this.getCurrentPosition().getY());
                break;
            case RIGHT:
                newPos = new Position(this.getCurrentPosition().getX() + this.speed, this.getCurrentPosition().getY());
                break;
        }
        return newPos;
    }

}