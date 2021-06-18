package pieces;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import couleur.Noir;

class PieceTest {

	@Test
	void getPositionTest() {
		IPiece piece = new Roi(0, new Noir());
		assertTrue(piece.getPosition() == 0);
		assertFalse(piece.getPosition() == 1);
		assertFalse(piece.getPosition() == 60);
	}

}
