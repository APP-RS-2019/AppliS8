package robot;
import java.io.Serializable;
import action.Action;
import upperClass.ClientSocket;


public class Pepper extends Robot implements Serializable{

	public Pepper(String idRobot, String name){
		super(idRobot, name);
	}

	public Pepper(String idRobot, String name, double posx, double posy, double angle){
		super( idRobot,  name,  posx,  posy, angle);
	}

	@Override
	public void doAction(Action a) {
		ClientSocket server = null;
		try {
			server = new ClientSocket("193.48.125.71",80,"Pepper");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("Erreur de connexion au serveur");
		}
		switch(a.getNom()){
		case "elephant":
			server.sendOrder(this.name,"elephant");
			break;
		case "avance":
			server.sendOrder(this.name,"avance");
			break;

		case "recule":
			server.sendOrder(this.name,"recule");
			break;

		case "demitour":
			server.sendOrder(this.name,"demitour");
			break;

		case "parler":
			server.sendOrder(this.name,"parler-message");
			break;
		}
	}
}
