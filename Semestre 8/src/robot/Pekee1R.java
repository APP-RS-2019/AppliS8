package robot;
import action.Action;

public class Pekee1R extends Robot{
	public Pekee1R(String idRobot, String name){
		super(idRobot, name);
	}
	
	Pekee1R(String idRobot, String name, float posx, float posy, float angle){
		super( idRobot,  name,  posx,  posy, angle);
	}

	@Override
	public void doAction(Action a) {
		// TODO Auto-generated method stub		
	}
	
	private void elephant(){
	}
	
}
