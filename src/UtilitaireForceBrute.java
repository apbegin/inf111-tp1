import java.util.Random;

/***
 * 
 * @author Antoine Proulx-Bégin
 * Cours: INF111
 * Groupe: 03
 * 
 * Différentes méthodes de force brute dans le cadre de
 * MNOP code Inc. (Travail 1)
 *
 */

public class UtilitaireForceBrute {

	public static Random rnd = new Random();


	/**
	 * Méthode de force brute de base pour obtenir la combinaison
	 * du cadenas numérique
	 * 
	 * @param cadenas
	 * @return nombre de caractères essayé (entier positif)
	 */
	public static int bruteForce(Cadenas cadenas) {

		int position = 0;
		char car = 0;
		int compteurCaractere = 0;
		
		while (!cadenas.estOuvert()) {

			//Sélectionne aléatoirement une des trois fonctions
			//pour générer un caractère entre a-z, A-Z et 0-9
			switch (rnd.nextInt(3)) {

			case 0:
				car = M_Fonctions.charEntreDeuxCodeAscii('a', 'z');
				break;

			case 1:
				car = M_Fonctions.lettreMajusculeAleatoire();
				break;

			case 2:
				car = M_Fonctions.chiffreAleatoire();
				break;
			}

			compteurCaractere += 1;
			
			if (cadenas.placerCarAlaPosition(car, position) == 0)
				position += 1;
		}
		return compteurCaractere;
	}

	/**
	 * Méthode de force brute optimisé pour obtenir la combinaison
	 * du cadenas numérique.
	 * 
	 * 
	 * @param cadenas
	 * @return nombre de caractères essayés (entier positif)
	 */
	public static int bruteForceOptimise(Cadenas cadenas) {

		int compteurCaractere = 0;

		char car = 0;
		int position = 0;

		while (!cadenas.estOuvert()) {

			//Deux premiers caractères de la combinaison sont entre A et Z
			if (position <= 1) {
				car = M_Fonctions.lettreMajusculeAleatoire();
			} 
			//autres caractères sont entre 0 et 9
			else {
				car = M_Fonctions.chiffreAleatoire();
			}
			//Si le caractère est le même que celui de la combinaison
			if (cadenas.placerCarAlaPosition(car, position) == 0) {
				position += 1;
				compteurCaractere += 1;
			} 
			else {
				compteurCaractere += 1;
			}
		}
		return compteurCaractere;
	}

	/**
	 * Méthode de force brute optimisé à l'aide d'intervalles 
	 * pour obtenir la combinaison du cadenas numérique.
	 * 
	 * @param cadenas
	 * @return nombre de caractères essayé (entier positif)
	 */
	public static int bruteForceOptimiseIntervalles(Cadenas cadenas) {

		int compteurCaractere = 0;

		char[] bornesLettres = { 'A', 'Z' };
		char[] bornesChiffres = { '0', '9' };
		
		int position = 0;
		char car = 0;
		int reponse;
		while (!cadenas.estOuvert()) {

			//Deux premiers caractères de la combinaison sont entre A et Z
			if (position <= 1) {
				car = M_Fonctions.charEntreDeuxCodeAscii(bornesLettres[0],
						bornesLettres[1]);
				reponse = cadenas.placerCarAlaPosition(car, position);
				
				compteurCaractere += 1;

				
				//On incrémente la position et on met les bornes
				//à leurs valeurs initiales lorsqu'on trouve un
				//caractère de la combinaison
				if (reponse == 0) {

					position += 1;
					bornesLettres[0] = 'A';
					bornesLettres[1] = 'Z';
				} 
				//Si la borne supérieur est avant le caractère
				//de la combinaison
				else if (reponse == -1) {
					bornesLettres[1] = car;
				}
				//Si la borne inférieur est après le caractère
				//de la combinaison
				else {
					bornesLettres[0] = car;
				}
			}
			//Caractères entre 0-9 pour le reste de la combinaison
			else {
				car = M_Fonctions.charEntreDeuxCodeAscii(bornesChiffres[0],
						bornesChiffres[1]);
				reponse = cadenas.placerCarAlaPosition(car, position);
				compteurCaractere += 1;
				
				//On incrémente la position et on met les bornes
				//à leurs valeurs initiales lorsqu'on trouve un
				//caractère de la combinaison
				if (reponse == 0) {
					position += 1;
					bornesChiffres[0] = '0';
					bornesChiffres[1] = '9';
					
				} 
				//Si la borne supérieur est avant le caractère
				//de la combinaison
				else if (reponse == -1) {
					bornesChiffres[1] = car;
				}
				//Si la borne inférieur est après le caractère
				//de la combinaison
				else {
					bornesChiffres[0] = car;
				}
			}
		}
		return compteurCaractere;

	}

	/**
	 * Méthode de force brute optimisé avec rétro ingénerie
	 * pour obtenir la combinaison du cadenas numérique.
	 * 
	 * 
	 * @param cadenas
	 * @return nombre de caractères essayé (entier positif)
	 */
	public static int bruteForceAvecRetroIngenerie(Cadenas cadenas) {

		int compteurCaractere = 0;
		char[] bornesLettres = { 'A', 'Z' };
		int position = 0;
		char car = 0;
		int reponse;
		int resultat;

		while (!cadenas.estOuvert()) {

			//Deux premiers caractères de la combinaison sont entre A et Z
			if (position <= 1) {
				car = M_Fonctions.charEntreDeuxCodeAscii(bornesLettres[0], 
						bornesLettres[1]);
				
				reponse = cadenas.placerCarAlaPosition((char) car, position);
				compteurCaractere += 1;

				//On incrémente la position et on met les bornes
				//à leurs valeurs initiales lorsqu'on trouve un
				//caractère de la combinaison
				if (reponse == 0) {

					position += 1;
					bornesLettres[0] = 'A';
					bornesLettres[1] = 'Z';
				}
				
				//Si la borne supérieur est avant le caractère
				//de la combinaison
				else if (reponse == -1)
					bornesLettres[1] = car;
				
				//Si la borne inférieur est après le caractère
				//de la combinaison
				else
					bornesLettres[0] = car;
				
			}
			// Pour les chiffres 0-9
			else {

				//Itération sur chaque couple de chiffre de tabIndices()
				for (int i = 0; i < cadenas.tabIndices().length; i = i + 2) {
					
					//Itération pour chaque type d'opération mathématique
					for (int j = 0; j < M_Fonctions.OPERATIONS.length; j++) {

						//Évite la division par zéro
						if (cadenas.tabIndices()[i + 1] == 0 
								&& M_Fonctions.OPERATIONS[j] == "/") {}

						else {
							resultat = M_Fonctions.resultatOperation(
									cadenas.tabIndices()[i],
									cadenas.tabIndices()[i + 1],
									M_Fonctions.OPERATIONS[j]);

							char[] charResultat = M_Fonctions.entierAString(
									resultat).toCharArray();

							//Itération sur chaque chiffre du résultat
							for(int k=0; k<charResultat.length; k++)
							{
								compteurCaractere+=1;
								
								//On incrémente la position si le caractère
								//est le même que la celui de la combinaison
								if (!cadenas.estOuvert() && cadenas.
										placerCarAlaPosition(charResultat[k]
												, position) == 0)
									position+=1;
							}
						}
					}
				}
			}
		}
		return compteurCaractere;
	}
}
