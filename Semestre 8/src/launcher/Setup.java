package launcher;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;

import graphique.Frame;
import upperClass.Syst;

public class Setup {
	
	
	public static void main(String[] args) throws Exception{
		//Définition du chemin ou les données seront enregistrées
		String userHome = System.getProperty("user.home");
		File Rep = new File(userHome+"/Desktop/Projet_INFO742");
		File fichier =  new File(userHome+"/Desktop/APP/save");
		//Si les fichiers de données n'existent pas alors on les crée et rempli
		if(!fichier.exists()) {
			fichier.mkdirs();
			InitData.actived();
		}

		//S'ils existent une versions lisibles des fichiers alors on les ajoute
		
		else {
			Syst syst = new Syst();
			syst.update();
		}
		Frame frame=new Frame();
	}

}

