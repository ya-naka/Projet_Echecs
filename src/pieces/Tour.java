package pieces;

import java.util.ArrayList;
import java.util.List;

import couleur.ICouleur;
import jeu.Deplacement;
import jeu.Plateau;

public class Tour extends Piece{
	private static final int[] coordDeplacements = {-8, -1, 1, 8};

	public Tour(int position, ICouleur couleur) {
		super(position, couleur);
	}
	
	public String toString() {
		if(super.getCouleur().estBlanc()) {
			return "t".toUpperCase();
		}
		return "t";
	}

	@Override
	public List<Deplacement> getDeplacementsPossibles(Plateau plateau) {
		List<Deplacement> depPossibles = new ArrayList<>();
		
		for(int i = 0; i < coordDeplacements.length; i++) {
			int position = super.getPosition();
			//on inspecte chaque case se trouvant sur la trajectoire
			while(position >= 0 && position < Plateau.NB_CASES) {
				position += coordDeplacements[i];
				if(estSurTrajectoire(coordDeplacements[i])) {
					if(!plateau.getCase(position).estOccupée()) {
						depPossibles.add(new Deplacement(plateau, super.getPosition(), position));
					}else if(!plateau.getCase(position).getPiece().getCouleur().estMemeCouleur(super.getCouleur())) {
						depPossibles.add(new Deplacement(plateau, super.getPosition(), position));
						break;
					}
				}
			}
		}
		return depPossibles;
	}
	
	public boolean estSurTrajectoire(int coordDeplacement) {
		//si première colonne
		if(super.getPosition()%8 == 0) {
			return !(coordDeplacement == -1);
		}
		//si dernière colonne
		if(super.getPosition()%8 == 7) {
			return !(coordDeplacement == 1);
		}
		return true;
	}

}
