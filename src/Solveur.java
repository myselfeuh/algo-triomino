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
		
		int next_pos = this.next();
		
		if (next_pos == -1) {
			return true;
		} else {
			for (int k = 0; k < liste_a_ranger.size(); k++) {
				Triomino t = liste_a_ranger.get(k); 
				liste_a_ranger.remove(k);
				
				for (int i = 0; i < 3; i++) {
					
					if ( this.verifContraintes(t, pos) ) {
						liste_rangee.add(pos, t);
						
						if ( this.resoudre(next_pos) ) {
							return true;
						}
						liste_rangee.remove(pos);
					}
					t.rotation();
				}
				liste_a_ranger.add(k, t);
			}
			return false;
		}
	
	}

	// renvoit la position du triomino suivant a placer dans la liste_rangee
	private int next() {
		if (liste_rangee.size() < N*N ) {
			return liste_rangee.size()+1;
		} else {
			return -1;
		} 
	}

	// renvoit true si les contrainte de la position pos sont respectees par le triomino t
	private boolean verifContraintes(Triomino t, int pos) {
		boolean verif = false;
		int etage = (int) Math.sqrt(pos); 
		int index_ligne = pos - etage*etage;
		
		if (index_ligne == 0) {
		// si le triomino est le 1er ou en debut de ligne alors il n'y a rien a verifier
			verif = true;
		} else if (index_ligne %2 == 0) {
		// si le triomino a la tete en haut, alors il faut verifier sa gauche avec la gauche du triomino precedent
			verif = ( t.getLeft() == liste_rangee.get(pos -1).getLeft() );
		} else if (index_ligne %2 == 1) {
		// si le triomino a la tete en bas, alors il faut le comparer avec celui du dessus et celui de gauche
			// j est la position du triomino du dessus
			int j = pos - 2*( (int)Math.sqrt(pos) );
			// verifier en haut et a gauche :
			verif = ( t.getCenter() == liste_rangee.get(j).getCenter() ) &&
			 		( t.getRight() == liste_rangee.get(pos -1).getRight() );
		}
		
		return verif;
	}

}
