//INF1010 Vaar 2013			OBLIG 6			Magnus Li 		Fil 2 av 5
//Kontrollobjekt-klasse

import java.util.Scanner;
import java.io.*;

public class Ordsorterer {


	String [] ordUsortert;
	Beholder delbeholder;
	int antallOrd;
	int antallTraader;
	long lastMillis;
	
	
	//Leser inn ord fra fil (filvei)
	public void lesInn(String filvei) {
		
		Scanner les;
		try{
			les = new Scanner(new File(filvei));
		 }catch(FileNotFoundException e){
			System.out.println("Filen ble ikke funnet :'( "); les = null; 
		 }
		 
		 antallOrd = les.nextInt();
		 ordUsortert = new String [antallOrd];
		 
		 les.nextLine();
		 for (int i = 0; i < antallOrd; i++) {
			ordUsortert[i] = les.nextLine();;
		 }
		System.out.println("Leste inn " + antallOrd + " ord!"); 
	}

	//Deler opp array i forhold til antall tråder, og starter trådene. Her går hver array inn i et binærtre. 
	public void delOgSorter(int antallTraader) {
		
		this.antallTraader = antallTraader;
		delbeholder = new Beholder(this, antallTraader);
		int ordPerTraad = antallOrd / antallTraader;
		int rest = antallOrd % antallTraader;

		System.out.println("Antall traader: " + antallTraader);
		lastMillis = System.currentTimeMillis(); //For å beregne tidsbruk for sortering
		int k = 0;
		for (int i = 0; i < antallTraader; i++) { 
			String [] temp;
			if (i < rest) {
				temp = new String[ordPerTraad + 1];
			} else {
				temp = new String[ordPerTraad];
			}
			for (int j = 0;  j < temp.length; j++) {
				temp[j] = ordUsortert[k++];
			}
			Delsortering traad = new Delsortering(temp, i, delbeholder);
			traad.start();
		}
	}
	
	//Printer sortert array til filen out.txt
	public synchronized void skrivTilFil(String [] ordSortert) {
		
		System.out.println("Tid brukt paa sortering : " + (System.currentTimeMillis() - lastMillis) + " ms");
		
		PrintWriter skriv;
		try{
			skriv = new PrintWriter("out.txt");
		}catch (FileNotFoundException e) { 
			System.out.println("Utfil ikke funnet. Skriving til fil ikke utfort!");
			skriv = null; 
		}
		skriv.println(antallOrd);
		for (int i = 0; i < ordSortert.length; i++) {
			skriv.println(ordSortert[i]);
		}
		System.out.println("Ordliste er printet fil");
		skriv.close(); 
	
	}
}