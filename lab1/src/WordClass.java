import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;




public class WordClass {
    private final HashMap<String, Integer> _words_map = new HashMap<>();
    private int _words_amount = 0;

    private @NotNull String GetNextWord(@NotNull StringBuilder str) {
        StringBuilder res_str = new StringBuilder();
        while (str.length() > 0 && Character.isLetterOrDigit(str.charAt(0))) {
            res_str.append(str.charAt(0));
            str.deleteCharAt(0);
        }
        while (str.length() > 0 && !Character.isLetterOrDigit(str.charAt(0))) {
            str.deleteCharAt(0);
        }
        return res_str.toString();
    }

    private void PrintSortedDataList(BufferedWriter writer) {
        List<Map.Entry<String, Integer>> wordsData = new ArrayList<>();
        int i = 0;
        for (Map.Entry<String, Integer> entry : _words_map.entrySet()) {
            wordsData.add(i, entry);
            i++;
        }
        Collections.sort(wordsData, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> lhs, Map.Entry<String, Integer> rhs) {
                return Integer.compare(lhs.getValue(), rhs.getValue());
            }
        });
        try {
            for (Map.Entry<String, Integer> entry : wordsData) {
                double frequency = (Double.valueOf(entry.getValue()) / this._words_amount) * 100.0;
                frequency = Math.round(frequency*100) / 100.0; // in case to have only 2 decimal places
                writer.write(entry.getKey() + ";" + entry.getValue() +";" + frequency + "%" + '\n');
                System.out.println(entry.getKey() + ";" + entry.getValue() + ";"+ frequency + "%");
            }

        }
        catch(IOException ex)
        {
            System.err.println("Error while writing file:" + ex.getLocalizedMessage());
        }


    }

    public void FillMap(@NotNull BufferedReader reader) {
        try {
            String str = reader.readLine();
            while (str != null) //read one line, then cut words one by one from it
            {
                StringBuilder str_copy = new StringBuilder(str);
                while (str_copy.length() > 0) {
                    String word = GetNextWord(str_copy);
                    if (word != null) {
                        _words_amount++;
                        if (!_words_map.containsKey(word)) {// add removed word to map
                            _words_map.put(word, 1);
                        } else// if word has already existed in map, increase value
                        {
                            _words_map.put(word, _words_map.get(word) + 1);
                        }
                    }
                }

                str = reader.readLine();
            }
        } catch (IOException ex) {
            System.err.println("Error while reading file:" + ex.getLocalizedMessage());
        }

    }

    public void PrintDataInCSV(BufferedReader reader, BufferedWriter writer) {
        this.FillMap(reader);
        this.PrintSortedDataList(writer);

    }


}


