package controleur;

import java.util.ArrayList;

public class Voiture 
{
	private int idv;
	private String marquev, modelv, matriculev, nbkm_v, anneev;
	
	public Voiture ()
	{
		this.idv = 0;
		this.marquev= "";
		this.modelv="";
		this.matriculev="";
		this.anneev= "";
		this.nbkm_v= "";	
	}
	
	public Voiture (int idv, String marquev, String modelv, String matriculev, String anneev, String  nbkm_v)
	{
		this.idv = idv ;
		this.marquev= marquev;
		this.modelv= modelv;
		this.matriculev= matriculev;
		this.anneev= anneev;
		this.nbkm_v= nbkm_v;	
	}
	
	public Voiture ( String marquev, String modelv, String matriculev, String anneev, String  nbkm_v) 
	{
		this.idv = 0 ;
		this.marquev= marquev;
		this.modelv= modelv;
		this.matriculev= matriculev;
		this.anneev= anneev;
		this.nbkm_v= nbkm_v;	

	}
	

	
	public int getIdv() {
		return idv;
	}

	public void setIdv(int idv) {
		this.idv = idv;
	}

	public String getMarquev() {
		return marquev;
	}

	public void setMarquev(String marquev) {
		this.marquev = marquev;
	}

	public String getModelv() {
		return modelv;
	}

	public void setModelv(String modelv) {
		this.modelv = modelv;
	}

	public String getMatriculev() {
		return matriculev;
	}

	public void setMatriculev(String matriculev) {
		this.matriculev = matriculev;
	}

	public String getNbkm_v() {
		return nbkm_v;
	}

	public void setNbkm_v(String nbkm_v) {
		this.nbkm_v = nbkm_v;
	}

	public String getAnneev() {
		return anneev;
	}

	public void setAnneev(String anneev) {
		this.anneev = anneev;
	}

}