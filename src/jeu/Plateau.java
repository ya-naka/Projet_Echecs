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
	private static final int NB_CASES = 64;
	private Case[] plateau;
	private List<Historique> historique;
	private List<IPiece> rois;
	
	public Plateau() {
		this.historique = new ArrayList<>();
		this.rois = new ArrayList<>();
		//A CHANGER POUR QUE CE SOIT ALEATOIRE
		this.plateau = new Case[NB_CASES];
		initFixe(new ArrayList<>());
		//initAleatoire();
	}
	
	//v�rifie si le param�tre est une coordonn�e valide du tableau de cases
	public static boolean estCaseValide(int position) {
		return position >= 0 && position < NB_CASES;
	}
	
	//enl�ve toutes les pi�ces du plateau
	private void viderPlateau() {
		for(int i = 0; i < NB_CASES; i++) {
			this.plateau[i] = new Case(i, null);
		}
	}
	
	//place al�atoirement les pi�ces sur le plateau au d�but de la partie
	public void initAleatoire() {
		viderPlateau();
		//ajouter les pi�ces al�atoirement
	}
	
	//place les pi�ces selon un ordre fix�
	public void initFixe(List<IPiece> pieces) {
		viderPlateau();
		for(IPiece piece : pieces) {
			getCase(piece.getPosition()).setPiece(piece);
			if(piece.getClass() == Roi.class) {
				this.rois.add(piece);
			}
		}
	}
	
	public Case getCase(int index) {
		return this.plateau[index];
	}
	
	//retourne le roi de la couleur demand�e
	public IPiece getRoi(ICouleur couleur) {
		for(IPiece roi : this.rois) {
			if(roi.getCouleur().estMemeCouleur(couleur)) {
				return roi;
			}
		}
		return null;
	}
	
	//ajoute le d�placement dans l'historique et d�place la pi�ce selon le d�placement envoy�
	public void deplacer(Deplacement deplacement) {
		//v�rifie que la pi�ce s�lectionn�e peut �tre d�plac�e
		assert(getCase(deplacement.getCoordActuelle()).getPiece().peutDeplacer(this, deplacement.getNouvelleCoord()));
		//ajoute le d�placement dans l'historique de la partie
		this.historique.add(new Historique(this, deplacement));
		//si un roi se trouve sur la case d'arriv�e, on le retire de la liste des rois de la partie
		if(getCase(deplacement.getNouvelleCoord()).estOccup�e()) {
			if(getCase(deplacement.getNouvelleCoord()).getPiece().getClass() == Roi.class) {
				this.rois.remove(getCase(deplacement.getNouvelleCoord()).getPiece());
			}
		}
		//r�cup�re la pi�ce � d�placer
		IPiece pieceDeplacee = getCase(deplacement.getCoordActuelle()).getPiece();
		//change les coordonn�es de la pi�ce � d�placer
		pieceDeplacee.deplacer(this, deplacement.getNouvelleCoord());
		//met la pi�ce � d�placer sur sa nouvelle case
		getCase(deplacement.getNouvelleCoord()).setPiece(pieceDeplacee);
		//vide la case de d�part
		getCase(deplacement.getCoordActuelle()).setPiece(null);
	}

	//retorune vrai si le camp envoy�e en param�tre est en �chec
	public boolean estEchec(ICouleur couleur) {
		//- r�cup�rer le roi qui correspond � la couleur pass�e en param�tre
		IPiece roi = getRoi(couleur);
		if(roi != null) {
			//- parcourir le plateau
			for(int i = 0; i < NB_CASES; i++) {
				if(getCase(i).estOccup�e()) {
					//- pour chaque pi�ce du camp adverse, v�rifier si un de ses d�placements possibles
					//coincide avec la case du roi
					if(!getCase(i).getPiece().getCouleur().estMemeCouleur(roi.getCouleur())) {
						for(Deplacement dep : getCase(i).getPiece().getDeplacementsPossibles(this)) {
							if(roi.getPosition() == dep.getNouvelleCoord()) {
								return true;
							}
						}
					}
				}
			}
		}
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
			//si la pi�ce qui avait �t� prise est un roi, on l'ajoute � la liste des rois de la partie
			if(derniereEntree.getPiecePrise() != null) {
				if(derniereEntree.getPiecePrise().getClass() == Roi.class) {
					this.rois.add(derniereEntree.getPiecePrise());
				}
			}
			//place la pi�ce jou�e sur sa position pr�c�dente sur le plateau
			getCase(derniereEntree.getDeplacement().getCoordActuelle()).setPiece(pieceDeplacee);
		}
	}
	
	//affichage du plateau
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
