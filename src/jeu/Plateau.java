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
	
	/**
	 * Constructeur
	 */
	public Plateau() {
		this.plateau = new Case[NB_CASES];
		initAleatoire();
	}
	
	/**
	 * vérifie si le paramètre est une coordonnée valide du tableau de cases
	 * @param position de la case
	 * @return
	 */
	public static boolean estCaseValide(int position) {
		return position >= 0 && position < NB_CASES;
	}
	
	/**
	 * enlève toutes les pièces du plateau
	 */
	private void viderPlateau() {
		for(int i = 0; i < NB_CASES; i++) {
			this.plateau[i] = new Case(i, null);
		}
	}

	/**
	 * retourne la case correspondante du plateau
	 * @param index : position sur l'échiquier
	 * @return
	 */
	public Case getCase(int index) {
		assert(estCaseValide(index));
		return this.plateau[index];
	}
	
	/**
	 * retourne le roi de la couleur demandée
	 * @param couleur
	 * @return
	 */
	public IPiece getRoi(ICouleur couleur) {
		for(IPiece roi : this.rois) {
			if(roi.getCouleur().estMemeCouleur(couleur)) {
				return roi;
			}
		}
		return null;
	}
	
	/**
	 * renvoie toutes les pièces d'un camp présentes sur le plateau
	 * @param couleur
	 * @return
	 */
	public List<IPiece> getPieces(ICouleur couleur){
		List<IPiece> pieces = new ArrayList<>();
		for(int i = 0; i < NB_CASES; i++) {
			if(getCase(i).estOccupée()) {
				if(getCase(i).getPiece().getCouleur().estMemeCouleur(couleur)) {
					pieces.add(getCase(i).getPiece());
				}
			}
		}
		return pieces;
	}
	
	/**
	 * renvoie tous les déplacements possibles d'un camp
	 * @param couleur
	 * @return
	 */
	public List<Deplacement> getDeplacementsPossibles(ICouleur couleur){
		List<Deplacement> deplacements = new ArrayList<>();
		for(IPiece piece : getPieces(couleur)) {
			for(Deplacement dep : piece.getDeplacementsPossibles(this)) {
				deplacements.add(dep);
			}
		}
		return deplacements;
	}

	/**
	 * retorune vrai si le camp envoyée en paramètre est en échec
	 * @param couleur
	 * @return
	 */
	public boolean estEchec(ICouleur couleur) {
		//- récupérer le roi qui correspond à la couleur passée en paramètre
		IPiece roi = getRoi(couleur);
		if(roi != null) {
			//récupère la couleur du camp adverse
			ICouleur campAdverse = couleur.estBlanc() ? new Noir() : new Blanc();
			//vérifie, pour chaque pièce du camp adverse, si un de ses déplacements possibles coincide avec la case du roi
			for(Deplacement dep : getDeplacementsPossibles(campAdverse)) {
				if(roi.getPosition() == dep.getNouvelleCoord()) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * vérifie si le camp est en échec et n'est pas en mesure de sauver son roi
	 * @param couleur
	 * @return
	 */
	public boolean estEchecEtMat(ICouleur couleur) {
		if(!estEchec(couleur)) {
			return false;
		}
		//vérifie, pour chaque déplacement possible du roi, si ce dernier est en échec
		//retourne faux s'il n'est pas en échec pour au moins un déplacement possible
		for(Deplacement dep : getRoi(couleur).getDeplacementsPossibles(this)) {
			deplacer(dep, couleur);
			if(!estEchec(couleur)) {
				revenirEnArriere();
				return false;
			}
			revenirEnArriere();
		}
		return true;
	}

	/**
	 * vérifie si le camp peut effectuer au moins un coup légal lors de son tour
	 * @param couleur
	 * @return
	 */
	public boolean estPat(ICouleur couleur) {
		//pour chaque déplacement des pièces alliées, retourne faux si au moins un déplacement est valide
		for(Deplacement dep : getDeplacementsPossibles(couleur)) {
			if(dep.estDeplacementValide(couleur)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * modalités de fin de partie
	 * @param camp
	 * @return
	 */
	public boolean estPartieFinie(ICouleur camp) {
		return (estEchecEtMat(new Blanc())
				|| estEchecEtMat(new Noir())
				|| estPat(camp));
	}

	/**
	 * place les pièces selon un ordre fixé
	 * @param pieces à placer sur le plateau
	 */
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
	
	/**
	 * place aléatoirement les pièces sur le plateau au début de la partie
	 */
	public void initAleatoire() {
		do {
			List<IPiece> pieces = PlateauFactory.initAleatoire(3);
			initFixe(pieces);
		}while(estPat(new Blanc()) || estEchec(new Noir())); 
		// on réinitialise le plateau tant que les noirs sont en échec ou que les blancs sont en incapacité de jouer
	}
	
	/**
	 * ajoute le déplacement dans l'historique et déplace la pièce selon le déplacement envoyé
	 * @param deplacement
	 * @param camp
	 */
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
	
	/**
	 * revient à l'état de la partie précédant le dernier coup
	 */
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
	
	/**
	 * @return affichage du plateau
	 */
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
