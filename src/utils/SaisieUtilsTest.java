package utils;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

import couleur.Blanc;
import couleur.Noir;
import jeu.Deplacement;
import jeu.Plateau;
import joueur.Joueur;

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

	@Test
	void estSaisieValideAvecSaisieTest() {
		Scanner sin = new Scanner(System.in);
		//saisie doit être correct
		String saisie = sin.nextLine();
		assertTrue(SaisieUtils.estSaisieValide(saisie));
		System.out.println(new Deplacement(new Plateau(new Joueur(new Blanc()), new Joueur(new Noir())), saisie));
	}
}
