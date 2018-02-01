package org.usfirst.frc3319.MyRobot.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc3319.MyRobot.Robot;

/**
 *
 */
public class DriveWithJoystick extends Command {

    public DriveWithJoystick() {
        requires(Robot.DriveTrain);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	//Declare variables for deadzone corrections
    	double turn;
    	double forward;
    	double right;
    	double turnSet;
    	double forwardSet;
    	double rightSet;
    	double deadThreshold = 0.1;
    	
    	//Correct deadzones
    	//Logic is: if the reading is greater than the threshold, make the setter equal to it, otherwise, make the setter equal to 0
    	turn = Robot.oi.getRotationLeft()-Robot.oi.getRotationRight();
    	forward = Robot.oi.getXValue();
    	right = Robot.oi.getYValue();
    	
    	if (Math.abs(turn) > deadThreshold) {
    		turnSet = turn;
    	}
    	else {
    		turnSet = 0;
    	}

    	if (Math.abs(forward) > deadThreshold) {
    		forwardSet = forward;
    	}
    	else {
    		forwardSet = 0;
    	}
    	

    	if (Math.abs(right) > deadThreshold) {
    		rightSet = right;
    	}
    	else {
    		rightSet = 0;
    	}
    	
    	Robot.DriveTrain.cartesianDrive(forwardSet, rightSet, turnSet, 0.0);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
