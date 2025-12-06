package Game;

import Board.Position;
import Board.Shop;
import Heroes.Hero;
import Heroes.Spell;
import Inventory.Armor;
import Inventory.Potion;
import Inventory.Weapon;
import Monsters.Monster;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Controls the logic of the game
 * move
 * fight
 * shop
 * manage
 */
public class GameController {
    private Game game;
    private final int MIN_DIM = 3;
    private final int MAX_DIM = 10;
    private List<Hero> heroes = new ArrayList<>();
    private Position position = new Position(0, 0);

    public void playGame(){
        List<String> ascii = ReadFile.readFile("src/Files/ASCII.txt");
        for (String line: ascii){
            System.out.println(line);
        }

        System.out.println();
        System.out.println("Welcome to the game!");
        Scanner scanner = new Scanner(System.in);
        List<String> boardSizeOptions = new ArrayList<>();
        for (int i = MIN_DIM; i <= MAX_DIM; i++) {
            boardSizeOptions.add(Integer.toString(i));
        }

        System.out.println("What game difficulty would you like? 1. Easy 2. Medium 3. Hard");
        List<String> difficultyChoice = Arrays.asList("1", "2", "3");
        int difficulty = Integer.parseInt(Input.getInput(difficultyChoice));
        System.out.println("What is the height of your board? 3-10");
        String height = Input.getInput(boardSizeOptions);
        int rows = Integer.parseInt(height);
        System.out.println("What is the width of your board? 3-10");
        int cols = Integer.parseInt(Input.getInput(boardSizeOptions));
        game = new Game(rows, cols, difficulty);

        System.out.println("How many players would you like? 1-3");
        List<String> playerCountChoice = Arrays.asList("1", "2", "3");
        int playersCount = Integer.parseInt(Input.getInput(playerCountChoice));
        for (int i = 0; i < playersCount; i++){
            heroes.add(createHero());
        }

        boolean playAgain = true;
        position.setRow(rows-1);
        position.setCol(Dice.rollDie(cols)-1);
        System.out.println("\t Your board:");
        while (playAgain) {
            game.getBoard().render(position);
            System.out.println("Would you like to 1.move 2.manage your character? or 3.quit");
            int playersGameChoice = Integer.parseInt(Input.getInput(playerCountChoice));
            if(playersGameChoice==1){
                playAgain = move();
                if (playAgain){
                    if (game.getBoard().getTile(position.getRow(), position.getCol()).getType().equals("FIGHT")){
                        fightSpace();
                    }
                    if (game.getBoard().getTile(position.getRow(), position.getCol()).getType().equals("SHOP")){
                        shopSpace();
                    }
                }
            } else if (playersGameChoice == 2){
                manageCharacter();
            } else {
                playAgain = false;
            }
            if (getHeroHealth() == 0){
                System.out.println("All heroes are dead and your quest is over :(");
                playAgain = false;
            }
        }

        for (int i = 0; i < playersCount; i++){
            heroes.get(i).render();
        }

    }

    private Hero createHero(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Would you like to create your own character (otherwise will generate a random hero) (yes/no): ");
        String choice = scanner.nextLine().trim().toLowerCase();
        boolean create = choice.equals("yes") || choice.equals("y");
        Hero player;
        if (create) {
            System.out.println("Would you like to be a 1. Warrior  2.Healer  3.Wizard");
            List<String> playerCountChoice = Arrays.asList("1", "2", "3");
            int heroClass = Integer.parseInt(Input.getInput(playerCountChoice));
            if (heroClass == 1){
                player = game.getAllHeroes().getBaseWarrior();
            } else if (heroClass == 2){
                player = game.getAllHeroes().getBaseHealer();
            } else {
                player = game.getAllHeroes().getBaseWizard();
            }

            System.out.println("Would you like to be a 1. Elf  2. Human  3. Dwarf");
            int heroRace = Integer.parseInt(Input.getInput(playerCountChoice));
            if (heroRace == 1){
                player.setRace("Elf");
            } else if (heroRace == 2){
                player.setRace("Human");
            } else {
                player.setRace("Dwarf");
            }

            System.out.println("What is your name?");
            player.setName(Input.getName());
        } else {
            player =  game.getAllHeroes().getRandomHero();
            player.setName(game.getAllHeroes().getRandomName(player.getClassName()));
        }
        return player;
    }

