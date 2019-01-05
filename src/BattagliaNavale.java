import java.util.Random;
import java.util.Scanner;

public class BattagliaNavale {

	private static CampoDiGioco campoPlayer;
	private static CampoDiGioco campoComputer = new CampoDiGioco("Computer", true);
	private static Scanner sc = new Scanner(System.in);
	private static int cont = 0;

	public static void main(String[] args) {
		System.out.println("Inserisci nome utente.");
		campoPlayer = new CampoDiGioco(sc.nextLine());
		campoPlayer.setMute(true);
		startMatch();
		//System.out.println(campoComputer.printCampo());
	}

	/**
	 * Utilizza questo metodo per inserire manualmente le navi.
	 */
	public static void aggiungiNave() {
		for (Navi nave : Navi.values()) {
			System.out.println("Nave di tipo: " + nave.getNome() + " | Grandezza: " + nave.getSize());
			System.out.println(campoPlayer.printCampo());
			System.out.println("Inserisci coordinate X,Y iniziali. (X Numeri in alto, Y numeri Laterali)");
			while (!campoPlayer.aggiungiNave(nave, getCoords("X Iniziale: "), getCoords("Y Iniziale: "),
					getCoords("X Finale: "), getCoords("Y Finale: ")))
				;
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
		} while (result <= 0 || result > 11);
		return result;
	}

	/**
	 * Utilizza questo metodo per impostare automaticamente le navi.
	 */
	private static void cpuSetShip(CampoDiGioco c) {
		Random r = new Random();
		int xIniziale, yIniziale, xFinale = 0, yFinale = 0;
		for (Navi nave : Navi.values()) {
			do {
				xIniziale = r.nextInt(10) + 1;
				yIniziale = r.nextInt(10) + 1;
				if (r.nextBoolean()) {
					xFinale = xIniziale;
					yFinale = ((yIniziale > (10 - nave.getSize())) ? yIniziale-nave.getSize()+1 : yIniziale+nave.getSize()-1);
				} else {
					yFinale = yIniziale;
					xFinale = ((xIniziale > (10 - nave.getSize())) ? xIniziale-nave.getSize()+1 : xIniziale+nave.getSize()-1);
				}
			} while (!c.aggiungiNave(nave, xIniziale, yIniziale, Math.abs(xFinale), Math.abs(yFinale)));
		}
	}
	
	private static void startMatch() {
		// aggiungiNave(); //Aggiungi manualmente le navi.
		cpuSetShip(campoPlayer);
		System.out.println("Ecco il tuo campo:\n"+campoPlayer.printCampo()+"\n\nCosa vuoi fare?");
		cpuSetShip(campoComputer);
	}
}
