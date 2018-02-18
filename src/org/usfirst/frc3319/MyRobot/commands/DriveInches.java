package org.usfirst.frc3319.MyRobot.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc3319.MyRobot.Robot;

/**
 * Drive a certain number of inches based on a double input.
 * If the input is negative, use the front ultrasonic sensor and drive backwards
 */
public class DriveInches extends Command {

	private double readingTarget;
	
    public DriveInches(double inchesToDrive, double maxTimeSeconds, boolean usingFront) {
    	System.out.println("new DriveInches(" + inchesToDrive + ", " + maxTimeSeconds + ", " + usingFront + ")");
    	setInterruptible(true);
        requires(Robot.DriveTrain);
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
        
        //Set the gyro controller's setpoint to be whatever the current reading is so that it drives straight
        Robot.DriveTrain.setGyroSetpoint(Robot.DriveTrain.getGyroValue());
        setTimeout(maxTimeSeconds);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	Robot.DriveTrain.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	Robot.DriveTrain.setSetpoint(readingTarget);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    	if (Robot.DriveTrain.onTarget() || isTimedOut()) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    	Robot.DriveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    	Robot.DriveTrain.stop();
    }
}
