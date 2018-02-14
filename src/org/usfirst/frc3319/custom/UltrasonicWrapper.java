package org.usfirst.frc3319.custom;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Ultrasonic;

//Wrapper class for the configuration with 2 ultrasonic sensors, one at the front, and one at the back

public class UltrasonicWrapper implements PIDSource{
	
	private Ultrasonic ultraSonicFront;
	private Ultrasonic ultraSonicBack;
	private boolean usingFront = true;
	
	public UltrasonicWrapper(Ultrasonic ultraSonicFront, Ultrasonic ultraSonicBack) {
		this.ultraSonicFront = ultraSonicFront;
		this.ultraSonicBack = ultraSonicBack;
	}
	
	public double getUltraSonic() {
		if (usingFront) {
			return ultraSonicFront.getRangeInches();
		} else {
			return ultraSonicBack.getRangeInches();
		}
	}
	
	public double pidGet() {
		return getUltraSonic();
	}
	
	public void useFront() {
		usingFront = true;
	}
	
	public void useBack() {
		usingFront = false;
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return null;
	}

}
