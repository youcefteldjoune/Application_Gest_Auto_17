package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import controleur.Main;

public class VueGenerale extends JFrame implements ActionListener 
{
	private JPanel panelMenu = new JPanel();
	private JButton tabButtons [] = new JButton[6];
	private String tab[] = {"Clients", "Moniteurs", "Lecons","Voitures","Motos", "Quitter"};
	
	// Instanciation des panels 
	private VueClients uneVueClients = new VueClients();
	private VueMoniteur uneVueMoniteur = new VueMoniteur();
	private VueLecon uneVueLecon = new VueLecon();
	private VueVoiture uneVueVoiture = new VueVoiture();
	private VueMoto uneVueMoto = new VueMoto();
	

	
	/***********************LE CONSTRUCTEUR ******************/
 	public VueGenerale() 
	{
		this.setTitle("Auto 17 GESTIONS");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setResizable(false);
		this.setBounds(300, 300, 700, 480);
		this.getContentPane().setBackground(Color.WHITE);
		
		//Construction du Panel Menu 
		this.panelMenu.setBounds(0, 20, 700, 30);
		this.panelMenu.setLayout(new GridLayout(1, 7));
		//construction des butons
		for (int i =0; i<this.tabButtons.length; i++)
		{
			this.tabButtons[i] = new JButton(this.tab[i]);
			this.panelMenu.add(this.tabButtons[i]);
			this.tabButtons[i].addActionListener(this);
		}
		this.panelMenu.setVisible(true);
		this.add(this.panelMenu);
		// Ajout des Panneaux
		this.add(this.uneVueClients);
		this.add(this.uneVueMoniteur);
		this.add(this.uneVueLecon);
		this.add(this.uneVueVoiture);
		this.add(this.uneVueMoto);

		
		this.setVisible(true);
		ImageIcon icone = new ImageIcon("src/images/logo.png");
		this.setIconImage(icone.getImage());
		
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		switch (e.getActionCommand())
		{
		case "Clients" :
			this.uneVueClients.setVisible(true);
			this.uneVueMoniteur.setVisible(false);
			this.uneVueLecon.setVisible(false);
			this.uneVueVoiture.setVisible(false);
			this.uneVueMoto.setVisible(false);
		
					break;
		case "Moniteurs" :
			this.uneVueClients.setVisible(false);
			this.uneVueMoniteur.setVisible(true);
			this.uneVueLecon.setVisible(false);
			this.uneVueVoiture.setVisible(false);
			this.uneVueMoto.setVisible(false);
	
					break;
		case "Lecons" :
			this.uneVueClients.setVisible(false);
			this.uneVueMoniteur.setVisible(false);
			this.uneVueLecon.setVisible(true);
			this.uneVueVoiture.setVisible(false);
			this.uneVueMoto.setVisible(false);
		
					break;
					
		case "Voitures" :
			this.uneVueClients.setVisible(false);
			this.uneVueMoniteur.setVisible(false);
			this.uneVueLecon.setVisible(false);
			this.uneVueVoiture.setVisible(true);
			this.uneVueMoto.setVisible(false);
			
					break;
					
		case "Motos" :
			this.uneVueClients.setVisible(false);
			this.uneVueMoniteur.setVisible(false);
			this.uneVueLecon.setVisible(false);
			this.uneVueVoiture.setVisible(false);
			this.uneVueMoto.setVisible(true);

					break;
									
		case "Quitter" : 
			this.dispose();// detruire la Fenetre 
			Main.rendreVisible(true);
			break; 
		}
		
	}
	
}
