package fastmetro;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


//cette classe nous permet de lire les fichier
//gson qui contients les stations et les temps 
	

public class ReadFile {

	// on retourne une liste de gare 
	// chaque gare peut contenir une ou plusieurs stations


	public ArrayList<Gare> initStation(String chemin, ArrayList<Gare> gareList) 
	{
		Reader reader;

		try {
			reader = new FileReader(chemin);
			Type type = new TypeToken<ArrayList<Gare>>() {}.getType();
			gareList = new Gson().fromJson(reader, type);
			int i = 0;
			for (Gare gare : gareList) 
			{
				gare.setGareId(i);
				for (Station station : gare.getStationGare())
				{
					station.setGareId(gare.getGareId());
					station.setGareNom(gare.getNom());
				}
				i++;
			}
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		return gareList;
	}

	// les stations a deux id different
	
	public ArrayList<Station> initGraphe(String chemin, ArrayList<Station> stationList) 
	{
		Reader reader;
		try {
			reader = new FileReader(chemin);
			final GsonBuilder builder = new GsonBuilder();
			
			final Gson gson = builder.create();
			final int[][] valeurs = gson.fromJson(reader, int[][].class);
			
			for (int i = 0; i < stationList.size(); i++)
			{
				stationList.set(i, initGrapheValeurs(stationList, valeurs, i));
			}
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		return stationList;
	}

	// on initiliase la valeur pour une station 
	// a la fin on retourne une station 
	// cette fonction est utilisé dans la methode en haut 
	
	private Station initGrapheValeurs(ArrayList<Station> stationList, int[][] valeurs, int id) 
	{
		Station station = new Station(stationList.get(id));
		for (int i = 0; i < valeurs.length; i++) 
		{
			for (int j = 0; j < 2; j++) 
			{
				if (valeurs[i][j] == id)
				{
					station.addCoupleVoisin(stationList.get(valeurs[i][j ^ 1]), valeurs[i][2]);
				}
			}
		}
		return station;
	}
	
	
}