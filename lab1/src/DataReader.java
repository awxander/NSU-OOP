import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

public class DataReader {
    private int wordsAmount;
    private final HashMap<String, Integer> wordsMap;

    public DataReader(){
        wordsAmount = 0;
        wordsMap = new HashMap<>();
    }

    private @NotNull String getNextWord(@NotNull StringBuilder str) {
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

    public void fillMap(BufferedReader reader){
        try {
            String str = reader.readLine();
            while (str != null) //read one line, then cut words one by one from it
            {
                StringBuilder str_copy = new StringBuilder(str);
                while (str_copy.length() > 0) {
                    String word = getNextWord(str_copy);
                    wordsAmount++;
                    if (!wordsMap.containsKey(word)) {// add removed word to map
                        wordsMap.put(word, 1);
                    } else// if word has already existed in map, increase value
                    {
                        wordsMap.put(word, wordsMap.get(word) + 1);
                    }
                }

                str = reader.readLine();
            }
        } catch (IOException ex) {
            System.err.println("Error while reading file:" + ex.getLocalizedMessage());
        }

    }

    public int getWordsAmount(){
        return wordsAmount;
    }

    public HashMap<String, Integer> getWordsMap(){
        return wordsMap;
    }
}
