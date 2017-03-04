package HTMLGrabber;

import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by anton on 3/1/17.
 */
class HTMLParser {


    private static boolean checkWithRegExp(String userNameString) {
        Pattern p = Pattern.compile("^[a-z0-9_-]");
        Matcher m = p.matcher(userNameString);
        return m.matches();
    }

    private StringBuilder removeScript(StringBuilder pageText, String beginExpression, String endExpression) {

        StringBuilder result = new StringBuilder(pageText);

        int beginScriptTag = 0;
        int endScriptTag = 0;
        while (beginScriptTag != -1) {
            beginScriptTag = result.indexOf(beginExpression);
            endScriptTag = result.indexOf(endExpression, beginScriptTag);
            if (beginScriptTag != -1) {
                if (endScriptTag == -1) {
                    endScriptTag = result.length() - 1;
                } else {
                    endScriptTag += endExpression.length();
                }
                result.delete(beginScriptTag, endScriptTag);
            }
        }
        return result;
    }

    public Map<String, Integer> parseHTML(StringBuilder pageText) {

        Map<String, Integer> result = new TreeMap<>();

        StringBuilder textToProcess;

        textToProcess = removeScript(pageText,
                "<script", "</script>");
        textToProcess = removeScript(textToProcess,
                "<style", "</style>");

        int beginSearchFrom = 0;
        int beginTag = 0;
        int endTag = 0;
        String stringWithText = "";
        while ((beginTag != -1) && (endTag != -1)) {
            beginTag = textToProcess.indexOf(">", beginSearchFrom);
            endTag = textToProcess.indexOf("<", beginTag);
            if (beginTag == -1)
                continue;
            if (beginTag == textToProcess.length() - 1)
                continue;
            if (endTag == -1) {
                endTag = textToProcess.length() - 1;
            }

            if (beginTag + 1 == endTag) {
                stringWithText = "";
            } else {
                stringWithText = textToProcess.substring(beginTag + 1, endTag).trim();
            }
            if (stringWithText.length() != 0) {
                for (String str : stringWithText.split(" ")) {
                    str = str.trim();
                    if (str.length() == 0) {
                        continue;
                    }

//                    if (checkWithRegExp(s3)) {
//                        continue;
//                    }

                    if (result.containsKey(str)) {
                        result.put(str, result.get(str) + 1);
                    } else {
                        result.put(str, 1);
                    }
                }
            }
            beginSearchFrom = endTag;
        }
        return result;
    }
}
