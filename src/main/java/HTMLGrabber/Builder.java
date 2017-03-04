package HTMLGrabber;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by anton on 3/1/17.
 */
class Builder {

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

        WordCounter wordCounter = new WordCounter();
        List<Map.Entry<String, Integer>> sortedDictionary = wordCounter.dictionarySorter(freqDictionary);
        wordCounter.printDictionary(sortedDictionary, url);
    }
}