package Verificare;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Scanner;

public class LucruCuFisier {

	public static void adauga (String str,String path) {
		try (FileWriter f = new FileWriter(path, true);
                BufferedWriter b = new BufferedWriter(f);
                PrintWriter p = new PrintWriter(b);) {
			p.println(str);

        } catch (IOException i) {
            i.printStackTrace();
        }
	}
	
	public static Boolean cauta (String str, Path path) throws IOException {
		
		@SuppressWarnings("resource")
		Scanner scanner=new Scanner(path);

		while(scanner.hasNextLine()){
//			System.out.println("Am intrat");
//			System.out.println(str);
			String alpha = scanner.nextLine().trim();
//			System.out.println(alpha);
		    if(alpha.contains(str) || alpha.equals(str) ) 
		    	return true;
		}
		return false;
	}
}
