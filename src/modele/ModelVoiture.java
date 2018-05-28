package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Voiture;


public class ModelVoiture 
{

/***************Modele Voiture ********/ 
	
	
	

	public static ArrayList<Voiture> selectAllVoitures()
	{
		ArrayList<Voiture> lesVoitures = new ArrayList<Voiture>();
		String requete = "select * from voiture;";
		Bdd uneBdd = new Bdd("localhost:8889","youcef", "root", "root");
		try 
			{
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnection().createStatement();
				ResultSet unRes = unStat.executeQuery(requete);
		while (unRes.next())
			{
			int idv =unRes.getInt("idv");
			String marquev =unRes.getString("marquev");
			String modelv= unRes.getString("modelv"); 
			String   matriculev =unRes.getString("matriculev");
			String anneev =unRes.getString("anneev");
			String  nbkm_v =unRes.getString("nbkm_v");
			
			Voiture uneVoiture = new Voiture (idv, marquev, modelv, matriculev, anneev, nbkm_v );
			lesVoitures.add(uneVoiture);
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
	return lesVoitures;
	}
	
	 public static Voiture selectWhereVoiture (Voiture uneVoiture)
	{
		Voiture laVoiture = null;
		String requete ="select idv from voiture "
				+ " where marquev ='"+uneVoiture.getMarquev()
				+ "'  and  matriculev ='" + uneVoiture.getMatriculev()
				+ "' and anneev ='" + uneVoiture.getAnneev()+"'; ";
		
		Bdd uneBdd = new Bdd("localhost:8889", "youcef","root","root");
		 
		try 
		{
		uneBdd.seConnecter();
		Statement unStat = uneBdd.getMaConnection().createStatement();
		ResultSet unRes = unStat.executeQuery(requete);
		if (unRes.next())
		{
			int idvoiture = unRes.getInt("idv");
			laVoiture = new Voiture (idvoiture, uneVoiture.getMarquev(), uneVoiture.getModelv(), uneVoiture.getMatriculev(), uneVoiture.getAnneev(), uneVoiture.getNbkm_v()) ;
		}
		unStat.close();
		unRes.close();
		uneBdd.seDeConnecter();
	}
	catch (SQLException exp)
	{
		System.out.println("Erreur :"+requete);
	}
	return laVoiture;
	}
	
	
	public static void insertVoiture (Voiture uneVoiture)
	{
		String requete = "insert into voiture  values (null,'"
				  +uneVoiture.getMarquev()+"','"
				  +uneVoiture.getModelv()+"','"
				  +uneVoiture.getMatriculev()+"','"
				  +uneVoiture.getAnneev()+"','"
				  +uneVoiture.getNbkm_v()+"');";
	execRequete(requete);
	}
	

	public static void updateVoiture (Voiture uneVoiture)
	{
		String requete = "update voiture set marquev ='" +
				uneVoiture.getMarquev()+"', modelv = '"+
				uneVoiture.getModelv()+"', matriculev = '"+
				uneVoiture.getMatriculev()+"', anneev = '"+
				uneVoiture.getAnneev()+"', nbkm_v = '"+
				uneVoiture.getNbkm_v()+"'  where idv =" +
				 +uneVoiture.getIdv()+";";
		execRequete(requete);		
	}

	
	public static void deleteVoiture(Voiture uneVoiture)
	{
		String requete = "delete from  voiture where idv =" 
				+ uneVoiture.getIdv()+";";
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

