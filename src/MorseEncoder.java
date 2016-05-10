/*
 * CS2852 - 041
 * Spring 2016
 * Lab 8
 * Name: Ian Guswiler
 * Created: 5/3/2016
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Loads text files and operates on them using a lookup table then saves the encoded message to a file.
 *
 * @author Ian Guswiler
 * @version 5/9/16
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

    /**
     * Main functioning of the program. Takes file inputs through scanners and writes to them with a file writer.
     * @param args Ignored
     */
    public static void main(String[] args) {
        loadTable(new File("morsecode.txt"));
        Scanner input = new Scanner(System.in);

        System.out.println("Enter an input filename:");
        String inputFileName = input.next();

        System.out.println("Enter an output filename:");
        String outputFileName = input.next();
        File output = new File(outputFileName);

        try(Scanner inputScan = new Scanner(new File(inputFileName))){
            Scanner lineScan;
            FileWriter writer = new FileWriter(output);

            while(inputScan.hasNextLine()){
                lineScan = new Scanner(inputScan.nextLine());
                while (lineScan.hasNext()) {
                    String nextIn = lineScan.next();
                    for(int i = 0; i<nextIn.length(); i++){
                        char nextChar = Character.toUpperCase(nextIn.charAt(i));
                        if(!table.containsKey("" + nextChar)){
                            System.err.println("The character " + nextChar + " could not be found in the morse encoder, " +
                                    "so it was skipped.");
                        } else{
                            writer.write("" + table.get("" + nextChar) + " ");
                        }
                    }
                    writer.write("| ");
                }
                writer.write("\n");
            }
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e){
            System.err.println("The input file " + inputFileName + " could not be found");
        } catch (IOException e1){
            System.err.println(e1.getMessage());
        }
    }
}
