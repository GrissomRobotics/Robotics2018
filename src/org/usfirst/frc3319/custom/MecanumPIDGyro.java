package org.usfirst.frc3319.custom;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.drive.MecanumDrive;


//This class implements PIDOutput for the Mecanum drive train to allow PID control for the purpose of turning using the gyro.
//Do NOT use for any other purpose, as it will control the drive train and prevent ordinary driving 

public class MecanumPIDGyro implements PIDOutput {
	
	private MecanumDrive driveTrain;
	
	public MecanumPIDGyro(MecanumDrive driveTrain) {
		this.driveTrain = driveTrain;
	}

	@Override
	public void pidWrite(double output) {
		//Pass the output only to the Z-rotation because this is for turning with the gyro
		//Negate so that the turns are consistent with gyro values
		 driveTrain.driveCartesian(0, 0, -output);

	}

}
