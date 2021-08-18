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
		this.piecePrise = plateau.getCase(deplacement.getNouvelleCoord()).estOccup�e() ? 
				plateau.getCase(deplacement.getNouvelleCoord()).getPiece() : null;
	}
	
	/**
	 * retourne le d�placement enregistr�
	 * @return
	 */
	public Deplacement getDeplacement() {
		return this.deplacement;
	}
	
	/**
	 * retourne la pi�ce sortie du jeu lors du tour
	 * @return
	 */
	public IPiece getPiecePrise() {
		return this.piecePrise;
	}
	
	/**
	 * @return les donn�es enregistr�es
	 */
	public String toString() {
		return "D�placement : " + getDeplacement().toString() + ", pi�ce prise : " + getPiecePrise().toString();
	}
}
