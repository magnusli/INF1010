//	Eksamensnotat INF1010 Vår 2013. 		Generisk lenkeliste med iterator		(c) Magnus Li 

import java.util.*;

class LenkelisteEksempel {

	public static void main(String [] args) {
		Beholder <String> b = new Beholder <String> ();
		Iterator i = b.iterator();
		
		while (i.hasNext()) {
			System.out.println(i.next());
		}
		
		for (String s: b) {
			System.out.println(s);
		}
	}
}


class Beholder <T extends Comparable> implements Iterable{

	private Node forste;
	private int antall;
	
	Beholder() {
		this.antall = 0;
	}
	
	public Iterator iterator() {
		return new BeholderIterator();
	}
	
	//	SETTER INN REKURSIVT
	public void settInnRekursivt(T innhold) {
		if (forste != null) {
			forste.settInn(innhold);
		} else {
			forste = new Node(innhold);
		}
	}
	
	// SETTER INN FORST (UTEN REKURSIVE KALL)
	public void settInnForst(T innhold) {
		if (forste != null) {
			Node temp = forste;
			forste = new Node(innhold);
			forste.neste = temp;
		} else {
			forste = new Node(innhold);
		}
	}
	
	//	YTRE METODE FOR Å SETTE INN SORTERT VED HJELP AV REKURSJON
	public void settInnSortert(T innhold) {
		if (forste != null) {
			if (forste.innhold.compareTo(innhold) > 0) {
				Node temp = forste;
				forste = new Node(innhold);
				forste.neste = temp;
			} else {
				forste.settInnSortert(innhold);
			}
		} else {
			Node temp = forste;
			forste = new Node(innhold);
			forste.neste = temp;
		}
	}
	
	// FJERN UTEN REKURSIVT KALL
	public void fjern(T objekt) {
		if (forste != null){
			for (Node n = forste; n.neste != null; n = n.neste) {
				if (n.neste.innhold.equals(objekt)) {
					n.neste = n.neste.neste;
				}
			}
		}
	}
	
	//	FJERN VED HJELP AV REKURSIVE KALL
	public void fjernRekursivt(T objekt) {
		if (forste != null) {
			if (forste.equals(objekt)) {
				forste = forste.neste;
			} else {
				forste.fjern(objekt);
			}
		}
	}
	//	SKRIVER UT ALLE NODER UTEN REKURSIVE KALL
	public void skrivUtAlle() {
		if (forste != null) {
			for (Node n = forste; n != null; n = n.neste) {
				System.out.println(n.innhold);
			}
		}
	}
	
	private class Node {
		private Node neste;
		private T innhold;
		
		Node(T innhold) {
			this.innhold = innhold;
		}
		
		//	SETTER INN REKURSIVT
		private void settInn(T innhold) {
			if (neste == null) {
				neste = new Node(innhold);
				antall++;
			} else {
				neste.settInn(innhold);
			}
		}
		
		//	SETTER INN SORTERT VED HJEP AV REKURSIVE KALL
		private void settInnSortert(T innhold) {
			
			if (neste != null) {
				if (neste.innhold.compareTo(innhold) >= 0) {
					Node temp = neste;
					neste = new Node(innhold);
					neste.neste = temp;
				} else {
					neste.settInnSortert(innhold);
				}
			} else {
				neste = new Node(innhold);
			}
		}
		
		//	FJERNER OBJEKT REKURSIVT
		private void fjern(T objekt) {
			if (neste != null) {
				if (neste.innhold.equals(objekt)) {
					
					if (neste.neste != null) {
						neste = neste.neste;
					} else {
						neste = null;
					}
				} else {
					neste.fjern(objekt);
				}
			}
		}
	}
	
	//	Iterator
	private class BeholderIterator implements Iterator {
	
		Node nesteNode;
	
		BeholderIterator() {
			nesteNode = forste;
		}
		
		public boolean hasNext() {
			return (nesteNode != null);
		}
		
		public T next() {
			Node temp = nesteNode;
			nesteNode = nesteNode.neste;
			return temp.innhold;
		}
		
		public void remove() {
			fjern(nesteNode.innhold);
		}
	}
}