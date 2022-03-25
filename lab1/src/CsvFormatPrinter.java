import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class CsvFormatPrinter {

    public void printDataInCSV(BufferedReader reader, BufferedWriter writer) {

        HashMap<String, Integer> wordsMap;
        DataReader dataReader = new DataReader();
        dataReader.fillMap(reader);
        wordsMap = dataReader.getWordsMap();

        List<Map.Entry<String, Integer>> wordsData;
        DataSorter dataSorter = new DataSorter();
        wordsData = dataSorter.getSortedDataList(wordsMap);
        try {
            for (Map.Entry<String, Integer> entry : wordsData) {
                double frequency = (Double.valueOf(entry.getValue()) / dataReader.getWordsAmount()) * 100.0;
                frequency = Math.round(frequency * 100) / 100.0; // in case to have only 2 decimal places
                writer.write(entry.getKey() + ";" + entry.getValue() + ";" + frequency + "%" + '\n');
                System.out.println(entry.getKey() + ";" + entry.getValue() + ";" + frequency + "%");
            }

        } catch (IOException ex) {
            System.err.println("Error while writing file:" + ex.getLocalizedMessage());
        }


    }


}


