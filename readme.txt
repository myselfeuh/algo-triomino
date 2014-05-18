algo-triomino
=============
LALAOUI YANN
&
PENAUD GUILLAUME

TP d'algorithmie :
resolution d'un probleme de disposition de N triominos de la forme : 
  ^
 c b
/_a_\
avec N compris entre 1 et 4.

# Fonctionnement #

- lancement du programme -
Une fois compilé, le programme doit etre lance via la commande :
java Parseur < FichierSource.txt


- Formatage du fichier source -
Pour etre lu correctement, le fichier source doit contenir 
les dessins des triominos les uns a la suite des autres,
separes par un seul espace.


- Problemes multiples -
Le programme peut resoudre plusieurs problemes contenus 
dans le meme fichier si chaque suite de triominos est separe 
par une seule ligne vide.
L'affichage des resultats se fera dans l'ordre de lecture 
des problemes du fichier source (de bas en haut).


- Problemes incomplets -
Si un probleme contient moins de N² triominos, 
alors le programme construira la plus grande pyramide possible
avec le nombre de triominos donnes.


# Explication de l'implementation #

- Deroulement du programme -

Le parseur parcourt le fichier source jusqu'a la fin. 

Pour chaque probleme lu, il cree un jeu a resoudre qui contient 
la liste des triominos a ranger dans le bon ordre (c'est-a-dire 
dans l'ordre qui permetra d'afficher une pyramide en prennant les triominos 
un par un dans la liste rangee).

Pour chaque jeu a resoudre, il cree ensuite un solveur charge de ranger 
la liste de triominos. 

Si le solveur trouve une solution, il renvoit un jeu contenant la liste rangee,
et on construit un afficheur charge de dessiner la pyramide.

Sinon, il renvoit null et on affiche que le probleme n'as pas de solution.


# Fichier de test #

Un fichier source de test est fourni avec le code source : "FichierSource.txt"

Il contient 4 problemes :
- le 1er est le probleme donne en exemple dans l'enonce (9 triominos)
- le 2e est un probleme ayant au moins une solution (16 triominos)
- le 3e est un probleme sans solution (4 triominos)
- le 4e est le meme que le 2e mais avec 4 triominos en moins (12 triominos)

