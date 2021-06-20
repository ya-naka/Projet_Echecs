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
		assert(Plateau.estCaseValide(positionActuelle) && Plateau.estCaseValide(nouvellePosition));
		this.coordCaseActuelle = positionActuelle;
		this.coordNouvelleCase = nouvellePosition;
		this.plateau = plateau;
	}
	
	//vérifie si le déplacement respecte les règles du jeu
	public boolean estDeplacementValide() {
		//vérifie qu'une pièce se trouve sur la case de départ
		if(!this.plateau.getCase(this.coordCaseActuelle).estOccupée()) {
			return false;
		}
		
		//vérifie que les règles des déplacement des pieces soient respectées
		IPiece piece = this.plateau.getCase(this.coordCaseActuelle).getPiece();
		boolean depPossible = false;
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
		Plateau plateauCopie = this.plateau;
		//effectue le déplacement
		plateauCopie.deplacer(this);
		//vérifie si le déplacement engendre un échec dans le camp de la pièce déplacée
		boolean estEchec = plateauCopie.estEchec(piece.getCouleur());
		//revient à l'état précédent de la partie
		plateauCopie.revenirEnArriere();
		return !estEchec;
	}
	
	public int getCoordActuelle() {
		return this.coordCaseActuelle;
	}
	
	public int getNouvelleCoord() {
		return this.coordNouvelleCase;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this.getClass() != o.getClass()) {
			return false;
		}
		Deplacement dep = (Deplacement)o;
		return (dep.getCoordActuelle() == this.getCoordActuelle()) && (dep.getNouvelleCoord() == this.getNouvelleCoord());
	}

}
