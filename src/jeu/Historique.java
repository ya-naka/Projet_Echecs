package jeu;

import pieces.IPiece;

public class Historique {
	private Plateau plateau;
	private final Deplacement deplacement;
	private final IPiece piecePrise;
	
	/**
	 * Constructeur
	 * @param plateau
	 * @param deplacement
	 */
	public Historique(Plateau plateau, Deplacement deplacement) {
		this.deplacement = deplacement;
		this.piecePrise = plateau.getCase(deplacement.getNouvelleCoord()).estOccupée() ? 
				plateau.getCase(deplacement.getNouvelleCoord()).getPiece() : null;
	}
	
	/**
	 * retourne le déplacement enregistré
	 * @return
	 */
	public Deplacement getDeplacement() {
		return this.deplacement;
	}
	
	/**
	 * retourne la pièce sortie du jeu lors du tour
	 * @return
	 */
	public IPiece getPiecePrise() {
		return this.piecePrise;
	}
	
	/**
	 * @return les données enregistrées
	 */
	public String toString() {
		return "Déplacement : " + getDeplacement().toString() + ", pièce prise : " + getPiecePrise().toString();
	}
}
