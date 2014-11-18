//INF1010 Vaar 2013			OBLIG 6			Magnus Li 		Fil 5 av 5
//Klasse for fletting av to og to arrayer med ord

public class Delfletter extends Thread {

	Beholder delbeholder;
	Ordsorterer sorterer;
	int antallFlettet;
	String [] a; 
	String [] b;
	
	Delfletter(Beholder delbeholder, Ordsorterer sorterer, String [] a, String [] b) {
		//System.out.println("Ny delfletter opprettet");
		this.delbeholder = delbeholder;
		this.sorterer = sorterer;
		this.a = a;
		this.b = b;
	}
	
	//Starter ny tråd av objekt og starter fletting
	public void run() {
		flett();
	}
	
	//Fletter array a og b og setter den flettede array inn i beholder. 
	public void flett() {
		//System.out.println("Fletting startet");
		
		String [] flettet =  new String [a.length + b.length];
		int i = 0;
		int j = 0;
		int k = 0;
		
		//Går igjennom og sammenlikner ord for ord i de to arrayene og putter inn i ny
		while (i < a.length && j < b.length) {
			if (a[i].compareTo(b[j]) < 0) {
				flettet[k++] = a[i++];
			} else {
				flettet[k++] = b[j++];
			}
		}	
		//Leger resterende ord inn i array
		while (i < a.length) {
			flettet[k++] = a[i++];
		}
		while (j < b.length) {
			flettet[k++] = b[j++];
		}
		delbeholder.settInn(flettet); //Setter fletter array inn i beholder (flettekø)
	}

}