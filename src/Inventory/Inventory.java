package Inventory;

import Game.Render;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to hold all the player (hero) items
 */
public class Inventory implements Render {
    private List<Armor> armory;
    private List<Potion> potions;
    private List<Weapon> weapons;
    private int gold;

    public Inventory(){
        this.gold = 100;
        this.armory = new ArrayList<>();
        this.potions = new ArrayList<>();
        this.weapons = new ArrayList<>();
    }

    public void addToInventory(Item item){
        String type = item.getType();
        switch (type){
            case "ARMOR":
                armory.add((Armor) item);
                break;
            case "WEAPON":
                weapons.add((Weapon) item);
                break;
            case "POTION":
                potions.add((Potion) item);
                break;
        }
    }

    public void removeFromInventory(Item item){
        String type = item.getType();
        switch (type){
            case "ARMOR":
                armory.removeIf(armor -> armor.getName().equals(item.getName()));
                break;
            case "WEAPON":
                weapons.removeIf(weapon -> weapon.getName().equals(item.getName()));
                break;
            case "POTION":
                potions.removeIf(potion -> potion.getName().equals(item.getName()));
                break;
        }
    }

    public List<Armor> getArmory() {
        return armory;
    }

    public List<Potion> getPotions() {
        return potions;
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public int getGold() {
        return gold;
    }

    public void addToGold(int gold) {
        this.gold += gold;
    }

    public void removeGold(int gold){
        this.gold -= gold;
    }

    @Override
    public void render() {
        System.out.println("Inventory:");
        System.out.println("\t Gold: "+ gold);
        System.out.println("\t Potions: ");
        for(Potion potion: potions){
            System.out.println("\t \t "+potion.getName()+"  Level: "+potion.getLevel()+"  Cost: "+ potion.getPrice()+"  Effects: "+potion.getEffectType()+"  Bonus: +"+potion.getBonus());
        }
        System.out.println("\t Weapons: ");
        for(Weapon weapon: weapons){
            System.out.println("\t \t "+weapon.getName()+"  Level: "+weapon.getLevel()+"  Cost: "+ weapon.getPrice()+"  Damage Die: D"+weapon.getDie());
        }
        System.out.println("\t Armor: ");
        for(Armor armor: armory){
            System.out.println("\t \t "+armor.getName()+"  Level: "+armor.getLevel()+"  Cost: "+ armor.getPrice()+"  Effects: "+"  Bonus to AC: +"+armor.getBonus());
        }
    }
}
