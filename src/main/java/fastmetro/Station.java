package fastmetro;

import java.util.ArrayList;

//la classe station contient le numero de la ligne a qui elle appartient
//le id ainsi que le nom de la gare a qui elle appartient


public class Station extends Gare {

	//le id de la station
	
	private int id;

	//le numero de la ligne de metro
	
	private int ligne;

	// liste des voisins d'une station
	
	private ArrayList<StationPereTime> voisin = new ArrayList<StationPereTime>();

	
	
	public Station(int id, int ligne) 
	{
		super();
		this.id = id;
		this.ligne = ligne;
	}

	public Station(Station station) 
	{
		this.id = station.id;
		this.ligne = station.ligne;
		this.setVoisin(station.getVoisins());
		this.setGareNom(station.getNom());
		this.setGareId(station.getGareId());
	}
	

	
	// methode qui retourne le id d'une station
	
	public int getId()
	{
		return this.id;
	}
	
	// on recupere le nom de la gare a qui appartient cette station
	
	public String getNomGare() 
	{
		return this.getNom();
	}
	
	
	//on ajoute les couple de voisins dans notre liste
	
	public void setVoisin(ArrayList<StationPereTime> voisin) 
	{
		if (voisin != null)
		{
			for (StationPereTime couple : voisin) 
			{
				this.voisin.add(couple);
			}
		}
	}

	
	 // on recupere le numero de la ligne
	
	public int getLigne() 
	{
		return this.ligne;
	}

	
	// on recupere les coordonnée de notre ligne
	
	@Override
	public String toString() 
	{
		return "{ id:" + this.id + "ligne:" + this.ligne + " } gareid: " + "gareid" + this.getGareId() + "\n";
	}

	
	// on ajoute les voisins a cette station pour apres les utiliser
	
	public void addCoupleVoisin(Station station, int date)
	{
		this.voisin.add(new StationPereTime(station.getId(), date));
	}

	
	// on recupere la liste des voisins  station
	
	public ArrayList<StationPereTime> getVoisins() 
	{
		return voisin;
	}
	
	
}