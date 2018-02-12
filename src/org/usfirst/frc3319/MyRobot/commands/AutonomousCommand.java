package org.usfirst.frc3319.MyRobot.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc3319.MyRobot.Robot;

/**
 *
 */
public class AutonomousCommand extends CommandGroup {

    public AutonomousCommand() {
        addSequential(new DriveInches());
        addSequential(new TurnAngle());
        addSequential(new DriveInches());
        addSequential(new TurnAngle());
        addSequential(new SetElevatorSetpoint());
        addSequentail(new DriveInches());
        addSequential(new OpenGripper());
    }
}
