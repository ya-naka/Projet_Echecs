package jeu;

import pieces.IPiece;
import utils.SaisieUtils;

public class Deplacement {
	private final int coordCaseActuelle;
	private final int coordNouvelleCase;
	private Plateau plateau;
	
	public Deplacement(Plateau plateau, String mouvement) {
		assert(SaisieUtils.estSaisieValide(mouvement));
		this.coordCaseActuelle = SaisieUtils.saisieToCoordonnée(mouvement.substring(0, 2));
		this.coordNouvelleCase = SaisieUtils.saisieToCoordonnée(mouvement.substring(2, 4));
		this.plateau = plateau;
	}
	
	public Deplacement(Plateau plateau, int positionActuelle, int nouvellePosition) {
		this.coordCaseActuelle = positionActuelle;
		this.coordNouvelleCase = nouvellePosition;
		this.plateau = plateau;
	}
	
	public boolean estDeplacementValide() {
		//vérifie qu'une pièce se trouve sur la case de départ
		if(!this.plateau.getCase(this.coordCaseActuelle).estOccupée()) {
			return false;
		}
		
		//vérifie que les règles des déplacement des pieces soient respectées
		IPiece piece = this.plateau.getCase(this.coordCaseActuelle).getPiece();
		if(!piece.getDeplacementsPossibles(this.plateau).contains(this)) {
			return false;
		}
		//vérifie que les règles du plateau soient respectées
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
