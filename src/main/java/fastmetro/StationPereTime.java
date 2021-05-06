package fastmetro;


public class StationPereTime {
	
	// le id de la station
	
	private int station;
	
	// le temps qui lui correspond
	
	private int temps;

	
	StationPereTime(int station, int temps)
	{
		this.setStation(station);
		this.setTemps(temps);
	}

	
	// on recupere le id 
	
	public int getStation() 
	{
		return station;
	}

	
	
	public void setStation(int station)
	{
		this.station = station;
	}

	// methode qui recupere le temps
	
	public int getTemps()
	{
		return temps;
	}

	// methode qui initialise le temps
	
	public void setTemps(int temps) 
	{
		this.temps = temps;
	}

	// on retourne les coordonnée 
	
	@Override
	public String toString() 
	{
		return "Station: " + station + " Temps: " + this.temps;
	}

}