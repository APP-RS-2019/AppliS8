package action;

import java.util.HashSet;
import java.util.Iterator;

public class Scenario {

	private HashSet<ActionToDo> data;
	public static boolean connexion = false;
	public static long maxT = 0;

	public Scenario(){
		this.data = new HashSet<ActionToDo>();
	}

	public Scenario(HashSet<ActionToDo> data){
		this.data = data;
	}

	public HashSet<ActionToDo> getData() {
		return data;
	}

	public void addAction(ActionToDo action) {
		this.data.add(action);
	}
	
	public void maxTime() {
		Iterator<ActionToDo> i = this.data.iterator();
		while (i.hasNext()) {
			ActionToDo action = i.next();
			if(action.getTime()>maxT) {
				maxT = action.getTime();
			}
		}
	}
	
	public synchronized void activation(ActionToDo action) throws InterruptedException {
		action.running = true;
		System.out.println("Activation of : " + action.getName());
		notifyAll();
	}
	
	public synchronized void desactivation(ActionToDo action) throws InterruptedException {
		action.running = false;
		System.out.println("Desactivation of : " + action.getName());
		notifyAll();
	}
	
	public void execute()  {
		Iterator<ActionToDo> i = this.data.iterator();
		System.out.println("Début du scénario avec " + this.data.size() + " d'actions\n");
		long time = 0;
		while (i.hasNext()) {
			ActionToDo action = i.next();
			
			try {
				Thread.sleep((action.time-time)*1000);
				time = action.time;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			action.process();
		}
		System.out.println("Fin du scénario\n");
	}
	
	/*public void execute() throws InterruptedException {
		Iterator<ActionToDo> i = this.data.iterator();
		System.out.println("Début du scénario avec " + this.data.size() + " d'actions");
		this.maxTime();
		while (i.hasNext()) {
			ActionToDo action = i.next();
			
			if(!action.isAlive()) {
				action.start();
			}
			
			this.activation(action);
			
			Thread.sleep(maxT*1000+1000);
			
			this.desactivation(action);

			System.out.println("Fin du scénario");
		}
	}*/
}
