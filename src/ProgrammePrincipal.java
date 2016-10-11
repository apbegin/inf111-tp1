
/***
 * 
 * @author Antoine Proulx-Bégin
 * Cours: INF111
 * Groupe: 03
 * 
 * Programme principal pour l'éxécution du code
 *
 */

public class ProgrammePrincipal {

	private static final int NB_TOURS = 100;

	/**
	 * 
	 * Affiche les statistiques sur les différentes méthodes de force brute
	 * 
	 * @param stats
	 */
	private static void afficherStats(Stats stats) {

		System.out.println("Force brute : " 
		+ stats.bruteforce / NB_TOURS + " essais");

		System.out.println("Force brute optimisée : " +
		stats.bruteForce2LettresPlusNombre / NB_TOURS + " essais");

		System.out.println("Force brute intervalle : " 
		+ stats.bruteForceInterval / NB_TOURS + " essais");

		System.out.println("Retro ingénierie : " 
		+ stats.retroIngenierie / NB_TOURS + " essais");
	}

	/**
	 * Déverouillage de cadenas en continue pour chaque méthode de force brute
	 * avec incrémentation des statistiques de chaque méthode
	 * 
	 */
	public static void main(String[] args) {

		Stats stats = new Stats();

		for (int i = 0; i <= NB_TOURS; i++) {

			Cadenas cadenas = new Cadenas();

			stats.bruteforce += UtilitaireForceBrute.bruteForce(cadenas);
			cadenas.fermer();

			stats.bruteForce2LettresPlusNombre += UtilitaireForceBrute.
					bruteForceOptimise(cadenas);
			cadenas.fermer();

			stats.bruteForceInterval += UtilitaireForceBrute.
					bruteForceOptimiseIntervalles(cadenas);
			cadenas.fermer();

			stats.retroIngenierie += UtilitaireForceBrute.
					bruteForceAvecRetroIngenerie(cadenas);
			cadenas.fermer();

		}

		afficherStats(stats);

	}

}
