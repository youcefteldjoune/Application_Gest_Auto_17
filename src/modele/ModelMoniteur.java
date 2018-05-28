package modele;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Client;
import controleur.Moniteur;

public class ModelMoniteur 
{

/***************Modele Moniteur ********/ 
	
	
	
	public static ArrayList<Moniteur> selectAllMoniteurs()
	{
		ArrayList<Moniteur> lesMoniteurs = new ArrayList<Moniteur>();
		String requete = "select * from moniteur;";
		Bdd uneBdd = new Bdd ("localhost:8889","youcef", "root", "root");
		try 
			{
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnection().createStatement();
				ResultSet unRes = unStat.executeQuery(requete);
		while (unRes.next())
			{
				
			int idm = unRes.getInt("idm");
			String nomm = unRes.getString("nomm");
			String prenom = unRes.getString("prenomm");
			String adressem = unRes.getString("adressem");
			String salaire = unRes.getString("salaire");
			String embauche = unRes.getString("embauche");
			Moniteur unMoniteur = new Moniteur (idm , nomm, prenom, adressem ,salaire, embauche);
			lesMoniteurs.add(unMoniteur);
			}
				unStat.close();
				unRes.close();
				uneBdd.seDeConnecter();
			}
		catch (SQLException exp)
			{
				System.out.println("Erreur : " + requete);
				exp.printStackTrace();
			}
	return lesMoniteurs;
	}
	public static Moniteur selectWhereMoniteur (Moniteur unMoniteur)
	{
		Moniteur leMoniteur = null;
		String requete ="select idm from moniteur "
				+ " where nomm ='"+unMoniteur.getNomm()
				+ "' and prenomm ='" + unMoniteur.getPrenomm()
				+"' and adressem ='"+unMoniteur.getAdressem()
				+"' and salaire ='"+unMoniteur.getSalaire()
				+"' and embauche ='"+unMoniteur.getEmbauche()
				+"'; ";
		Bdd uneBdd = new Bdd("localhost:8889", "youcef","root","root");
		try 
		{
		uneBdd.seConnecter();
		Statement unStat = uneBdd.getMaConnection().createStatement();
		ResultSet unRes = unStat.executeQuery(requete);
		if (unRes.next())
		{
			int idm = unRes.getInt("idm");
			leMoniteur = new Moniteur (idm , unMoniteur.getNomm(), unMoniteur.getPrenomm(),  unMoniteur.getAdressem() ,unMoniteur.getSalaire(), unMoniteur.getEmbauche());
		}
		unStat.close();
		unRes.close();
		uneBdd.seDeConnecter();
	}
	catch (SQLException exp)
	{
		System.out.println("Erreur :"+requete);
	}
	return leMoniteur;
	}
	
	public static void insertMoniteur (Moniteur unMoniteur)
	{
		String requete = "insert into moniteur values (null,'"
				  +unMoniteur.getNomm()+"','"
				  +unMoniteur.getPrenomm()+"','"
				 +unMoniteur.getAdressem()+"','"
				 +unMoniteur.getEmbauche()+"','"
				+unMoniteur.getSalaire()+"','');";
	execRequete(requete);
		
		
	}
	public static void updateMoniteur (Moniteur unMoniteur)
	{
		String requete = "update moniteur set = nomm ='" +
				unMoniteur.getNomm() + "', prenomm = '"+
				unMoniteur.getPrenomm() + "', adressem = '"+
				unMoniteur.getAdressem() + "', embauche = '"+
				unMoniteur.getEmbauche() + "', salaire = '"+
				unMoniteur.getSalaire() +"'  where idm =" +
				+ unMoniteur.getIdm()+";";
		execRequete(requete);
		
	}

	
	public static void deleteMoniteur (Moniteur unMoniteur)
	{
		String requete = "delete from  moniteur where idm =" 
				+ unMoniteur.getIdm()+";";
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
			exp.printStackTrace();
		}
	}
}
