package Test;

import Board.Board;
import Board.Position;
import Heroes.Hero;
import Heroes.HeroFactory;
import Heroes.SpellFactory;
import Inventory.ItemFactory;
import Monsters.MonsterFactory;


public class Testing {
    public static void main(String[] args) {
        HeroFactory test = new HeroFactory();
        Hero test6 = test.getRandomHero();
        System.out.println(test6.addEXP(300));
        //System.out.println(test.getRandomHero());
//        Board test2 = new Board(4,4,3);
//        test2.render(new Position(0,0));
//        System.out.println(test2.getTile(0,0).getRank());
//        System.out.println(test2.getTile(1,0).getRank());
//        System.out.println(test2.getTile(2,0).getRank());
//        System.out.println(test2.getTile(3,0).getRank());
//        MonsterFactory test3 = new MonsterFactory();
//        System.out.println(test3.getMonstersOfLevel(1));
//        ItemFactory test4 = new ItemFactory();
//        System.out.println(test4.getArmory());
        //SpellFactory test5 = new SpellFactory();
    }
}
