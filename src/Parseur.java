import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


/**
 * Classe Parseur qui s'occupe de lire le fichier donne en entree du programme 
 * et de le transformer en differents problemes a resoudre
 */
public class Parseur {
	
	private static ArrayList<Jeu> jeux_a_resoudre = new ArrayList<Jeu>(1);
	private static ArrayList<Jeu> jeux_resolus = new ArrayList<Jeu>(1);
	
	public static void main(String[] args) {
				
		lireLeFichierSource();
		
		for (int i = 0; i < jeux_a_resoudre.size(); i++) {
		// pour chaque probleme cree on lance la procedure de resolution :
			
			jeux_a_resoudre.get(i).affiche();
			Afficheur aff = new Afficheur(jeux_a_resoudre.get(i));
			aff.afficherSolution();
			
			// for (int j = 0; j < jeux_resolus.size(); j++) {
			// pour chaque probleme resolu on lance la procedure d'affichage du resultat :
				// Creation d'un afficheur associé au jeu resolu
				// Afficheur aff = new Afficheur(jeux_resolus.get(j));
			// }
		}
		
	}

	private static ArrayList<Jeu> lireLeFichierSource() {
		
		BufferedReader bufReader = null;
		BufferedWriter bufWriter = null;
		
		try {
			bufReader = new BufferedReader(new InputStreamReader(System.in));
		    
			String[] middles, middle, bottoms;

			middle = new String[2];
			middles = new String[10];
			bottoms = new String[10];
			
			// initialisation bidon pour démarrer la boucle
			String input = "initialisation";
			int k = 1;
			
			while(input != null) {
				
				ArrayList<Triomino> listeTriominos = new ArrayList<Triomino>(1);
				
				System.out.println("Lecture de la serie " + k + " :");
				int i = 1;
				input = bufReader.readLine();
				
				while(input != null && !input.isEmpty()) {
					input = input.trim();
									
					if (i % 3 == 2) {
						// split the middle
						middles = input.split("  ");	
					} else if (i % 3 == 0) {
						// clean the bottom
						input = input.replaceAll("/_", "");
						input = input.replaceAll("_\\\\", "");
	
						// split the bottom
						bottoms = input.split(" ");
					} else {
						// top: do fuckin' nothing
					}
					i++;
					input = bufReader.readLine();
					System.out.println("Ligne "+i);
					if (input != null) {
						System.out.println(input.toString());
					}
				}
				System.out.println("");
				
				System.out.println("Resultat :");
				Triomino triomino;
				
				for (int j = 0; j < bottoms.length; j++) {
					middles[j] = middles[j].trim();
					middle = middles[j].split(" ");
	
					triomino = new Triomino(
						Integer.parseInt(bottoms[j], 16), 
						Integer.parseInt(middle[1], 16), 
						Integer.parseInt(middle[0], 16)
					);
					// on ajoute le triomino cree a la liste
					listeTriominos.add(triomino);
					System.out.println("Triomino" + (j+1) + " = " + triomino.toString());
				}
				// Creation du jeu a partir des donnees lues juste au-dessus
				int largeur_jeu = (int) Math.sqrt(bottoms.length);
				listeTriominos.trimToSize();
				System.out.println("Creation du jeu " + k + " :" + " de taille " + listeTriominos.size());
				Jeu jeu = new Jeu(largeur_jeu, listeTriominos);
				jeux_a_resoudre.add(jeu);
								
				// Saut de ligne et incrementation pour la lecture du jeu suivant
				System.out.println("");
				k++;
			}
			
		} catch(IOException io) {
			io.printStackTrace();
			
		} finally {
		    try {
		       if (bufReader != null)
		    	   bufReader.close();
		    } catch (IOException e) {
		    	e.printStackTrace();
		    }
		    try {
		       if (bufWriter != null)
		    	   bufWriter.close();
		    } catch (IOException e) {
		    	e.printStackTrace();
		    }
		}
		jeux_a_resoudre.trimToSize();
		return jeux_a_resoudre;
	}
	}
