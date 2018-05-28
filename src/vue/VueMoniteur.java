package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.Moniteur;
import controleur.Tableau;
import modele.ModelMoniteur;


public class VueMoniteur extends JPanel implements ActionListener
{
	private JButton btAjouter = new JButton("Ajouter");
	private JButton btSupprimer = new JButton("Supprimer");
	private JButton btEditer = new JButton("Editeur");
	private JButton btAnnuler = new JButton("Annuler");
	
	private JTextField txtidmoniteur = new JTextField();
	private JTextField txtNommoniteur = new JTextField();
	private JTextField txtPrenommoniteur = new JTextField();
	private JTextField txtAdressemoniteur = new JTextField();
	private JTextField txtSalaire = new JTextField();
	private JTextField txtEmbauche = new JTextField();
	
	private JPanel unPanel = new JPanel();
	//declaration d'une JTable
	private JTable tableMoniteur ;
	private Tableau unTableau;
	

	
	public VueMoniteur ()
	{
		this.setLayout(null);
		this.setBounds(50, 50, 600, 300);
		this.setBackground(Color.blue);
		
		this.unPanel.setLayout(new GridLayout(3,4));
		this.unPanel.setBounds(20, 180, 550, 100);
		this.unPanel.add(new JLabel("Id Moniteur : "));
		this.unPanel.add(this.txtidmoniteur);
		
		this.txtidmoniteur.setEditable(false);
		
		this.unPanel.add(new JLabel("Nom Moniteur : "));
		this.unPanel.add(this.txtNommoniteur);
		
		this.unPanel.add(new JLabel("Prenom Moniteur : "));
		this.unPanel.add(this.txtPrenommoniteur);
	
		
		this.unPanel.add(new JLabel("Adresse Moniteur : "));
		this.unPanel.add(this.txtAdressemoniteur);
		
		this.unPanel.add(new JLabel("Salaire Moniteur :"));
		this.unPanel.add(this.txtSalaire);
		
		this.unPanel.add(new JLabel("Date d'Embauche Moniteur :"));
		this.unPanel.add(this.txtEmbauche);
		
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
		String entetes [] = { "idm", "nomm","prenomm" , "adressem", "salaire", "embauche"  }; 
		
		Object donnes [] [] = this.remplirDonnes();
		this.unTableau = new Tableau(donnes, entetes);
		this.tableMoniteur = new JTable(unTableau);
		JScrollPane uneScroll = new JScrollPane(tableMoniteur);
		uneScroll.setBounds(20, 20, 550, 150);
		this.add(uneScroll);
		//ajout d'un evenement de clik sur les ligne de la table
		this.tableMoniteur.addMouseListener(new MouseListener() 
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
				int ligne= tableMoniteur.getSelectedRow();
				txtidmoniteur.setText(tableMoniteur.getValueAt(ligne, 0).toString());
				txtNommoniteur.setText(tableMoniteur.getValueAt(ligne, 1).toString());
				txtPrenommoniteur.setText(tableMoniteur.getValueAt(ligne, 2).toString());
				txtAdressemoniteur.setText(tableMoniteur.getValueAt(ligne, 3).toString());
				txtSalaire.setText(tableMoniteur.getValueAt(ligne, 4).toString());
				txtEmbauche.setText(tableMoniteur.getValueAt(ligne, 5).toString());
			}
		});
		
		this.setVisible(false);
	}

	public void actionPerformed(ActionEvent e) 
	{	
		if (e.getSource() == this.btAjouter)
		{
			String nomm = this.txtNommoniteur.getText();
			String prenomm = this.txtPrenommoniteur.getText();
			String adressem = this.txtAdressemoniteur.getText();
			String salaire = this.txtSalaire.getText();
			String embauche = this.txtEmbauche.getText();
			if (nomm.equals("") || prenomm.equals("") || adressem.equals("") || salaire.equals("") || embauche.equals(""))
			{
				JOptionPane.showConfirmDialog(this, "Veuillez remplire les données");
			}
			else
			{
			//instantiation d'un client 
			Moniteur unMoniteur = new Moniteur ( nomm, prenomm, adressem, salaire, embauche);
			//appel du Modele pour insert un cleint dans la BDD
			ModelMoniteur.insertMoniteur(unMoniteur);
			JOptionPane.showMessageDialog(this, "Insertion reussie !");
			unMoniteur = ModelMoniteur.selectWhereMoniteur(unMoniteur);
			System.out.println("id" + unMoniteur.getIdm());
			
			Object uneLigne[] =
				{
						unMoniteur.getIdm(),
						unMoniteur.getNomm(),
						unMoniteur.getPrenomm(),
						unMoniteur.getAdressem(),
						unMoniteur.getSalaire(),
						unMoniteur.getEmbauche(),
						
				};
			
			this.unTableau.add(uneLigne);
			
			this.txtNommoniteur.setText("");
			this.txtPrenommoniteur.setText("");
			this.txtAdressemoniteur.setText("");
			this.txtSalaire.setText("");
			this.txtEmbauche.setText("");
			
			}
		}
		else if (e.getSource() == this.btSupprimer)
		{
			
			if (txtidmoniteur.getText().equals(""))
				
			{
				JOptionPane.showConfirmDialog(this, "Veuillez renseignier l'id Client");
			}else 
			{
				int idm = Integer.parseInt(this.txtidmoniteur.getText());
				String nomm = this.txtNommoniteur.getText();
				String prenomm = this.txtPrenommoniteur.getText();
				String adressem = this.txtAdressemoniteur.getText();
				String salaire = this.txtSalaire.getText();
				String embauche = this.txtEmbauche.getText();
			
			// instanciation du client
				Moniteur unMoniteur = new Moniteur (idm, nomm, prenomm, adressem, salaire, embauche);
			//appel du model client pour supprimer  
			ModelMoniteur.deleteMoniteur(unMoniteur);		
 			JOptionPane.showMessageDialog(this, "Suppression reussie !");
 			int row = this.tableMoniteur.getSelectedRow();
 			this.unTableau.delete(row);
			}
		}
		else if (e.getSource() == this.btEditer)
		{
			if (txtidmoniteur.getText().equals(""))
				
			{
				JOptionPane.showConfirmDialog(this, "Veuillez renseignier l'id Client");
			}else 
			{
			int idm = Integer.parseInt(this.txtidmoniteur.getText());
			String nomm = this.txtNommoniteur.getText();
			String prenomm = this.txtPrenommoniteur.getText();
			String adressem = this.txtAdressemoniteur.getText();
			String salaire = this.txtSalaire.getText();
			String embauche = this.txtEmbauche.getText();
			
			// instanciation du client
			Moniteur unMoniteur = new Moniteur (idm, nomm, prenomm, adressem, salaire, embauche);
			ModelMoniteur.updateMoniteur(unMoniteur);
			JOptionPane.showMessageDialog(this, "Modification reussie !");
		
			}	
		}
		
		else if (e.getSource() == this.btAnnuler)
		{
			
			this.txtNommoniteur.setText("");
			this.txtPrenommoniteur.setText("");
			this.txtAdressemoniteur.setText("");
			this.txtSalaire.setText("");
			this.txtEmbauche.setText("");
			
		}
	}
	
	private Object[][] remplirDonnes() 
	{
		ArrayList<Moniteur> lesMoniteurs = ModelMoniteur.selectAllMoniteurs();
		Object donnes [] [] = new Object [lesMoniteurs.size()] [6];
		int i = 0;
		for (Moniteur unMoniteure : lesMoniteurs)
		{
			donnes [i] [0] = unMoniteure.getIdm() + "";
			donnes [i] [1] = unMoniteure.getNomm();
			donnes [i] [2] = unMoniteure.getPrenomm();
			donnes [i] [4] = unMoniteure.getAdressem();
			donnes [i] [4] = unMoniteure.getSalaire();
			donnes [i] [5] = unMoniteure.getEmbauche();
			i++;
		}
		return donnes;
	}


}
