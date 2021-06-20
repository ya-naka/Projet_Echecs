package jeu;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import couleur.Blanc;
import couleur.ICouleur;
import couleur.Noir;
import joueur.Joueur;
import pieces.IPiece;
import pieces.Roi;
import pieces.Tour;

class PlateauTest {

	@Test
	void estCaseValideTest() {
		assertTrue(Plateau.estCaseValide(0));
		assertTrue(Plateau.estCaseValide(63));
		assertTrue(Plateau.estCaseValide(12));
		assertTrue(Plateau.estCaseValide(49));
		assertFalse(Plateau.estCaseValide(-1));
		assertFalse(Plateau.estCaseValide(64));
		assertFalse(Plateau.estCaseValide(70));
		assertFalse(Plateau.estCaseValide(-10));
	}
	
	@Test
	void getRoiTest() {
		Plateau plateau = new Plateau();
		ICouleur blanc = new Blanc();
		ICouleur noir = new Noir();
		//aucun roi n'est sur le plateau
		assertTrue(plateau.getRoi(noir) == null);
		assertTrue(plateau.getRoi(blanc) == null);
		List<IPiece> pieces = new ArrayList<>();
		//ajout d'un roi noir en case 0
		pieces.add(new Roi(0, noir));
		plateau.initFixe(pieces);
		assertTrue(plateau.getRoi(noir) == pieces.get(0));
		assertFalse(plateau.getRoi(noir) == null);
		assertFalse(plateau.getRoi(blanc) == pieces.get(0));
		assertTrue(plateau.getRoi(blanc) == null);
		//ajout d'un roi blanc en case 10
		pieces.add(new Roi(10, blanc));
		plateau.initFixe(pieces);
		assertTrue(plateau.getRoi(blanc) == pieces.get(1));
		assertFalse(plateau.getRoi(blanc) == pieces.get(0));
	}
	
	@Test
	void estEchecTest() {
		Plateau plateau = new Plateau();
		ICouleur noir = new Noir();
		ICouleur blanc = new Blanc();
		List<IPiece> pieces = new ArrayList<>();
		//ajout d'un roi noir en case 0
		pieces.add(new Roi(0, noir));
		plateau.initFixe(pieces);
		assertFalse(plateau.estEchec(noir));
		//ajout d'une tour noire en case 6 (même ligne que le roi noir)
		pieces.add(new Tour(6, noir));
		plateau.initFixe(pieces);
		assertFalse(plateau.estEchec(noir));
		//ajout d'une tour blanche en case 2 (même ligne que le roi noir)
		pieces.add(new Tour(2, blanc));
		plateau.initFixe(pieces);
		assertTrue(plateau.estEchec(noir));
		assertFalse(plateau.estEchec(blanc));
		//ajout d'unre tour blanche en case 16 (même colonne que le roi noir)
		pieces.add(new Tour(16, blanc));
		plateau.initFixe(pieces);
		assertTrue(plateau.estEchec(noir));
		assertFalse(plateau.estEchec(blanc));
		//ajout d'un roi blanc en case 8 (case adjacente verticalement du roi noir)
		pieces.add(new Roi(8, blanc));
		plateau.initFixe(pieces);
		assertTrue(plateau.estEchec(blanc));
		assertTrue(plateau.estEchec(noir));
	}
	
	@Test
	void deplacerTest() {
		Plateau plateau = new Plateau();
		ICouleur noir = new Noir();
		ICouleur blanc = new Blanc();
		List<IPiece> pieces = new ArrayList<>();
		//ajour d'un roi noir en case 0
		pieces.add(new Roi(0, noir));
		plateau.initFixe(pieces);
		assertTrue(plateau.getCase(0).getPiece().equals(pieces.get(0)));
		assertFalse(plateau.getCase(1).estOccupée());
		assertTrue(plateau.getCase(0).getPiece().getPosition() == plateau.getRoi(noir).getPosition());
		//on déplace le roi en case 1
		plateau.deplacer(new Deplacement(plateau, 0, 1));
		assertTrue(plateau.getCase(1).getPiece().equals(pieces.get(0)));
		assertFalse(plateau.getCase(0).estOccupée());
		assertTrue(plateau.getCase(1).getPiece().getPosition() == plateau.getRoi(noir).getPosition());
		//ajout d'une tour blanche en case 4 (même ligne que le roi noir)
		pieces.add(new Tour(4, blanc));
		plateau.initFixe(pieces);
		assertTrue(plateau.getCase(4).getPiece().equals(pieces.get(1)));
		//on déplace la tour blanche sur la case du roi noir
		plateau.deplacer(new Deplacement(plateau, 4, 1));
		assertFalse(plateau.getCase(4).estOccupée());
		assertTrue(plateau.getCase(1).getPiece().equals(pieces.get(1)));
		assertTrue(plateau.getRoi(noir) == null);
	}
	
	
	
	
	
	
	
	
	
	

}
