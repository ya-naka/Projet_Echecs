package jeu;

import java.util.ArrayList;
import java.util.List;

import couleur.Blanc;
import couleur.Noir;
import joueur.IJoueur;
import pieces.IPiece;
import pieces.Piece;
import pieces.Roi;
import pieces.Tour;

public class Plateau {
	private static final int NB_CASES = 64;
	private Case[] plateau;
	private IJoueur j1, j2;
	
	public Plateau(IJoueur j1, IJoueur j2) {
		this.plateau = new Case[NB_CASES];
		for(int i = 0; i < NB_CASES; i++) {
			this.plateau[i] = new Case(i, null);
		}
		
		this.plateau[0] = new Case(0, new Roi(0, new Blanc()));
		
		this.j1 = j1;
		this.j2 = j2;
	}
	
	public String toString() {
		String plateau;
		String caractere;
		plateau = "    a   b   c   d   e   f   g   h\n";
		plateau += "   --- --- --- --- --- --- --- ---\n";
		//la première case s'affiche en bas à droite, A CHANGER
		for(int i = NB_CASES-1; i >= 0; i--) {
			if((i+1)%8 == 0) {
				plateau += ((i/8)+1) + " |";
			}
			caractere = this.plateau[i].estOccupée() ? this.plateau[i].getPiece().toString() : " ";
			plateau += " " + caractere + " |";
			if((i+1)%8 == 1) {
				plateau += " " + ((i/8)+1) + System.lineSeparator();
				plateau += "   --- --- --- --- --- --- --- ---\n";
			}
		}
		plateau += "    a   b   c   d   e   f   g   h\n";
		return plateau;
	}
}
