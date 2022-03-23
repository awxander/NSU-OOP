import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try(BufferedReader reader  = new BufferedReader(new FileReader("input.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.csv")))
        {
            CsvFormatPrinter csvPrinter = new CsvFormatPrinter();
            csvPrinter.printDataInCSV(reader, writer);
        } catch (IOException ex) {
            System.err.println("Error while reading file:" + ex.getLocalizedMessage());
        }

    }
}
