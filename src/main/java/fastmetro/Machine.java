package fastmetro;

import java.awt.Point;
import java.util.ArrayList;

import gui.Fenetre;

//cette classe est representé comme la machine de cette application
//elle contiens toute les methode qui nous permettre d'avoir un code bien
	
public class Machine {

	// instanciation de notre fenetre
	
	private Fenetre fenetre;

	
	//liste de gare
	
	private ArrayList<Gare> gareList = new ArrayList<Gare>();

	// le nom qu'aura notre interface
	
	private String nom;

	// le chemin de notre carte cad la photo 
	
	private String cheminCarte;

	// on instancie la classe djikstra
	
	private DijkstraPath dijkstraPath = new DijkstraPath(this);

	// la liste des stations 
	
	private ArrayList<Station> stationList = new ArrayList<Station>();

	// la lecture de nos fichiers
	
	private ReadFile readFile = new ReadFile();

	// initialisations
	
	public Machine(String nom, String cheminCarte)
	{
		this.nom = nom;
		this.cheminCarte = cheminCarte;
	}
	
	
	//comme son nom l'indique cette methode
	// nous permet d'importer la liste des stations
	
	public void importStations(String chemin) 
	{
		setGareList(readFile.initStation(chemin, getGareList()));
		setStationList();
	}

	
	// on importe notre image et on affiche 
	// toutes les stations sur celle ci
	
	public void initStationCarte()
	{
		this.fenetre = new Fenetre(nom, cheminCarte);
		fenetre.printStation(gareList);
	}

	// on importe le graphe qu'on avait dans notre classe readFile 
	
	public void importGraphe(String chemin) 
	{
		stationList = readFile.initGraphe(chemin, stationList);
		int i = 0;
		int j = 0;
		for (Gare gare : gareList) 
		{
			for (Station station : gare.getStationGare())
			{
				gare.getStationGare().set(j, stationList.get(i));
				j++;
				i++;
			}
			j = 0;
		}
	}

	// on recherche le plus chemin parmis une liste station
	// on utilise djikstra 
	
	public void cherchePlusCourtChemin() 
	{
		fenetre.selectStation(this, dijkstraPath);
	}

	
	// comme son nom l'indique 
	// cette methode retourne une liste de gare
	// donc la lieste de stations dans ces gare
	
	public ArrayList<Gare> getGareList()
	{
		return this.gareList;
	}

	// methode qui initialise la liste des gares
	
	public void setGareList(ArrayList<Gare> gareList) 
	{
		this.gareList = gareList;
	}

	
	// cette methode permet de rechercher la gare
	//et la retourner a la fin de ce programme 
	// et ca en fonction des coordoonée entrer
	
	public Gare chercheGare(Point point) 
	{
		for (Gare gare : gareList) 
		{
			if (gare.getPoint().distance(point) < 10)
			{
				return gare;
			}
		}
		return null;
	}

	// cette methode permet d'initialiser toutes 
	//liste de stations que nous avons utilisé en haut

	public void setStationList() 
	{
		for (Gare gare : gareList) 
		{
			for (Station station : gare.getStationGare())
			{
				stationList.add(station);
			}
		}
	}

	// cette derniere methode retourne la liste des stations
	
	public ArrayList<Station> getStationList()
	{
		return stationList;
	}
}