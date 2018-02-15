package org.usfirst.frc3319.MyRobot.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc3319.MyRobot.Robot;

/**
 * Use this command to drive until a certain reading is reached on the ultrasonic sensor
 * Once you have the UltrasonicWrapper implemented you'll need to add a argument in the constructor
 * that tells the drive train whether to use the front or back sensor. 
 * Write methods there instead of directly manipulating it in this class.
 */
public class DriveToInches extends Command {
	
    public DriveToInches(double ultrasonicTarget) {
    	setInterruptible(true);
        Robot.DriveTrain.setSetpoint(ultrasonicTarget);
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
