package gui;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;

import fastmetro.Machine;
import fastmetro.DijkstraPath;
import fastmetro.Gare;
import fastmetro.Station;

// cette classe gere le panel ainsi que les cliques 

public class ClickSelectStation implements MouseListener {

	// pour trouver les gares
	
	private Machine machine;

	// elle permettra d'afficher les modifications du clique
	
	private StationPanel panel;

	// designer le plus court chemin
	
	private DijkstraPath dijkstraPath;

	// nombre de clique
	
	private int click;

	
	
	public ClickSelectStation(StationPanel panel, Machine machine, DijkstraPath dijkstraPath) 
	{
		super();
		this.panel = panel;
		this.machine = machine;
		this.dijkstraPath = dijkstraPath;
		this.click = 1;
	}

	@Override
	public void mouseClicked(MouseEvent event) 
	{
		if (click == 0) 
		{
			ArrayList<Station> listStations = new ArrayList<Station>();
			this.dijkstraPath.calculPlusCourtChemin(machine.getStationList(), null,listStations);
			for (Station station : listStations) 
			{
				panel.setCircleColor(Color.red, station.getGareId());
			}
			click = 1 - click;
		}
		if (!dijkstraPath.aDeuxGaresValider()) {
			Point point = new Point(event.getX(), event.getY());
			try { /* si le clique est en dehors */
				Gare gare = new Gare(machine.chercheGare(point));
				this.dijkstraPath.addGareId(gare.getGareId());
				panel.setCircleColor(Color.yellow, gare.getGareId());
			} catch (Exception e) {
			}
		} else {
			click = 1 - click;
			if (click == 0) {
				ArrayList<Station> listStations = new ArrayList<Station>();
				ArrayList<String> str = new ArrayList<String>();
				int total = 0;
				total = this.dijkstraPath.calculPlusCourtChemin(machine.getStationList(), str,listStations);
				for (Station station : listStations) {
					panel.setCircleColor(Color.black, station.getGareId());
				}
				
				Collections.reverse(str);
				if(total < 60)
				{
				 String res = String.join("", str).concat("dans " + total   +" secondes");
				 JOptionPane.showMessageDialog(null, res);
				}
				if(total >= 60)
				{
				   total =total / 60;
				   String res = String.join("", str).concat("  dans " + total   +" minutes");
				   JOptionPane.showMessageDialog(null, res);
				}
				
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
}