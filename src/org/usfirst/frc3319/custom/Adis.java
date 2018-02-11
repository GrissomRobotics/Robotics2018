package org.usfirst.frc3319.custom;

import edu.wpi.first.wpilibj.PIDSource;

//Wrapper class for the builtin gyro

public class Adis extends ADIS16448_IMU implements PIDSource{
	
	public Adis() {
		super();
	}
	
	public double getRobotHeading() {
		//Divide by 4 to get a standard 360 degree coordinate system
		return getAngleZ()/4;
	}
	
	public double pidGet() {
		return getRobotHeading();
	}

}
