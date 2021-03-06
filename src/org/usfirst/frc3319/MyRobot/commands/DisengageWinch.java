package org.usfirst.frc3319.MyRobot.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc3319.MyRobot.Robot;

/**
 *
 */
public class DisengageWinch extends Command {
	//TODO Ramp function

    
    public DisengageWinch() {
        requires(Robot.Climber);
        setInterruptible(true);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	//TODO make this value whatever the appropriate value is
    	//Also, I think this will keep that much power going to the speed controller until it is told to do otherwise, but I may be wrong, so this may 
    	//need to go into the execute method
    	Robot.Climber.engageWinch(-1.0);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    	//Doesn't need to ever be finished because being finished will be determined by when the driver releases the button
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    	Robot.Climber.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    	Robot.Climber.stop();
    }
    
}
