package org.usfirst.frc3319.MyRobot.commands;

import org.usfirst.frc3319.MyRobot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ZeroEncoders extends Command {
	
	public ZeroEncoders() {
		requires(Robot.Elevator);
	}
	
	@Override
	public void initialize() {
		Robot.Elevator.zeroEncoders();
	}
	
	@Override
	public void execute() {}
	

	@Override
	protected boolean isFinished() {
		// Cannot use == for garbage data at the end of the double value
		return Robot.Elevator.getPosition()<=0.01; 
	}

}
