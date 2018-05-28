package vue;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controleur.Main;
import modele.Modele;

public class VueConnexion extends JFrame implements ActionListener, KeyListener
{
	private JPanel unPanel = new JPanel();
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btSeConnecter = new JButton("Se Connecter");
	
	private JTextField txtLogin = new JTextField();
	private JPasswordField txtMdp = new JPasswordField();
	
	
	public VueConnexion()
	{
		this.setTitle("AUTO 17 GESTION");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setBounds(200, 200, 600, 400);
		this.getContentPane().setBackground(Color.white);
		
		//constuction de panel
		this.unPanel.setBounds(50, 200, 500, 150);
		this.unPanel.setBackground(Color.white);
		this.unPanel.setLayout(new GridLayout(3,2)); // 3lignes 2Colones
		this.unPanel.add(new JLabel("Login : "));
		this.unPanel.add(txtLogin);
		this.unPanel.add(new JLabel("MDP : "));
		this.unPanel.add(txtMdp);
		this.unPanel.add(btAnnuler);
		this.unPanel.add(btSeConnecter);
		
		// Ajouter le panel dans la fenettre 
		this.add(this.unPanel);
		
		// ajout image logo dans la fenetre
		ImageIcon logo = new ImageIcon("src/images/auto17logo.png");
		JLabel lblogo = new JLabel(logo);
		lblogo.setBounds(50, 20, 500, 150);
		this.add(lblogo);
		
		//Rendre les buton cliquable
		this.btAnnuler.addActionListener(this);
		this.btSeConnecter.addActionListener(this);
		this.txtLogin.addKeyListener(this);
		this.txtMdp.addKeyListener(this);
		
		
		this.setVisible(true);

	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == this.btAnnuler)
		{
			this.txtLogin.setText("");;
			this.txtMdp.setText("");
		}
		else if (e.getSource() == this.btSeConnecter)
		{
			traitement();
			
		}
		
		
	}
	
	public void traitement ()
	{
		String login = this.txtLogin.getText();
		String mdp = new String (this.txtMdp.getPassword());
		// verification des identification dans la bdd
		String droits = Modele.verifConnexion(login, mdp);
		if (droits.equals(""))
		{
			JOptionPane.showMessageDialog(this, "Veuillez vérifier vos identifiants");
		}
		else 
		{
			JOptionPane.showConfirmDialog(this, "Bienvenu ! \n" + "Vos droits sont :" + droits);
			// Démarrage du logiciel 
			new VueGenerale(); 
			Main.rendreVisible(false); // methode static 
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getKeyChar() == KeyEvent.VK_ENTER)
		{
			traitement();
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
