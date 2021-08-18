package jeu;

import pieces.IPiece;
import pieces.Piece;

public class Case {
	private final int coordonnée;
	private IPiece piece;
	
	/**
	 * Constructeur
	 * @param coordonnée
	 * @param piece
	 */
	public Case(int coordonnée, IPiece piece) {
		this.coordonnée = coordonnée;
		this.piece = piece;
	}
	
	/** 
	 * retourne la position de la case
	 * @return
	 */
	public int getCoordonnée() {
		return this.coordonnée;
	}
	
	/**
	 * retourne la pièce se trouvant sur la case
	 * @return
	 */
	public IPiece getPiece() {
		return this.piece;
	}
	
	/**
	 * place une pièce sur la case
	 * @param piece à placer
	 */
	public void setPiece(IPiece piece) {
		if(piece != null) {
			assert(piece.getPosition() == getCoordonnée());
		}
		this.piece = piece;
	}
	
	/**
	 * retourne vrai si une pièce se trouve sur la case
	 * @return
	 */
	public boolean estOccupée() {
		return this.piece != null;
	}
	
	/**
	 * @return nom de la case
	 */
	public String toString() {
		return "Case " + getCoordonnée();
	}
}
