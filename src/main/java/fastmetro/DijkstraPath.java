package fastmetro;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

//cette classe va nous permettre de calculer 
//le plus court chemin on utilisant l'algorithme de djikstra

public class DijkstraPath {


	// premiere station selectionné sur la carte
	// c'est la station de depart
	
	Station stationDepart;

	// deuxieme stations selectionner
	// c'est la stations d'arriver
		
	Station stationArriver;

	// le nombre de stations 
	
	int nbrStation;

	
	// il verifie si c'est la station de depart ou nom
	
	int isStationDepart;

	// recuperer les données de notre fentere
	
	Machine machine;


	// initialisation
	
	public DijkstraPath(Machine machine) 
	{
		this.machine = machine;
		this.nbrStation = 0;
		this.isStationDepart = 1;
	}

	
	// cette methode nous permet d'ajouter le id ou plus generalement
	// les stations de depart et d'arriver pour retrouver apres le plus 
	// cours chemin
	
	public void addGareId(int id) 
	{
		ArrayList<Station> stationsGare = new ArrayList<Station>();
		stationsGare = machine.getGareList().get(id).getStationGare();
		Station station = getQuai(stationsGare, id);
		station.setGareId(id);

		this.isStationDepart = 1 - this.isStationDepart;

		if (isStationDepart == 0) 
		{
			stationDepart = station;
			nbrStation++;
		} 
		else
		{
			stationArriver = station;
			nbrStation++;
		}
	}

	    // comme son nom l'indique cette methode nous permet de deerminer
		// sur quel quai on se trouve 
		// pour apres indiquer la ligne
		// il se peut que dans la station d'arriver y  aura 2 quai different
		// dans se cas on choisis dans quel quai on veut desecndre
		// et par quel quai on veut aller ou dans quel quai nous sommes
		
	private Station getQuai(ArrayList<Station> stationsGare, int id) 
	{
		
		if (stationsGare.size() > 1)
		{
			int ligne = -1;
			String[] mesOptions = new String[stationsGare.size()];
			int i = 0;
			for (Station station : stationsGare) 
			{
				mesOptions[i] = String.valueOf(station.getLigne());
				i++;
			}
			String ligneEntree = new String();
			do {
				ligneEntree = (String) JOptionPane.showInputDialog(null, "Dans quel quai etes vous ?", "Faites votre choix SVP ",
						JOptionPane.QUESTION_MESSAGE, null, mesOptions, mesOptions[0]);
			} while (ligneEntree == null); 
			
			try 
			{
				ligne = Integer.parseInt(ligneEntree);
			} catch (NumberFormatException e) 
			{
				System.out.println("les données de la ligne sont corrumpu");
				System.exit(0); 
			}
			for (Station station : stationsGare) 
			{
				if (station.getLigne() == ligne) 
				{
					return station;
				}
			}
		}
		return stationsGare.get(0);
	}

		// focntion qui nous permet de verifier le nombre de clique
		// que l'utilisateur a fait ca renvoie  "true" si y a deux ou plus
		// et flase dans le cas contraire
	
	public boolean aDeuxGaresValider() 
	{
		if (nbrStation >= 2) 
		{
			this.nbrStation = 0;
			return true;
		}
		return false;
	}

	
		// cette methode permet de calculer le plus court chemin possible
		// entre deux stations
	
