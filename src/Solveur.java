import java.util.ArrayList;

/**
 * Classe Solveur qui s'occupe de ranger dans l'ordre le jeu qu'on lui fournit
 *
 */

public class Solveur {

	private Jeu jeu_a_resoudre;
	private ArrayList<Triomino> liste_a_ranger;
	private Jeu jeu_resolu;
	private ArrayList<Triomino> liste_rangee;
	private int N;
	
	// initialisation des variables dans le constructeur
	public Solveur(Jeu jeu_p) {
		this.jeu_a_resoudre = jeu_p;
		this.liste_a_ranger = this.jeu_a_resoudre.getListeTriominos();
		this.N = (int) Math.sqrt(liste_a_ranger.size());
		this.jeu_resolu  = null;
		this.liste_rangee = new ArrayList<Triomino>(liste_a_ranger.size());
	}

	// methode renvoyant un Jeu trie si une solution a ete trouvee et null sinon
	public Jeu getSolution() {
		
		if ( this.resoudre(0) ) {
			jeu_resolu = new Jeu(this.jeu_a_resoudre.getLargeur(), liste_rangee);	
		} 
		
		return this.jeu_resolu;
	}

	// resolution du jeu, c-a-d qu'on range les triominos dans l'ordre,
	// pour ensuite pouvoir les afficher en pyramide grace a l'afficheur.
	// l'objectif de cette methode est de creer une arraylist rangee dans l'ordre d'affichage de la pyramide
	private boolean resoudre(int pos) {
			
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
		boolean sol_trouvee = false;
		boolean res = true;
		int next_pos = this.next();
		
		if ( next_pos == liste_a_ranger.size() ) {
			sol_trouvee = true;
		} else {
			for (int k = 0; k < liste_a_ranger.size(); k++) {
				Triomino t = liste_a_ranger.get(k); 
				liste_a_ranger.remove(t);
				for (int i = 0; i < 3; i++) {
					if ( this.verifContraintes(t, pos) ) {
					// si les contraintes sont respectees on pose le triomino
						// liste_rangee.set(pos, t);
						liste_rangee.add(pos, t);
						
						if ( res = this.resoudre(next_pos) ) {
							sol_trouvee = true;
						} else {
							liste_rangee.remove(pos);
						}
					} else {
						t.rotation();
					}
				}
				if (!res) {
					liste_a_ranger.add(k,t);
					liste_a_ranger.trimToSize();
				}	
			}
			// sol_trouvee = false;
		} 

		System.out.println(sol_trouvee);
		return sol_trouvee;
	}
	
	private boolean verifContraintes(Triomino t, int pos) {
		boolean verif = false;
		
		if (pos == 0 || pos%N == 1) {
			verif = true;
		} else if (pos%2 == 0) {
			// verifier a gauche
			verif = ( t.getLeft() == liste_rangee.get(pos -1).getRight() );
		} else if (pos%2 == 1) {
			// verifier en haut
			int j = pos - 2*( (int)Math.sqrt(pos) );
			verif = ( t.getCenter() == liste_rangee.get(j).getCenter() ) &&
			// verifier a gauche 
					( t.getLeft() == liste_rangee.get(pos -1).getRight() );
		}
		
		return verif;
	}

	private int next() {
		return liste_rangee.size();
	}
}
