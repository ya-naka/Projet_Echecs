package couleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CouleurTest {

	@Test
	void couleurTest() {
		ICouleur noir = new Noir();
		ICouleur blanc = new Blanc();
		
		assertTrue(noir.estNoir());
		assertFalse(noir.estBlanc());
		assertTrue(blanc.estBlanc());
		assertFalse(blanc.estNoir());
		assertFalse(blanc.estMemeCouleur(noir));
		assertTrue(noir.estMemeCouleur(new Noir()));
		assertTrue(blanc.estMemeCouleur(new Blanc()));
	}

}
