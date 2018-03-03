package org.usfirst.frc3319.MyRobot.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc3319.MyRobot.Robot;

/**
 * Drive a certain number of inches based on a double input.
 * If the input is negative, use the front ultrasonic sensor and drive backwards
 */
public class DriveByTime extends Command {
	private boolean forward;
	
    public DriveByTime(double distance) {
    	if (distance > 0) {
    		forward = true;
    	} else {
    		forward = false;
    	}
    	requires(Robot.DriveTrain);
    	this.setTimeout(calculateTime(distance));
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	if (forward) {
    		Robot.DriveTrain.cartesianDrive(0, -0.3, 0);
    	} else {
    		Robot.DriveTrain.cartesianDrive(0, 0.3, 0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    	return isTimedOut();
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
    
	static double calculateTime(double distance) {
		return (0.000008122 * (Math.pow(distance,2))) + (0.024 * distance) + 0.411;
	}
}
