package Board;

import java.util.Arrays;
import java.util.List;

/**
 * Represents a single tile or piece on the game board.
 */
public class Tile {
    public static List<String> TYPES = Arrays.asList("BLANK", "FIGHT", "SHOP");
    protected String type;
    protected int rank;

    public Tile(String type, int rank) {
        this.type = type;
        this.rank = rank;
    }

    public String getType() {
        return type;
    }

    public int getRank() { return rank; }
}
