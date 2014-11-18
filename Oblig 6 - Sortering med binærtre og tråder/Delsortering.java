//INF1010 Vaar 2013			OBLIG 6			Magnus Li 		Fil 3 av 5
//Klasse med sorteringsalgoritme (Binærtre)

public class Delsortering extends Thread {

	Node rot;
	int antallOrd;
	String ordUsortert[];
	String ordSortert[];
	Beholder delbeholder;
	int nesteOrd;
	int id;
	
	Delsortering(String [] ordUsortert, int id, Beholder delbeholder) {
		this.ordUsortert = ordUsortert;
		this.delbeholder = delbeholder;
		ordSortert = new String [ordUsortert.length];
		nesteOrd = 0;
		this.id = id;
	}

	//Intern klasse for oppbevaring av ord i binærtre
	private class Node {
		
		Node (String ord) {
			this.ord = ord;
		}
		public Node hoyre;
		public Node venstre;
		public String ord;
		
		//Setter inn ord i sortert binærtre. (Dersom større settes/sendes det til høyre og motsatt.)
		public void settInn(String ord) {
			
			if (ord.compareTo(this.ord) > 0) {
				if (hoyre == null) {
					hoyre = new Node(ord);
				} else {
					hoyre.settInn(ord);
				}
			} else { 
				if (venstre == null) {
					venstre = new Node(ord);
				} else {
					venstre.settInn(ord);
				}
			}
		}
		
		//Legger aktuelt objekt inn i array, og kaller samme metode i neste objekt
		public void leggDenneOgRestenIArray() {
		
			if (venstre != null) {
				venstre.leggDenneOgRestenIArray();
			}
			ordSortert[nesteOrd++] = ord;
			if (hoyre != null) {
				hoyre.leggDenneOgRestenIArray();
			}	
		}
	}
	
	//Starter objektet som egen tråd og setter i gang delsorteringsprosess
	public void run() {
		genererSortertArray();
	}
	
	//Setter inn ord i binærtre (Kaller metode i Node-klasse)
	public void settInn(String ord) {
	
		if (rot == null) { 
			rot = new Node(ord);
		} else {
			rot.settInn(ord);
		}
	}
	
	//Genererer en sortert array (A-Z) og sender denne videre til felles beholderobjekt
	public void genererSortertArray() {

		for (int i = 0; i < ordUsortert.length; i++) {
			settInn(ordUsortert[i]);
		}
		if (rot != null) {
			rot.leggDenneOgRestenIArray();
			nesteOrd = 0;
		}
		delbeholder.settInn(ordSortert);
	}
}