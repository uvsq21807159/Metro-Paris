package gui;

import javax.swing.*;

import fastmetro.Machine;
import fastmetro.DijkstraPath;
import fastmetro.Gare;

import java.awt.*;
import java.util.ArrayList;


// cette station permet de gerer la fenetre de notre interface graphique


public class Fenetre extends JFrame {

	/** error */
	private static final long serialVersionUID = 1L;

	/* pannel */
	private JPanel pan = new JPanel();

	/* station panel */
	StationPanel panel;

	
	public Fenetre(String nom, String cheminCarte) {
		super(nom);
		setSize(new Dimension(1008, 735));
		this.panel = new StationPanel(cheminCarte);

		BoxLayout bl = new BoxLayout(pan, BoxLayout.Y_AXIS);
		pan.setLayout(bl);
		pan.setBackground(Color.BLACK);
		setContentPane(pan);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// afficher les stations dans le panel
	
	public void printStation(ArrayList<Gare> gareList) 
	{
		// dessine les stations sur la carte
		this.add(panel);
		for (Gare gare : gareList) 
		{
			Circle circle = new Circle(gare.getPoint());
			panel.addCircle(circle);
		}
	}

	// on mets sous ecoute la souris des qu'il y a un clique elle reagit
	
	public void selectStation(Machine machine, DijkstraPath dijkstraPath) 
	{
		panel.addMouseListener(new ClickSelectStation(panel, machine, dijkstraPath));
	}
}