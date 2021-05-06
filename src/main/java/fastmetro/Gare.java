package fastmetro;

import java.awt.Point;
import java.util.ArrayList;

//cette classe est une liste de stations qu'on a appelleé gare tels que 
//cette derniere peut avoir une ou plusieurs stations a la fois 
//une gare contiens un nom des coordonées ainsi q'un id 


public class Gare {

	// le nom de la gare
	
	private String nom;

		// les coordonnéé d'une gare sur la carte
		// ou se trouve cette gare sur la carte
	
	private Point point;

	// la liste des stations
	
	private ArrayList<Station> stationGare = new ArrayList<Station>();

	// le id d'une gare
	
	private int gareid;

	
	Gare(String nom, Point point) 
	{
		this.nom = nom;
		this.point = point;
	}

	
	public Gare(Gare gare) 
	{
		this.nom = gare.getNom();
		this.point = gare.getPoint();
		this.gareid = gare.getGareId();
	}

	public Gare() {}

		// comme son nom l'indique cette station
		//permet d'ajouter une station dans une liste
	
	public void addStation(Station station) 
	{
		stationGare.add(station);
	}

	
	// on recupere les donnée d'une gare en retournant une chaine 
	// de caratere
	
	@Override
	public String toString() 
	{
		return "Gare:" + nom + " Coords {" + point.toString() + "}" + stationGare.toString() + "+ id:" + this.gareid
				+ '\n';
	}

	// cette methode recupere les coordonnées 
	// d'une gare sous forme de type Point
	
	public Point getPoint() 
	{
		return this.point;
	}

	// on recupere le nom d'une gare
	
	public String getNom()
	{
		return this.nom;
	}

		// cette methode nous permet de recuperer 
		// la liste des stations d'une gare
	
	public ArrayList<Station> getStationGare()
	{
		return stationGare;
	}

	
	// elle recupere la taille d'une liste
	// qui sera la taille d'une gare
		
	public int getNbrStations() 
	{
		return this.stationGare.size();
	}

	
//////////setters//////
	
	//initialisation du id de la gare	
	
	public void setGareId(int i) 
	{
		this.gareid = i;
	}
	
	// initialisations d'un nom d'une gare
	
	public void setGareNom(String nom) 
	{
		this.nom = nom;
	}

	
/////////////////
	
// elle recupere le id d'une gare
	
	public int getGareId() 
	{
		return this.gareid;
	}
	
	// recupere le nom de la gare
	
	public String getGareNom() 
	{
		return this.nom;
	}
	
}
