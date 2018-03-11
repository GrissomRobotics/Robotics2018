package org.usfirst.frc3319.MyRobot.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc3319.MyRobot.OI;
import org.usfirst.frc3319.MyRobot.Robot;

public class AutonomousCommand extends CommandGroup {

    public AutonomousCommand(String gameSpecificData, int startingConfig) {
    	//Right Outer config = 1
    	//Left Outer config = 2
    	//Center config = 3
    	//Right Inner config = 4
    	//Left Inner config = 5
    	//Scale Approach = 6
    	double startingDistance = 140;
    	
    	switch (startingConfig) {
    		case 1:   //If we are starting on the right outer
    			if (gameSpecificData.charAt(0) == 'R') { //The first character represents which side our color of the switch is on
    				System.out.println("GameSpecificData is R");
    				addSequential(new DriveByTime(startingDistance));
    				//addSequential(new DriveInches(startingDistance, 7, false));
    				addSequential(new TurnAngle(90, 3));
    				addSequential(new DriveByTime(45));
    				//addSequential(new DriveInches(45, 5, false);
    				depositAndBackAway();
    			} else if (gameSpecificData.charAt(0) == 'L') {
    				addSequential(new DriveByTime(startingDistance));
    			}
    			break;
    		
    	
    		case 2: //Starting left outer
    			if (gameSpecificData.charAt(0) == 'L') { //The first character represents which side our color of the switch is on
    				System.out.println("GameSpecificData is L");
    				addSequential(new DriveByTime(startingDistance));
    				addSequential(new TurnAngle(-90, 3));
    				addSequential(new DriveByTime(45));
   					depositAndBackAway();
    			} else if (gameSpecificData.charAt(0) == 'R') {
    				addSequential(new DriveByTime(startingDistance));
    			}
    			break;
    	
    		case 3: //Starting center
    			//Go cross the line, but go the opposite direction of whichever side of the switch is ours
       			addSequential(new DriveByTime(58));
       			if (gameSpecificData.charAt(0)=='L') {
       				addSequential(new TurnAngle(90, 3));
       				addSequential(new DriveByTime(87));
       				addSequential(new TurnAngle(-90, 3));
       				addSequential(new DriveByTime(86));
       			} else {
       				addSequential(new TurnAngle(-90, 3));
       				addSequential(new DriveByTime(87));
       				addSequential(new TurnAngle(90, 3));
       				addSequential(new DriveByTime(86));
       			}
       			break;
    		case 4: //Starting right inner
    			addSequential(new DriveByTime(107));
    			if (gameSpecificData.charAt(0)=='R') {
    				depositAndBackAway();
    			}
    			break;
    		case 5: //Starting left inner
    			addSequential(new DriveByTime(107));
    			if (gameSpecificData.charAt(0)=='L') {
    				depositAndBackAway();
    			}
    			break;
    		case 6:
    	}
    }
    
    private void depositAndBackAway() {
    	//If this method does not work, copy it and replace every occurrence of it with this
    	//Wait after using the pneumatics to give it time to complete the command
    	addSequential(new SetElevatorSetpoint(OI.SWITCH_HEIGHT));
		addSequential(new LowerGripper());
		addSequential(new Wait(0.5));
		addSequential(new OpenGripper());
		addSequential(new Wait(0.5));
		addSequential(new RaiseGripper());
		addSequential(new DriveByTime(-12));
		addSequential(new SetElevatorSetpoint(OI.DEFAULT_HEIGHT));
    }
}
