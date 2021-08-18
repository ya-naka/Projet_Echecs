package jeu;

import couleur.ICouleur;
import pieces.IPiece;
import utils.SaisieUtils;

public class Deplacement {
	private final int coordCaseActuelle;
	private final int coordNouvelleCase;
	private Plateau plateau;
	
	/**
	 * Constructeur
	 * @param plateau
	 * @param mouvement au format String
	 */
	public Deplacement(Plateau plateau, String mouvement) {
		assert(SaisieUtils.estSaisieValide(mouvement));
		this.coordCaseActuelle = SaisieUtils.saisieToCoordonnée(mouvement.substring(0, 2));
		this.coordNouvelleCase = SaisieUtils.saisieToCoordonnée(mouvement.substring(2, 4));
		this.plateau = plateau;
	}
	
	/**
	 * Constructeur
	 * @param plateau
	 * @param positionActuelle
	 * @param nouvellePosition
	 */
	public Deplacement(Plateau plateau, int positionActuelle, int nouvellePosition) {
		assert(Plateau.estCaseValide(positionActuelle) && Plateau.estCaseValide(nouvellePosition));
		this.coordCaseActuelle = positionActuelle;
		this.coordNouvelleCase = nouvellePosition;
		this.plateau = plateau;
	}
	
	/**
	 * vérifie si le déplacement respecte les règles du jeu
	 * @param camp
	 * @return
	 */
	public boolean estDeplacementValide(ICouleur camp) {
		//vérifie qu'une pièce se trouve sur la case de départ
		if(!this.plateau.getCase(getCoordActuelle()).estOccupée()) {
			return false;
		}
		
		//récupère la pièce se trouvant sur la case de départ
		IPiece piece = this.plateau.getCase(getCoordActuelle()).getPiece();
		//vérifie que la pièce sélectionnée fait partie du camp du joueur
		if(!(piece.getCouleur().estMemeCouleur(camp))) {
			return false;
		}
		boolean depPossible = false;
		//vérifie que ce déplacement fait partie des déplacements possibles de la pièce sélectionnée
		for(Deplacement dep : piece.getDeplacementsPossibles(this.plateau)) {
			if(dep.equals(this)) {
				depPossible = true;
				break;
			}
		}
		if(!depPossible) {
			return false;
		}
		
		//vérifie que les règles du plateau soient respectées
		plateau.deplacer(this, piece.getCouleur());
		//vérifie si le déplacement engendre un échec dans le camp de la pièce déplacée
		boolean estEchec = plateau.estEchec(piece.getCouleur());
		plateau.revenirEnArriere();
		return !estEchec;
	}
	
	/**
	 * retourne la position de la case de départ
	 * @return
	 */
	public int getCoordActuelle() {
		return this.coordCaseActuelle;
	}
	
	/**
	 * retourne la position de la case d'arrivée
	 * @return
	 */
	public int getNouvelleCoord() {
		return this.coordNouvelleCase;
	}
	
	/**
	 * redéfinit la fonction equals en ne prenant en compte que les positions de départ et d'arrivée
	 */
	@Override
	public boolean equals(Object o) {
		if(this.getClass() != o.getClass()) {
			return false;
		}
		Deplacement dep = (Deplacement)o;
		return (dep.getCoordActuelle() == this.getCoordActuelle()) && (dep.getNouvelleCoord() == this.getNouvelleCoord());
	}

	/**
	 * @return affichage du déplacement
	 */
	public String toString() {
		return SaisieUtils.coordonnéeToSaisie(getCoordActuelle()) + "-" + SaisieUtils.coordonnéeToSaisie(getNouvelleCoord());
	}
}
