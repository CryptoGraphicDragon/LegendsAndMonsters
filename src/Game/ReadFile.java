package Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Static class to read files into a list of strings
 * taken from https://www.w3schools.com/java/java_files_read.asp
 */
public class ReadFile {
    public static List<String> readFile(String fileName){
        File myObj = new File(fileName);
        List<String> contents = new ArrayList<>();
        try (Scanner myReader = new Scanner(myObj)) {
            int line = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (line != 0) {
                    contents.add(data);
                }
                line++;
            }
            return contents;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
        return null;
    }
}
