package org.usfirst.frc3319.MyRobot.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc3319.MyRobot.OI;
import org.usfirst.frc3319.MyRobot.Robot;

public class AutonomousCommandScale extends CommandGroup {

    public AutonomousCommandScale(String gameSpecificData) {
    	double startingDistance = 280;
    	addSequential(new DriveByTime(startingDistance));
    }
}