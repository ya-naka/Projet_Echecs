package jeu;

import pieces.IPiece;
import utils.SaisieUtils;

public class Deplacement {
	private final int coordCaseActuelle;
	private final int coordNouvelleCase;
	private Plateau plateau;
	
	public Deplacement(Plateau plateau, String mouvement) {
		assert(SaisieUtils.estSaisieValide(mouvement));
		this.coordCaseActuelle = SaisieUtils.saisieToCoordonn�e(mouvement.substring(0, 2));
		this.coordNouvelleCase = SaisieUtils.saisieToCoordonn�e(mouvement.substring(2, 4));
		this.plateau = plateau;
	}
	
	public Deplacement(Plateau plateau, int positionActuelle, int nouvellePosition) {
		this.coordCaseActuelle = positionActuelle;
		this.coordNouvelleCase = nouvellePosition;
		this.plateau = plateau;
	}
	
	public boolean estDeplacementValide() {
		//v�rifie qu'une pi�ce se trouve sur la case de d�part
		if(!this.plateau.getCase(this.coordCaseActuelle).estOccup�e()) {
			return false;
		}
		
		//v�rifie que les r�gles des d�placement des pieces soient respect�es
		IPiece piece = this.plateau.getCase(this.coordCaseActuelle).getPiece();
		if(!piece.getDeplacementsPossibles(this.plateau).contains(this)) {
			return false;
		}
		//v�rifie que les r�gles du plateau soient respect�es
		Plateau plateauCopie = this.plateau;
		plateauCopie.deplacer(this);
		return !plateauCopie.estEchec(piece.getCouleur());
	}
	
	public void deplacer() {
		assert(estDeplacementValide());
	}
	
	public int getCoordActuelle() {
		return this.coordCaseActuelle;
	}
	
	public int getNouvelleCoord() {
		return this.coordNouvelleCase;
	}

}
