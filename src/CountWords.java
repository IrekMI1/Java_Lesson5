import java.util.HashMap;
import java.util.Map;

public class CountWords {
    public int countWords(String[] words1, String[] words2) {
        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();

        for (String word : words1) {
            map1.putIfAbsent(word, 0);
            map1.put(word, map1.get(word) + 1);
        }

        for (String word : words2) {
            map2.putIfAbsent(word, 0);
            map2.put(word, map2.get(word) + 1);
        }

        int count = 0;
        for (String key1 : map1.keySet()) {
            if (map2.containsKey(key1) &&
                    map2.get(key1) == 1 &&
                    map1.get(key1) == 1) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        String[] words1 = {"leetcode", "is", "amazing", "as", "is"};

        String[] words2 = {"amazing", "leetcode", "is"};

        CountWords cw = new CountWords();
        System.out.println(cw.countWords(words1, words2));
    }
}