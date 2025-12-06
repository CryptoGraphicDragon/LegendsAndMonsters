package Game;

import java.util.Random;

/**
 * A static class to get a random number between 1 and input
 */
public class Dice {
    public static int rollDie(int die) {
        Random random = new Random();
        return random.nextInt(die) + 1;
    }
}
