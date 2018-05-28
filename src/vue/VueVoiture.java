package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.Tableau;
import controleur.Voiture;
import modele.ModelVoiture;

public class VueVoiture extends JPanel implements ActionListener
{
	
	private JButton btAjouter = new JButton("Ajouter");
	private JButton btSupprimer = new JButton("Supprimer");
	private JButton btEditer = new JButton("Editeur");
	private JButton btAnnuler = new JButton("Annuler");
	
	private JTextField txtidv = new JTextField();
	private JTextField txtMarquev = new JTextField();
	private JTextField txtModelev = new JTextField();
	private JTextField txtmatriculev = new JTextField();
	private JTextField txtanneev = new JTextField();
	private JTextField txtnbkm_v = new JTextField();
	
	
	private JPanel unPanel = new JPanel();
	//declaration d'une JTable
	private JTable tableVoiture ;
	private Tableau  unTableau;
	public VueVoiture ()
	{
		this.setLayout(null);
		this.setBounds(50, 50, 600, 300);
		this.setBackground(Color.white);
		
		this.setVisible(false);
		this.unPanel.setLayout(new GridLayout(3,4));
		this.unPanel.setBounds(20, 180, 550, 100);
		this.unPanel.add(new JLabel("Id Voiture : "));
		this.unPanel.add(this.txtidv);
		
		this.txtidv.setEditable(false);
		
		this.unPanel.add(new JLabel("Marque Voiture : "));
		this.unPanel.add(this.txtMarquev);
		
		this.unPanel.add(new JLabel("Modele Voiture : "));
		this.unPanel.add(this.txtModelev);
		
		this.unPanel.add(new JLabel("Matricule Voiture"));
		this.unPanel.add(this.txtmatriculev);
		
		this.unPanel.add(new JLabel("Anneem Voiture : "));
		this.unPanel.add(this.txtanneev);
		
		this.unPanel.add(new JLabel("Nb Km Voiture :"));
		this.unPanel.add(this.txtnbkm_v);
		
	
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
				String entetes [] = { "idv", "marquev","modelev" ,"matriculev" , "nbkm_v","anneev" }; 
				
				Object donnes [] [] = this.remplirDonnes();
				this.unTableau= new Tableau(donnes, entetes);
				this.tableVoiture = new JTable(unTableau);
				JScrollPane uneScroll = new JScrollPane(tableVoiture);
				uneScroll.setBounds(20, 20, 550, 150);
				this.add(uneScroll);
				//ajout d'un evenement de clik sur les ligne de la table
				this.tableVoiture.addMouseListener(new MouseListener() 
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
						int ligne= tableVoiture.getSelectedRow();
						txtidv.setText(tableVoiture.getValueAt(ligne, 0).toString());
						txtMarquev.setText(tableVoiture.getValueAt(ligne, 1).toString());
						txtModelev.setText(tableVoiture.getValueAt(ligne, 2).toString());
						txtmatriculev.setText(tableVoiture.getValueAt(ligne, 3).toString());
						txtanneev.setText(tableVoiture.getValueAt(ligne, 4).toString());
						txtnbkm_v.setText(tableVoiture.getValueAt(ligne, 5).toString());
					}
				});
				
				this.setVisible(false);
			}

			public void actionPerformed(ActionEvent e) 
			{	
				if (e.getSource() == this.btAjouter)
				{
					String marquev = this.txtMarquev.getText();
					String modelv = this.txtModelev.getText();
					String matriculev = this.txtmatriculev.getText();
					String anneev = this.txtanneev.getText();
					String nbkm_v = this.txtnbkm_v.getText();
					if (marquev.equals("") || modelv.equals(""))
					{
						JOptionPane.showConfirmDialog(this, "Veuillez remplire les données");
					}
					else
					{
					//instantiation d'un client 
					Voiture uneVoiture = new Voiture ( marquev, modelv, matriculev, anneev, nbkm_v);
					//appel du Modele pour insert un cleint dans la BDD
					ModelVoiture.insertVoiture(uneVoiture);
					JOptionPane.showMessageDialog(this, "Insertion reussie !");
					uneVoiture = ModelVoiture.selectWhereVoiture(uneVoiture);
					System.out.println("idv" + uneVoiture.getIdv());
					Object uneLigne[] =
						{
								uneVoiture.getIdv(),
								uneVoiture.getMarquev(),
								uneVoiture.getModelv(),
								uneVoiture.getMatriculev(),
								uneVoiture.getAnneev(),
								uneVoiture.getNbkm_v(),
								
						};
					
					this.unTableau.add(uneLigne);
					
					this.txtMarquev.setText("");
					this.txtModelev.setText("");
					this.txtmatriculev.setText("");
					this.txtanneev.setText("");
					this.txtnbkm_v.setText("");
					
					}
				}
				else if (e.getSource() == this.btSupprimer)
				{
					
					if (txtidv.getText().equals(""))
						
					{
						JOptionPane.showConfirmDialog(this, "Veuillez renseignier l'id Client");
					}else 
					{
						int idv = Integer.parseInt(this.txtidv.getText());
						String marquev = this.txtMarquev.getText();
						String modelv = this.txtModelev.getText();
						String matriculev = this.txtmatriculev.getText();
						String annnev = this.txtanneev.getText();
						String nbkm_v = this.txtnbkm_v.getText();
					
					// instanciation du client
						Voiture uneVoiture = new Voiture (idv, marquev, modelv, matriculev, annnev, nbkm_v);
					//appel du model client pour supprimer  
					ModelVoiture.deleteVoiture(uneVoiture);		
		 			JOptionPane.showMessageDialog(this, "Suppression reussie !");
		 			int row = this.tableVoiture.getSelectedRow();
		 			this.unTableau.delete(row);
					}
				}
				else if (e.getSource() == this.btEditer)
				{
					if (txtidv.getText().equals(""))
						
					{
						JOptionPane.showConfirmDialog(this, "Veuillez renseignier l'id Client");
					}else 
					{
					int idv = Integer.parseInt(this.txtidv.getText());
					String marquev = this.txtMarquev.getText();
					String modelev = this.txtModelev.getText();
					String matriculev = this.txtmatriculev.getText();
					String anneev = this.txtanneev.getText();
					String nbkm_v = this.txtnbkm_v.getText();
					
					// instanciation du client
					Voiture uneVoiture = new Voiture  (idv, marquev, modelev, matriculev, anneev, nbkm_v);
					ModelVoiture.updateVoiture(uneVoiture);
					JOptionPane.showMessageDialog(this, "Modification reussie !");
				
					}	
				}
				
				else if (e.getSource() == this.btAnnuler)
				{
					this.txtMarquev.setText("");
					this.txtModelev.setText("");
					this.txtmatriculev.setText("");
					this.txtanneev.setText("");
					this.txtnbkm_v.setText("");
					
				
				}
			}
			
			private Object[][] remplirDonnes() 
			{
				ArrayList<Voiture> lesVoitures = ModelVoiture.selectAllVoitures();
				Object donnes [] [] = new Object [lesVoitures.size()] [6];
				int i = 0;
				for (Voiture uneVoiture : lesVoitures)
				{
					donnes [i] [0] = uneVoiture.getIdv() + "";
					donnes [i] [1] = uneVoiture.getMarquev();
					donnes [i] [2] = uneVoiture.getModelv();
					donnes [i] [3] = uneVoiture.getMatriculev();
					donnes [i] [4] = uneVoiture.getAnneev();
					donnes [i] [5] = uneVoiture.getNbkm_v();
					
					i++;
				}
				return donnes;
			}

		
}