    private boolean move(){
        List<String> options = new ArrayList<>();
        if (position.getRow() < game.getBoard().getRows()-1){
            if (!game.getBoard().getTile(position.getRow()+1, position.getCol()).getType().equals("BLANK")){
                options.add("down");
            }
        } if (position.getRow() > 0){
            if (!game.getBoard().getTile(position.getRow()-1, position.getCol()).getType().equals("BLANK")) {
                options.add("up");
            }
        } if (position.getCol() > 0){
            if (!game.getBoard().getTile(position.getRow(), position.getCol()-1).getType().equals("BLANK")) {
                options.add("left");
            }
        } if (position.getCol() < game.getBoard().getCols()-1){
            if (!game.getBoard().getTile(position.getRow(), position.getCol()+1).getType().equals("BLANK")) {
                options.add("right");
            }
        }
        System.out.println("Where would you like to move?");
        options.add("quit");
        System.out.println(options);
        String choice = Input.getInput(options);
        if (choice.equals("quit")){
            return false;
        } else {
            switch (choice) {
                case "up":
                    position.setRow(position.getRow() - 1);
                    break;
                case "down":
                    position.setRow(position.getRow() + 1);
                    break;
                case "right":
                    position.setCol(position.getCol() + 1);
                    break;
                case "left":
                    position.setCol(position.getCol() - 1);
                    break;
            }
            return true;
        }

    }

