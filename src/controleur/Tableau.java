package controleur;

import javax.swing.table.AbstractTableModel;

public class Tableau extends AbstractTableModel
{
	private Object [] [] donnees; // matrice des données 
	private String entete []; // entetes des colonnes
	
	public Tableau (Object donnes [] [], String entete []) 
	{
		this.donnees = donnes ;
		this.entete = entete;
		
	}
	
	
	@Override
	public int getRowCount() 
	{
		
		return this.donnees.length; // nombre des lignes de la matrices 
	}

	@Override
	public int getColumnCount() 
	{
		
		return this.entete.length; // nombre de colonnes de l'entete 
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) 
	{
		
		return this.donnees[rowIndex][columnIndex];// retoutner l'element de la matrice d'indices rowIndex, columnIndex 
	}
	public String getColumnName(int columnIndex)
	{
		return this.entete[columnIndex];
	}
	
	public void add(Object ligne[])
	{
		Object newTable [] [] = new Object [this.donnees.length + 1] [this.entete.length];
		// recopie de la matrice dans la nouvelle matrice 
		 
		for (int i = 0; i < this.donnees.length; i++)
		{
			newTable[i] = this.donnees[i];
		}
		// ajout de la ligne a la fin de la table 
		newTable[this.donnees.length] = ligne;
		//mise a jour de la matrice
		this.donnees = newTable;
		// mise a jour du graphique 
		this.fireTableDataChanged();
	}
	public void delete (int rowIndex)
	{
		Object newTable [] [] = new Object [this.donnees.length -1] [this.entete.length];
		// recopie de la matrice dans la nouvelle matrice 
		 int j =0;
		for (int i = 0; i < this.donnees.length; i++)
		{
			if (i != rowIndex)
				{
					newTable[j] = this.donnees[i];
					j++ ;
				}
		}
		
		//mise a jour de la matrice
		this.donnees = newTable;
		// mise a jour du graphique 
		this.fireTableDataChanged();
	}
	public void update (int rowIndex, Object ligne [])// recevoir le num ligne et la updater 
	{
		Object newTable [] [] = new Object [this.donnees.length] [this.entete.length];
		// recopie de la matrice dans la nouvelle matrice 
		 
		for (int i = 0; i < this.donnees.length; i++)
		{
			if (i == rowIndex)
				{
				newTable[i] = ligne;
				}else {
					newTable[i] = this.donnees[i];
					}
		}
		
		//mise a jour de la matrice
		this.donnees = newTable;
		// mise a jour du graphique 
		this.fireTableDataChanged();
	}
}
