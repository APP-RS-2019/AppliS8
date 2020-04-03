package robot;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

import action.Action;
import upperClass.Syst;


public class Fleet implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String ipFleet;
	private String name;
	private HashSet<Robot> robots;
	
	public Fleet(String idFleet){
		this.ipFleet = idFleet;
		this.robots = new HashSet<Robot> ();
	}
	
	Fleet(String idFleet, String name){
		this.ipFleet = idFleet;
		this.name = name;
		this.robots = new HashSet<Robot> ();
	}
	
	public String getId(){
		return this.ipFleet;
	}
	
	public String getName(){
		return this.name;
	}
	
	public HashSet<Robot> getRobots(){
		return robots;
	}

	public void setId(String idFleet){
		this.ipFleet = idFleet;
	}
	
	public void setIpFleet(String ipFleet) {
		this.ipFleet = ipFleet;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void addRobot(Robot robot){
		this.robots.add(robot);
	}
	
	public void deleteRobot(Robot robot){
		this.robots.remove(robot);
	}
	
	public String toString(){
		return "Fleet's name : " + this.name + " with IP : " + this.ipFleet;
	}
	
	public int sizeFleet(){
		return this.robots.size();
	}
	
	public String generatID() {
		int nb = (int) Math.random()*(99999);
		String newID = String.valueOf(nb);
		boolean test = true;
		
		for(Robot robot : robots) {
			if (newID==robot.getId()) {
				test = false;
			}
		}
		
		if (test) {
			return newID;
		}
		else {
			return generatID();
		}
	}

}
