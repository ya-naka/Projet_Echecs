package jeu;

import java.util.ArrayList;
import java.util.List;

import couleur.Blanc;
import couleur.ICouleur;
import couleur.Noir;
import joueur.IJoueur;
import pieces.IPiece;
import pieces.Piece;
import pieces.Roi;
import pieces.Tour;

public class Plateau {
	public static final int NB_CASES = 64;
	private Case[] plateau;
	private IJoueur j1, j2;
	private List<Historique> historique;
	private List<IPiece> rois;
	
	public Plateau(IJoueur j1, IJoueur j2) {
		this.plateau = new Case[NB_CASES];
		for(int i = 0; i < NB_CASES; i++) {
			this.plateau[i] = new Case(i, null);
		}
		this.j1 = j1;
		this.j2 = j2;
		this.historique = new ArrayList<>();
		this.rois = new ArrayList<>();
		//A CHANGER POUR QUE CE SOIT ALEATOIRE
		this.plateau[0] = new Case(0, new Roi(0, new Blanc()));
		this.rois.add(getCase(0).getPiece());
		this.plateau[63] = new Case(63, new Roi(63, new Noir()));
		this.rois.add(getCase(63).getPiece());
		//placerPieces();
	}
	
	//vérifie si le paramètre est une coordonnée valide du tableau de cases
	public static boolean estCaseValide(int position) {
		return position >= 0 && position < NB_CASES;
	}
	
	//place aléatoirement les pièces sur le plateau au début de la partie
	public void placerPieces() {
		
	}
	
	public Case getCase(int index) {
		return this.plateau[index];
	}
	
	public void deplacer(Deplacement deplacement) {
		//ajoute le déplacement dans l'historique de la partie
		this.historique.add(new Historique(this, deplacement));
		//récupère la pièce à déplacer
		IPiece pieceDeplacee = getCase(deplacement.getCoordActuelle()).getPiece();
		//change les coordonnées de la pièce à déplacer
		pieceDeplacee.deplacer(this, deplacement.getNouvelleCoord());
		//met la pièce à déplacer sur sa nouvelle case
		getCase(deplacement.getNouvelleCoord()).setPiece(pieceDeplacee);
		//vide la case de départ
		getCase(deplacement.getCoordActuelle()).setPiece(null);
	}

	public boolean estEchec(ICouleur couleur) {
		//- récupérer le roi qui correspond à la couleur passée en paramètre
		//- parcourir le plateau
		//- pour chaque pièce du camp adverse, vérifier si un de ses déplacements possibles
		//coincide avec la case du roi
		//- si c'est le cas, retourner vrai
		return false;
	}
	
	//revient à l'état de la partie avant le dernier coup
	public void revenirEnArriere() {
		if(!this.historique.isEmpty()) {
			Historique derniereEntree = this.historique.remove(0);
			//récupère la pièce qui a été jouée
			IPiece pieceDeplacee = getCase(derniereEntree.getDeplacement().getNouvelleCoord()).getPiece();
			//change la position de la pièce qui a été jouée
			pieceDeplacee.deplacer(this, derniereEntree.getDeplacement().getCoordActuelle());
			//remet la pièce qui a été prise sur le plateau, null si aucun pièce n'avait été prise
			getCase(derniereEntree.getDeplacement().getNouvelleCoord()).setPiece(derniereEntree.getPiecePrise());
			//place la pièce jouée sur sa position précédente sur le plateau
			getCase(derniereEntree.getDeplacement().getCoordActuelle()).setPiece(pieceDeplacee);
		}
	}
	
	public String toString() {
		String plateau;
		String caractere;
		plateau = "    a   b   c   d   e   f   g   h" + System.lineSeparator();
		plateau += "   --- --- --- --- --- --- --- ---" + System.lineSeparator();
		
		for(int i = 7; i >= 0; i--) {
			plateau += (i+1) + " |";
			for(int j = 0; j <= 7; j++) {
				caractere = this.plateau[(i*8)+j].estOccupée() ? this.plateau[(i*8)+j].getPiece().toString() : " ";
				plateau += " " + caractere + " |";
			}
			plateau += " " + (i+1) + System.lineSeparator();
			plateau += "   --- --- --- --- --- --- --- ---" + System.lineSeparator();
		}
		
		plateau += "    a   b   c   d   e   f   g   h" + System.lineSeparator();
		return plateau;
	}

}
