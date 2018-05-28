package controleur;

public class Moniteur 
{
	private int idm ;
	private String nomm, prenomm, adressem, salaire, embauche;
	
	public Moniteur ()
	{
		this.idm= 0 ;
		this.nomm="";
		this.prenomm="";
		this.adressem="";
		this.salaire="";
		this.embauche="";
	}
	
	public Moniteur ( int idm, String nomm, String prenomm, String adressem, String salaire, String embauche)
	{
		this.idm= idm;
		this.nomm= nomm;
		this.prenomm= prenomm;
		this.adressem= adressem;
		this.salaire= salaire;
		this.embauche= embauche;
	}
	
	public Moniteur ( String nomm, String prenom, String adressem, String salaire, String embauche)
	{
		this.idm=0;
		this.nomm= nomm;
		this.prenomm= prenomm;
		this.adressem= adressem;
		this.salaire= salaire;
		this.embauche= embauche;
	
	}
	
	public int getIdm() {
		return idm;
	}

	public void setIdm(int idm) {
		this.idm = idm;
	}

	public String getNomm() {
		return nomm;
	}

	public void setNomm(String nomm) {
		this.nomm = nomm;
	}

	public String getPrenomm() {
		return prenomm;
	}

	public void setPrenomm(String prenomm) {
		this.prenomm = prenomm;
	}

	public String getAdressem() {
		return adressem;
	}

	public void setAdressem(String adressem) {
		this.adressem = adressem;
	}

	public String getSalaire() {
		return salaire;
	}

	public void setSalaire(String salaire) {
		this.salaire = salaire;
	}

	public String getEmbauche() {
		return embauche;
	}

	public void setEmbauche(String embauche) {
		this.embauche = embauche;
	}
	
}
