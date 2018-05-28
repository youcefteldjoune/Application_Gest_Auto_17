package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import controleur.Tableau;

import controleur.Planning;
import modele.ModelPlanning;


public abstract class VuePlanning extends JPanel implements ActionListener
{
	private JButton btAjouter = new JButton("Ajouter");
	private JButton btSupprimer = new JButton("Supprimer");
	private JButton btEditer = new JButton("Editeur");
	private JButton btAnnuler = new JButton("Annuler");
	
	
	
	
	private JPanel unPanel = new JPanel();
	//declaration d'une JTable
	private JTable tablePlanning ;
	private Tableau unTableau; // object Modele de la classe Tableau 
		
	public VuePlanning ()
	{
		this.setLayout(null);
		this.setBounds(50, 50, 600, 300);
		this.setBackground(Color.yellow);
		
		//construction du pannel d'administraton
		this.unPanel.setLayout(new GridLayout(3,4));
		this.unPanel.setBounds(20, 180, 550, 100);
		this.unPanel.add(new JLabel("Id Client : "));
		
		
		
		
		this.unPanel.add(new JLabel("Nom Client : "));
	
		
		this.unPanel.add(new JLabel("Prenom Client : "));
		
	
		
		this.unPanel.add(new JLabel("Email Client : "));
		
		
		this.unPanel.add(this.btAnnuler);
		this.unPanel.add(this.btAjouter);
		this.unPanel.add(this.btSupprimer);
		this.unPanel.add(this.btEditer);
		this.unPanel.setVisible(true);
		this.add(unPanel);
		
		this.btAjouter.addActionListener(this);
		this.btSupprimer.addActionListener(this);
		this.btEditer.addActionListener(this);
		this.btAnnuler.addActionListener(this);
		
		//insertion de la table dans la fenetrre 
		String entetes [] = { ""  }; 
		Object donnes [] [] = this.remplirDonnes();
		this.unTableau = new Tableau(donnes, entetes);
		this.tablePlanning = new JTable(unTableau);
		JScrollPane uneScroll = new JScrollPane(tablePlanning);
		uneScroll.setBounds(20, 20, 550, 150);
		this.add(uneScroll);
		//ajout d'un evenement de clik sur les ligne de la table
		this.tablePlanning.addMouseListener(new MouseListener() 
		{
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) 
			{	
				
			
			}
		});
		
		this.setVisible(false);
	}

	private Object[][] remplirDonnes() 
	{
		// TODO Auto-generated method stub
		return null;
	}
}
