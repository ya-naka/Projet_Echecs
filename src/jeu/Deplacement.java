package jeu;

import utils.SaisieUtils;

public class Deplacement {
	int coordCaseActuelle;
	int coordNouvelleCase;
	Plateau plateau;
	
	public Deplacement(Plateau plateau, String mouvement) {
		assert(SaisieUtils.estSaisieValide(mouvement));
		this.coordCaseActuelle = SaisieUtils.saisieToCoordonnée(mouvement.substring(0, 2));
		this.coordNouvelleCase = SaisieUtils.saisieToCoordonnée(mouvement.substring(2, 4));
		this.plateau = plateau;
	}
}
