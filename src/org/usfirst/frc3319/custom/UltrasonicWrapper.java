package org.usfirst.frc3319.custom;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Ultrasonic;

//Wrapper class for the configuration with 2 ultrasonic sensors, one at the front, and one at the back

public class UltrasonicWrapper implements PIDSource{
	
	private DigitalOutput ultrasoundSelector;
	private SerialPort arduino;
	private boolean usingFront = true;
	private long ultrasoundRange;
	private boolean sensor;
	
	public UltrasonicWrapper(DigitalOutput ultrasoundSelector, SerialPort arduino) {
		this.ultrasoundSelector = ultrasoundSelector;
		this.arduino = arduino;
	}
	
	public long getUltraSonic() {
		return ultrasoundRange;
	}
	
	public boolean getUltrasonicSensor() {
    	return usingFront;
    }
	
	public void readUltrasonicInches() {
    	Pattern p = Pattern.compile("\\{\"d\":\\d*\\}");
    	String jsonString = arduino.readString();
    	//System.out.println(jsonString);
    	JSONParser parser = new JSONParser();
    	Object jsonObj;
		Matcher m = p.matcher(jsonString);
		byte count = 0;
		while (m.find()) {
			try {
				jsonString = m.group(count);
				jsonObj = parser.parse(jsonString);
				JSONObject jsonObject = (JSONObject) jsonObj;
				
				long dist = (long) jsonObject.get("d");
				if ((dist > 0) && (dist < 1000)) {
					ultrasoundRange = dist;
				}
				count++;
			}
			catch(NullPointerException | ParseException | IllegalStateException | IndexOutOfBoundsException e){
	    	}
			
		}
    	
    }
	
	public double pidGet() {
		return getUltraSonic();
	}
	
	public void setUltrasonicSensor(boolean front) {
    	if (front) {
    		ultrasoundSelector.set(true);
    		usingFront = true;
    	}
    	else {
    		ultrasoundSelector.set(false);
    		usingFront = false;
    	}
    }

	//These methods are not strictly necessary to be filled in. If you want to figure out what to put in it, you can, but I don't think you need to
	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return null;
	}

}
