package jeu;

import joueur.IJoueur;
import pieces.IPiece;
import pieces.Piece;
import pieces.Roi;
import pieces.Tour;

public class Plateau {
	private static final int NB_CASES = 8;
	private IPiece[][] plateau;
	private IJoueur j1, j2;
	
	public Plateau(IJoueur j1, IJoueur j2) {
		this.plateau = new Piece[NB_CASES][NB_CASES];
		this.plateau[0][0] = new Tour(0,0,"blanc");
		this.plateau[0][1] = new Roi(0,1, "blanc");
		this.plateau[2][4] = new Roi(2,4, "noir");
		this.j1 = j1;
		this.j2 = j2;
	}
	
	public String toString() {
		String plateau;
		plateau = "    a   b   c   d   e   f   g   h\n";
		plateau += "   --- --- --- --- --- --- --- ---\n";
		for(int i = NB_CASES-1; i >= 0; i--) {
			plateau += (i+1) + " |";
			for(int j = 0; j < NB_CASES; j++) {
				String caractere = this.plateau[i][j] == null ? " " : this.plateau[i][j].toString();
				plateau += " " + caractere + " |";
			}
			plateau += " " + (i+1) + System.lineSeparator();
			plateau += "   --- --- --- --- --- --- --- ---\n";
		}
		plateau += "    a   b   c   d   e   f   g   h\n";
		return plateau;
	}
}
