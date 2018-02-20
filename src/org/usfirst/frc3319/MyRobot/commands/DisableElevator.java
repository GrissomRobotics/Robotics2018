package org.usfirst.frc3319.MyRobot.commands;

import org.usfirst.frc3319.MyRobot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DisableElevator extends Command {
	
	//As this is currently set up, it will keep the elevator disabled until it receives another command (I think) 
	//Definitely test in case I don't understand it correctly
	public DisableElevator() {
		requires(Robot.Elevator);
		setInterruptible(true);

		Robot.Elevator.stop();
	}
	
	@Override 
	protected void initialize() {
	}
	
	@Override
	protected void execute() {}

	@Override
	protected boolean isFinished() {
		return true;
	}
	
	@Override
	protected void end() {}
	
	@Override
	protected void interrupted() {}

}
