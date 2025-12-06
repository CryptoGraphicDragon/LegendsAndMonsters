package Board;

import Game.Render;
import Inventory.*;

import java.util.List;

/**
 * Represents a type of tile on the board where a player can purchase items.
 */
public class Shop implements Render {
    private List<Armor> armorList;
    private List<Potion> potionList;
    private List<Weapon> weaponList;
    private final int rank;

    public Shop(int rank, ItemFactory factory) {
        this.rank = rank;
        createShop(factory);
    }

    private void createShop(ItemFactory factory){
        this.potionList = factory.getPotions().get(rank);
        this.armorList = factory.getArmory().get(rank);
        this.weaponList = factory.getWeapons().get(rank);
    }

    public Item buyItem(String itemName, String itemType){
        switch (itemType) {
            case "ARMOR":
                for (int i = 0; i < armorList.size(); i++) {
                    if (armorList.get(i).getName().equals(itemName)) {
                        armorList.remove(i);
                        return armorList.get(i);
                    }
                }
                break;
            case "WEAPON":
                for (int i = 0; i < weaponList.size(); i++) {
                    if (weaponList.get(i).getName().equals(itemName)) {
                        weaponList.remove(i);
                        return weaponList.get(i);
                    }
                }
                break;
            case "POTION":
                for (int i = 0; i < potionList.size(); i++) {
                    if (potionList.get(i).getName().equals(itemName)) {
                        potionList.remove(i);
                        return potionList.get(i);
                    }
                }
                break;
        }
        return null;
    }

    public void sellItem(Item item){
        item.setPrice((int) (item.getPrice()+ (item.getPrice()*.2)));
        switch (item.getType()) {
            case "ARMOR":
                armorList.add((Armor) item);
                break;
            case "WEAPON":
                weaponList.add((Weapon) item);
                break;
            case "POTION":
                potionList.add((Potion) item);
                break;
        }
    }

    public List<Armor> getArmorList() {
        return armorList;
    }

    public List<Potion> getPotionList() {
        return potionList;
    }

    public List<Weapon> getWeaponList() {
        return weaponList;
    }

    @Override
    public void render() {
        System.out.println("Shop:");
        System.out.println("\t Potions: ");
        for(Potion potion: potionList){
            System.out.println("\t \t "+potion.getName()+"  Level: "+potion.getLevel()+"  Cost: "+ potion.getPrice()+"  Effects: "+potion.getEffectType()+"  Bonus: +"+potion.getBonus());
        }
        System.out.println("\t Weapons: ");
        for(Weapon weapon: weaponList){
            System.out.println("\t \t "+weapon.getName()+"  Level: "+weapon.getLevel()+"  Cost: "+ weapon.getPrice()+"  Damage Die: D"+weapon.getDie());
        }
        System.out.println("\t Armor: ");
        for(Armor armor: armorList){
            System.out.println("\t \t "+armor.getName()+"  Level: "+armor.getLevel()+"  Cost: "+ armor.getPrice()+"  Effects: "+"  Bonus to AC: +"+armor.getBonus());
        }
    }
}