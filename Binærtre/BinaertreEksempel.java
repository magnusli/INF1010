//	Eksamensnotat INF1010 Vår 2013. 		BINÆRTRE (GENERISK)			(c) Magnus Li 

class BinaertreEksempel {

	public static void main(String [] args) {
	
		Beholder b = new Beholder();
		b.settInn("E");
		b.settInn("B");
		b.settInn("A");
		b.settInn("C");
		b.settInn("D");
		b.skrivUt(); //Gir en sortert utskrivt (A B C D E) 
	}
}

//T MÅ extende comparabe slik at kompilator vet at metode CompareTo() kan brukes
class Beholder <T extends Comparable> {

	private Node rot;
	
	public void settInn(T ny) {
		if (rot == null) {
			rot = new Node(ny);
		} else {
			rot.settInn(ny);
		}
	}
	
	public void skrivUt() {
		if (rot != null) {
			rot.skrivUtMegOgNeste();
		}
	}
	
	//Indre node-klasse
	private class Node{
		
		private Node hoyre;
		private Node venstre;
		private T innhold;
		
		Node(T ny) {
			this.innhold = ny;
		}
		
		public void settInn(T ny) {
			
			//Det sjekkes om objekt er større eller 
			//mindre en nodens objekt, og metode kalles i høyre eller venstre peker
			//evt. settes inn i hoyre eller venstre
			if (ny.compareTo(innhold) < 0) {
				if (venstre == null) {
					venstre = new Node(ny);
				} else {
					venstre.settInn(ny);
				}
			} else {
				if (hoyre == null) {
					hoyre = new Node(ny);
				} else {
					hoyre.settInn(ny);
				}
			}
		}
		
		public void skrivUtMegOgNeste() {
		
			//Sjekker om det finnes innhold i venstrepeker og kaller evt. videre på dennes utskriftsmetode
			if (venstre != null) {
				venstre.skrivUtMegOgNeste();
			}
			
			//Når man når "bunn" på venstre side, traverserer metoden tilbake, og skriver ut objektene sortert.
			System.out.println(innhold); //Her kunne man også satt inn i array!
			
			//Når en node har skrevet ut sitt venstre tre, kaller den på sin høyre node, og gjør det samme her. 
			if (hoyre != null) {
				hoyre.skrivUtMegOgNeste();
			}
		}
	}

}