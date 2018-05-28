package controleur;

public class Lecon 
{
	private int idl;
	private String datel, dureel, typel;
	
	public Lecon ()
	{
		this.idl = 0;
		this.datel ="";
		this.dureel = "";
		this.typel ="";
	}
	
	public Lecon (int idl, String datel, String dureel, String typel )
	{
		this.idl = idl;
		this.datel = datel ;
		this.dureel = dureel;
		this.typel = typel ;
	}
	public Lecon (String datel, String dureel, String typel )
	{
		this.idl=0;
		this.datel = datel ;
		this.dureel = dureel;
		this.typel = typel ;
	}
	public Lecon (int idl, String datel, String dureel)
	{
		this.idl = idl;
		this.datel = datel ;
		this.dureel = dureel;
		this.typel = "" ;
	}

	public int getIdl() {
		return idl;
	}

	public void setIdl(int idl) {
		this.idl = idl;
	}

	public String getDatel() {
		return datel;
	}

	public void setDatel(String datel) {
		this.datel = datel;
	}

	public String getDureel() {
		return dureel;
	}

	public void setDureel(String dureel) {
		this.dureel = dureel;
	}

	public String getTypel() {
		return typel;
	}

	public void setTypel(String typel) {
		this.typel = typel;
	}
}