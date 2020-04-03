package robot;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

import sensor.Sensor;
import action.Action;

public abstract class Robot implements Serializable{
	protected String idRobot;
	protected String name;
	protected double posx;
	protected double posy;
	protected double angle;
	private HashSet<Action> action;
	private HashSet<Sensor> sensor;

	public Robot(String idRobot, String name){
		this.idRobot = idRobot;
		this.name = name;
		this.posx = 0;
		this.posy = 0;
		this.angle = 0;
		this.action = new HashSet<Action> ();
		this.sensor = new HashSet<Sensor> ();
	}

	public Robot(String idRobot, String name, double posx, double posy, double angle){
		this.idRobot = idRobot;
		this.name = name;
		this.posx = posx;
		this.posy = posy;
		this.angle = angle;
		this.action = new HashSet<Action> ();
		this.sensor = new HashSet<Sensor> ();
	}

	public String getId() {
		// TODO Auto-generated method stub
		return this.idRobot;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	public double getPosx() {
		// TODO Auto-generated method stub
		return this.posx;
	}

	public double getPosy() {
		// TODO Auto-generated method stub
		return this.posy;
	}

	public double getAngle() {
		// TODO Auto-generated method stub
		return this.angle;
	}

	public HashSet<Action> getAction() {
		return action;
	}

	public HashSet<Sensor> getSensor() {
		return sensor;
	}

	public void setId(String id) {
		// TODO Auto-generated method stub
		this.idRobot = id;
	}

	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name = name;
	}

	public void setPosx(float posx) {
		// TODO Auto-generated method stub
		this.posx = posx;
	}

	public void setPosy(float posy) {
		// TODO Auto-generated method stub
		this.posy = posy;
	}

	public void setAngle(float angle) {
		// TODO Auto-generated method stub
		this.angle = angle;
	}

	@Override
	public String toString() {
		return "Robot [idRobot=" + idRobot + ", name=" + name + ", posx="
				+ posx + ", posy=" + posy + ", angle=" + angle + ", Nbaction="
				+ this.nbAction() + ", Nbsensor=" + this.nbSensor() + "]";
	}

	public HashSet<Action> Capacity() {
		return this.action;
	}
	
	public void addAction(Action a){
		action.add(a);
	}
	
	public int nbAction(){
		return this.action.size();
	}
	
	public void addSensor(Sensor s){
		this.sensor.add(s);
	}
	
	public int nbSensor(){
		return this.sensor.size();
	}

	public abstract void doAction(Action a);
	
	public String generatIDAction() {
		int nb = (int) Math.random()*(99999);
		String newID = String.valueOf(nb);
		boolean test = true;
		
		for(Action action : action) {
			if (newID==action.getAction()) {
				test = false;
			}
		}
		
		if (test) {
			return newID;
		}
		else {
			return generatIDAction();
		}
	}
	
	public String generatIDSensor() {
		int nb = (int) Math.random()*(99999);
		String newID = String.valueOf(nb);
		boolean test = true;
		
		for(Sensor sensor : sensor) {
			if (newID==sensor.getIdSensor()) {
				test = false;
			}
		}
		
		if (test) {
			return newID;
		}
		else {
			return generatIDAction();
		}
	}
}
