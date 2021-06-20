package jeu;

import pieces.IPiece;

public class Historique {
	private Plateau plateau;
	private Deplacement deplacement;
	private IPiece piecePrise;
	
	public Historique(Plateau plateau, Deplacement deplacement) {
		this.deplacement = deplacement;
		this.piecePrise = plateau.getCase(deplacement.getNouvelleCoord()).estOccupée() ? 
				plateau.getCase(deplacement.getNouvelleCoord()).getPiece() : null;
	}
	
	public Deplacement getDeplacement() {
		return this.deplacement;
	}
	
	public IPiece getPiecePrise() {
		return this.piecePrise;
	}
}
