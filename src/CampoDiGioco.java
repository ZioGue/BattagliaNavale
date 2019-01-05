import java.util.ArrayList;
import java.util.Arrays;

/**
 * 15*15 N = nave intera X = nave colpita O = Miss
 * 
 * @author reloonfire
 *
 */
public class CampoDiGioco {

	@SuppressWarnings("unused")
	private String player;
	private boolean mute, inGioco;
	private Navi[] armeria = new Navi[6]; // Navi usabili per player
	private int contatoreArmeria = 0, naviInVita = armeria.length;
	private char[][] campo = new char[10][10]; // Dimensioni campo
	private char[][] celleColpite = new char[10][10];

	public CampoDiGioco(String player) {
		this(player, false);
	}
	
	public CampoDiGioco(String player, boolean mute) {
		this.mute = mute;
		this.player = player;
		for (int i = 0; i < campo.length; i++) {
			for (int j = 0; j < campo.length; j++) {
				campo[i][j] = ' ';
				celleColpite[i][j] = ' ';
			}
		}
	}

	public boolean aggiungiNave(Navi nave, int xInizio, int yInizio, int xFine, int yFine) {
		int dimensione = 0;
		if ((Math.abs(xInizio - xFine) + 1) == nave.getSize() && yInizio == yFine) {
			dimensione = Math.abs(xInizio - xFine) + 1;
			// Orizzontale
			// Controllo che Inizio sia più piccolo
			if (xFine < xInizio) {
				// Scambio
				int temp = xInizio;
				xInizio = xFine;
				xFine = temp;
			}
			// Navi vicine controllo
			if (checkOrizzontale(yInizio - 1, xInizio - 1, xFine - 1, nave.getIcon()))
				return false;

			for (int x = xInizio - 1; x <= xFine - 1; x++) {
				campo[yInizio - 1][x] = nave.getIcon();
			}
		} else if ((Math.abs(yInizio - yFine) + 1) == nave.getSize() && xInizio == xFine) {
			dimensione = Math.abs(yInizio - yFine) + 1;
			// Verticale

			// Controllo che Inizio sia più piccolo
			if (yFine < yInizio) {
				// Scambio
				int temp = yInizio;
				yInizio = yFine;
				yFine = temp;
			}
			if (checkVerticale(xInizio - 1, yInizio - 1, yFine - 1, nave.getIcon()))
				return false;
			for (int y = yInizio - 1; y <= yFine - 1; y++) {
				campo[y][xInizio - 1] = nave.getIcon();
			}

		} else {
			if(!mute)
				System.err.println("Dimensioni errate nave " + nave.getIcon() + " dimensione inserita: " + dimensione + " coords = " + xInizio + " ? " + yInizio + " - " + xFine + " | " + yFine);
			return false;
		}
		armeria[contatoreArmeria] = nave;
		contatoreArmeria++;
		return true;
	}

	private boolean checkVerticale(int constante, int inizio, int fine, char nomeNave) {

		for (int i = inizio; i <= fine; i++) {
			if (!(check(i - 1, constante - 1, nomeNave) && check(i - 1, constante, nomeNave)
					&& check(i - 1, constante + 1, nomeNave) && check(i, constante + 1, nomeNave)
					&& check(i + 1, constante + 1, nomeNave) && check(i + 1, constante, nomeNave)
					&& check(i + 1, constante - 1, nomeNave) && check(i, constante - 1, nomeNave)
					&& check(i, constante, nomeNave))) {
				if(!mute)
					System.out.println("Collisione");
				return true;
			}
		}
		return false;
	}

	private boolean checkOrizzontale(int constante, int inizio, int fine, char nomeNave) {

		for (int i = inizio; i <= fine; i++) {
			if (!(check(constante - 1, i - 1, nomeNave) && check(constante - 1, i, nomeNave)
					&& check(constante - 1, i + 1, nomeNave) && check(constante, i + 1, nomeNave)
					&& check(constante + 1, i + 1, nomeNave) && check(constante + 1, i, nomeNave)
					&& check(constante + 1, i - 1, nomeNave) && check(constante, i - 1, nomeNave)
					&& check(constante, i, nomeNave))) {
				if(!mute)
					System.out.println("Collisione");
				return true;
			}
		}
		return false;
	}

	private boolean check(int y, int x, char icon) {
		try {
			// campo[y][x] = '*'; //Dove controllo?
			if (!(campo[y][x] == icon || campo[y][x] == ' ')) {
				if(!mute)
					System.out.println((y + 1) + "|" + (x + 1) + " ! " + icon + " ? " + campo[y][x]);
				return false;
			}
		} catch (Exception e) {
			// RIP
		}
		return true;
	}
	
	public boolean checkWin() {
		return (naviInVita == 0) ? true : false;
	}

	public String printCampo() {

		String result = "  | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10|\n";
		int contatore = 0;
		for (int x = 0; x < 10; x++) {
			result += "-------------------------------------------\n";
			contatore = x + 1;
			result += contatore;
			if (contatore <= 9)
				result += ' ' + "|";
			else
				result += "|";
			for (int y = 0; y < 10; y++) {
				result += " " + campo[x][y] + " |";
			}
			result += "\n";
		}
		result += "-------------------------------------------\n";
		return result;
	}
	
	public char returnCharInPos(int x, int y) {
		return campo[y][x];
	}
	
	public void setMute(boolean mute) {
		this.mute = mute;
	}

}
