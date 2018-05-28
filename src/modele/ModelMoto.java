package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Moto;

public class ModelMoto 
{

/***************Modele Moto ********/ 
	
	
	

	public static ArrayList<Moto> selectAllMotos()
	{
		ArrayList<Moto> lesMotos = new ArrayList<Moto>();
		String requete = "select * from moto;";
		Bdd uneBdd = new Bdd("localhost:8889","youcef", "root", "root");
		try 
			{
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnection().createStatement();
				ResultSet unRes = unStat.executeQuery(requete);
		while (unRes.next())
			{
			int idmoto = unRes.getInt("idmoto");
			String modele = unRes.getString("Modele"); 
			String marquem = unRes.getString("Marquem");
			String anneem = unRes.getString("Anneem");
			String nbkm_m = unRes.getString("Nbkm_m");
			String matriculem = unRes.getString("Matriculem");
		
			Moto uneMoto = new Moto (idmoto, modele, marquem, anneem, nbkm_m, matriculem);
			lesMotos.add(uneMoto);
			
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
	return lesMotos;
	}
	
	 public static Moto selectWhereMoto (Moto uneMoto)
	{
		Moto laMoto = null;
		String requete ="select idmoto from moto "
				+ " where marquem ='"+uneMoto.getMarquem()
				+ "'  and  matricule ='" + uneMoto.getMatriculem()
				+ "' and anneem ='" + uneMoto.getAnneem()+"'; ";
		
		Bdd uneBdd = new Bdd("localhost:8889", "youcef","root","root");
		try 
		{
		uneBdd.seConnecter();
		Statement unStat = uneBdd.getMaConnection().createStatement();
		ResultSet unRes = unStat.executeQuery(requete);
		if (unRes.next())
		{
			int idmoto = unRes.getInt("idmoto");
			laMoto = new Moto (idmoto, uneMoto.getModele(), uneMoto.getMarquem(),  uneMoto.getAnneem(), uneMoto.getNbkm_m(), uneMoto.getMatriculem()) ;
		}
		unStat.close();
		unRes.close();
		uneBdd.seDeConnecter();
	}
	catch (SQLException exp)
	{
		System.out.println("Erreur :"+requete);
	}
	return laMoto;
	}
	
	
	public static void insertMoto (Moto uneMoto)
	{
		String requete = "insert into moto  values (null,'"
				 +uneMoto.getModele()+"','" 
				 +uneMoto.getMarquem()+"','"
				 +uneMoto.getMatriculem()+"','"
				 +uneMoto.getNbkm_m()+  "','"
				 +uneMoto.getAnneem()+"');";
	execRequete(requete);
	}
	

	public static void updateMoto (Moto uneMoto)
	{
		String requete = "update moto set modele ='" +
				uneMoto.getMarquem()+"', marquem = '"+
				uneMoto.getMatriculem()+"', anneem = '"+
				uneMoto.getAnneem()+"', nbkm_m = '"+
				uneMoto.getModele()+"', matriculem = '"+
				uneMoto.getNbkm_m()+"'  where idmoto =" +
				 +uneMoto.getIdmoto()+";";
		execRequete(requete);		
	}

	
	public static void deleteMoto(Moto uneMoto)
	{
		String requete = "delete from  moto where idmoto =" 
				+ uneMoto.getIdmoto()+";";
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

