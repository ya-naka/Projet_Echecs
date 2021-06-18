package jeu;

import pieces.IPiece;
import pieces.Piece;

public class Case {
	private final int coordonnée;
	private IPiece piece;
	
	public Case(int coordonnée, IPiece piece) {
		this.coordonnée = coordonnée;
		this.piece = piece;
	}
	
	public int getCoordonnée() {
		return this.coordonnée;
	}
	
	public IPiece getPiece() {
		return this.piece;
	}
	
	public void setPiece(IPiece piece) {
		this.piece = piece;
	}
	
	public boolean estOccupée() {
		return this.piece != null;
	}
}
