//INF1010 Vaar 2013			OBLIG 6			Magnus Li 		Fil 4 av 5
//Beholder som fungerer som kø for ferdig sorterte deler. Når to deler er klare, sendes disse til fletting.

public class Beholder {

	String [] sortert1;
	Ordsorterer sorterer;
	int antallTraader;
	
	Beholder(Ordsorterer sorterer, int antallTraader) {
		this.sorterer = sorterer;
		this.antallTraader = antallTraader;
	}
	
	//Synkronisert metode for å sette ferdig sortere deler i kø for fletting. 
	public synchronized void settInn(String [] sortert) {
		
		if (sortert.length < sorterer.antallOrd) { //Avslutter fletting dersom array er ferdig størrelse
			
			if (sortert1 == null) {
				sortert1 = sortert;
			} else {
				Delfletter temp = new Delfletter(this, sorterer, sortert1, sortert);
				sortert1 = null;
				temp.start();
			} 
			
		} else if (sortert.length == sorterer.antallOrd) {
			System.out.println("Fletting er ferdig");
			sorterer.skrivTilFil(sortert); //Sender ferdig flettet array for utskrift
		}
	}
}