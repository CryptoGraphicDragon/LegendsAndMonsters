package Inventory;

import Game.ReadFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class to read files so as to build usable item classes
 */
public class ItemFactory {
    private final Map<Integer, List<Armor>> armory = new HashMap<>();
    private final Map<Integer, List<Potion>> potions = new HashMap<>();
    private final Map<Integer, List<Weapon>> weapons = new HashMap<>();

    public ItemFactory(){
        List<String> file = ReadFile.readFile("src/Files/Armory.txt");
        if (file != null) {
            for (String line : file) {
                String[] parts = line.split("   ");
                Armor add = new Armor(parts[0], Integer.parseInt(parts[2]), Integer.parseInt(parts[1]), "ARMOR", Integer.parseInt(parts[3]));
                List<Armor> list = armory.get(add.getLevel());
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(add);
                armory.put(add.getLevel(), list);
            }
        }

        file = ReadFile.readFile("src/Files/Potions.txt");
        if (file != null) {
            for (String line : file) {
                String[] parts = line.split("   ");
                Potion add = new Potion(parts[0], Integer.parseInt(parts[2]), Integer.parseInt(parts[1]), "POTION", Integer.parseInt(parts[3]), parts[4]);
                List<Potion> list = potions.get(add.getLevel());
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(add);
                potions.put(add.getLevel(), list);
            }
        }

        file = ReadFile.readFile("src/Files/Weaponry.txt");
        if (file != null) {
            for (String line : file) {
                String[] parts = line.split("   ");
                Weapon add = new Weapon(parts[0], Integer.parseInt(parts[2]), Integer.parseInt(parts[1]), "WEAPON", Integer.parseInt(parts[3]));
                List<Weapon> list = weapons.get(add.getLevel());
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(add);
                weapons.put(add.getLevel(), list);
            }
        }
    }

    public Map<Integer, List<Weapon>> getWeapons() {
        return weapons;
    }

    public Map<Integer, List<Potion>> getPotions() {
        return potions;
    }

    public Map<Integer, List<Armor>> getArmory() {
        return armory;
    }
}
