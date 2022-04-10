import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String inputFileName, outputFileName;
        if (args.length == 2) {
            inputFileName = args[0];
            outputFileName = args[1];
        }else{
            System.out.println("wrong arguments amount, using default setting:" +
                    "\n"+ "input: input.txt" + "\n" + "output: output.csv");
            inputFileName = "input.txt";
            outputFileName = "output.csv";
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            CsvFormatPrinter csvPrinter = new CsvFormatPrinter();
            csvPrinter.printDataInCSV(reader, writer);
        } catch (IOException ex) {
            System.err.println("Error while reading file:" + ex.getLocalizedMessage());
        }

    }
}
