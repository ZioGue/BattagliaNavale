import java.util.InputMismatchException;
import java.util.Scanner;

public class BattagliaNavale {
	
	private static CampoDiGioco campoPlayer = new CampoDiGioco("Ava");
	private static Scanner sc = new Scanner(System.in);
	private static int cont = 0;
	
	public static void main(String[] args) {
		//CampoDiGioco test = new CampoDiGioco("Ava");
		aggiungiNave();
		//System.out.println(test.printCampo());
	}
	
	public static void aggiungiNave() {
		for(Navi nave: Navi.values()) {
			System.out.println("Nave di tipo: " + nave.getNome() + " | Grandezza: " + nave.getSize());
			System.out.println(campoPlayer.printCampo());
			System.out.println("Inserisci coordinate X,Y iniziali. (X Numeri in alto, Y numeri Laterali)");
			while(!campoPlayer.aggiungiNave(nave, getCoords("X Iniziale: "), getCoords("Y Iniziale: "), getCoords("X Finale: "), getCoords("Y Finale: ")));
		}
		
	}
	
	private static int getCoords(String testo) {
		int result = 0;
		do {
			try {
				System.out.print(testo);
				result = sc.nextInt();		
			} catch (Exception error) {
				System.out.println("Solo numeri interi.");
				sc.nextLine();
			}
		}while(result <= 0 || result > 11);
		return result;
	}
}
