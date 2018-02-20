package org.usfirst.frc3319.MyRobot.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc3319.MyRobot.Robot;

/**
 * Use this command to drive until a certain reading is reached on the ultrasonic sensor
 * Make sure you use Robot.DriveTrain.setUltraSonicSensor(boolean front) before you call this command.
 * 
 */
public class DriveToInches extends Command {
	private double ultrasoundTarget;
	private boolean usingFront;
	
    public DriveToInches(double ultrasonicTarget, double maxTimeSeconds, boolean front) {
    	usingFront = front;
    	ultrasoundTarget = ultrasonicTarget;
    	setInterruptible(true);
    	Robot.DriveTrain.setUltrasonicSensor(usingFront);
        //Robot.DriveTrain.getPIDController().setPID(SmartDashboard.getNumber("Drive Proportional", 0.5), SmartDashboard.getNumber("Drive Integral", 0.0), SmartDashboard.getNumber("Drive Differential", 2.0));
    	if (ultrasonicTarget < 0) {
    		throw new java.lang.Error("NegativeDistanceError");
    	}
    	if (front) {
    		System.out.println("Driving until wall is " + ultrasonicTarget + " inches from front sensor");
    	}
    	else {
    		System.out.println("Driving until wall is " + ultrasonicTarget + " inches from rear sensor");
    	}
        
        setTimeout(maxTimeSeconds);

        //Set the gyro controller's setpoint to be whatever the current reading is so that it drives straight
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	//Reset the ultraSonic sensor in case some other command has used it since instantiation of the class
    	Robot.DriveTrain.setUltrasonicSensor(usingFront);
    	Robot.DriveTrain.setSetpoint(ultrasoundTarget);
    	Robot.DriveTrain.enable();
    	Robot.DriveTrain.setGyroSetpoint(Robot.DriveTrain.getGyroValue());
    	System.out.println("Initialize in DriveToInches gyro setpoint: "+ Robot.DriveTrain.getGyroSetpoint());
    	System.out.println("Ultrasonic target: " + Robot.DriveTrain.getSetpoint());
    	
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	SmartDashboard.putNumber("DriveToInches PID Power", Robot.DriveTrain.getPIDController().get());
    	SmartDashboard.putNumber("DriveToInches Setpoint", Robot.DriveTrain.getSetpoint());
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    	if (Robot.DriveTrain.onTarget() || isTimedOut()) {
    		Robot.DriveTrain.stop();
    		return true;
    	}
    	else {
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
