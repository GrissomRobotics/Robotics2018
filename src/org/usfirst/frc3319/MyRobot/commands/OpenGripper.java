package org.usfirst.frc3319.MyRobot.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc3319.MyRobot.Robot;

/**
 *
 */
public class OpenGripper extends Command {

    
    public OpenGripper() {
        requires(Robot.Gripper);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
		if (!Robot.Gripper.isLowered()) {
			Robot.Gripper.lower();
		}
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	Robot.Gripper.open();
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return !Robot.Gripper.isClosed();
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
