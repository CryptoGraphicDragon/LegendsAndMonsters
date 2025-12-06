package Inventory;

import java.util.Arrays;
import java.util.List;

/**
 * Parent Class to represent an item a player (hero) can use
 */
public class Item {
    public final static List<String> TYPES = Arrays.asList("ARMOR", "WEAPON", "POTION");

    protected final String name;
    protected final int level;
    protected int price;
    protected final String type;

    public Item(String name, int level, int price, String type){
        this.name = name;
        this.level = level;
        this.price = price;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
