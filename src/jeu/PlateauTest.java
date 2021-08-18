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
		ICouleur blanc = new Blanc();
		ICouleur noir = new Noir();
		Plateau plateau = new Plateau();
		List<IPiece> pieces = new ArrayList<>();
		plateau.initFixe(pieces);
		//aucun roi n'est sur le plateau
		assertTrue(plateau.getRoi(noir) == null);
		assertTrue(plateau.getRoi(blanc) == null);
		//ajout d'un roi noir en case a1
		pieces.add(new Roi(0, noir));
		plateau.initFixe(pieces);
		assertTrue(plateau.getRoi(noir) == pieces.get(0));
		assertFalse(plateau.getRoi(noir) == null);
		assertFalse(plateau.getRoi(blanc) == pieces.get(0));
		assertTrue(plateau.getRoi(blanc) == null);
		//ajout d'un roi blanc en case c2
		pieces.add(new Roi(10, blanc));
		plateau.initFixe(pieces);
		assertTrue(plateau.getRoi(blanc) == pieces.get(1));
		assertFalse(plateau.getRoi(blanc) == pieces.get(0));
	}
	
	@Test
	void estEchecTest() {
		ICouleur noir = new Noir();
		ICouleur blanc = new Blanc();
		Plateau plateau = new Plateau();
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
		ICouleur noir = new Noir();
		ICouleur blanc = new Blanc();
		Plateau plateau = new Plateau();
		List<IPiece> pieces = new ArrayList<>();
		//ajour d'un roi noir en case 0
		pieces.add(new Roi(0, noir));
		plateau.initFixe(pieces);
		assertTrue(plateau.getCase(0).getPiece().equals(pieces.get(0)));
		assertFalse(plateau.getCase(1).estOccupée());
		assertTrue(plateau.getCase(0).getPiece().getPosition() == plateau.getRoi(noir).getPosition());
		//on déplace le roi en case 1
		plateau.deplacer(new Deplacement(plateau, 0, 1), noir);
		assertTrue(plateau.getCase(1).getPiece().equals(pieces.get(0)));
		assertFalse(plateau.getCase(0).estOccupée());
		assertTrue(plateau.getCase(1).getPiece().getPosition() == plateau.getRoi(noir).getPosition());
		//ajout d'une tour blanche en case 4 (même ligne que le roi noir)
		pieces.add(new Tour(4, blanc));
		//ajout d'un roi blanc en case 63
		pieces.add(new Roi(63, blanc));
		plateau.initFixe(pieces);
		assertTrue(plateau.getCase(4).getPiece().equals(pieces.get(1)));
		//on déplace la tour blanche sur la case du roi noir
		assertTrue(plateau.getCase(1).getPiece() == pieces.get(0));
		assertTrue(plateau.getCase(4).getPiece() == pieces.get(1));
		//on vérifie que le roi noir est bien dans la liste des rois du plateau
		assertTrue(plateau.getRoi(noir) == pieces.get(0));
		plateau.deplacer(new Deplacement(plateau, 4, 1), blanc);
		assertFalse(plateau.getCase(4).estOccupée());
		assertTrue(plateau.getCase(1).getPiece().equals(pieces.get(1)));
		//on vérifie que le roi noir a bien été enlevé de la liste des rois
		assertTrue(plateau.getRoi(noir) == null);
		//on vérifie que le roi blanc est toujours présent dans la liste des rois
		assertTrue(plateau.getRoi(blanc) == pieces.get(2));
	}
	
	@Test
	void revenirEnArriereTest() {
		ICouleur noir = new Noir();
		ICouleur blanc = new Blanc();
		Plateau plateau = new Plateau();
		List<IPiece> pieces = new ArrayList<>();
		//ajout d'un roi noir en case 0
		pieces.add(new Roi(0, noir));
		plateau.initFixe(pieces);
		assertTrue(plateau.getRoi(noir) == pieces.get(0));
		assertTrue(plateau.getCase(0).getPiece() == pieces.get(0));
		plateau.revenirEnArriere();
		//on vérifie que le roi n'a pas été retiré de la liste des rois
		assertTrue(plateau.getRoi(noir) == pieces.get(0));
		//on vérifie que l'état du plateau n'a changé, étant donné qu'aucun déplacement n'a été effectué
		assertTrue(plateau.getCase(0).getPiece() == pieces.get(0));
		//ajout d'une tour blanche en case 6
		pieces.add(new Tour(6, blanc));
		plateau.initFixe(pieces);
		assertTrue(plateau.getCase(6).getPiece() == pieces.get(1));
		//on déplace la tour sur la case 5
		plateau.deplacer(new Deplacement(plateau, 6, 5), blanc);
		assertTrue(plateau.getCase(5).getPiece() == pieces.get(1));
		//on revient à l'état précédent de la partie, la tour revient en case 6
		plateau.revenirEnArriere();
		assertTrue(plateau.getCase(6).getPiece() == pieces.get(1));
		assertFalse(plateau.getCase(5).getPiece() == pieces.get(1));
		assertFalse(plateau.getCase(5).estOccupée());
		assertTrue(plateau.getCase(5).getPiece() == null);
		//on déplace la tour blanche sur la case du roi noir
		plateau.deplacer(new Deplacement(plateau, 6, 0), blanc);
		//on vérifie que le roi a été retiré de la liste des rois
		assertFalse(plateau.getRoi(noir) == pieces.get(0));
		assertTrue(plateau.getCase(6).getPiece() == null);
		assertFalse(plateau.getCase(6).estOccupée());
		assertTrue(plateau.getCase(0).getPiece() == pieces.get(1));
		//on revient à l'état précédent de la partie, le roie st en case 0 et la tour en case 6
		plateau.revenirEnArriere();
		//on vérifie que le roi est de nouveau dans la liste des rois
		assertTrue(plateau.getRoi(noir) == pieces.get(0));
		assertTrue(plateau.getCase(6).getPiece() == pieces.get(1));
		assertTrue(plateau.getCase(0).getPiece() == pieces.get(0));
	}
	
	// revoir le test pour prender en compte les modifications apportées à la fonction
	@Test
	void estPatTest() {
		ICouleur noir = new Noir();
		ICouleur blanc = new Blanc();
		Plateau plateau = new Plateau();
		List<IPiece> pieces = new ArrayList<>();
		//ajout d'un roi noir en a1
		pieces.add(new Roi(0, noir));
		//ajout d'une tour blanche en a3
		pieces.add(new Tour(16, blanc));
		plateau.initFixe(pieces);
		assertFalse(plateau.estPat(noir));
		assertFalse(plateau.estPat(blanc));
		//ajout d'une tour blanche en e1
		pieces.add(new Tour(4, blanc));
		plateau.initFixe(pieces);
		assertFalse(plateau.estPat(noir));
		assertFalse(plateau.estPat(blanc));
		//ajout d'une tour blanche en d2, mettant les blancs en pat
		pieces.add(new Tour(11, blanc));
		plateau.initFixe(pieces);
		assertTrue(plateau.estPat(noir));
		assertFalse(plateau.estPat(blanc));
	}
	
	@Test
	void estEchecEtMat() {
		ICouleur noir = new Noir();
		ICouleur blanc = new Blanc();
		Plateau plateau = new Plateau();
		List<IPiece> pieces = new ArrayList<>();
		//ajout d'un roi blanc en case a1
		pieces.add(new Roi(0, blanc));
		plateau.initFixe(pieces);
		assertFalse(plateau.estEchecEtMat(blanc));
		//ajout d'un roi noir en case c2
		pieces.add(new Roi(10, noir));
		plateau.initFixe(pieces);
		assertFalse(plateau.estEchecEtMat(blanc));
		assertFalse(plateau.estEchecEtMat(noir));
		//ajout d'une tour noire en case a3, mettant ainsi le roi blanc en échec et mat
		pieces.add(new Tour(16, noir));
		plateau.initFixe(pieces);
		assertTrue(plateau.estEchecEtMat(blanc));
		assertFalse(plateau.estEchecEtMat(noir));
	}

}
