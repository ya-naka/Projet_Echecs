package jeu;

import pieces.IPiece;
import pieces.Piece;

public class Case {
	private final int coordonn�e;
	private IPiece piece;
	
	/**
	 * Constructeur
	 * @param coordonn�e
	 * @param piece
	 */
	public Case(int coordonn�e, IPiece piece) {
		this.coordonn�e = coordonn�e;
		this.piece = piece;
	}
	
	/** 
	 * retourne la position de la case
	 * @return
	 */
	public int getCoordonn�e() {
		return this.coordonn�e;
	}
	
	/**
	 * retourne la pi�ce se trouvant sur la case
	 * @return
	 */
	public IPiece getPiece() {
		return this.piece;
	}
	
	/**
	 * place une pi�ce sur la case
	 * @param piece � placer
	 */
	public void setPiece(IPiece piece) {
		if(piece != null) {
			assert(piece.getPosition() == getCoordonn�e());
		}
		this.piece = piece;
	}
	
	/**
	 * retourne vrai si une pi�ce se trouve sur la case
	 * @return
	 */
	public boolean estOccup�e() {
		return this.piece != null;
	}
	
	/**
	 * @return nom de la case
	 */
	public String toString() {
		return "Case " + getCoordonn�e();
	}
}
