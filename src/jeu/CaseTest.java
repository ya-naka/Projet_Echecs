package jeu;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import couleur.Blanc;
import couleur.Noir;
import pieces.IPiece;
import pieces.Roi;

class CaseTest {

	@Test
	void CaseTest() {
		IPiece piece = new Roi(0, new Noir());
		Case c = new Case(piece.getPosition(), piece);
		assertTrue(c.estOccupée());
		assertTrue(c.getCoordonnée() == piece.getPosition());
		assertTrue(c.getCoordonnée() == 0);
		assertTrue(c.getPiece() == piece);
		
		c = new Case(2, null);
		assertTrue(c.getCoordonnée() == 2);
		assertFalse(c.estOccupée());
		assertTrue(c.getPiece() == null);
		
		piece = new Roi(2, new Blanc());
		c.setPiece(piece);
		assertTrue(c.estOccupée());
		assertTrue(c.getPiece() == piece);
	}

}
