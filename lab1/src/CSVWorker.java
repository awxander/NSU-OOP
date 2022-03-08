import java.io.*;

public class CSVWorker {
    public static void main(String[] args) {
        BufferedReader reader;
        BufferedWriter writer;
        try
        {
            reader = new  BufferedReader(new FileReader("input.txt"));
            writer = new BufferedWriter(new FileWriter("output.csv"));
            WordClass arr = new WordClass();
            arr.PrintDataInCSV(reader, writer);
            reader.close();
            writer.close();
        }
        catch (IOException ex)
        {
            System.err.println("Error while reading file:" + ex.getLocalizedMessage());
        }

    }
}
