package Inventory;

/**
 * Class to represent a usable tool for a player (hero) to cause damage
 */
public class Weapon extends Item{
    private final int die;

    public Weapon(String name, int level, int price, String type, int die){
        super(name, level, price, type);
        this.die = die;
    }

    public int getDie() {
        return die;
    }
}