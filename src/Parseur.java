import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


/**
 * Classe Parseur qui s'occupe de lire le fichier donne en entree du programme 
 * et de le transformer en differents problemes a resoudre
 */
public class Parseur {
	
	private static boolean debug = false; 
	private static ArrayList<Jeu> jeux_a_resoudre = new ArrayList<Jeu>(1);
	
	public static void main(String[] args) {
				
		lireLeFichierSource();
		
		for (int i = 0; i < jeux_a_resoudre.size(); i++) {
		// pour chaque probleme cree on lance la procedure de resolution :
			Jeu jeu_non_resolu = jeux_a_resoudre.get(i);
			// Affichage du probleme a resoudre
			System.out.println("Probleme " + (i+1) + " :");
			jeu_non_resolu.affiche();
			
			// debug
			// Afficheur aff1 = new Afficheur(jeu_non_resolu);
			// aff1.afficherPyramide();
			// fin debug
			
			// Creation du solveur 
			Solveur solv = new Solveur(jeu_non_resolu);
			Jeu jeu_resolu = solv.getSolution();
			
				if (jeu_resolu == null){
				// Si le jeu n'a pas de solution
					System.out.println("Ce probleme n'a pas de solution... :(");
					System.out.println("");
				} else {
				// Si le jeu a une solution, on l'affiche
					// Creation d'un afficheur associé au jeu resolu et affichage de la pyramide
					Afficheur aff = new Afficheur(jeu_resolu);
					aff.afficherPyramide();
				}
		}
		
	}

	private static ArrayList<Jeu> lireLeFichierSource() {
		
		BufferedReader bufReader = null;
		BufferedWriter bufWriter = null;
		
		try {
			// redirection de stdin pour que Eclipse le prenne en compte
			System.setIn(new FileInputStream("FichierSource3.txt"));
			
			bufReader = new BufferedReader(new InputStreamReader(System.in));
		    
			String[] middles, middle, bottoms;

			middle = new String[2];
			middles = new String[16];
			bottoms = new String[16];
			
			// initialisation bidon pour démarrer la boucle
			String input = "initialisation";
			int k = 1;
			
			while(input != null) {
				
				ArrayList<Triomino> listeTriominos = new ArrayList<Triomino>(1);
				
				if (debug){
					System.out.println("Lecture de la serie " + k + " :");
				}
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
					if (debug){
						System.out.println("Ligne "+i);
						if (input != null) {
							System.out.println(input.toString());
						}
					}
				}
				if (debug){
					System.out.println("");
					System.out.println("Resultat :");
				}
				
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
					if (debug){
						System.out.println("Triomino" + (j+1) + " = " + triomino.toString());
					}
				}
				// Creation du jeu a partir des donnees lues juste au-dessus
				int largeur_jeu = (int) Math.sqrt(bottoms.length);
				listeTriominos.trimToSize();
				Jeu jeu = new Jeu(largeur_jeu, listeTriominos);
				jeux_a_resoudre.add(jeu);
				if (debug){
					System.out.println("Creation du jeu " + k + " :" + " de taille " + listeTriominos.size());
				// Saut de ligne et incrementation pour la lecture du jeu suivant
				System.out.println("");
				}
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
