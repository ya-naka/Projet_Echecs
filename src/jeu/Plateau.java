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
	 * v�rifie si le param�tre est une coordonn�e valide du tableau de cases
	 * @param position de la case
	 * @return
	 */
	public static boolean estCaseValide(int position) {
		return position >= 0 && position < NB_CASES;
	}
	
	/**
	 * enl�ve toutes les pi�ces du plateau
	 */
	private void viderPlateau() {
		for(int i = 0; i < NB_CASES; i++) {
			this.plateau[i] = new Case(i, null);
		}
	}

	/**
	 * retourne la case correspondante du plateau
	 * @param index : position sur l'�chiquier
	 * @return
	 */
	public Case getCase(int index) {
		assert(estCaseValide(index));
		return this.plateau[index];
	}
	
	/**
	 * retourne le roi de la couleur demand�e
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
	 * renvoie toutes les pi�ces d'un camp pr�sentes sur le plateau
	 * @param couleur
	 * @return
	 */
	public List<IPiece> getPieces(ICouleur couleur){
		List<IPiece> pieces = new ArrayList<>();
		for(int i = 0; i < NB_CASES; i++) {
			if(getCase(i).estOccup�e()) {
				if(getCase(i).getPiece().getCouleur().estMemeCouleur(couleur)) {
					pieces.add(getCase(i).getPiece());
				}
			}
		}
		return pieces;
	}
	
	/**
	 * renvoie tous les d�placements possibles d'un camp
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
	 * retorune vrai si le camp envoy�e en param�tre est en �chec
	 * @param couleur
	 * @return
	 */
	public boolean estEchec(ICouleur couleur) {
		//- r�cup�rer le roi qui correspond � la couleur pass�e en param�tre
		IPiece roi = getRoi(couleur);
		if(roi != null) {
			//r�cup�re la couleur du camp adverse
			ICouleur campAdverse = couleur.estBlanc() ? new Noir() : new Blanc();
			//v�rifie, pour chaque pi�ce du camp adverse, si un de ses d�placements possibles coincide avec la case du roi
			for(Deplacement dep : getDeplacementsPossibles(campAdverse)) {
				if(roi.getPosition() == dep.getNouvelleCoord()) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * v�rifie si le camp est en �chec et n'est pas en mesure de sauver son roi
	 * @param couleur
	 * @return
	 */
	public boolean estEchecEtMat(ICouleur couleur) {
		if(!estEchec(couleur)) {
			return false;
		}
		//v�rifie, pour chaque d�placement possible du roi, si ce dernier est en �chec
		//retourne faux s'il n'est pas en �chec pour au moins un d�placement possible
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
	 * v�rifie si le camp peut effectuer au moins un coup l�gal lors de son tour
	 * @param couleur
	 * @return
	 */
	public boolean estPat(ICouleur couleur) {
		//pour chaque d�placement des pi�ces alli�es, retourne faux si au moins un d�placement est valide
		for(Deplacement dep : getDeplacementsPossibles(couleur)) {
			if(dep.estDeplacementValide(couleur)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * modalit�s de fin de partie
	 * @param camp
	 * @return
	 */
	public boolean estPartieFinie(ICouleur camp) {
		return (estEchecEtMat(new Blanc())
				|| estEchecEtMat(new Noir())
				|| estPat(camp));
	}

	/**
	 * place les pi�ces selon un ordre fix�
	 * @param pieces � placer sur le plateau
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
	 * place al�atoirement les pi�ces sur le plateau au d�but de la partie
	 */
	public void initAleatoire() {
		do {
			List<IPiece> pieces = PlateauFactory.initAleatoire(3);
			initFixe(pieces);
		}while(estPat(new Blanc()) || estEchec(new Noir())); 
		// on r�initialise le plateau tant que les noirs sont en �chec ou que les blancs sont en incapacit� de jouer
	}
	
	/**
	 * ajoute le d�placement dans l'historique et d�place la pi�ce selon le d�placement envoy�
	 * @param deplacement
	 * @param camp
	 */
	public void deplacer(Deplacement deplacement, ICouleur camp) {
		//v�rifie qu'il y a une pi�ce sur la case s�lectionn�e
		assert(getCase(deplacement.getCoordActuelle()).estOccup�e());
		//v�rifie que la pi�ce s�lectionn�e peut �tre d�plac�e
		assert(getCase(deplacement.getCoordActuelle()).getPiece().peutDeplacer(this, deplacement.getNouvelleCoord()));
		assert(getCase(deplacement.getCoordActuelle()).getPiece().getCouleur().estMemeCouleur(camp));
		//ajoute le d�placement dans l'historique de la partie
		this.historique.add(new Historique(this, deplacement));
		//si un roi se trouve sur la case d'arriv�e, on le retire de la liste des rois de la partie
		IPiece piecePrise = this.historique.get(this.historique.size()-1).getPiecePrise();
		if(piecePrise != null) {
			if(piecePrise.getClass() == Roi.class) {
				this.rois.remove(getRoi(piecePrise.getCouleur()));
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
	
	/**
	 * revient � l'�tat de la partie pr�c�dant le dernier coup
	 */
	public void revenirEnArriere() {
		if(!this.historique.isEmpty()) {
			Historique derniereEntree = this.historique.remove(this.historique.size()-1);
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
