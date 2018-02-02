package org.usfirst.frc3319.MyRobot.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc3319.MyRobot.Robot;

/**
 *
 */
public class DriveInches extends Command {
	private double readingTarget;
	
    public DriveInches(double inchesToDrive) {
        requires(Robot.DriveTrain);
        double currentUltraSonicReading = Robot.DriveTrain.getUltraSonicInches();
        readingTarget = currentUltraSonicReading - inchesToDrive;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	//Drive forward (along the y-axis) at full speed
    	Robot.DriveTrain.cartesianDrive(0, 1.0, 0, 0);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    	//The task is finished with 0.05 inches of accuracy
    	//Do not use == because garbage data likely exists at the very end of the double reading
        return (Robot.DriveTrain.getUltraSonicInches()-readingTarget) <= 0.05;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    	Robot.DriveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
