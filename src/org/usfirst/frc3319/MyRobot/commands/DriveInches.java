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
	private double inchesToDrive;
	
    public DriveInches(double inchesToDrive, double maxTimeSeconds, boolean usingFront) {
    	this.usingFront = usingFront;
    	System.out.println("new DriveInches(" + inchesToDrive + ", " + maxTimeSeconds + ", " + usingFront + ")");
    	setInterruptible(true);
        requires(Robot.DriveTrain);
        this.inchesToDrive = inchesToDrive;
        setTimeout(maxTimeSeconds);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() { 
    	Robot.DriveTrain.getPIDController().setPID(SmartDashboard.getNumber("Drive Proportional", 0.5), SmartDashboard.getNumber("Drive Integral", 0.0), SmartDashboard.getNumber("Drive Differential", 2.0));
    	//Reset the ultraSonic sensor in case some other command has used it since instantiation of the class
    	Robot.DriveTrain.setUltrasonicSensor(usingFront);
        double currentUltraSonicReading = (double) Robot.DriveTrain.getUltrasonicInches();
        if (inchesToDrive > 0) {
        	System.out.println("InchesToDrive > 0");
        	if (usingFront) {
        		readingTarget = currentUltraSonicReading - inchesToDrive;
        		System.out.println("Using Front Sensor to Drive " + readingTarget);

        	}
        	else {
        		readingTarget = currentUltraSonicReading + inchesToDrive;
        		System.out.println("Using Rear Sensor to Drive " + readingTarget);
        	}
        }
        else {
        	System.out.println("InchesToDrive <= 0");
        	if (usingFront) {
        		readingTarget = currentUltraSonicReading + inchesToDrive;
        		System.out.println("Using Front Sensor to Drive " + readingTarget);
        	}
        	else {
        		readingTarget = currentUltraSonicReading - inchesToDrive;
        		System.out.println("Using Rear Sensor to Drive " + readingTarget);

        	}
        }
        if (readingTarget < 0) {
        	throw new java.lang.Error("NEGATIVE READING TARGET");
        }
        Robot.DriveTrain.enable();
    	Robot.DriveTrain.setSetpoint(readingTarget);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    	if (Robot.DriveTrain.getUltrasonicInches() > Robot.DriveTrain.getSetpoint() && !usingFront) {
    		System.out.println("DriveInches Finished");
       		return true;
    	} else if (Robot.DriveTrain.getUltrasonicInches() < Robot.DriveTrain.getSetpoint() && usingFront) {
    		System.out.println("DriveInches Finished");
    		return true;
    	}
    	
    	if (Robot.DriveTrain.onTarget() || isTimedOut()) {
    		System.out.println("DriveInches Finished");
    		return true;
    	} else {
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