	public int calculPlusCourtChemin(ArrayList<Station> stationList, ArrayList<String> str,
			ArrayList<Station> stationListRes) 
	{
		//on a utiliser des matrices en utilisant des hashtable et les couple de pere/temps
		
		Hashtable<Integer, StationPereTime> matriceDijkstra_atraiter = new Hashtable<Integer, StationPereTime>();
		Hashtable<Integer, StationPereTime> matriceDijkstra_res = new Hashtable<Integer, StationPereTime>();

		//Initialisation 
		matriceDijkstra_atraiter.put(stationDepart.getId(), new StationPereTime(stationDepart.getId(), 0));
		int station = stationDepart.getId();
		
		while (!matriceDijkstra_atraiter.isEmpty())
		{

			for (StationPereTime couple : stationList.get(station).getVoisins())
			{
				
				if (!(matriceDijkstra_res.containsKey(couple.getStation()))) 
				{

					if (matriceDijkstra_atraiter.containsKey(couple.getStation())) 
					{

						if (matriceDijkstra_atraiter.get(couple.getStation())
								.getTemps() < matriceDijkstra_atraiter.get(station).getTemps() + couple.getTemps())
						{
							
							matriceDijkstra_atraiter.put(couple.getStation(), new StationPereTime(station,
									matriceDijkstra_atraiter.get(station).getTemps() + couple.getTemps()));
						}
					} 
					else
					{
						matriceDijkstra_atraiter.put(couple.getStation(), new StationPereTime(station,
								matriceDijkstra_atraiter.get(station).getTemps() + couple.getTemps()));
					}
				}
			}
			
			matriceDijkstra_res.put(station, matriceDijkstra_atraiter.get(station));
			matriceDijkstra_atraiter.remove(station);

			station = getMinDijkstra(matriceDijkstra_atraiter);
		}
		return retrouveChemin(matriceDijkstra_res, str, stationList, stationListRes);
	}

	
		// cette methode permet de trouver de la liste a traiter
		// pour apres ldans la methode calcul_plus_court_chemin 
		// ou elle designera le min de notre matrice
	
	private int getMinDijkstra(Hashtable<Integer, StationPereTime> matriceDijkstra_atraiter)
	{
		int min = (int) Math.pow(2, 31);
		int e = 0;
		int tempsTmp = 0;

		Iterator<Entry<Integer, StationPereTime>> it = matriceDijkstra_atraiter.entrySet().iterator();

		while (it.hasNext()) 
		{
			Entry<Integer, StationPereTime> element = it.next();
			tempsTmp = element.getValue().getTemps();
			if (Math.min(min, tempsTmp) != min)
			{
				min = tempsTmp;
				e = element.getKey();
			}
		}

		return e;
	}

	// permet de trouver les station pere par monté successive
	// cette methode est utilisé dans la methode calcul_plus_court_chemin
			
	
	private int retrouveChemin(Hashtable<Integer, StationPereTime> matriceDijkstra, ArrayList<String> str,
			ArrayList<Station> stationList, ArrayList<Station> stationListRes) 
	{

		// station innacessible
		if (!matriceDijkstra.containsKey(stationArriver.getId())) 
		{
			stationListRes.add(stationDepart);
			stationListRes.add(stationArriver);
			JOptionPane.showMessageDialog(null, "Ouh! Ligne non connecté");
			return 0;
		}
		
		// Initialisation 
		
		StationPereTime couple;
		couple = matriceDijkstra.get(stationArriver.getId());
		AddToStringArriver(str, stationArriver);
		stationListRes.add(stationArriver);
		
		int station = couple.getStation();

		// cette boucle nous permet de remonter les stations
		
		while (station != stationDepart.getId())
		{  
			
			AddToStringDeplacement(str, stationList.get(station));
			stationListRes.add(stationList.get(station));
			couple = matriceDijkstra.get(station);
			station = couple.getStation();
		}

		addToStringDepart(str, stationDepart);
		stationListRes.add(stationDepart);
		return matriceDijkstra.get(stationArriver.getId()).getTemps();
	}

	// ajouter les donneés pour les afficher apres
	
	private void addToStringDepart(ArrayList<String> str, Station station)
	{
		try 
		{
			str.add("☞" + "Vous etês à " + station.getGareNom() + "\n" + "☞" + "Prenez la ligne " + station.getLigne() + "🗸"+"\n");
		} catch (Exception e) 
		{

		}
	}
	private void AddToStringArriver(ArrayList<String> str, Station station)
	{
		try 
		{
			str.add("☞" + "Vous devriez arriver à  " + station.getGareNom() );
		} catch (Exception e) 
		{

		}
	}
	
	private void AddToStringDeplacement(ArrayList<String> str, Station station)
	{  
		try 
		 
		{
			str.add("☞" + " À " + station.getGareNom() + "," +  "Prenez la ligne " + station.getLigne() + "🗸"+"\n");
		}
		
	     catch (Exception e) 
		{

		}
	}
	
	
}