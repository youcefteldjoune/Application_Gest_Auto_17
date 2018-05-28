package controleur;

public class Client 
{
	private int id;
	private String nom, prenom, email, mdp;
	
	public Client()
	{
		this.id= 0;
		this.nom="";
		this.prenom= "";
		this.email= "";
		this.mdp= "";
	}
	public Client (int id, String nom, String prenom, String email, String mdp)
	{
		this.id= id;
		this.nom= nom;
		this.prenom= prenom;
		this.email= email;
		this.mdp= mdp;
	}
	public Client ( String nom , String prenom, String email, String mdp)
	{
		this.id= 0;
		this.nom=nom;
		this.prenom=prenom;
		this.email= email;
		this.mdp= mdp;
	}
	public Client ( int id, String nom , String prenom, String email)
	{
		this.id= id;
		this.nom=nom;
		this.prenom=prenom;
		this.email= email;
		this.mdp= "";
	}
	public int getId() 
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}
	public String getNom() 
	{
		return nom;
	}
	public void setNom(String nom) 
	{
		this.nom = nom;
	}
	public String getPrenom() 
	{
		return prenom;
	}
	public void setPrenom(String prenom) 
	{
		this.prenom = prenom;
	}
	public String getEmail() 
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	public String getMdp() 
	{
		return mdp;
	}
	public void setMdp(String mdp) 
	{
		this.mdp = mdp;
	}
}
