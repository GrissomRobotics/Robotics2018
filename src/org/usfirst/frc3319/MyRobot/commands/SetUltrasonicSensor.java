package org.usfirst.frc3319.MyRobot.commands;

import org.usfirst.frc3319.MyRobot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SetUltrasonicSensor extends Command {
	private boolean front2;
	public SetUltrasonicSensor(boolean front) {
		
		front2 = front;
	}
	@Override
	protected void execute() {
		Robot.DriveTrain.setUltrasonicSensor(front2);
	}
	@Override
	protected boolean isFinished() {
		if (Robot.DriveTrain.getUltrasonicSensor() == front2) {
			return true;
		}
		else {
			return false;
		}
	}
}
