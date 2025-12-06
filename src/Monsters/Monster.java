package Monsters;

import Game.Render;

/**
 * Class to represent an enemy a player (hero) can fight)
 */
public class Monster implements Render {
    private final String name;
    private final int armorClass;
    private final int level;
    private int health;
    private final int attackDamage;
    private final int dexterity;

    public Monster(String name, int AC, int level, int health, int attack, int dex){
        this.name = name;
        this.armorClass = AC;
        this.level = level;
        this.health = health;
        this.attackDamage = attack;
        this.dexterity = dex;
    }

    public String getName() {
        return name;
    }

    public int getArmorClass() {
        return armorClass;
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

    public int getAttackDamage() {
        return attackDamage;
    }

    public int getDexterity() {
        return dexterity;
    }

    @Override
    public void render() {
        System.out.println("Name: "+name+"    Level: "+level+"    Health: "+health);
    }
}
