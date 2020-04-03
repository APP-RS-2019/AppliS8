package robot;
import action.Action;

public class Robotino extends Robot{

	Robotino(String idRobot, String name){
		super(idRobot, name);
	}
	
	Robotino(String idRobot, String name, float posx, float posy, float angle){
		super( idRobot,  name,  posx,  posy, angle);
	}

	@Override
	public void doAction(Action a) {
		// TODO Auto-generated method stub		
	}
	
	private void avance(){
	}
}
