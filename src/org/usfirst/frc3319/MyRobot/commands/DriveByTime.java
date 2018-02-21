package org.usfirst.frc3319.MyRobot.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc3319.MyRobot.Robot;

/**
 * Drive a certain number of inches based on a double input.
 * If the input is negative, use the front ultrasonic sensor and drive backwards
 */
public class DriveByTime extends Command {
    public DriveByTime(double seconds) {
    	requires(Robot.DriveTrain);
    	
    	//Drive forward for 8 seconds
    	this.setTimeout(seconds);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	Robot.DriveTrain.cartesianDrive(0, -0.3, 0);
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
}
