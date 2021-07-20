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

class DeplacementTest {

	@Test
	void equalsTest() {
		Plateau plateau = new Plateau(new Joueur(new Blanc()), new Joueur(new Noir()));
		assertTrue(new Deplacement(plateau, 0, 1).equals(new Deplacement(plateau, 0, 1)));
		assertFalse(new Deplacement(plateau, 0, 1).equals(new Deplacement(plateau, 1, 0)));
	}
	
	@Test void estDepalcementValideTest() {
		ICouleur noir = new Noir();
		ICouleur blanc = new Blanc();
		Plateau plateau = new Plateau(new Joueur(blanc), new Joueur(noir));
		List<IPiece> pieces = new ArrayList<>();
		//ajout d'un roi noir en case 0
		pieces.add(new Roi(0, noir));
		plateau.initFixe(pieces);
		assertTrue(plateau.getCase(0).getPiece() == pieces.get(0));
		//le roi se déplace sur une des cases adjacentes
		assertTrue(new Deplacement(plateau, 0, 1).estDeplacementValide(noir));
		assertTrue(new Deplacement(plateau, 0, 8).estDeplacementValide(noir));
		assertTrue(new Deplacement(plateau, 0, 9).estDeplacementValide(noir));
		//le roi se déplace sur une case non adjacente
		assertFalse(new Deplacement(plateau, 0, 7).estDeplacementValide(noir));
		assertFalse(new Deplacement(plateau, 0, 15).estDeplacementValide(noir));
		assertFalse(new Deplacement(plateau, 0, 60).estDeplacementValide(noir));
		
		//ajout d'une tour blanche en case 6 (même ligne que le roi noir)
		pieces.add(new Tour(6, blanc));
		plateau.initFixe(pieces);
		//le roi change de ligne
		assertTrue(new Deplacement(plateau, 0, 8).estDeplacementValide(noir));
		assertTrue(new Deplacement(plateau, 0, 9).estDeplacementValide(noir));
		//le roi se déplace sur la même ligne, en direction de la tour
		assertFalse(new Deplacement(plateau, 0, 1).estDeplacementValide(noir));
		assertFalse(new Deplacement(plateau, 0, 5).estDeplacementValide(noir));
		
		//ajout d'une tour blanche en case 24 (même colonne que le roi noir)
		pieces.add(new Tour(24, blanc));
		plateau.initFixe(pieces);
		assertTrue(new Deplacement(plateau, 0, 9).estDeplacementValide(noir));
		assertFalse(new Deplacement(plateau, 0, 8).estDeplacementValide(noir));
		assertFalse(new Deplacement(plateau, 0, 1).estDeplacementValide(noir));
		
		//on retire la tour en case 6
		pieces.remove(1);
		//on ajoute une tour noire en case 8 (entre le roi noir et la tour blanche)
		pieces.add(new Tour(8, noir));
		plateau.initFixe(pieces);
		assertTrue(new Deplacement(plateau, 0, 9).estDeplacementValide(noir));
		assertTrue(new Deplacement(plateau, 0, 1).estDeplacementValide(noir));
		assertTrue(new Deplacement(plateau, 8, 16).estDeplacementValide(noir));
		assertTrue(new Deplacement(plateau, 8, 24).estDeplacementValide(noir));
		assertFalse(new Deplacement(plateau, 8, 0).estDeplacementValide(noir));
		assertFalse(new Deplacement(plateau, 8, 9).estDeplacementValide(noir));
	}

}
