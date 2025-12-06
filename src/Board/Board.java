package Board;

import Game.Dice;

/**
 * Represents the game board, a grid of tiles.
 */
public class Board{
    protected final int rows;
    protected final int cols;
    private Tile[][] grid;
    private final int difficulty;

    public Board(int rows, int cols, int difficulty){
        this.rows = rows;
        this.cols = cols;
        this.grid = new Tile[rows][cols];
        this.difficulty = difficulty;
        createGrid();
    }

    private void createGrid(){
        int rank = -1;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                String type = "FIGHT";
                if (r == rows-1){
                    rank = difficulty;
                } else {
                    rank = rows+difficulty-r-1;
                    int rand = Dice.rollDie(20);
                    if(rand == 1 || rand == 2){
                        type = "BLANK";
                    } if(rand == 19 || rand == 20){
                        type = "SHOP";
                    }
                }

                grid[r][c] = new Tile(type , rank);
            }
        }
    }

    public Tile getTile(int row, int col){
        return grid[row][col];
    }

    public int getRows(){return rows;}
    public int getCols(){return cols;}


    public void render(Position current) {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                System.out.print("+------");
            }
            System.out.println("+");
            for (int c = 0; c <cols; c++) {
                Tile tile = grid[r][c];
                String val = " ";
                if (tile.getType().equals("BLANK")){
                    val = "XXX";
                }
                if (tile.getType().equals("SHOP")){
                    val = "Shop";
                }
                if (current.getRow() == r && current.getCol() == c){
                    val = "You";
                }
                System.out.printf("| %-4s ", (val.equals(" ")) ? "" : val);
            }
            System.out.println("|");
        }
        for (int c = 0; c < cols; c++) {
            System.out.print("+------");
        }
        System.out.println("+");
    }
}