    private void fightSpace(){
        System.out.println();
        System.out.println("FIGHT!");
        int rank = game.getBoard().getTile(position.getRow(), position.getCol()).getRank();
        List<Monster> possibleMonsters = game.getAllMonsters().getMonstersOfLevel(rank);
        Monster encounter = possibleMonsters.get(Dice.rollDie(possibleMonsters.size())-1);
        while (encounter.getHealth() > 0 || getHeroHealth() > 0){
            System.out.println("You are fighting:");
            encounter.render();
            System.out.println("VS");
            for (Hero hero : heroes) {
                hero.smallRender();
                if (hero.getHealth() > 0) {
                    System.out.println("Would you like this hero to 1.attack with weapon or 2.use spell?");
                    List<String> playerCountChoice = Arrays.asList("1", "2");
                    int attackChoice = Integer.parseInt(Input.getInput(playerCountChoice));
                    switch (attackChoice) {
                        case 1:
                            int attack = Dice.rollDie(20) + hero.getDexterity();
                            if (attack >= encounter.getArmorClass()) {
                                if (hero.getEquipedWeapon() != null) {
                                    int roll = Dice.rollDie(hero.getEquipedWeapon().getDie());
                                    System.out.println("Swung your " + hero.getEquipedWeapon().getName() + " and did " + (roll + hero.getStrength()) + " damage");
                                    encounter.takeDamage(roll + hero.getStrength());
                                } else {
                                    int roll = Dice.rollDie(4);
                                    System.out.println("Swung your fists and did " + (roll + hero.getStrength()) + " damage");
                                    encounter.takeDamage(roll + hero.getStrength());
                                }
                            } else {
                                System.out.println("Attack missed");
                            }
                            break;
                        case 2:
                            Spell spell = chooseSpell(hero);
                            if (spell != null) {
                                if (hero.getMana() >= spell.getManaCost()) {
                                    if (spell.getType().equals("HEALING")) {
                                        Hero target;
                                        if (heroes.size() == 1) {
                                            target = heroes.get(0);
                                        } else {
                                            System.out.println("Which hero would you like to heal?");
                                            target = getPlayer();
                                        }
                                        target.heal(spell.getDamage());
                                        hero.looseMana(spell.getManaCost());
                                        System.out.println("Hero healed");
                                    } else {
                                        int DC = spell.getDC();
                                        int monsterCheck = encounter.getDexterity() + Dice.rollDie(20);
                                        if (monsterCheck >= DC) {
                                            System.out.println(encounter.getName() + " dodged and took half damage");
                                            hero.looseMana(spell.getManaCost());
                                            encounter.takeDamage(spell.getDamage() / 2);
                                        } else {
                                            System.out.println(encounter.getName() + " failed to dodge and took full damage");
                                            hero.looseMana(spell.getManaCost());
                                            encounter.takeDamage(spell.getDamage());
                                        }
                                    }
                                } else {
                                    System.out.println("Not enough mana");
                                }
                            } else {
                                System.out.println("You have no spells");
                            }
                            break;
                    }
                } else {
                    System.out.println(hero.getName()+ " is currently dead and can not attack");
                    break;
                }
            }
            if (encounter.getHealth() > 0) {
                Hero attacked = heroes.get(Dice.rollDie(heroes.size()) - 1);
                int attack = Dice.rollDie(20) + encounter.getDexterity();
                if (attack >= attacked.getArmorClass()) {
                    System.out.println(encounter.getName() + " attacked " + attacked.getName() + " for " + encounter.getAttackDamage() + " points of damage");
                    if (attacked.takeDamage(encounter.getAttackDamage())) {
                        System.out.println(attacked.getName() + " has died!");
                    }
                } else {
                    System.out.println(encounter.getName() + " attacked " + attacked.getName() + " and missed");
                }
            } else {
                System.out.println(encounter.getName() + " has died! You win!");
                int exp = (((encounter.getLevel() - heroes.get(0).getLevel())*100)/heroes.size())-(game.getDifficulty()*20);
                System.out.println("Each hero gets "+ exp+ " experience");
                for (Hero hero : heroes){
                    int changed = hero.addEXP(exp);
                    if (changed > 0){
                        levelUp(changed, hero.getLevel(), hero);
                    }
                }
            }
        }
    }

    private void levelUp(int levelsChanged, int newLevel, Hero hero) {
        System.out.println(hero.getName()+" has have leveled up! " + levelsChanged + " new levels earned. Gained bonuses and new spells");
        switch (hero.getClassName()) {
            case "WARRIOR":
                hero.setStrength(hero.getStrength() + 2 * levelsChanged);
                hero.setDexterity(hero.getDexterity() + 1 * levelsChanged);
                hero.heal( 20 * levelsChanged);
                hero.setMana(hero.getMana() + 5 * levelsChanged);
                break;
            case "WIZARD":
                hero.setStrength(hero.getStrength() + 1 * levelsChanged);
                hero.setDexterity(hero.getDexterity() + 1 * levelsChanged);
                hero.heal( 10 * levelsChanged);
                hero.setMana(hero.getMana() + 20 * levelsChanged);
                for (int i = 0; i < newLevel; i++) {
                    List<Spell> iceSpells = game.getAllHeroes().getAllSpells().getIceSpells().get(i);
                    if(iceSpells!=null) {
                        for (Spell ice : iceSpells) {
                            hero.addSpell(ice);
                        }
                    }
                    List<Spell> fireSpells = game.getAllHeroes().getAllSpells().getFireSpells().get(i);
                    if(fireSpells!=null) {
                        for (Spell fire : fireSpells) {
                            hero.addSpell(fire);
                        }
                    }
                    List<Spell> lightningSpells = game.getAllHeroes().getAllSpells().getLightningSpells().get(i);
                    if(lightningSpells!=null) {
                        for (Spell lightning : lightningSpells) {
                            hero.addSpell(lightning);
                        }
                    }
                }
                break;
            case "HEALER":
                hero.setStrength(hero.getStrength() + 1 * levelsChanged);
                hero.setDexterity(hero.getDexterity() + 2 * levelsChanged);
                hero.heal( 10 * levelsChanged);
                hero.setMana(hero.getMana() + 10 * levelsChanged);
                for (int i = 0; i < newLevel; i++) {
                    List<Spell> healingSpells = game.getAllHeroes().getAllSpells().getHealingSpells().get(i);
                    if(healingSpells!=null){
                        for(Spell healing : healingSpells){
                            hero.addSpell(healing);
                        }
                    }
                    List<Spell> fireSpells = game.getAllHeroes().getAllSpells().getFireSpells().get(i);
                    if(fireSpells!=null) {
                        for (Spell fire : fireSpells) {
                            hero.addSpell(fire);
                        }
                    }
                }
                break;
        }
    }

