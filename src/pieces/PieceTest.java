package pieces;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import couleur.Blanc;
import couleur.Noir;
import jeu.Plateau;
import joueur.Joueur;

class PieceTest {

	@Test
	void getPositionTest() {
		IPiece piece = new Roi(0, new Noir());
		assertTrue(piece.getPosition() == 0);
		assertFalse(piece.getPosition() == 1);
		assertFalse(piece.getPosition() == 60);
	}
	
	@Test
	void getCouleurTest() {
		IPiece piece = new Roi(0, new Noir());
		assertTrue(piece.getCouleur().estNoir());
		assertFalse(piece.getCouleur().estBlanc());
		assertTrue(piece.getCouleur().estMemeCouleur(new Noir()));
		assertFalse(piece.getCouleur().estMemeCouleur(new Blanc()));
	}
	
	@Test
	void peutDeplacerRoiTest() {
		Plateau plateau = new Plateau(new Joueur(new Blanc()), new Joueur(new Noir()));
		List<IPiece> pieces = new ArrayList<>();
		pieces.add(new Roi(0, new Noir()));
		pieces.add(new Roi(15, new Blanc()));
		plateau.initFixe(pieces);
		//récupère le roi en case 0 sans obstacle
		IPiece piece = plateau.getCase(0).getPiece();
		assertTrue(piece.peutDeplacer(plateau, 1));
		assertTrue(piece.peutDeplacer(plateau, 8));
		assertTrue(piece.peutDeplacer(plateau, 9));
		assertFalse(piece.peutDeplacer(plateau, 2));
		assertFalse(piece.peutDeplacer(plateau, 7));
		assertFalse(piece.peutDeplacer(plateau, 0));
		assertFalse(piece.peutDeplacer(plateau, -1));
		assertFalse(piece.peutDeplacer(plateau, -8));
		assertFalse(piece.peutDeplacer(plateau, -7));
		assertFalse(piece.peutDeplacer(plateau, -9));
		//récupère le roi en case 15 sans obstacle
		piece = plateau.getCase(15).getPiece();
		assertTrue(piece.peutDeplacer(plateau, 7));
		assertTrue(piece.peutDeplacer(plateau, 6));
		assertTrue(piece.peutDeplacer(plateau, 14));
		assertTrue(piece.peutDeplacer(plateau, 23));
		assertTrue(piece.peutDeplacer(plateau, 22));
		assertFalse(piece.peutDeplacer(plateau, 16));
		assertFalse(piece.peutDeplacer(plateau, 8));
		assertFalse(piece.peutDeplacer(plateau, 24));
		assertFalse(piece.peutDeplacer(plateau, 9));
		//ajout d'une pièce adverse sur une case voisine du roi blanc
		pieces.add(new Tour(14, new Noir()));
		plateau.initFixe(pieces);
		assertTrue(piece.peutDeplacer(plateau, 14));
		//ajout d'une pièce alliée sur une case voisine du roi blanc
		pieces.add(new Tour(7, new Blanc()));
		plateau.initFixe(pieces);
		assertFalse(piece.peutDeplacer(plateau, 7));
	}
	
	@Test
	void peutDeplacerTourTest() {
		Plateau plateau = new Plateau(new Joueur(new Blanc()), new Joueur(new Noir()));
		List<IPiece> pieces = new ArrayList<>();
		pieces.add(new Tour(18, new Noir()));
		pieces.add(new Tour(7, new Blanc()));
		plateau.initFixe(pieces);
		//récupère la tour en case 18 sans obstacle
		IPiece piece = plateau.getCase(18).getPiece();
		assertTrue(piece.peutDeplacer(plateau, 10));
		assertTrue(piece.peutDeplacer(plateau, 2));
		assertTrue(piece.peutDeplacer(plateau, 26));
		assertTrue(piece.peutDeplacer(plateau, 58));
		assertTrue(piece.peutDeplacer(plateau, 16));
		assertTrue(piece.peutDeplacer(plateau, 19));
		assertTrue(piece.peutDeplacer(plateau, 22));
		assertTrue(piece.peutDeplacer(plateau, 23));
		assertFalse(piece.peutDeplacer(plateau, 9));
		assertFalse(piece.peutDeplacer(plateau, 0));
		assertFalse(piece.peutDeplacer(plateau, 11));
		assertFalse(piece.peutDeplacer(plateau,27));
		assertFalse(piece.peutDeplacer(plateau, 25));
		assertFalse(piece.peutDeplacer(plateau, 24));
		//récupère la tour en case 7 sans obstacle
		piece = plateau.getCase(7).getPiece();
		assertTrue(piece.peutDeplacer(plateau, 6));
		assertTrue(piece.peutDeplacer(plateau, 3));
		assertTrue(piece.peutDeplacer(plateau, 0));
		assertTrue(piece.peutDeplacer(plateau, 15));
		assertTrue(piece.peutDeplacer(plateau, 47));
		assertTrue(piece.peutDeplacer(plateau, 63));
		assertFalse(piece.peutDeplacer(plateau, 8));
		assertFalse(piece.peutDeplacer(plateau, 14));
		assertFalse(piece.peutDeplacer(plateau, 13));
		assertFalse(piece.peutDeplacer(plateau, 22));
		assertFalse(piece.peutDeplacer(plateau, 41));
		//ajout d'une pièce adverse sur la trajectoire horizontale de la tour blanche
		pieces.add(new Roi(4, new Noir()));
		plateau.initFixe(pieces);
		assertTrue(piece.peutDeplacer(plateau, 6));
		assertTrue(piece.peutDeplacer(plateau, 4));
		assertFalse(piece.peutDeplacer(plateau, 3));
		assertFalse(piece.peutDeplacer(plateau, 2));
		assertFalse(piece.peutDeplacer(plateau, 1));
		assertFalse(piece.peutDeplacer(plateau, 0));
		//ajout d'une pièce alliée sur la trajectoire horizontale de la tour blanche
		pieces.add(new Roi(47, new Blanc()));
		plateau.initFixe(pieces);
		assertTrue(piece.peutDeplacer(plateau, 15));
		assertTrue(piece.peutDeplacer(plateau, 23));
		assertTrue(piece.peutDeplacer(plateau, 31));
		assertTrue(piece.peutDeplacer(plateau, 39));
		assertFalse(piece.peutDeplacer(plateau, 47));
		assertFalse(piece.peutDeplacer(plateau, 55));
		assertFalse(piece.peutDeplacer(plateau, 63));
	}

}
