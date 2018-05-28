package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Lecon;

public class ModelLecon 
{

	/******** Modele Lecon ********/
	
	
	public static ArrayList<Lecon> selectAllLecons()
	{
		ArrayList<Lecon> lesLecons = new ArrayList<Lecon>();
		String requete = "select * from lecon;";
		Bdd uneBdd = new Bdd ("localhost:8889","youcef", "root", "root");
		try 
			{
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnection().createStatement();
				ResultSet unRes = unStat.executeQuery(requete);
		while (unRes.next())
			{
				int idl = unRes.getInt("idl");
				String  datel = unRes.getString("datel");
				String dureel = unRes.getString("dureel");
				String typel = unRes.getString("typel");
						
			Lecon uneLecon = new Lecon(idl, datel, dureel, typel);
			lesLecons.add(uneLecon);
			}
				unStat.close();
				unRes.close();
				uneBdd.seDeConnecter();
			}
		catch (SQLException exp)
			{
				System.out.println("Erreur : " + requete);
	
			}
	return lesLecons;
	}
	public static Lecon selectWhereLecon (Lecon uneLecon)
	{
		Lecon laLecon = null;
		String requete ="select idl from lecon "
				+ " where datel ='"+uneLecon.getDatel()
				+ "' and dureel ='" + uneLecon.getDureel()
				+"' and typel ='"+uneLecon.getTypel()
				+"'; ";
		Bdd uneBdd = new Bdd("localhost:8889", "youcef","root","root");
		try 
		{
		uneBdd.seConnecter();
		Statement unStat = uneBdd.getMaConnection().createStatement();
		ResultSet unRes = unStat.executeQuery(requete);
		if (unRes.next())
		{
			int idl = unRes.getInt("idl");
			laLecon = new Lecon (idl, uneLecon.getDatel(),
					uneLecon.getDureel(), uneLecon.getTypel());
		}
		unStat.close();
		unRes.close();
		uneBdd.seDeConnecter();
	}
	catch (SQLException exp)
	{
		System.out.println("Erreur :"+requete);
	}
	return laLecon;
	}
	public static void insertLecon (Lecon uneLecon)
	{
		String requete = "insert into lecon values (null'"  
				+uneLecon.getDatel()+"','"
				  +uneLecon.getDureel()+"','"
				 +uneLecon.getTypel()+"');";
	execRequete(requete);	
	}
	
	public static void updateLecon (Lecon uneLecon)
	{
		String requete = "update lecon set datel ='" +
				uneLecon.getDatel()+"', dureel = '"+
				uneLecon.getDureel()+"', typel = '"+
				uneLecon.getTypel()+"'  where idl =" + 
					+ uneLecon.getIdl()+";";
		execRequete(requete);
		
	}
	public static void deleteLecon (Lecon uneLecon)
	{
		String requete = "delete from  lecon where idl =" 
				+ uneLecon.getIdl()+";";
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
