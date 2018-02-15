package org.usfirst.frc3319.MyRobot.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc3319.MyRobot.Robot;

/**
 *
 */
public class ControlElevator extends Command {
	//TODO Ramp function

    
    public ControlElevator() {
        requires(Robot.Elevator);
        setInterruptible(true);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	//This will run any time the elevator is not going to a setpoint, because this is the default command. Therefore:
    	//Set speed to be just enough to keep it in place
    	//You may be able to configure the feed forward variable to do the same thing, but I am not sure how. This seems like it works fine.
    	Robot.Elevator.setSpeed(-0.125);
    	
    	if (Robot.oi.getPOV()==0) {
    		//Negative is up
    		Robot.Elevator.setSpeed(-0.4);
    	}
    	else if (Robot.oi.getPOV()==180) {
    		Robot.Elevator.setSpeed(0.4);
    	}
    	
    	SmartDashboard.putNumber("POV", Robot.oi.getPOV());
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    	Robot.Elevator.setSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    	Robot.Elevator.setSpeed(0);
    }
    
}
