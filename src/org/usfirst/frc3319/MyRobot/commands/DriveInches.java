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
        Robot.DriveTrain.setSetpoint(readingTarget);
        //Set the gyro controller's setpoint to be whatever the current reading is so that it drives straight
        Robot.DriveTrain.setGyroSetpoint(Robot.DriveTrain.getGyroValue());
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	Robot.DriveTrain.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return Robot.DriveTrain.onTarget();
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
    	Robot.DriveTrain.stop();
    }
}
