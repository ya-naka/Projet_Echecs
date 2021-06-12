package jeu;

import utils.SaisieUtils;

public class Deplacement {
	int coordCaseActuelle;
	int coordNouvelleCase;
	Plateau plateau;
	
	public Deplacement(Plateau plateau, String mouvement) {
		assert(SaisieUtils.estSaisieValide(mouvement));
		this.coordCaseActuelle = SaisieUtils.saisieToCoordonn�e(mouvement.substring(0, 2));
		this.coordNouvelleCase = SaisieUtils.saisieToCoordonn�e(mouvement.substring(2, 4));
		this.plateau = plateau;
	}
}
