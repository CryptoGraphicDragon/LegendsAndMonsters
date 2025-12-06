package Heroes;

import Game.Dice;
import Game.ReadFile;
import Inventory.Weapon;

import java.util.*;

/**
 * Class to read files so as to build basic players (heroes)
 */
public class HeroFactory {
    private Hero baseHealer;
    private Hero baseWarrior;
    private Hero baseWizard;
    private List<String> healerNames = new ArrayList<>();
    private List<String> warriorNames = new ArrayList<>();
    private List<String> wizardNames = new ArrayList<>();
    private SpellFactory allSpells = new SpellFactory();

    public HeroFactory() {
        List<String> file = ReadFile.readFile("src/Files/Heroes.txt");
        if (file != null) {
            for (String line : file) {
                String[] parts = line.split("   ");
                Weapon basic = new Weapon("Rusted_Sword",0,10,"WEAPON",6);
                Spell fire = new Spell("Spark",0,10,20,10,"FIRE");
                Spell ice = new Spell("Chill_Touch",0,8,10,8,"ICE");
                Spell healing = new Spell("Minor_Healing",0,20,25,0,"HEALING");
                switch (parts[0]) {
                    case "Warrior":
                        baseWarrior = new Hero("WARRIOR", "DWARF", Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), Integer.parseInt(parts[5]), Integer.parseInt(parts[6]));
                        baseWarrior.addToInventory(basic);
                        baseWarrior.setEquipedWeapon(basic);
                        break;
                    case "Healer":
                        baseHealer = new Hero("HEALER", "HUMAN", Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), Integer.parseInt(parts[5]), Integer.parseInt(parts[6]));
                        baseHealer.addToInventory(basic);
                        baseHealer.setEquipedWeapon(basic);
                        baseHealer.addSpell(fire);
                        baseHealer.addSpell(healing);
                        break;
                    case "Wizard":
                        baseWizard = new Hero("WIZARD", "ELF", Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), Integer.parseInt(parts[5]), Integer.parseInt(parts[6]));
                        baseWizard.addSpell(fire);
                        baseWizard.addSpell(ice);
                        break;
                }
            }
        }

        file = ReadFile.readFile("src/Files/Names.txt");
        if (file != null) {
            for (String line : file) {
                String[] parts = line.split("   ");
                switch (parts[0]) {
                    case "Warrior":
                        warriorNames.add(parts[1]);
                        break;
                    case "Healer":
                        healerNames.add(parts[1]);
                        break;
                    case "Wizard":
                        wizardNames.add(parts[1]);
                        break;
                }
            }
        }
    }

    public Hero getBaseHealer() {
        return baseHealer;
    }

    public Hero getBaseWarrior() {
        return baseWarrior;
    }

    public Hero getBaseWizard() {
        return baseWizard;
    }

    public SpellFactory getAllSpells() {
        return allSpells;
    }

    public String getRandomName(String className){
        int rand;
        switch (className) {
            case "WARRIOR":
                rand = Dice.rollDie(warriorNames.size())-1;
                return warriorNames.get(rand);
            case "HEALER":
                rand = Dice.rollDie(healerNames.size())-1;
                return healerNames.get(rand);
            case "WIZARD":
                rand = Dice.rollDie(wizardNames.size())-1;
                return wizardNames.get(rand);
        }
        return "Mathew Mercer";
    }

    public Hero getRandomHero(){
        int rand = Dice.rollDie(3);
        if (rand == 1){
            return baseHealer;
        } else if (rand == 2){
            return baseWizard;
        } else {
            return baseWarrior;
        }
    }
}
