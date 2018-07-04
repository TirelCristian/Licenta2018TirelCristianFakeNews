package Verificare;

import java.util.List;

public class ComparareRezultat {

		public static boolean comparare (List<String> verificare, List<String> rezultate){
		int raspuns=0,nr=0;
//		System.out.println("Incep" + rezultate.size());
			for(int i=0;i<rezultate.size();i++) {
				System.out.println("Uite  " + i + "  "+ TransformareURL.GetSLDTLD(rezultate.get(i)));
				for(int j=0;j<verificare.size();j++) {
					if(TransformareURL.GetSLDTLD(rezultate.get(i)).length()>4)
						if(TransformareURL.GetSLDTLD(rezultate.get(i)).contains(verificare.get(j))) 
							raspuns++;
//					if(TransformareURL.GetSLDTLD(rezultate.get(i)).length()>4)
//						System.out.println(raspuns);
				}
				if(raspuns>=2)
					nr++;
				raspuns=0;
			}
		System.out.println("NR =  " + nr);
	    return false;
		}
}
