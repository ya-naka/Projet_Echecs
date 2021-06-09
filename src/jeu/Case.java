package jeu;

import pieces.Piece;

public class Case {
	private final int coordonnée;
	private Piece piece;
	
	public Case(int coordonnée, Piece piece) {
		this.coordonnée = coordonnée;
		this.piece = piece;
	}
	
	public int getCoordonnée() {
		return this.coordonnée;
	}
	
	public Piece getPiece() {
		return this.piece;
	}
	
	public boolean estOccupée() {
		return this.piece != null;
	}
}
