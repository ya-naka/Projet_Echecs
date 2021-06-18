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
	
	//v�rifie si le param�tre est une coordonn�e valide du tableau de cases
	public static boolean estCaseValide(int position) {
		return position >= 0 && position < NB_CASES;
	}
	
	//place al�atoirement les pi�ces sur le plateau au d�but de la partie
	public void placerPieces() {
		
	}
	
	public Case getCase(int index) {
		return this.plateau[index];
	}
	
	public void deplacer(Deplacement deplacement) {
		//ajoute le d�placement dans l'historique de la partie
		this.historique.add(new Historique(this, deplacement));
		//r�cup�re la pi�ce � d�placer
		IPiece pieceDeplacee = getCase(deplacement.getCoordActuelle()).getPiece();
		//change les coordonn�es de la pi�ce � d�placer
		pieceDeplacee.deplacer(this, deplacement.getNouvelleCoord());
		//met la pi�ce � d�placer sur sa nouvelle case
		getCase(deplacement.getNouvelleCoord()).setPiece(pieceDeplacee);
		//vide la case de d�part
		getCase(deplacement.getCoordActuelle()).setPiece(null);
	}

	public boolean estEchec(ICouleur couleur) {
		//- r�cup�rer le roi qui correspond � la couleur pass�e en param�tre
		//- parcourir le plateau
		//- pour chaque pi�ce du camp adverse, v�rifier si un de ses d�placements possibles
		//coincide avec la case du roi
		//- si c'est le cas, retourner vrai
		return false;
	}
	
	//revient � l'�tat de la partie avant le dernier coup
	public void revenirEnArriere() {
		if(!this.historique.isEmpty()) {
			Historique derniereEntree = this.historique.remove(0);
			//r�cup�re la pi�ce qui a �t� jou�e
			IPiece pieceDeplacee = getCase(derniereEntree.getDeplacement().getNouvelleCoord()).getPiece();
			//change la position de la pi�ce qui a �t� jou�e
			pieceDeplacee.deplacer(this, derniereEntree.getDeplacement().getCoordActuelle());
			//remet la pi�ce qui a �t� prise sur le plateau, null si aucun pi�ce n'avait �t� prise
			getCase(derniereEntree.getDeplacement().getNouvelleCoord()).setPiece(derniereEntree.getPiecePrise());
			//place la pi�ce jou�e sur sa position pr�c�dente sur le plateau
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
				caractere = this.plateau[(i*8)+j].estOccup�e() ? this.plateau[(i*8)+j].getPiece().toString() : " ";
				plateau += " " + caractere + " |";
			}
			plateau += " " + (i+1) + System.lineSeparator();
			plateau += "   --- --- --- --- --- --- --- ---" + System.lineSeparator();
		}
		
		plateau += "    a   b   c   d   e   f   g   h" + System.lineSeparator();
		return plateau;
	}

}
