package action;

public class ActionToDo extends Thread{

	private String nameRobot;
	private String nameAction;
	public long time;
	public boolean running;

	public ActionToDo(String nameRobot, String nameAction, long time) {
		this.nameRobot = nameRobot;
		this.nameAction = nameAction;
		this.time = time;
		this.running = false;
	}

	public String getNameRobot() {
		return nameRobot;
	}

	public void setNameRobot(String nameRobot) {
		this.nameRobot = nameRobot;
	}

	public String getNameAction() {
		return nameAction;
	}

	public void setNameAction(String nameAction) {
		this.nameAction = nameAction;
	}

	public long getTime() {
		return this.time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public synchronized boolean checkActivation() throws InterruptedException {
		while(!this.running) {
			System.out.println("Wait of : " + this.nameRobot);
			wait();
		}
		notifyAll();
		return this.running;
	}
	
	public void process() {
		
		if(Scenario.connexion) {
			System.out.println("Le robot : " + this.nameRobot + " effectue l'action " + this.nameAction + " au bout de " + this.time + "s\n");
			// Action non simulée
		}
		if(!Scenario.connexion) {
			System.out.println("Le robot : " + this.nameRobot + " effectue l'action simulée " + this.nameAction + " au bout de " + this.time + "s\n");
			// Action simulée
		}
	}
	
	public boolean getRunning() {
		return this.running;
	}
	public void activeRuning() {
		this.running=true;
	}
	
	/*public void run() {
		while(true) {
			try {
				if(this.checkActivation()) {
					System.err.println("passage");
					try {
						Thread.sleep(this.getTime()*1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					if(Scenario.connexion) {
						System.out.println("Le robot : " + this.nameRobot + " effectue l'action " + this.nameAction + " au bout de " + this.time + " s");
						// Action non simulée
					}
					if(!Scenario.connexion) {
						System.out.println("Le robot : " + this.nameRobot + " effectue l'action simulée " + this.nameAction + " au bout de " + this.time + " s");
						// Action simulée
					}
				}
				
				try {
					Thread.sleep(1000*(Scenario.maxT-this.getTime()+1));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}*/
}
