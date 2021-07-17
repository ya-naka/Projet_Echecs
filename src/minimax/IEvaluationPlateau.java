package minimax;

import jeu.Plateau;

public interface IEvaluationPlateau {

	int evaluer(Plateau plateau, int profondeur);
}
