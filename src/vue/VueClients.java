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

import controleur.Client;
import controleur.Tableau;
import modele.Modele;

public class VueClients extends JPanel implements ActionListener
{
	private JButton btAjouter = new JButton("Ajouter");
	private JButton btSupprimer = new JButton("Supprimer");
	private JButton btEditer = new JButton("Editeur");
	private JButton btAnnuler = new JButton("Annuler");
	
	
	private JTextField txtidclient = new JTextField();
	private JTextField txtNomclient = new JTextField();
	private JTextField txtPrenom = new JTextField();
	private JTextField txtEmail = new JTextField();
	
	private JPanel unPanel = new JPanel();
	//declaration d'une JTable
	private JTable tableClients ;
	private Tableau unTableau; // object Modele de la classe Tableau 
		
	public VueClients ()
	{
		this.setLayout(null);
		this.setBounds(50, 50, 600, 300);
		this.setBackground(Color.yellow);
		
		//construction du pannel d'administraton
		this.unPanel.setLayout(new GridLayout(3,4));
		this.unPanel.setBounds(20, 180, 550, 100);
		this.unPanel.add(new JLabel("Id Client : "));
		this.unPanel.add(this.txtidclient);
		
		this.txtidclient.setEditable(false);
		
		this.unPanel.add(new JLabel("Nom Client : "));
		this.unPanel.add(this.txtNomclient);
		
		this.unPanel.add(new JLabel("Prenom Client : "));
		this.unPanel.add(this.txtPrenom);
	
		
		this.unPanel.add(new JLabel("Email Client : "));
		this.unPanel.add(this.txtEmail);
		
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
		String entetes [] = { "Id Cleint", "Nom Client","Prenom Client" , "Email"  }; 
		Object donnes [] [] = this.remplirDonnes();
		this.unTableau = new Tableau(donnes, entetes);
		this.tableClients = new JTable(this.unTableau);
		JScrollPane uneScroll = new JScrollPane(tableClients);
		uneScroll.setBounds(20, 20, 550, 150);
		this.add(uneScroll);
		//ajout d'un evenement de clik sur les ligne de la table
		this.tableClients.addMouseListener(new MouseListener() 
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
				int ligne= tableClients.getSelectedRow();
				txtidclient.setText(tableClients.getValueAt(ligne, 0).toString());
				txtNomclient.setText(tableClients.getValueAt(ligne, 1).toString());
				txtPrenom.setText(tableClients.getValueAt(ligne, 2).toString());
				txtEmail.setText(tableClients.getValueAt(ligne, 3).toString());
			
			}
		});
		
		this.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{	
		if (e.getSource() == this.btAjouter)
		{
			String nom = this.txtNomclient.getText();
			String prenom = this.txtPrenom.getText();
			String email = this.txtEmail.getText();
			if (nom.equals("") || prenom.equals(""))
			{
				JOptionPane.showConfirmDialog(this, "Veuillez remplire les données");
			}
			else
			{
			//instantiation d'un client 
			Client unClient = new Client (nom, prenom, email, "");
			//appel du Modele pour insert un cleint dans la BDD
			Modele.insertClient(unClient);
			JOptionPane.showMessageDialog(this, "Insertion reussie !");
			unClient = Modele.selectWhereClient(unClient);
			 
			Object uneLigne[] =
				{
					unClient.getId(),
					unClient.getNom(),
					unClient.getPrenom(),
					unClient.getEmail(),
					unClient.getMdp()
				};
			this.unTableau.add(uneLigne);
			this.txtNomclient.setText("");
			this.txtPrenom.setText("");
			
			}
		}
		else if (e.getSource() == this.btSupprimer)
		{
			
			if (txtidclient.getText().equals(""))
				
			{
				JOptionPane.showConfirmDialog(this, "Veuillez renseignier l'id Client");
			}
			else 
			{
				int idclient = Integer.parseInt(this.txtidclient.getText());
				String nom = this.txtNomclient.getText();
				String adresse = this.txtPrenom.getText();
				String email = this.txtEmail.getText();		
			// instanciation du client
			Client unClient = new Client (idclient, nom, adresse, email, "");
			//appel du model client pour supprimer  
			Modele.deleteClient(unClient);		
 			JOptionPane.showMessageDialog(this, "Suppression reussie !");
 			int row = this.tableClients.getSelectedRow(); 
 			this.unTableau.delete(row);
			}
		}
		else if (e.getSource() == this.btEditer)
		{
			if (txtidclient.getText().equals(""))
				
			{
				JOptionPane.showConfirmDialog(this, "Veuillez renseignier l'id Client");
			}else 
			{
			int idclient = Integer.parseInt(this.txtidclient.getText());
			String nom = this.txtNomclient.getText();
			String prenom = this.txtPrenom.getText();
			String email = this.txtEmail.getText();
			// instanciation du client
			Client unClient = new Client ( idclient, nom, prenom, email);
			Modele.updateClient(unClient);
			JOptionPane.showMessageDialog(this, "Modification reussie !");
		
			}	
		}
		
		else if (e.getSource() == this.btAnnuler)
		{
			this.txtidclient.setText("");
			this.txtNomclient.setText("");
			this.txtPrenom.setText("");
			
		
		}
	}
	
	private Object[][] remplirDonnes() 
	{
		ArrayList<Client> lesClients = Modele.selectAllClients();
		Object donnes [] [] = new Object [lesClients.size()] [4];
		int i = 0;
		for (Client unClient : lesClients)
		{
			donnes [i] [0] = unClient.getId() + "";
			donnes [i] [1] = unClient.getNom();
			donnes [i] [2] = unClient.getPrenom();
			donnes [i] [3] = unClient.getEmail();
			i++;
		}
		return donnes;
	}
	
	
}
