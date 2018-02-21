package org.usfirst.frc3319.MyRobot.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc3319.MyRobot.OI;
import org.usfirst.frc3319.MyRobot.Robot;

public class AutonomousCommand extends CommandGroup {

    public AutonomousCommand(String gameSpecificData, char startingConfig) {
    	
    	if (startingConfig=='R') { //If we are starting on the right
    		if (gameSpecificData.charAt(0) == 'R') { //The first character represents which side our color of the switch is on
    			System.out.println("GameSpecificData is R");
    			//Figure out the appropriate values. Be aware that negative numbers for TurnAngle are for turning left
    			//Test driveInches() command
        		addSequential(new DriveInches(133, 10, false));
        		addSequential(new TurnAngle(-90, 3));
        		addSequential(new DriveToInches(30, 10, true));
        		addSequential(new SetElevatorSetpoint(OI.SWITCH_HEIGHT));
        		//Go forward a few inches
        		addSequential(new DriveToInches(10, 10, true));
        		addSequential(new LowerGripper());
        		addSequential(new OpenGripper());
    			//Back up some, and then lower the elevator
        		addSequential(new DriveInches(-12, 10, true));
        		addSequential(new SetElevatorSetpoint(OI.DEFAULT_HEIGHT));
        	} //else if (gameSpecificData.charAt(0) == 'L') {
    			//Fill in
        	//}
    	
    	}
    	if (startingConfig=='E') {
    		addSequential(new DriveByTime(8));
    	}
    }
}
