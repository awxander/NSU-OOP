import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class DataSorter {
    private final List<Map.Entry<String, Integer>> wordsData;
    public DataSorter(){
        wordsData = new ArrayList<>();
    }
    public List<Map.Entry<String, Integer>> getSortedDataList(HashMap<String, Integer> wordsMap){
        int i = 0;
        for (Map.Entry<String, Integer> entry : wordsMap.entrySet()) {
            wordsData.add(i, entry);
            i++;
        }
        wordsData.sort(Comparator.comparingInt(Map.Entry::getValue));
        return wordsData;
    }
}
