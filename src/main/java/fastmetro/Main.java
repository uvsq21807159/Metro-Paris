package fastmetro;

import javax.swing.JOptionPane;

///////////////////////////CLASSE PRINCIPALE//////////////////////////////

public class Main {

	
	public static void main(String[] args) {
		
		// on initialise
		
		Machine metroParisien = new Machine("Metro", "image/paris.gif");

		//on importe les stations du fichier dont le chemin est indiquer
		
		metroParisien.importStations("data/parisStation.json");

		// on initialise notre carte sur l'interface
		
		metroParisien.initStationCarte();

		// on importe les graphes sur cette interface
		
		metroParisien.importGraphe("data/parisGraphe.json");

		
		JOptionPane.showMessageDialog(null,
				"Bienvenue à Metro! \n Le logiciel qui vous permet de trouver le plus court chemin entre deux stations\n Pour commencer :\n 1.On clique sur une station (station de depart) et on selectionne si besoin la ligne souhaité \n 2.On clique sur une deuxieme station(station d'arrivee) et on selectionne la ligne si besoin .\n 3.Puis on clique pour afficher le chemin.\n let's Go");

		
		// on selectionne les stations et on cherche le plus court chemin
		metroParisien.cherchePlusCourtChemin();
		
	}

}
