package jeu;

import pieces.Piece;

public class Case {
	private final int coordonn�e;
	private Piece piece;
	
	public Case(int coordonn�e, Piece piece) {
		this.coordonn�e = coordonn�e;
		this.piece = piece;
	}
	
	public int getCoordonn�e() {
		return this.coordonn�e;
	}
	
	public Piece getPiece() {
		return this.piece;
	}
	
	public boolean estOccup�e() {
		return this.piece != null;
	}
}
