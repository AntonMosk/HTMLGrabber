package HTMLGrabber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Hello world!
 */
public class HTMLHandler {
//    public static void main( String[] args ) throws IOException {
//        HTMLHandler handler = new HTMLHandler();
//        System.out.println(handler.handleWebPage("http://www.stackoverflow.com"));
//    }

    public StringBuilder handleWebPage(String url) throws IOException {
        StringBuilder result;
        URL targetUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) targetUrl.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Chrome/41.0.2228.0");
        result = new StringBuilder(readHTMLDocument(connection));
        return result;
    }

    private StringBuilder readHTMLDocument(HttpURLConnection connection) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder result = new StringBuilder();
        String nextLine;
        while ((nextLine = reader.readLine()) != null) {
            result.append(nextLine);
        }

        return getBody(result);
    }

    private StringBuilder getBody(StringBuilder htmlDocument) {
        int startBodyTag = htmlDocument.indexOf("<body");
        int endBodyTag = htmlDocument.indexOf("</body");

        return new StringBuilder(htmlDocument.substring(startBodyTag, endBodyTag));

    }
}
