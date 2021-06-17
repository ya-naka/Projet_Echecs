package utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SaisieUtilsTest {

	@Test
	void estColonneValideTest() {
		assertTrue(SaisieUtils.estColonneValide('a'));
		assertTrue(SaisieUtils.estColonneValide('d'));
		assertTrue(SaisieUtils.estColonneValide('h'));
		
		assertFalse(SaisieUtils.estColonneValide('k'));
		assertFalse(SaisieUtils.estColonneValide('4'));
		assertFalse(SaisieUtils.estColonneValide('9'));
	}
	
	@Test
	void estLigneValideTest() {
		assertTrue(SaisieUtils.estLigneValide('1'));
		assertTrue(SaisieUtils.estLigneValide('5'));
		assertTrue(SaisieUtils.estLigneValide('8'));
		
		assertFalse(SaisieUtils.estLigneValide('9'));
		assertFalse(SaisieUtils.estLigneValide('a'));
		assertFalse(SaisieUtils.estLigneValide('0'));
	}
	
	@Test
	void saisieCorrecteTest() {
		assertTrue(SaisieUtils.estSaisieValide("a1b2"));
		assertTrue(SaisieUtils.estSaisieValide("c6g8"));
		assertTrue(SaisieUtils.estSaisieValide("h8h7"));
		
		assertFalse(SaisieUtils.estSaisieValide("a1a1"));
		assertFalse(SaisieUtils.estSaisieValide("r1a1"));
		assertFalse(SaisieUtils.estSaisieValide("b2p1"));
	}

}