    private Spell chooseSpell(Hero player){
        List<String> names = new ArrayList<>();
        for (Spell spell : player.getSpells()){
            names.add(spell.getName());
        }
        if (!names.isEmpty()) {
            System.out.println("Which spell would you like to use?");
            System.out.println(names);
            String spellName = Input.getInput(names);
            for (Spell spell : player.getSpells()) {
                if (spell.getName().equals(spellName)) {
                    return spell;
                }
            }
        }
        return null;
    }

    private int getHeroHealth(){
        int total = 0;
        for (Hero hero : heroes) {
            total += hero.getHealth();
        }
        return total;
    }

    private void shopSpace(){
        System.out.println();
        System.out.println("Welcome to the shop!");
        Shop shop = new Shop(game.getBoard().getTile(position.getRow(), position.getCol()).getRank(), game.getAllItems());
        shop.render();
        Hero player;
        if (heroes.size() == 1){
            player = heroes.get(0);
        } else {
            System.out.println("Which hero would you like to send into the shop?");
            player = getPlayer();
        }
        System.out.println("Would you like to 1.buy 2.sell or 3.go back to the board?");
        List<String> playerCountChoice = Arrays.asList("1", "2", "3");
        int shopChoice = Integer.parseInt(Input.getInput(playerCountChoice));
        List<String> names = new ArrayList<>();
        switch (shopChoice) {
            case 1:
                System.out.println("Would you like to buy 1.potions 2.armor 3.weapons or 4.go back to the shop?");
                int buyChoice = Integer.parseInt(Input.getInput(playerCountChoice));
                String buyItem;
                switch (buyChoice){
                    case 1:
                        for (Potion potion : shop.getPotionList()){
                            names.add(potion.getName());
                        }
                        System.out.println("Which potion would you like?");
                        System.out.println(names);
                        buyItem = Input.getInput(names);
                        for (Potion potion : shop.getPotionList()){
                            if (potion.getName().equals(buyItem)){
                                if (player.getInventory().getGold() >= potion.getPrice()){
                                    System.out.println("Bought potion "+buyItem);
                                    player.getInventory().removeGold(potion.getPrice());
                                    player.getInventory().addToInventory(potion);
                                    shop.sellItem(potion);
                                } else {
                                    System.out.println("Not enough gold");
                                }
                            }
                        }
                        break;
                    case 2:
                        for (Armor armor : shop.getArmorList()){
                            names.add(armor.getName());
                        }
                        System.out.println("Which armor would you like?");
                        System.out.println(names);
                        buyItem = Input.getInput(names);
                        for (Armor armor : shop.getArmorList()){
                            if (armor.getName().equals(buyItem)){
                                if (player.getInventory().getGold() >= armor.getPrice()){
                                    System.out.println("Bought armor "+buyItem);
                                    player.getInventory().removeGold(armor.getPrice());
                                    player.getInventory().addToInventory(armor);
                                    shop.sellItem(armor);
                                } else {
                                    System.out.println("Not enough gold");
                                }
                            }
                        }
                        break;
                    case 3:
                        for (Weapon weapon : shop.getWeaponList()){
                            names.add(weapon.getName());
                        }
                        System.out.println("Which potion would you like?");
                        System.out.println(names);
                        buyItem = Input.getInput(names);
                        for (Weapon weapon : shop.getWeaponList()){
                            if (weapon.getName().equals(buyItem)){
                                if (player.getInventory().getGold() >= weapon.getPrice()){
                                    System.out.println("Bought weapon "+buyItem);
                                    player.getInventory().removeGold(weapon.getPrice());
                                    player.getInventory().addToInventory(weapon);
                                    shop.sellItem(weapon);
                                } else {
                                    System.out.println("Not enough gold");
                                }
                            }
                        }
                        break;
                }
                break;
            case 2:
                System.out.println("Would you like to sell 1.potions 2.armor 3.weapons or 4.go back to the shop?");
                int sellChoice = Integer.parseInt(Input.getInput(playerCountChoice));
                String sellItem;
                switch (sellChoice){
                    case 1:
                        for (Potion potion : player.getInventory().getPotions()){
                            names.add(potion.getName());
                        }
                        if (!names.isEmpty()){
                            System.out.println("Which potion would you like to sell?");
                            System.out.println(names);
                            sellItem = Input.getInput(names);
                            for (Potion potion : player.getInventory().getPotions()){
                                if (potion.getName().equals(sellItem)){
                                    System.out.println("Sold potion "+sellItem);
                                    player.getInventory().removeFromInventory(potion);
                                    player.getInventory().addToGold(potion.getPrice());
                                    shop.buyItem(potion.getName(), potion.getType());
                                }
                            }
                        } else {
                            System.out.println("You have no potions");
                        }
                        break;
                    case 2:
                        for (Armor armor : player.getInventory().getArmory()){
                            names.add(armor.getName());
                        }
                        if (!names.isEmpty()){
                            System.out.println("Which armor would you like to sell?");
                            System.out.println(names);
                            sellItem = Input.getInput(names);
                            for (Armor armor : player.getInventory().getArmory()){
                                if (armor.getName().equals(sellItem)){
                                    System.out.println("Sold armor "+sellItem);
                                    player.getInventory().removeFromInventory(armor);
                                    player.getInventory().addToGold(armor.getPrice());
                                    if (player.getEquipedArmor().getName().equals(sellItem)){
                                        System.out.println("Armor unequipped as it is sold");
                                        player.setEquipedArmor(null);
                                    }
                                    shop.buyItem(armor.getName(), armor.getType());
                                }
                            }
                        } else {
                            System.out.println("You have no armor");
                        }
                        break;
                    case 3:
                        for (Weapon weapon : player.getInventory().getWeapons()){
                            names.add(weapon.getName());
                        }
                        if (!names.isEmpty()){
                            System.out.println("Which potion would you like to sell?");
                            System.out.println(names);
                            sellItem = Input.getInput(names);
                            for (Weapon weapon : player.getInventory().getWeapons()){
                                if (weapon.getName().equals(sellItem)){
                                    System.out.println("Sold weapon "+sellItem);
                                    player.getInventory().removeFromInventory(weapon);
                                    player.getInventory().addToGold(weapon.getPrice());
                                    if (player.getEquipedWeapon().getName().equals(sellItem)){
                                        System.out.println("Weapon unequipped as it is sold");
                                        player.setEquipedWeapon(null);
                                    }
                                    shop.buyItem(weapon.getName(), weapon.getType());
                                }
                            }
                        } else {
                            System.out.println("You have no weapons");
                        }
                        break;
                }
                break;
            case 3:
                break;
        }
    }

