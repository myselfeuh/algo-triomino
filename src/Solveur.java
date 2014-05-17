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
	private int nb_essais;
	private int nb_trio_places = 0;
	private int nb_trio_places_max = 0;
	
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
		nb_essais = 0;
		System.out.println("AVANT => Taille liste a ranger : " + liste_a_ranger.size() 
						+ " - Taille liste rangee : " + liste_rangee.size());
		
		if ( this.resoudre(0) ) {
			jeu_resolu = new Jeu(this.jeu_a_resoudre.getLargeur(), liste_rangee);	
		} 

		System.out.println("Nb d'essais : " + nb_essais);
		System.out.println("APRES => Taille liste a ranger : " + liste_a_ranger.size() 
				+ " - Taille liste rangee : " + liste_rangee.size());
		
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
		int next_pos = this.next();
		
		nb_essais++;
		
		
		if ( next_pos == -1 ) {
			sol_trouvee = true;
		} else {
			for (int k = 0; k < liste_a_ranger.size(); k++) {
				Triomino t = liste_a_ranger.get(k); 
				liste_a_ranger.remove(k);
				
				boolean contraintes_ok = false;
				for (int i = 0; i < 3 && !contraintes_ok; i++) {
				// on a 3 essais possible par triomino et on le tourne 2 fois maximum
				// si le triomino ne respecte pas les contraintes, on le tourne et on reessaye :
					
						if ( contraintes_ok = this.verifContraintes(t, pos) ) {
						// si les contraintes sont respectees on pose le triomino
							liste_rangee.add(pos, t);
							this.nb_trio_places++;
							System.out.println("Nb trio places : " + nb_trio_places);
							this.afficheSolutionInterm();
							
							if ( this.resoudre(next_pos) ) {
							// si l'appel suivant a la fonction resoudre renvoit true alors c'est gagné
								sol_trouvee = true;
							} else {
							// sinon on enleve ce triomino
								liste_rangee.remove(pos);
								this.nb_trio_places--;
							}
						} // else {
							t.rotation();
						// }
					}
				
				if (!sol_trouvee) {
				// si on a pas trouve on remet le triomino dans la liste des triominos a ranger
					liste_a_ranger.add(k, t);
				}	
			}
			// sol_trouvee = false;
		} 
		return sol_trouvee;
	}

	// Affiche une pyramide intermediaire si un triomino supplementaire est pose
	private void afficheSolutionInterm() {
		// if (nb_trio_places > nb_trio_places_max ) {
			nb_trio_places_max = nb_trio_places;
			Jeu jeu_interm = new Jeu( (int) Math.ceil(Math.sqrt(liste_rangee.size())), this.liste_rangee );
			Afficheur aff_interm = new Afficheur(jeu_interm);
			aff_interm.afficherPyramide();
		// }
	}

	// renvoit la position du triomino suivant a placer dans la liste_rangee
	private int next() {
		if (liste_rangee.size() < N*N ) {
			return liste_rangee.size()+1;
		} else {
			return -1;
		} 
	}

	private boolean verifContraintes(Triomino t, int pos) {
		boolean verif = false;
		int etage = (int) Math.sqrt(pos); 
		int index_ligne = pos - etage*etage;
		
		if (pos == 0 || pos %N == 1) {
		// si le triomino est le 1er ou en debut de ligne alors il n'y a rien a verifier
			verif = true;
		} else if (index_ligne %2 == 0) {
		// si le triomino a la tete en haut, alors il faut verifier sa gauche avec la gauche du triomino precedent
			verif = ( t.getLeft() == liste_rangee.get(pos -1).getLeft() );
		} else if (index_ligne %2 == 1) {
		// si le triomino a la tete en bas, alors il faut le comparer avec celui du dessus et celui de gauche
			// j est la position du triomino du dessus
			int j = pos - 2*( (int)Math.sqrt(pos) );
			int centerT = t.getCenter(); 
			int centerH = liste_rangee.get(j).getCenter();
			int rightT = t.getRight(); 
			int rightH = liste_rangee.get(pos -1).getRight();
			verif = (centerT == centerH) && (rightT == rightH);
			// verifier en haut :
			// verif = ( t.getCenter() == liste_rangee.get(j).getCenter() ) &&
			// et verifier a gauche :
			// 		( t.getRight() == liste_rangee.get(pos -1).getRight() );
		}
		
		return verif;
	}

}
