package Monsters;

import Game.ReadFile;

import java.util.*;

/**
 * Class to read files so as to build usable monster classes
 */
public class MonsterFactory {
    private final Map<Integer, List<Monster>> allMonsters = new HashMap<>();

    public MonsterFactory() {
        List<String> file = ReadFile.readFile("src/Files/Monsters.txt");
        if (file != null) {
            for (String line : file) {
                String[] parts = line.split("   ");
                Monster add = new Monster(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), Integer.parseInt(parts[5]));
                List<Monster> list = allMonsters.get(add.getLevel());
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(add);
                allMonsters.put(add.getLevel(), list);
            }
        }
    }

    public Map<Integer, List<Monster>> getAllMonsters() {
        return allMonsters;
    }

    public List<Monster> getMonstersOfLevel(int level){
        return allMonsters.get(level);
    }
}
