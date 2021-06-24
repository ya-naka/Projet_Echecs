package jeu;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
	private IJoueur joueurActif, j1, j2;
	
	public Plateau() {
		this.plateau = new Case[NB_CASES];
		initAleatoire();
	}
	
	//vérifie si le paramètre est une coordonnée valide du tableau de cases
	public static boolean estCaseValide(int position) {
		return position >= 0 && position < NB_CASES;
	}
	
	//enlève toutes les pièces du plateau
	private void viderPlateau() {
		for(int i = 0; i < NB_CASES; i++) {
			this.plateau[i] = new Case(i, null);
		}
	}
	
	//créer une fabrique pour gérer la création des pièces ?
	//place aléatoirement les pièces sur le plateau au début de la partie
	public void initAleatoire() {
		
		ICouleur noir = new Noir();
		ICouleur blanc = new Blanc();
		
		//ajouter les pièces aléatoirement
		Random r = new Random();
		
		do {
			List<IPiece> pieces = new ArrayList<>();
			//ajout roi noir
			int randNum = r.nextInt(NB_CASES);
			pieces.add(new Roi(randNum, noir));
			initFixe(pieces);
			//ajout roi blanc
			do {
				randNum = r.nextInt(NB_CASES);
			}while(getCase(randNum).estOccupée());
			pieces.add(new Roi(randNum, blanc));
			initFixe(pieces);
			//ajout d'une tour, noire ou blanche
			//choix de la case
			do {
				randNum = r.nextInt(NB_CASES);
			}while(getCase(randNum).estOccupée());
			//choix du camp
			if(r.nextInt(2) == 0) {
				pieces.add(new Tour(randNum, blanc));
			}else {
				pieces.add(new Tour(randNum, noir));
			}
			initFixe(pieces);
		}while(estPat(blanc) || estEchec(noir));
		//le camp qui commence la partie doit pouvoir jouer au moins 1 coup
		//le camp adverse ne doit pas être en échec dès le 1er coup
		
	}
	
	//place les pièces selon un ordre fixé
	public void initFixe(List<IPiece> pieces) {
		this.historique = new ArrayList<>();
		this.rois = new ArrayList<>();
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
	
	//retourne le roi de la couleur demandée
	public IPiece getRoi(ICouleur couleur) {
		for(IPiece roi : this.rois) {
			if(roi.getCouleur().estMemeCouleur(couleur)) {
				return roi;
			}
		}
		return null;
	}
	
	//ajoute le déplacement dans l'historique et déplace la pièce selon le déplacement envoyé
	public void deplacer(Deplacement deplacement, ICouleur camp) {
		//vérifie qu'il y a une pièce sur la case sélectionnée
		assert(getCase(deplacement.getCoordActuelle()).estOccupée());
		//vérifie que la pièce sélectionnée peut être déplacée
		assert(getCase(deplacement.getCoordActuelle()).getPiece().peutDeplacer(this, deplacement.getNouvelleCoord()));
		assert(getCase(deplacement.getCoordActuelle()).getPiece().getCouleur().estMemeCouleur(camp));
		//ajoute le déplacement dans l'historique de la partie
		this.historique.add(new Historique(this, deplacement));
		//si un roi se trouve sur la case d'arrivée, on le retire de la liste des rois de la partie
		IPiece piecePrise = this.historique.get(this.historique.size()-1).getPiecePrise();
		if(piecePrise != null) {
			if(piecePrise.getClass() == Roi.class) {
				this.rois.remove(getRoi(piecePrise.getCouleur()));
			}
		}
		//récupère la pièce à déplacer
		IPiece pieceDeplacee = getCase(deplacement.getCoordActuelle()).getPiece();
		//change les coordonnées de la pièce à déplacer
		pieceDeplacee.deplacer(this, deplacement.getNouvelleCoord());
		//met la pièce à déplacer sur sa nouvelle case
		getCase(deplacement.getNouvelleCoord()).setPiece(pieceDeplacee);
		//vide la case de départ
		getCase(deplacement.getCoordActuelle()).setPiece(null);
	}

	//retorune vrai si le camp envoyée en paramètre est en échec
	public boolean estEchec(ICouleur couleur) {
		//- récupérer le roi qui correspond à la couleur passée en paramètre
		IPiece roi = getRoi(couleur);
		if(roi != null) {
			//- parcourir le plateau
			for(int i = 0; i < NB_CASES; i++) {
				if(getCase(i).estOccupée()) {
					//- pour chaque pièce du camp adverse, vérifier si un de ses déplacements possibles
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

	public boolean estPat(ICouleur couleur) {
		//vérifie si le roi est présent sur le plateau
		if(getRoi(couleur) == null) {
			return true;
		}
		//pour chaque déplacement des pièces alliées, si au moins un est valide 
		//alors retourne faux
		for(int i = 0; i < NB_CASES; i++) {
			if(getCase(i).estOccupée()) {
				IPiece piece = getCase(i).getPiece();
				if(piece.getCouleur().estMemeCouleur(couleur)) {
					for(Deplacement dep : piece.getDeplacementsPossibles(this)) {
						if(dep.estDeplacementValide(couleur)) {
							return false;
						}
					}
				}
				
			}
		}
		return true;
	}
	
	//revient à l'état de la partie avant le dernier coup
	public void revenirEnArriere() {
		if(!this.historique.isEmpty()) {
			Historique derniereEntree = this.historique.remove(this.historique.size()-1);
			//récupère la pièce qui a été jouée
			IPiece pieceDeplacee = getCase(derniereEntree.getDeplacement().getNouvelleCoord()).getPiece();
			//change la position de la pièce qui a été jouée
			pieceDeplacee.deplacer(this, derniereEntree.getDeplacement().getCoordActuelle());
			//remet la pièce qui a été prise sur le plateau, null si aucun pièce n'avait été prise
			getCase(derniereEntree.getDeplacement().getNouvelleCoord()).setPiece(derniereEntree.getPiecePrise());
			//si la pièce qui avait été prise est un roi, on l'ajoute à la liste des rois de la partie
			if(derniereEntree.getPiecePrise() != null) {
				if(derniereEntree.getPiecePrise().getClass() == Roi.class) {
					this.rois.add(derniereEntree.getPiecePrise());
				}
			}
			//place la pièce jouée sur sa position précédente sur le plateau
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