    private Hero getPlayer(){
        List<String> names = new ArrayList<>();
        for (Hero hero : heroes) {
            names.add(hero.getName());
        }
        System.out.println(names);
        String chosen = Input.getInput(names);
        for (Hero hero : heroes) {
            if (hero.getName().equals(chosen)){
                return hero;
            }
        }
        return heroes.get(0);
    }

    private void manageCharacter(){
        for (Hero hero : heroes) {
            hero.render();
        }
        Hero player;
        if (heroes.size() == 1){
            player = heroes.get(0);
        } else {
            System.out.println("Which hero would you like to manage?");
            player = getPlayer();
        }

        if (player.getHealth() > 0) {
            System.out.println("Would you like to 1.change weapon 2.change armor 3.use a potion 4.use a spell or 5.go back to the board?");
            List<String> playerCountChoice = Arrays.asList("1", "2", "3", "4", "5");
            int manageChoice = Integer.parseInt(Input.getInput(playerCountChoice));
            List<String> names = new ArrayList<>();

            switch (manageChoice) {
                case 1:
                    for (Weapon weapon : player.getInventory().getWeapons()) {
                        names.add(weapon.getName());
                    }
                    if (!names.isEmpty()) {
                        System.out.println("Which weapon would you like to equip?");
                        System.out.println(names);
                        String weaponName = Input.getInput(names);
                        for (Weapon weapon : player.getInventory().getWeapons()) {
                            if (weapon.getName().equals(weaponName)) {
                                if (player.getLevel() < weapon.getLevel()) {
                                    System.out.println("Weapon is too high level for you to use");
                                } else {
                                    player.setEquipedWeapon(weapon);
                                    System.out.println("Weapon changed");
                                }
                            }
                        }
                    } else {
                        System.out.println("No weapons to equip");
                    }
                    break;
                case 2:
                    for (Armor armor : player.getInventory().getArmory()) {
                        names.add(armor.getName());
                    }
                    if (!names.isEmpty()) {
                        System.out.println("Which armor would you like to equip?");
                        System.out.println(names);
                        String armorName = Input.getInput(names);
                        for (Armor armor : player.getInventory().getArmory()) {
                            if (armor.getName().equals(armorName)) {
                                if (player.getLevel() < armor.getLevel()) {
                                    System.out.println("Armor is too high level for you to use");
                                } else {
                                    player.setEquipedArmor(armor);
                                    System.out.println("Armor changed");
                                }
                            }
                        }
                    } else {
                        System.out.println("No armor to equip");
                    }
                    break;
                case 3:
                    for (Potion potion : player.getInventory().getPotions()) {
                        names.add(potion.getName());
                    }
                    if (!names.isEmpty()) {
                        System.out.println("Which potion would you like to use?");
                        System.out.println(names);
                        String potionName = Input.getInput(names);
                        for (Potion potion : player.getInventory().getPotions()) {
                            if (potion.getName().equals(potionName)) {
                                player.usePotion(potion);
                                player.getInventory().removeFromInventory(potion);
                            }
                        }
                    } else {
                        System.out.println("No potions to use");
                    }
                    break;
                case 4:
                    for (Spell spell : player.getSpells()) {
                        names.add(spell.getName());
                    }
                    if (!names.isEmpty()) {
                        System.out.println("Which spell would you like to use?");
                        System.out.println(names);
                        String spellName = Input.getInput(names);
                        for (Spell spell : player.getSpells()) {
                            if (spell.getName().equals(spellName)) {
                                if (spell.getType().equals("HEALING")) {
                                    if (player.getMana() >= spell.getManaCost()) {
                                        Hero target;
                                        if (heroes.size() == 1) {
                                            target = heroes.get(0);
                                        } else {
                                            System.out.println("Which hero would you like to heal?");
                                            target = getPlayer();
                                        }
                                        target.heal(spell.getDamage());
                                        player.looseMana(spell.getManaCost());
                                        System.out.println("Hero healed");
                                    } else {
                                        System.out.println("Not enough mana");
                                    }
                                } else {
                                    System.out.println("There is no use for combat spells outside battle");
                                }
                            }
                        }
                    } else {
                        System.out.println("No spells to use");
                    }
                    break;
                case 5:
                    break;
            }
        } else {
            System.out.println(player.getName()+" is currently dead and can't do anything");
        }
    }
}
