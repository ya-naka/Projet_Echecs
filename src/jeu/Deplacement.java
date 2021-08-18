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
		this.coordCaseActuelle = SaisieUtils.saisieToCoordonn�e(mouvement.substring(0, 2));
		this.coordNouvelleCase = SaisieUtils.saisieToCoordonn�e(mouvement.substring(2, 4));
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
	 * v�rifie si le d�placement respecte les r�gles du jeu
	 * @param camp
	 * @return
	 */
	public boolean estDeplacementValide(ICouleur camp) {
		//v�rifie qu'une pi�ce se trouve sur la case de d�part
		if(!this.plateau.getCase(getCoordActuelle()).estOccup�e()) {
			return false;
		}
		
		//r�cup�re la pi�ce se trouvant sur la case de d�part
		IPiece piece = this.plateau.getCase(getCoordActuelle()).getPiece();
		//v�rifie que la pi�ce s�lectionn�e fait partie du camp du joueur
		if(!(piece.getCouleur().estMemeCouleur(camp))) {
			return false;
		}
		boolean depPossible = false;
		//v�rifie que ce d�placement fait partie des d�placements possibles de la pi�ce s�lectionn�e
		for(Deplacement dep : piece.getDeplacementsPossibles(this.plateau)) {
			if(dep.equals(this)) {
				depPossible = true;
				break;
			}
		}
		if(!depPossible) {
			return false;
		}
		
		//v�rifie que les r�gles du plateau soient respect�es
		plateau.deplacer(this, piece.getCouleur());
		//v�rifie si le d�placement engendre un �chec dans le camp de la pi�ce d�plac�e
		boolean estEchec = plateau.estEchec(piece.getCouleur());
		plateau.revenirEnArriere();
		return !estEchec;
	}
	
	/**
	 * retourne la position de la case de d�part
	 * @return
	 */
	public int getCoordActuelle() {
		return this.coordCaseActuelle;
	}
	
	/**
	 * retourne la position de la case d'arriv�e
	 * @return
	 */
	public int getNouvelleCoord() {
		return this.coordNouvelleCase;
	}
	
	/**
	 * red�finit la fonction equals en ne prenant en compte que les positions de d�part et d'arriv�e
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
	 * @return affichage du d�placement
	 */
	public String toString() {
		return SaisieUtils.coordonn�eToSaisie(getCoordActuelle()) + "-" + SaisieUtils.coordonn�eToSaisie(getNouvelleCoord());
	}
}
