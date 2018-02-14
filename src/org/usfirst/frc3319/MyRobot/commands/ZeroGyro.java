package org.usfirst.frc3319.MyRobot.commands;

import org.usfirst.frc3319.MyRobot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ZeroGyro extends Command {
	
	public ZeroGyro() {
		requires(Robot.DriveTrain);
	}
	
	@Override
	public void initialize() {
		Robot.DriveTrain.resetGyro();
	}
	
	@Override
	public void execute() {}
	

	@Override
	protected boolean isFinished() {
		return true;
	}

}
