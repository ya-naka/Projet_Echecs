package jeu;

import pieces.IPiece;
import pieces.Piece;

public class Case {
	private final int coordonn�e;
	private IPiece piece;
	
	public Case(int coordonn�e, IPiece piece) {
		this.coordonn�e = coordonn�e;
		this.piece = piece;
	}
	
	public int getCoordonn�e() {
		return this.coordonn�e;
	}
	
	public IPiece getPiece() {
		return this.piece;
	}
	
	public void setPiece(IPiece piece) {
		this.piece = piece;
	}
	
	public boolean estOccup�e() {
		return this.piece != null;
	}
}
