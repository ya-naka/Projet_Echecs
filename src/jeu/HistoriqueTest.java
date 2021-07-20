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

class HistoriqueTest {

	@Test
	void piecePriseTest() {
		ICouleur noir = new Noir();
		ICouleur blanc = new Blanc();
		Plateau plateau = new Plateau(new Joueur(blanc), new Joueur(noir));
		List<IPiece> pieces = new ArrayList<>();
		//ajout d'un roi noir en case 0
		pieces.add(new Roi(0, noir));
		plateau.initFixe(pieces);
		//le roi se rend en case 1
		Historique histo = new Historique(plateau, new Deplacement(plateau, 0, 1));
		assertTrue(histo.getPiecePrise() == null);
		//ajout d'une tour blanche en case 1, adjacente au roi noir
		pieces.add(new Tour(1, blanc));
		plateau.initFixe(pieces);
		//le roi prend la place de la tour
		histo = new Historique(plateau, new Deplacement(plateau, 0, 1));
		assertTrue(histo.getPiecePrise() == pieces.get(1));
		assertFalse(histo.getPiecePrise() == null);
		//la tour prend la place du roi
		histo = new Historique(plateau, new Deplacement(plateau, 1, 0));
		assertTrue(histo.getPiecePrise() == pieces.get(0));
		assertFalse(histo.getPiecePrise() == null);
	}

}
