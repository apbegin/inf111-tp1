import java.util.Random;

public class Stats {

	public int bruteforce = 0;
	public int bruteForce2LettresPlusNombre = 0;
	public int bruteForceInterval = 0;
	public int retroIngenierie =0;
	public Random rnd = new Random();
	
	public void bruteForce(Cadenas cadenas){
		
		char car;
		int position=0;
		while(!cadenas.estOuvert()){
			car = (char)(48 + rnd.nextInt(90));
			
			if(cadenas.placerCarAlaPosition(car, position)==0)
				position+=1;
		}
	}
	
	public void bruteForceOptimise(Cadenas cadenas){
		
		char car;
		int position=0;
		
		while(!cadenas.estOuvert()){
			car = (char)(65+rnd.nextInt(90))
			
		}
	}
}

