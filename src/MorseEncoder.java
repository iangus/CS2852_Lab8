import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * CS2852 - 041
 * Spring 2016
 * Lab
 * Name: Ian Guswiler
 * Created: 5/3/2016
 */
public class MorseEncoder {
    private static LookupTable table = new LookupTable();

    private static void loadTable(File file){
        try(Scanner fileScan = new Scanner(file)){
            while(fileScan.hasNext()){
                table.put(fileScan.next(),fileScan.next());
            }
        } catch (FileNotFoundException e){
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        loadTable(new File("morsecode.txt"));
    }
}
