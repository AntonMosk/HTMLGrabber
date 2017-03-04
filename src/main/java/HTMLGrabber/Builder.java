package HTMLGrabber;

import java.io.IOException;
import java.util.*;

/**
 * Created by anton on 3/1/17.
 */
class Builder {

    private static List<Map.Entry<String, Integer>> dictionarySorter(Map<String, Integer> freqDictTreeMap) {
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(freqDictTreeMap.entrySet());
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

    public static void main(String[] args) throws IOException {
        String url;
        if (args.length == 0) {
            url = "http://www.stackoverflow.com";
        } else {
            url = args[0];
        }

        HTMLHandler handler = new HTMLHandler();
        StringBuilder pageText = handler.handleWebPage(url);

        HTMLParser htmlParser = new HTMLParser();
        Map<String, Integer> freqDictionary = htmlParser.parseHTML(pageText);

        List<Map.Entry<String, Integer>> entries = dictionarySorter(freqDictionary);

        System.out.println("Dictionary of the words from site: " + url + " by frequancy:");
        int wordsTotal = 0;
        int wordsCount = 0;
        for (Map.Entry<String, Integer> words : entries) {
            System.out.println("" + words.getKey() + " " + words.getValue());
            wordsCount++;
            wordsTotal += words.getValue();
        }
        System.out.println("-------------------------------------");
        System.out.println(url + ": Words count - " + wordsCount + ", Total words - " + wordsTotal);
    }
}