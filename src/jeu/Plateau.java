package jeu;

import java.util.ArrayList;
import java.util.List;

import couleur.Blanc;
import couleur.ICouleur;
import couleur.Noir;
import joueur.IJoueur;
import pieces.IPiece;
import pieces.Piece;
import pieces.Roi;
import pieces.Tour;

public class Plateau {
	public static final int NB_CASES = 64;
	private Case[] plateau;
	private IJoueur j1, j2;
	private List<Deplacement> historique;
	
	public Plateau(IJoueur j1, IJoueur j2) {
		this.plateau = new Case[NB_CASES];
		for(int i = 0; i < NB_CASES; i++) {
			this.plateau[i] = new Case(i, null);
		}
		
		this.plateau[0] = new Case(0, new Roi(0, new Blanc()));
		this.plateau[63] = new Case(63, new Roi(63, new Noir()));
		this.j1 = j1;
		this.j2 = j2;
		this.historique = new ArrayList<>();
	}
	
	public Case getCase(int index) {
		return this.plateau[index];
	}
	
	public String toString() {
		String plateau;
		String caractere;
		plateau = "    a   b   c   d   e   f   g   h" + System.lineSeparator();
		plateau += "   --- --- --- --- --- --- --- ---" + System.lineSeparator();
		
		for(int i = 7; i >= 0; i--) {
			plateau += (i+1) + " |";
			for(int j = 0; j <= 7; j++) {
				caractere = this.plateau[(i*8)+j].estOccupée() ? this.plateau[(i*8)+j].getPiece().toString() : " ";
				plateau += " " + caractere + " |";
			}
			plateau += " " + (i+1) + System.lineSeparator();
			plateau += "   --- --- --- --- --- --- --- ---" + System.lineSeparator();
		}
		
		plateau += "    a   b   c   d   e   f   g   h" + System.lineSeparator();
		return plateau;
	}

	public void deplacer(Deplacement deplacement) {
		// TODO Auto-generated method stub
		
	}

	public boolean estEchec(ICouleur couleur) {
		// TODO Auto-generated method stub
		return false;
	}
}
