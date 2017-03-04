package HTMLGrabber;

import java.io.IOException;
import java.util.*;

/**
 * Created by anton on 3/1/17.
 */
public class Builder {

    private static void dictionarySorter(Map<String, Integer> freqDictTreeMap) {
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(freqDictTreeMap.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                int v1 = e1.getValue();
                int v2 = e2.getValue();
                return (v1 < v2) ? 1 : (v1 == v2) ? 0 : -1;
            }
        });

        for (Map.Entry<String, Integer> words : entries) {
            System.out.println("" + words.getKey() + " " + words.getValue());
        }
    }

    public static void main(String[] args) throws IOException {
        String url;
        if (args.length == 0) {
            url = "http://www.stackoverflow.com";
        } else {
            url = args[0];
        }

//        String url = "https://habrahabr.ru";

        HTMLHandler handler = new HTMLHandler();
        StringBuilder pageText = handler.handleWebPage(url);

        HTMLParser htmlParser = new HTMLParser();

        dictionarySorter(htmlParser.parseHTML(pageText));

    }
}