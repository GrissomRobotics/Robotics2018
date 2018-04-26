package org.usfirst.frc3319.MyRobot.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc3319.MyRobot.Robot;
import org.usfirst.frc3319.custom.Ramper;

/**
 *
 */
public class DriveWithJoystick extends Command {
	private Ramper rampForward;
	private Ramper rampRight;
	

    public DriveWithJoystick() {
        requires(Robot.DriveTrain);
        setInterruptible(true);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	rampForward = new Ramper(Robot.DriveTrain.defaultStep); 
    	rampRight = new Ramper(Robot.DriveTrain.defaultStep); 
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	Robot.DriveTrain.disableGyroController();
    	//Declare variables for deadzone corrections
    	double turn;
    	double forward;
    	double right;
    	double turnSet;
    	double forwardSet;
    	double rightSet;
    	double rightThreshold = 0.1;
    	double deadThreshold = 0.1;
    	
    	//Correct deadzones
    	//Logic is: if the r		
    		
    	
    	
    	
    	//reading is greater than the threshold, make the setter equal to it, otherwise, make the setter equal to 0
    	turn = Robot.oi.getRotationLeft()-Robot.oi.getRotationRight();
    	right = Robot.oi.getXValue();
    	forward = Robot.oi.getYValue();
    	
    	if (Math.abs(turn) > deadThreshold) {
    		turnSet = turn;
    	}
    	else {
    		turnSet = 0;
    	}

    	if (Math.abs(forward) > deadThreshold) {
    		forwardSet = rampForward.ramp(forward);
    	}
    	else {
    		forwardSet = 0;
    	}
    	

    	if (Math.abs(right) > rightThreshold) {
    		rightSet = rampRight.ramp(right);
    	}
    	else {
    		rightSet = 0;
    	}
    	
    	Robot.DriveTrain.cartesianDrive(rightSet, forwardSet, (turnSet*0.6));
       	}
    

    // Make this return true when this Command no longer needs to run execute()
    @Override	
    
    
    	
    	
    protected boolean isFinished() {
        return false;
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
    }
}
