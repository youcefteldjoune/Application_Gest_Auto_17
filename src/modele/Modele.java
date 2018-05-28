package modele;

import java.sql.Statement;
import java.util.ArrayList;

import javax.security.sasl.SaslException;

import controleur.Client;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Modele 
{
	public static String	verifConnexion (String login , String mdp)
	{
		String droits ="";
		String requete ="Select count(*) as nb, droit " 
				+" from user"
				+ " Where login ='" + login +"' and "
				+ " mdp = '" + mdp + "' ;";
		Bdd uneBdd = new Bdd ("localhost:8889", "youcef", "root", "root");
		try
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnection().createStatement();
			ResultSet unRes = unStat.executeQuery(requete);
			if (unRes.next ())
			{
				int nb = unRes.getInt("nb");
				if (nb > 0 ) 
				{
					droits = unRes.getString("droit");
				}
				unStat.close();
				unRes.close();
				uneBdd.seDeConnecter();
			}
		}
		catch(SQLException exp)
		{
			System.out.println("Erreur : " + requete);
		}
		catch(NullPointerException exp)
		{
			System.out.println("Erreur Connexion BDD non fonctionnelle!");
		}
		return droits;
	}
	/***************Modele Client ********/ 
	
	
	
	public static ArrayList<Client> selectAllClients()
	{
		ArrayList<Client> lesClients = new ArrayList<Client>();
		String requete = "select * from client;";
		Bdd uneBdd = new Bdd ("localhost:8889","youcef", "root", "root");
		try 
			{
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnection().createStatement();
				ResultSet unRes = unStat.executeQuery(requete);
		while (unRes.next())
			{
				int id = unRes.getInt("id");
				String nom = unRes.getString("nom");
				String prenom = unRes.getString("prenom");
			String email = unRes.getString("email");
			Client unClient = new Client(id, nom, prenom, email);
			lesClients.add(unClient);
			}
				unStat.close();
				unRes.close();
				uneBdd.seDeConnecter();
			}
		catch (SQLException exp)
			{
				System.out.println("Erreur : " + requete);
	
			}
	return lesClients;
	}
	public static Client selectWhereClient (Client unClient)
	{
		Client leClient = null;
		String requete ="select id from client "
				+ " where nom ='"+unClient.getNom()
				+ "' and prenom ='" + unClient.getPrenom()
				+"' and email ='"+unClient.getEmail()
				+"'; ";
		Bdd uneBdd = new Bdd("localhost:8889", "youcef","root","root");
		 try
		{
		uneBdd.seConnecter();
		Statement unStat = uneBdd.getMaConnection().createStatement();
		ResultSet unRes = unStat.executeQuery(requete);
		if (unRes.next())
		{
			int idclient = unRes.getInt("id");
			leClient = new Client (idclient, unClient.getNom(),
					unClient.getPrenom(), unClient.getEmail());
		}
		unStat.close();
		unRes.close();
		uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur :"+requete);
		}
	return leClient;
	}
	public static void insertClient (Client unClient)
	{
		String requete = "insert into client values (null,'"
				  +unClient.getNom()+"','"
				  +unClient.getPrenom()+"','"
				 +unClient.getEmail()+"','');";
			execRequete(requete);
		
		
	}
	public static void updateClient (Client unClient)
	{
		String requete = "update client set nom ='" +
				unClient.getNom()+"', prenom = '"+
				unClient.getPrenom()+"', email = '"+
				unClient.getEmail()+"'  where id =" + 
					+ unClient.getId()+";";
		execRequete(requete);
		
	}
	public static void deleteClient (Client unClient)
	{
		String requete = "delete from  client where id =" 
				+ unClient.getId()+";";
		execRequete(requete);
		
	} 
	private static void execRequete (String requete)
	{
		Bdd uneBdd = new Bdd ("localhost:8889","youcef", "root", "root");
		try 
			{
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnection().createStatement();
				unStat.execute(requete);
				unStat.close();
				uneBdd.seDeConnecter();			
	}
		catch(SQLException exp)
		{
			System.out.println("Erreru :"+ requete);
		}
	}

}
