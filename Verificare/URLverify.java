package Verificare;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Paths;

import javax.net.ssl.HttpsURLConnection;
 
public class URLverify {

    public static boolean checkURLexistance(String targetUrl) {
        HttpURLConnection URLconn;
        try {
        	URLconn = (HttpURLConnection) new URL(targetUrl).openConnection();
        	URLconn.setRequestMethod("HEAD");

            return (URLconn.getResponseCode() != HttpURLConnection.HTTP_NOT_FOUND);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    
    public static boolean checkURLsafety(String targetUrl) {
        HttpsURLConnection URLconn;
        try {
        	URLconn = (HttpsURLConnection) new URL(targetUrl).openConnection();
        	URLconn.setRequestMethod("HEAD");

            return (URLconn.getResponseCode() != HttpsURLConnection.HTTP_NOT_FOUND);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    
    public static long getLastModified(String uri) {
        try {
            URL url = new URL(uri);
            URLConnection connection = url.openConnection();
            long timestamp = connection.getLastModified();

            if (timestamp == 0){ 
                if ("file".equals(url.getProtocol())){
                    File localfile = Paths.get(url.toURI()).toFile();
                    timestamp = localfile.lastModified();
                }
            }
            return(timestamp);
        }
        catch (Exception e) {
            return(System.currentTimeMillis());
        }
    }
    
    
}