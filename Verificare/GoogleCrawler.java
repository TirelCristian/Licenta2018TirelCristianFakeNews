package Verificare;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GoogleCrawler {

    public static String getString(InputStream is) {
        StringBuilder sb = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        try {
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    public static String getSearchContent(String path) throws Exception {
        final String agent = "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)";
        URL url = new URL(path);
        final URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent", agent);
        final InputStream stream = connection.getInputStream();
        return getString(stream);
    }

    public static List<String> parseLinks(final String html) throws Exception {
        List<String> result = new ArrayList<String>();
        String pattern1 = "<h3 class=\"r\"><a href=\"/url?q=";
        String pattern2 = "\">";
        Pattern p = Pattern.compile(Pattern.quote(pattern1) + "(.*?)" + Pattern.quote(pattern2));
        Matcher m = p.matcher(html);

        while (m.find()) {
            String domainName = m.group(0).trim();

            /** remove the unwanted text */
            domainName = domainName.substring(domainName.indexOf("/url?q=") + 7);
            domainName = domainName.substring(0, domainName.indexOf("&amp;"));

            result.add(domainName);
        }
        return result;
    }

//    public static void main(String[] args) throws Exception {
//        String searchTerm = "something";
//
//        System.out.println("Searching for: " + searchTerm);
//        String query = "https://www.google.com/search?q=" + searchTerm + "&num=10";
//        String page = getSearchContent(query);
//        List<String> links = parseLinks(page);
//        System.out.println();
//        System.out.println("Results:");
//        for (int i = 0; i < links.size(); i++) {
//            System.out.println(links.get(i));
//        }
//    }
}

//https://www.admfactory.com/how-to-parse-google-search-result-in-java/