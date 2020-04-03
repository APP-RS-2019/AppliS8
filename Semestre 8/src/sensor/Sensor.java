package sensor;

import robot.Robot;

public class Sensor {
	
	private String idSensor;
	private String name;
	private String description;
	
	public Sensor(String idSensor, String name, String description) {
		this.idSensor = idSensor;
		this.name = name;
		this.description = description;
	}

	public String getIdSensor() {
		return idSensor;
	}

	public void setIdSensor(String idSensor) {
		this.idSensor = idSensor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void addRobot(Robot robot) {
		// TODO Auto-generated method stub
		
	}
}
