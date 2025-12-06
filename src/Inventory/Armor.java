package Inventory;

/**
 * Class to represent armor a player (hero) can wear
 */
public class Armor extends Item{
    private final int bonus;

    public Armor(String name, int level, int price, String type, int bonus){
        super(name, level, price, type);
        this.bonus = bonus;
    }

    public int getBonus() {
        return bonus;
    }
}
