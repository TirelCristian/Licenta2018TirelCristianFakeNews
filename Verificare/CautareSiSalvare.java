package Verificare;

import java.util.ArrayList;
import java.util.List;

public class CautareSiSalvare {

	public static List<String> listare (String string){
    String query = "https://www.google.com/search?q=" + string + "&num=10";
    String page;
    List<String> result = new ArrayList<String>();
		try {
			page = GoogleCrawler.getSearchContent(query);
			List<String> links = GoogleCrawler.parseLinks(page);
			result=links;
//			System.out.println("Results:");
//			for (int i = 0; i < links.size(); i++) {
//				System.out.println(links.get(i));
//			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return result;
	}
}
