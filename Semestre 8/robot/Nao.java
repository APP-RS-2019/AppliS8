package robot;
import action.Action;

public class Nao extends Robot{
	
	public Nao(String idRobot, String name){
		super(idRobot, name);
	}
	
	public Nao(String idRobot, String name, float posx, float posy, float angle){
		super( idRobot,  name,  posx,  posy, angle);
	}

	@Override
	public void doAction(Action a) {
		switch(a.getNom()){
		case "elephant":
			//server.send(this.name,"elephant");
			break;
			
		case "avance":
			//server.send(this.name,"elephant");
			break;
			
		case "recule":
			//server.send(this.name,"recule");
			break;
			
		case "demitour":
			//server.send(this.name,"demitour");
			break;
			
		case "parler":
			//server.send(this.name,"parler-message");
			break;
		}
		}	
}
