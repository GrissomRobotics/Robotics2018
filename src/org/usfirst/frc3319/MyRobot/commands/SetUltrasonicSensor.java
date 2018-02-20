package org.usfirst.frc3319.MyRobot.commands;

import org.usfirst.frc3319.MyRobot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SetUltrasonicSensor extends Command {
	
	private boolean usingFront;
	
	public SetUltrasonicSensor(boolean front) {
		
		usingFront = front;
	}
	@Override
	protected void execute() {
		Robot.DriveTrain.setUltrasonicSensor(usingFront);
	}
	@Override
	protected boolean isFinished() {
		if (Robot.DriveTrain.getUltrasonicSensor() == usingFront) {
			return true;
		}
		else {
			return false;
		}
	}
}
