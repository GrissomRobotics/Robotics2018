package org.usfirst.frc3319.MyRobot.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc3319.MyRobot.Robot;

/**
 * This program is for if we start at the center
 */
public class AutonomousCommand extends CommandGroup {

    public AutonomousCommand(String gameSpecificData) {
    	//TODO finish
    	/*
    	if (gameSpecificData.charAt(0) == 'L') { //The first character represents which color the switch is
    		addSequential(new DriveInches(70));
    		addSequential(new TurnAngle(-90));
    		addSequential(new DriveInches());
    		addSequential(new TurnAngle());
    		addSequential(new SetElevatorSetpoint(Robot.oi.SWITCH_HEIGHT));
    		addSequentail(new DriveInches());
    		addSequential(new OpenGripper());
    	}
    	*/
    }
}
