package Game;
import java.util.List;
import java.util.Scanner;

/**
 * Static class to get input of user restricted by input parameters
 */
public class Input {
    public static String getInput(List<String> options){
        boolean done = false;
        String choice = "";

        Scanner scanner = new Scanner(System.in);
        while (!done) {
            choice = scanner.next();

            for (String option : options) {
                if (option.equals(choice)) {
                    done = true;
                    break;
                }
            }
            if (!done) {
                System.out.println("Please enter a valid choice");
            }
        }
        return choice;
    }

    public static String getName(){
        Scanner scanner = new Scanner(System.in);
        String input;
        int maxLength = 12;

        do {
            input = scanner.nextLine();

            if (input.length() > maxLength) {
                System.out.println("Invalid input. Name can only be " + maxLength + " characters long. Try again.");
            }
        } while (input.length() > maxLength);

        return input;
    }
}
