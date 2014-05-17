import java.util.ArrayList;

/**
 * Classe Solveur qui s'occupe de ranger dans l'ordre le jeu qu'on lui fournit
 *
 */

public class Solveur {

	private Jeu jeu_a_resoudre;
	private Jeu jeu_resolu = null;
	private ArrayList<Triomino> listeRangee = new ArrayList<Triomino>(1);
	
	public Solveur(Jeu jeu_p) {
		this.jeu_a_resoudre = jeu_p;
	}

	public Jeu getSolution() {
		
		if ( this.resoudre() ) {
			jeu_resolu = new Jeu(this.jeu_a_resoudre.getLargeur(), listeRangee);	
		} 
		
		return this.jeu_resolu;
	}

	// resolution du jeu, c-a-d qu'on range les triominos dans l'ordre,
	// pour ensuite pouvoir les afficher en pyramide grace a l'afficheur.
	private boolean resoudre() {
	// l'objectif de cette methode est de creer une arraylist rangee dans l'ordre d'affichage de la pyramide
		
		boolean sol_trouvee = false;
		
		/**
		* Données : ensemble de triominos à poser T, plateau P, position pos, largeur du plateau n
		* Résultat : soit true, auquel cas la solution est P, sinon false
		* next_pos = next(pos, n);
		* 
		* si next_pos = fin alors
		*   retourner true;
		* sinon
		*   pour chaque t appartenant a T faire
		*     enlever(T , t);
		*     pour r appartenant a {0, 1, 2} faire
		*       si contraintes(t, pos, P) alors
		*	      placer(P, t, pos);
		*         si resoudre(T , P, next) alors
		*	    retourner true;
		*	  fsi
		*         enlever(P, t, pos);
		*       fsi
		*       rotation(t, r);
		*     fpour
		*     ajouter(T , t);
		*   fpour
		*   retourner false;
		* fsi
		*/
		
		return sol_trouvee;
	}
	
}
