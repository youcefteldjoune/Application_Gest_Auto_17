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

import controleur.Moto;
import controleur.Tableau;
import modele.ModelMoto;

public class VueMoto extends JPanel implements ActionListener
{
	private JButton btAjouter = new JButton("Ajouter");
	private JButton btSupprimer = new JButton("Supprimer");
	private JButton btEditer = new JButton("Editeur");
	private JButton btAnnuler = new JButton("Annuler");
	
	private JTextField txtidmoto = new JTextField();
	private JTextField txtModele = new JTextField();
	private JTextField txtMarquem = new JTextField();
	private JTextField txtanneem = new JTextField();
	private JTextField txtnbkm_m = new JTextField();
	private JTextField txtmatriculem = new JTextField();
	
	private JPanel unPanel = new JPanel();
	//declaration d'une JTable
	private JTable tableMoto ;
	private Tableau unTableau;

	public VueMoto ()
	{
		this.setLayout(null);
		this.setBounds(50, 50, 600, 300);
		this.setBackground(Color.WHITE);
		
		this.setVisible(false);
		this.unPanel.setLayout(new GridLayout(3,4));
		this.unPanel.setBounds(20, 180, 550, 100);
		this.unPanel.add(new JLabel("Id  : "));
		this.unPanel.add(this.txtidmoto);
		
		this.txtidmoto.setEditable(false);
		
		this.unPanel.add(new JLabel("Modele : "));
		this.unPanel.add(this.txtModele);
		
		this.unPanel.add(new JLabel("Marque : "));
		this.unPanel.add(this.txtMarquem);
	
		
		this.unPanel.add(new JLabel("Annee  : "));
		this.unPanel.add(this.txtanneem);
		
		this.unPanel.add(new JLabel("Nb Km :"));
		this.unPanel.add(this.txtnbkm_m);
		
		this.unPanel.add(new JLabel("Matricule"));
		this.unPanel.add(this.txtmatriculem);
		
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
		String entetes [] = { "idmoto", "marquem" , "modele","matriculem",  "nbkm_m" , "anneem" }; 
		
		Object donnes [] [] = this.remplirDonnes();
		this.unTableau= new Tableau(donnes, entetes);
		this.tableMoto = new JTable(unTableau);
		JScrollPane uneScroll = new JScrollPane(tableMoto);
		uneScroll.setBounds(20, 20, 550, 150);
		this.add(uneScroll);
		//ajout d'un evenement de clik sur les ligne de la table
		this.tableMoto.addMouseListener(new MouseListener() 
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
				int ligne= tableMoto.getSelectedRow();
				txtidmoto.setText(tableMoto.getValueAt(ligne, 0).toString());
				txtMarquem.setText(tableMoto.getValueAt(ligne, 1).toString());
				txtModele.setText(tableMoto.getValueAt(ligne, 2).toString());				
				txtanneem.setText(tableMoto.getValueAt(ligne, 3).toString());	
				txtnbkm_m.setText(tableMoto.getValueAt(ligne, 4).toString());
				txtmatriculem.setText(tableMoto.getValueAt(ligne, 5).toString());
				
			}
		});
		
		this.setVisible(false);
	}

	public void actionPerformed(ActionEvent e) 
	{	
		if (e.getSource() == this.btAjouter)
		{
			String modele = this.txtModele.getText();
			String marquem = this.txtMarquem.getText();
			String matriculem = this.txtmatriculem.getText();
			String nbkm_m = this.txtnbkm_m.getText();			
			String anneem = this.txtanneem.getText();
			if (marquem.equals("") || modele.equals(""))
			{
				JOptionPane.showConfirmDialog(this, "Veuillez remplire les données");
			}
			else
			{
			//instantiation d'un client 
			Moto uneMoto = new Moto (modele, marquem, anneem, nbkm_m, matriculem);
			//appel du Modele pour insert un cleint dans la BDD
			ModelMoto.insertMoto(uneMoto);
			JOptionPane.showMessageDialog(this, "Insertion reussie !");
			uneMoto = ModelMoto.selectWhereMoto(uneMoto);
			System.out.println("idmoto" + uneMoto.getIdmoto());
			Object uneLigne[] =
				{
						uneMoto.getIdmoto(),
						uneMoto.getModele(),
						uneMoto.getMarquem(),
						uneMoto.getMatriculem(),
						uneMoto.getNbkm_m(),
						uneMoto.getAnneem(),
						
						
						
				};
			
			this.unTableau.add(uneLigne);
			
			this.txtModele.setText("");
			this.txtMarquem.setText("");
			this.txtanneem.setText("");
			this.txtnbkm_m.setText("");
			this.txtmatriculem.setText("");
			
			}
		}
		else if (e.getSource() == this.btSupprimer)
		{
			
			if (txtidmoto.getText().equals(""))
				
			{
				JOptionPane.showConfirmDialog(this, "Veuillez renseignier l'id Client");
			}else 
			{
				int idmoto = Integer.parseInt(this.txtidmoto.getText());
				String modele = this.txtModele.getText();
				String marquem = this.txtMarquem.getText();
				String annnem = this.txtanneem.getText();
				String nbkm_m = this.txtnbkm_m.getText();
				String matriculem = this.txtmatriculem.getText();
			
			// instanciation du client
				Moto uneMoto = new Moto (idmoto, modele, marquem,  matriculem, nbkm_m, annnem);
			//appel du model client pour supprimer  
			ModelMoto.deleteMoto(uneMoto);		
 			JOptionPane.showMessageDialog(this, "Suppression reussie !");
 			int row = this.tableMoto.getSelectedRow();
 			this.unTableau.delete(row);
			}
		}
		else if (e.getSource() == this.btEditer)
		{
			if (txtidmoto.getText().equals(""))
				
			{
				JOptionPane.showConfirmDialog(this, "Veuillez renseignier l'id Client");
			}else 
			{
			int idmoto = Integer.parseInt(this.txtidmoto.getText());
			String modele = this.txtModele.getText();
			String marquem = this.txtMarquem.getText();
			String annnem = this.txtanneem.getText();
			String nbkm_m = this.txtnbkm_m.getText();
			String matriculem = this.txtmatriculem.getText();
			
			// instanciation du client
			Moto uneMoto = new Moto (idmoto, modele, marquem, annnem, nbkm_m,  matriculem);
			ModelMoto.insertMoto(uneMoto);
			JOptionPane.showMessageDialog(this, "Modification reussie !");
		
			}	
		}
		
		else if (e.getSource() == this.btAnnuler)
		{
			
			this.txtModele.setText("");
			this.txtMarquem.setText("");
			this.txtanneem.setText("");
			this.txtnbkm_m.setText("");
			this.txtmatriculem.setText("");
		
		}
	}
	
	private Object[][] remplirDonnes() 
	{
		ArrayList<Moto> lesMotos = ModelMoto.selectAllMotos();
		Object donnes [] [] = new Object [lesMotos.size()] [6];
		int i = 0;
		for (Moto uneMoto : lesMotos)
		{
			donnes [i] [0] = uneMoto.getIdmoto() + "";
			donnes [i] [1] = uneMoto.getModele();
			donnes [i] [2] = uneMoto.getMarquem();
			donnes [i] [3] = uneMoto.getAnneem();
			donnes [i] [4] = uneMoto.getNbkm_m();
			donnes [i] [5] = uneMoto.getMatriculem();
			
			i++;
		}
		return donnes;
	}


}
