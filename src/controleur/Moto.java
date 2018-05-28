package controleur;

import java.util.ArrayList;

public class Moto 
{
	private int idmoto;
	private String marquem, modele, matriculem, nbkm_m, anneem;
	
	

	public Moto ()
	{
		this.idmoto = 0;
		this.marquem= "";
		this.modele="";
		this.matriculem="";
		this.anneem= "";
		this.nbkm_m= "";	
	}
	
	public Moto (int idmoto, String marquem, String modele, String matriculem, String nbkm_m, String anneem )
	{
		this.idmoto = idmoto ;
		this.marquem= marquem;
		this.modele= modele;
		this.matriculem= matriculem;
		this.nbkm_m= nbkm_m;	
		this.anneem= anneem;
	
	}
	
	public Moto ( String marquem, String modele, String matriculem, String nbkm_m, String anneem ) 
	{
		this.idmoto=0;
		this.marquem= marquem;
		this.modele= modele;
		this.matriculem= matriculem;
		this.nbkm_m= nbkm_m;	
		this.anneem= anneem;
		
	}
	

	
	public int getIdmoto() {
		return idmoto;
	}

	public void setIdmoto(int idmoto) {
		this.idmoto = idmoto;
	}

	public String getMarquem() {
		return marquem;
	}

	public void setMarquem(String marquem) {
		this.marquem = marquem;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public String getMatriculem() {
		return matriculem;
	}

	public void setMatriculem(String matriculem) {
		this.matriculem = matriculem;
	}

	public String getNbkm_m() {
		return nbkm_m;
	}

	public void setNbkm_m(String nbkm_m) {
		this.nbkm_m = nbkm_m;
	}

	public String getAnneem() {
		return anneem;
	}

	public void setAnneem(String anneem) {
		this.anneem = anneem;
	}
}
