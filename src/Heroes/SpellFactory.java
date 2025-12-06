package Heroes;

import Game.ReadFile;

import java.util.*;

/**
 * Class to read files so as to build usable spell classes
 */
public class SpellFactory {
    private final Map<Integer, List<Spell>> fireSpells = new HashMap<>();
    private final Map<Integer, List<Spell>> iceSpells = new HashMap<>();
    private final Map<Integer, List<Spell>> lightningSpells = new HashMap<>();
    private final Map<Integer, List<Spell>> healingSpells = new HashMap<>();

    public SpellFactory(){
        List<String> file = ReadFile.readFile("src/Files/FireSpells.txt");
        if (file != null) {
            for (String line : file) {
                String[] parts = line.split("   ");
                Spell add = new Spell(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), "FIRE");
                List<Spell> list = fireSpells.get(add.getLevel());
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(add);
                fireSpells.put(add.getLevel(), list);
            }
        }

        file = ReadFile.readFile("src/Files/IceSpells.txt");
        if (file != null) {
            for (String line : file) {
                String[] parts = line.split("   ");
                Spell add = new Spell(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), "ICE");
                List<Spell> list = iceSpells.get(add.getLevel());
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(add);
                iceSpells.put(add.getLevel(), list);
            }
        }

        file = ReadFile.readFile("src/Files/LightningSpells.txt");
        if (file != null) {
            for (String line : file) {
                String[] parts = line.split("   ");
                Spell add = new Spell(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), "LIGHTNING");
                List<Spell> list = lightningSpells.get(add.getLevel());
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(add);
                lightningSpells.put(add.getLevel(), list);
            }
        }

        file = ReadFile.readFile("src/Files/HealingSpells.txt");
        if (file != null) {
            for (String line : file) {
                String[] parts = line.split("   ");
                Spell add = new Spell(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), "HEALING");
                List<Spell> list = healingSpells.get(add.getLevel());
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(add);
                healingSpells.put(add.getLevel(), list);
            }
        }
    }

    public Map<Integer, List<Spell>> getFireSpells() {
        return fireSpells;
    }

    public Map<Integer, List<Spell>> getIceSpells() {
        return iceSpells;
    }

    public Map<Integer, List<Spell>> getLightningSpells() {
        return lightningSpells;
    }

    public Map<Integer, List<Spell>> getHealingSpells() {
        return healingSpells;
    }
}
