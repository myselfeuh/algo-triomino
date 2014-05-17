/**
 * Classe Solveur qui s'occupe de ranger dans l'ordre le jeu qu'on lui fournit
 *
 */

public class Solveur {

	private Jeu jeu;
	
	public Solveur(Jeu jeu_p) {
		this.jeu = jeu_p;
	}

	
	// resolution du jeu, c-a-d qu'on range les triominos dans l'ordre,
	// pour ensuite pouvoir les afficher en pyramide grace a l'afficheur.
	public Jeu resoudre() {
		boolean sol_trouvee = false;
		/**
		* Données : ensemble de triominos à Résultat : soit true, auquel cas la sonext_pos = next(pos, n);
		* next_pos = next(pos, n)
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

		if (!sol_trouvee){
			return null;
		} else {
			return this.jeu;
		}
	}

}
