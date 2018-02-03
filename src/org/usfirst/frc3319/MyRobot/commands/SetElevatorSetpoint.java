package org.usfirst.frc3319.MyRobot.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc3319.MyRobot.Robot;

/**
 *
 */
public class SetElevatorSetpoint extends Command {
	
	private double setpoint;

    
    public SetElevatorSetpoint(double setpoint) {
        requires(Robot.Elevator);
        this.setpoint = setpoint;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	Robot.Elevator.enable();
    	Robot.Elevator.setSetpoint(setpoint);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return Robot.Elevator.onTarget();
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    	Robot.Elevator.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    	Robot.Elevator.disable();
    }
}
