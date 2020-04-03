package graphique;

import java.awt.*;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import action.ActionToDo;

import action.Scenario;

public class CanvasSimulation extends Canvas{
	private int time;
	private Scenario scenario;

	public CanvasSimulation(Scenario scenario) {
		this.scenario=scenario;
		this.setBackground(Color.LIGHT_GRAY);
		this.time=0;

	}
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.drawRect(0, 0, 1879, 424);
		//xi,yi,xf,yf
		g.drawLine(9,30, 9, 80); g.drawLine(10, 55, 1810, 55);
		g.drawLine(9,135, 9, 185);g.drawLine(10, 160, 1810, 160);
		g.drawLine(9, 240, 9, 290);g.drawLine(10, 265, 1810, 265);
		g.drawLine(9, 345, 9, 395);g.drawLine(10, 370, 1810, 370);

		//affichage de la barre temps
		g.setColor(Color.white);
		g.fillRect(time+1, 1, 3, 423);
		paintAction(g);
	}

	public void evolution() throws InterruptedException {
		time++;
		this.repaint();
	}
	public void paintAction(Graphics g) {
		HashSet<ActionToDo> act=scenario.getData();
		for (ActionToDo action : act) {
			//switch(action.getNameRobot()) {
			if(action.getNameRobot().equals("Pepper")){
				if (time>=action.getTime()*20+50) {
					g.setColor(Color.green);
				}
				else if (time<action.getTime()*20+50) {
					//vérifier si l'action est running ou non if ()

					//deuxième if
					g.setColor(Color.orange);
				}
				g.fillRect((int) (action.getTime()*20)+10, 35, 40, 40);
			}
			else if(action.getNameRobot().equals("Nao")){
				if (time>=action.getTime()*20+50) {
					g.setColor(Color.green);
				}
				else if (time<action.getTime()*20+50) {
					//vérifier si l'action est running ou non if ()

					//deuxième if
					g.setColor(Color.orange);
				}
				g.fillRect((int) (action.getTime()*20)+10, 140, 40, 40);
			}
			if(action.getNameRobot().equals("Peekee1R")){
				if (time>=action.getTime()*20+50) {
					g.setColor(Color.green);
				}
				else if (time<action.getTime()*20+50) {
					//vérifier si l'action est running ou non if ()

					//deuxième if
					g.setColor(Color.orange);
				}
				g.fillRect((int) (action.getTime()*20)+10, 245, 40, 40);
			}
			if(action.getNameRobot().equals("Robotino")){
				if (time>=action.getTime()*20+50) {
					g.setColor(Color.green);
				}
				else if (time<action.getTime()*20+50) {
					//vérifier si l'action est running ou non if ()

					//deuxième if
					g.setColor(Color.orange);
				}
				g.fillRect((int) (action.getTime()*20)+10, 350, 40, 40);
			}
		}
	}
	public void increaseTime() {
		this.time++;
		this.repaint();
	}
}

