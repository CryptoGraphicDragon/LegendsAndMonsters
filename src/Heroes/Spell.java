package Heroes;

import java.util.Arrays;
import java.util.List;

/**
 * Class to represent a spell that can be cast by a player (hero)
 */
public class Spell{
    public final static List<String> TYPES = Arrays.asList("FIRE", "ICE", "LIGHTNING", "HEALTH");

    private final String name;
    private final int level;
    private final int damage;
    private final String type;
    private final int DC;
    private final int manaCost;

    public Spell(String name, int level, int damage, int manaCost, int DC, String type){
        this.name = name;
        this.level = level;
        this.damage = damage;
        this.type = type;
        this.DC = DC;
        this.manaCost = manaCost;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public String getType() {
        return type;
    }

    public int getDC() {
        return DC;
    }

    public int getLevel() {
        return level;
    }

    public int getManaCost() {
        return manaCost;
    }
}