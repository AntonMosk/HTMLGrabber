package HTMLGrabber;


import java.util.*;

/**
 * Created by anton on 3/4/17.
 */
public class WordCounter {

    List<Map.Entry<String, Integer>> dictionarySorter(Map<String, Integer> dictionary) {
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(dictionary.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                int v1 = e1.getValue();
                int v2 = e2.getValue();
                return (v1 < v2) ? 1 : (v1 == v2) ? 0 : -1;
            }
        });
        return entries;
    }

    void printDictionary (List<Map.Entry<String, Integer>> dictionary, String url) {
        System.out.println("Dictionary of the words from site: " + url + " by frequancy:");
        int wordsTotal = 0;
        int wordsCount = 0;
        for (Map.Entry<String, Integer> word : dictionary) {
            System.out.println("" + word.getKey() + " " + word.getValue());
            wordsCount++;
            wordsTotal += word.getValue();
        }
        System.out.println("-------------------------------------");
        System.out.println(url + ": Words count - " + wordsCount + ", Total words - " + wordsTotal);
    }
}
