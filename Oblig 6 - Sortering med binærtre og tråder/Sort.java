//INF1010 Vaar 2013			OBLIG 6			Magnus Li 		Fil 1 av 5

public class Sort {

	public static void main(String [] args) {
		
		if (args.length == 2) {
		
			String filsti = args[1];
			int antallTraader = Integer.parseInt(args[0]);
			Ordsorterer sortering = new Ordsorterer();
			sortering.lesInn(filsti);
			sortering.delOgSorter(antallTraader);
			
		} else {
			System.out.println("Mangler argumenter!");
		}
	}
	
}