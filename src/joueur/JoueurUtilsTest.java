package joueur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class JoueurUtilsTest {

	@Test
	void estColonneValideTest() {
		assertTrue(JoueurUtils.estColonneValide('a'));
		assertTrue(JoueurUtils.estColonneValide('d'));
		assertTrue(JoueurUtils.estColonneValide('h'));
		
		assertFalse(JoueurUtils.estColonneValide('k'));
		assertFalse(JoueurUtils.estColonneValide('4'));
		assertFalse(JoueurUtils.estColonneValide('9'));
	}
	
	@Test
	void estLigneValideTest() {
		assertTrue(JoueurUtils.estLigneValide('1'));
		assertTrue(JoueurUtils.estLigneValide('5'));
		assertTrue(JoueurUtils.estLigneValide('8'));
		
		assertFalse(JoueurUtils.estLigneValide('9'));
		assertFalse(JoueurUtils.estLigneValide('a'));
		assertFalse(JoueurUtils.estLigneValide('0'));
	}
	
	@Test
	void saisieCorrecteTest() {
		assertTrue(JoueurUtils.estSaisieValide("a1b2"));
		assertTrue(JoueurUtils.estSaisieValide("c6g8"));
		assertTrue(JoueurUtils.estSaisieValide("h8h7"));
		
		assertFalse(JoueurUtils.estSaisieValide("a1a1"));
		assertFalse(JoueurUtils.estSaisieValide("r1a1"));
		assertFalse(JoueurUtils.estSaisieValide("b2p1"));
	}

}
