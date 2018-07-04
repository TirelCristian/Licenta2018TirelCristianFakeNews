package Verificare;

import java.util.ArrayList;
import java.util.List;

public class TransformareURL {
	
	public static String GetSLDTLD (String string) {
		System.out.println(string);
		int ok=0,ok2=0;
		String rezultat="";
		for (int i=0;i<string.length();i++) {
			if(string.charAt(i)=='/' && string.charAt(i+1)=='/') {
				ok=i+2;
				break;
				}
		}
		if(string.charAt(ok)=='w')
			ok+=4;
		for (int i=ok;i<string.length();i++) {
			if(string.charAt(i)=='/') {
				ok2=i;
				break;
				}
		}
		for(int j=ok;j<ok2;j++) {
			rezultat+=string.charAt(j);
		}		
		return rezultat;
	}
	
	public static List<String> SpargereRezultat (String string){
		List<String> result = new ArrayList<String>();
		int ok=0;
		String creez="";
			
		for (int i=0;i<string.length();i++) {
			
			if(ok==1 && string.charAt(i)!='.')
				creez+=string.charAt(i);
			
			
			if(ok==1 && string.charAt(i)=='.') {
				ok=0;
				result.add(creez);
				creez="";
			}
			if(string.charAt(i)=='.' && ok==0) {
				ok=1;
				creez+=string.charAt(i);
			}
			if(i==0 && string.charAt(i)!='w') {
				ok=1;
				creez+=string.charAt(i);
			}
				
		}
		if(creez.endsWith ("/"))
			creez=creez.substring(0, creez.length()-1);
		result.add(creez);
		return result;
	}
	
	public static String GetPath (String string) {
		String rezultat="";
		int ok=0,count=0;
		for (int i=0;i<string.length();i++) {
			if(string.charAt(i)=='/')
				count++;
			if(string.charAt(i)=='/' && count==3) {
				ok=i+1;
				break;
				}
		}
		if(string.charAt(string.length()-1)=='/')
			ok=0;
		if(ok==0 && string.charAt(4)=='s')
			ok=8;
		if(ok==0 && string.charAt(4)!='s')
			ok=7;
		for(int j=ok;j<string.length();j++) {
			rezultat+=string.charAt(j);
		}
		if(rezultat.endsWith ("/"))
			rezultat=rezultat.substring(0, rezultat.length()-1);
		return rezultat;
	}
	
//	public static boolean VerificareTLD(String string) {
//		String TLD;
//		int i=0;
//		while(i<string.length()) {
//			
//		}
//		
//		String A="acadaeafagaialamaoaqarasatauawaxazbabbbdbebfbgbhbibjbmbnbobrbsbtbwbybzcacccdcfcgchcickclcmcncocrcucvcwcxcyczdedjdkdmdodzeceeegereseteufifjfkfmfofrgagdgegfggghgiglgmgngpgqgrgsgtgugwgyhkhmhnhrhthuidieiliminioiqirisitjejmjojpkekgkhkikmknkpkrkwkykzlalblclilklrlsltlulvlymamcmdmemgmhmkmlmmmnmompmqmrmsmtmumvmwmxmymznancnenfngninlnonpnrnunzom.papepfpgphpkplpmpnprpsptpwpyqa.acadaeafagaialamaoaqarasatauawaxazbabbbdbebfbgbhbibjbmbnbobrbsbtbwbybzcacccdcfcgchcickclcmcncocrcucvcwcxcyczdedjdkdmdodzeceeegereseteufifjfkfmfofrgagdgegfggghgiglgmgngpgqgrgsgtgugwgyhkhmhnhrhthuidieiliminioiqirisitjejmjojpkekgkhkikmknkpkrkwkykzlalblclilklrlsltlulvlymamcmdmemgmhmkmlmmmnmompmqmrmsmtmumvmwmxmymznancnenfngninlnonpnrnunzompapepfpgphpkplpmpnprpsptpwpyqarerorsrurwsasbscsdsesgshsiskslsmsnsosrssstsvsxsysztctdtftgthtjtktltmtntotrtttvtwtzuaugukusuyuzvavcvevgvivnvuwfwsyeytzazmzw";
//			for(int i=0;i<A.length();i=i+2) {
//				
//			}
//
//	return false;
//	}
	
	
//	public static void main(String[] args) {
//		GetSLDTLD("https://www.timesnewroman.ro/politic/patriarhul-nu-l-a-recunoscut-pe-dragnea-in-hotelul-din-elvetia-si-i-a-dat-10-euro-ca-sa-i-care-bagajele-la-masina");
//		GetPath("https://www.timesnewroman.ro");
//	}
}