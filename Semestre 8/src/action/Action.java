package action;

import java.io.Serializable;

public class Action implements Serializable{
	
	private String idaction;
	private String nom;
	private String description;
	
	public Action (String idaction, String nom, String description)
	{
		this.idaction=idaction;
		this.nom=nom;
		this.description = description;
	}
	
	public String getAction()
	{
		return this.idaction;
	}
	
	public String getNom()
	{
		return this.nom;
	}
	
	public String getDescription()
	{
		return this.description;
	}

	public void setAction(String idaction) {
		this.idaction = idaction;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
