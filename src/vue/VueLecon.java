package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.jar.JarFile;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.Lecon;
import controleur.Tableau;
import modele.ModelLecon;


public class VueLecon extends JPanel implements ActionListener
{
	private JButton btAjouter = new JButton("Ajouter");
	private JButton btSupprimer = new JButton("Supprimer");
	private JButton btEditer = new JButton("Editeur");
	private JButton btAnnuler = new JButton("Annuler");
	
	private JTextField txtidl = new JTextField();
	private JComboBox txtTypel = new JComboBox();
	private JTextField txtDatel = new JTextField();
	private JTextField txtDureel = new JTextField();
	
	private JPanel unPanel = new JPanel();
	//declaration d'une JTable
	private JTable tableLecon;	
	private Tableau unTableau;
	public VueLecon ()
	{
		this.setLayout(null);
		this.setBounds(50, 50, 600, 300);
		this.setBackground(Color.pink);
		
		this.setVisible(false);
		this.unPanel.setLayout(new GridLayout(3,4));
		this.unPanel.setBounds(20, 180, 550, 100);
		this.unPanel.add(new JLabel("Id Lecon : "));
		this.unPanel.add(this.txtidl);
		
		this.txtidl.setEditable(false);
		
		this.txtTypel.addItem("auto");
		this.txtTypel.addItem("moto");
		
		this.unPanel.add(new JLabel("Type lecon: "));
		this.unPanel.add(this.txtTypel);
		
		this.unPanel.add(new JLabel("Date Lecon : "));
		this.unPanel.add(this.txtDatel);
		
		this.unPanel.add(new JLabel("Durée Lecon"));
		this.unPanel.add(this.txtDureel);
	
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
	String entetes [] = { "idl", "datel", "dureel" ,"typel"  }; 
	
	Object donnes [] [] = this.remplirDonnes();
	unTableau = new Tableau(donnes, entetes);
	this.tableLecon = new JTable(unTableau);
	JScrollPane uneScroll = new JScrollPane(tableLecon);
	uneScroll.setBounds(20, 20, 550, 150);
	this.add(uneScroll);
	//ajout d'un evenement de clik sur les ligne de la table
	this.tableLecon.addMouseListener(new MouseListener() 
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
			int ligne= tableLecon.getSelectedRow();
			txtidl.setText(tableLecon.getValueAt(ligne, 0).toString());
			txtDatel.setText(tableLecon.getValueAt(ligne, 1).toString());
			txtDureel.setText(tableLecon.getValueAt(ligne, 2).toString());
			txtTypel.setToolTipText(tableLecon.getValueAt(ligne, 3).toString());
	
		}
	});
	
	this.setVisible(false);
}
	public void actionPerformed(ActionEvent e) 
	{	
		if (e.getSource() == this.btAjouter)
		{
			String datel = this.txtDatel.getText();
			String dureel = this.txtDureel.getText();
			String typel = this.txtTypel.getSelectedItem().toString();
			if (datel.equals("") || dureel.equals(""))
			{
				JOptionPane.showConfirmDialog(this, "Veuillez remplire les données");
			}
			else
			{
			//instantiation d'un client 
			Lecon uneLecon = new Lecon (datel, dureel, typel);
			//appel du Modele pour insert un cleint dans la BDD
			ModelLecon.insertLecon(uneLecon);
			JOptionPane.showMessageDialog(this, "Insertion reussie !");
			uneLecon = ModelLecon.selectWhereLecon(uneLecon);
			System.out.println("idl" + uneLecon.getIdl());
			
			Object uneLigne[] =
				{
					uneLecon.getIdl(),
					uneLecon.getDatel(),
					uneLecon.getDureel(),
					uneLecon.getTypel()
		
				};
			
			this.unTableau.add(uneLigne);
			
			this.txtDatel.setText("");
			this.txtDureel.setText("");
			
			
			}
		}
		else if (e.getSource() == this.btSupprimer)
		{
			
			if (txtidl.getText().equals(""))
				
			{
				JOptionPane.showConfirmDialog(this, "Veuillez renseignier l'id Client");
			}else 
			{
				int idl = Integer.parseInt(this.txtidl.getText());
				String datel = this.txtDatel.getText();
				String dureel = this.txtDureel.getText();
				String typel = this.txtTypel.getSelectedItem().toString();
				
			
			// instanciation du client
			Lecon uneLecon = new Lecon (idl, datel, dureel, typel);
			//appel du model client pour supprimer  
			ModelLecon.deleteLecon(uneLecon);		
 			JOptionPane.showMessageDialog(this, "Suppression reussie !");
 			int row = this.tableLecon.getSelectedRow();
 			this.unTableau.delete(row);
			}
		}
		else if (e.getSource() == this.btEditer)
		{
			if (txtidl.getText().equals(""))
				
			{
				JOptionPane.showConfirmDialog(this, "Veuillez renseignier l'id Client");
			}else 
			{
			int idl = Integer.parseInt(this.txtidl.getText());
			String datel = this.txtDatel.getText();
			String dureel = this.txtDureel.getText();
			String typel = this.txtTypel.getSelectedItem().toString();
			
			// instanciation du client
			Lecon uneLecon = new Lecon (idl, datel, dureel,typel);
			ModelLecon.updateLecon(uneLecon);
			JOptionPane.showMessageDialog(this, "Modification reussie !");
		
			}	
		}
		
		else if (e.getSource() == this.btAnnuler)
		{
			this.txtidl.setText("");
			this.txtDatel.setText("");
			this.txtDureel.setText("");
			
		
		}
	}
	
	private Object[][] remplirDonnes() 
	{
		ArrayList<Lecon> lesLecons = ModelLecon.selectAllLecons();
		Object donnes [] [] = new Object [lesLecons.size()] [4];
		int i = 0;
		for (Lecon uneLecon : lesLecons)
		{
			donnes [i] [0] = uneLecon.getIdl() + "";
			donnes [i] [1] = uneLecon.getDatel();
			donnes [i] [2] = uneLecon.getDureel();
			donnes [i] [3] = uneLecon.getTypel();
			i++;
		}
		return donnes;
	}
	
}