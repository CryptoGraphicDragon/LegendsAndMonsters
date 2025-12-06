package Heroes;

import Game.Render;
import Inventory.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Class to represent the player of the game
 */
public class Hero implements Render {
    public final static List<String> CLASSES = Arrays.asList("HEALER", "WARRIOR", "WIZARD");
    public final static List<String> RACES = Arrays.asList("HUMAN", "ELF", "DWARF");

    protected String name;
    protected final String className;
    protected String race;
    protected int armorClass;

    protected Inventory inventory = new Inventory();
    protected Weapon equipedWeapon;
    protected Armor equipedArmor;
    protected List<Spell> spells = new ArrayList<>();

    protected int level;
    protected int experience;

    protected int health;
    protected int mana;

    protected int strength;
    protected int dexterity;
    protected int intelligence;


    public Hero(String className, String race, int AC, int health, int mana, int strength, int dexterity, int intelligence){
        this.level = 0;
        this.experience = 0;
        this.className = className;
        this.race = race;
        this.armorClass = AC;
        this.health = health;
        this.mana = mana;
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getClassName() {
        return className;
    }

    public int getArmorClass() {
        if (equipedArmor != null) {
            return armorClass + equipedArmor.getBonus();
        }
        return armorClass;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race){
        this.race = race;
    }

    public int getLevel() {
        return level;
    }

    public int getHealth() {
        return health;
    }

    public boolean takeDamage(int damage){
        health = health-damage;
        return health <= 0;
    }

    public void heal(int healing){
        this.health += healing;
    }

    public int getMana() {
        return mana;
    }

    public void looseMana(int manaCost){
        mana = mana-manaCost;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public Armor getEquipedArmor() {
        return equipedArmor;
    }

    public void setEquipedArmor(Armor equipedArmor) {
        this.equipedArmor = equipedArmor;
    }

    public Weapon getEquipedWeapon() {
        return equipedWeapon;
    }

    public void setEquipedWeapon(Weapon equipedWeapon) {
        this.equipedWeapon = equipedWeapon;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public List<Spell> getSpells() {
        return spells;
    }

    public void addToInventory(Item item){
        inventory.addToInventory(item);
    }

    public void removeFromInventory(Item item){
        inventory.removeFromInventory(item);
    }

    public int getExperience() {
        return experience;
    }

    public int addEXP(int amount){
        experience += amount;
        int newLevel = experience / 100;
        int changed = 0;
        if (newLevel > level){
            changed = newLevel-level;
            level = newLevel;
        }
        return changed;
    }

    public void usePotion(Potion potion){
        String type = potion.getEffectType();
        if (Objects.equals(type, Potion.TYPES.get(0))){
            health = health + potion.getBonus();
        } else if (Objects.equals(type, Potion.TYPES.get(1))){
            mana = mana + potion.getBonus();
        } else if (Objects.equals(type, Potion.TYPES.get(2))){
            strength = strength + potion.getBonus();
        } else if (Objects.equals(type, Potion.TYPES.get(3))){
            dexterity = dexterity + potion.getBonus();
        } else if (Objects.equals(type, Potion.TYPES.get(4))){
            armorClass = armorClass + potion.getBonus();
        }

    }

    public void addSpell(Spell spell) {
        boolean found = false;
        for (Spell looking: spells){
            if (looking.getName().equals(spell.getName())) {
                found = true;
                break;
            }
        }
        if (!found){
            this.spells.add(spell);
        }
    }


    public void smallRender(){
        System.out.println("Name: "+name+"    Level: "+level+"    Class: "+className+"    Race: "+race);
        System.out.println("Health: "+health+"    Mana: "+mana);
    }

    @Override
    public void render() {
        System.out.println("Name: "+name+"    Level: "+level+"    Class: "+className+"    Race: "+race);
        System.out.println("Experience: "+experience);
        System.out.println("Health: "+health+"    Mana: "+mana);
        System.out.println("Dexterity: "+dexterity+"    Strength: "+strength+"    Intelligence: "+intelligence);
        if (equipedArmor != null) {
            System.out.println("Equipped Armor: " + equipedArmor.getName()+"    Bonus to AC: +"+equipedArmor.getBonus());
        }
        if (equipedWeapon != null) {
            System.out.println("Equipped Weapon: " + equipedWeapon.getName()+"    Damage die: D"+equipedWeapon.getDie());
        }
        System.out.println("Spells: ");
        for(Spell spell: spells){
            System.out.println("\t "+spell.getName()+"  Level: "+spell.getLevel()+"  Type: "+spell.getType()+"  Damage: "+spell.getDamage()+"  Mana Cost: "+spell.getManaCost()+"  DC: "+spell.getDC());
        }
        inventory.render();


    }
}
