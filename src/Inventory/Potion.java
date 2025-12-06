package Inventory;

import java.util.Arrays;
import java.util.List;

/**
 * Class to represent a usable boost to a player (hero)
 */
public class Potion extends Item{
    public final static List<String> TYPES = Arrays.asList("HEALTH", "MANA", "STRENGTH", "DEXTERITY", "ARMORCLASS");

    private final int bonus;
    private final String effectType;

    public Potion(String name, int level, int price, String itemType, int bonus, String potionType){
        super(name, level, price, itemType);
        this.bonus = bonus;
        this.effectType = potionType;
    }

    public int getBonus() {
        return bonus;
    }

    public String getEffectType() {
        return effectType;
    }
}