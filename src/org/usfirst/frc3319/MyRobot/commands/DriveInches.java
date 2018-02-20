package org.usfirst.frc3319.MyRobot.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc3319.MyRobot.Robot;

/**
 * Drive a certain number of inches based on a double input.
 * If the input is negative, use the front ultrasonic sensor and drive backwards
 */
public class DriveInches extends Command {

	private double readingTarget;
	private boolean usingFront;
	
    public DriveInches(double inchesToDrive, double maxTimeSeconds, boolean usingFront) {
    	this.usingFront = usingFront;
    	System.out.println("new DriveInches(" + inchesToDrive + ", " + maxTimeSeconds + ", " + usingFront + ")");
    	setInterruptible(true);
        requires(Robot.DriveTrain);
        //Robot.DriveTrain.getPIDController().setPID(SmartDashboard.getNumber("Drive Proportional", 0.5), SmartDashboard.getNumber("Drive Integral", 0.0), SmartDashboard.getNumber("Drive Differential", 2.0));
    	Robot.DriveTrain.setUltrasonicSensor(usingFront);
        double currentUltraSonicReading = (double) Robot.DriveTrain.getUltrasonicInches();
        if (inchesToDrive > 0) {
        	System.out.println("InchesToDrive > 0");
        	if (usingFront) {
        		System.out.println("Using Front Sensor");
        		readingTarget = currentUltraSonicReading - inchesToDrive;
        	}
        	else {
        		System.out.println("Using Rear Sensor");
        		readingTarget = currentUltraSonicReading + inchesToDrive;
        	}
        }
        else {
        	System.out.println("InchesToDrive <= 0");
        	if (usingFront) {
        		System.out.println("Using Front Sensor");
        		readingTarget = currentUltraSonicReading + inchesToDrive;
        	}
        	else {
        		System.out.println("Using Rear Sensor");
        		readingTarget = currentUltraSonicReading - inchesToDrive;
        	}
        }
        if (readingTarget < 0) {
        	throw new java.lang.Error("NEGATIVE READING TARGET");
        }
        
        setTimeout(maxTimeSeconds);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	//Reset the ultraSonic sensor in case some other command has used it since instantiation of the class
    	Robot.DriveTrain.setUltrasonicSensor(usingFront);
    	Robot.DriveTrain.enable();
    	Robot.DriveTrain.resetGyro();
    	Robot.DriveTrain.setSetpoint(readingTarget);
        Robot.DriveTrain.setGyroSetpoint(Robot.DriveTrain.getGyroValue());
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    	if (Robot.DriveTrain.onTarget() || isTimedOut()) {
    		Robot.DriveTrain.stop();
    		return true;
    	} else {
    		return false;
    	}
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    	Robot.DriveTrain.stop();
    }
}
