package Game;

import Board.Board;
import Heroes.Hero;
import Heroes.HeroFactory;
import Inventory.ItemFactory;
import Monsters.MonsterFactory;

import java.util.List;

/**
 * Represents the components of the game
 * board
 * difficulty
 * items
 * heroes
 * monsters
 */
public class Game {
    private List<Hero> players;
    private Board board;
    private int difficulty;
    private ItemFactory allItems;
    private HeroFactory allHeroes;
    private MonsterFactory allMonsters;

    public Game(int rows, int cols, int difficulty){
        allHeroes = new HeroFactory();
        allItems = new ItemFactory();
        allMonsters = new MonsterFactory();
        this.difficulty = difficulty;
        this.board = new Board(rows, cols, difficulty);
    }

    public Board getBoard() {
        return board;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public MonsterFactory getAllMonsters() {
        return allMonsters;
    }

    public HeroFactory getAllHeroes() {
        return allHeroes;
    }

    public ItemFactory getAllItems() {
        return allItems;
    }
}
