package org.usfirst.frc3319.MyRobot.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc3319.MyRobot.OI;
import org.usfirst.frc3319.MyRobot.Robot;

/**
 * This program is for if we start at the center
 */
public class AutonomousCommand extends CommandGroup {

    public AutonomousCommand(String gameSpecificData) {
    	
    	if (gameSpecificData.charAt(0) == 'R') { //The first character represents which side our color of the switch is on
    		//Figure out the appropriate values. Be aware that negative numbers for TurnAngle are for turning left
    		//Test driveInches() command
        	addParallel(new SetElevatorSetpoint(OI.SWITCH_HEIGHT));
        	addSequential(new DriveInches(133, 10, false));
        	addSequential(new TurnAngle(-90, 3));
        	addSequential(new DriveInches(45, 10, false));
    		//Add something to move away, should probably include something to disable the elevator
    	} else if (gameSpecificData.charAt(0) == 'R') {
    		//Fill in
    	}
    	
    }
}
