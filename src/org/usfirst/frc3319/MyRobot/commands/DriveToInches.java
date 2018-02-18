package org.usfirst.frc3319.MyRobot.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc3319.MyRobot.Robot;

/**
 * Use this command to drive until a certain reading is reached on the ultrasonic sensor
 * Make sure you use Robot.DriveTrain.setUltraSonicSensor(boolean front) before you call this command.
 * 
 */
public class DriveToInches extends Command {
	double ultrasoundTarget;
	
    public DriveToInches(double ultrasonicTarget, double maxTimeSeconds, boolean front) {
    	ultrasoundTarget = ultrasonicTarget;
    	setInterruptible(true);
    	if (ultrasonicTarget < 0) {
    		throw new java.lang.Error("NegativeDistanceError");
    	}
    	Robot.DriveTrain.setUltrasonicSensor(front);
    	if (front) {
    		System.out.println("Driving until wall is " + ultrasonicTarget + " inches from front sensor");
    	}
    	else {
    		System.out.println("Driving until wall is " + ultrasonicTarget + " inches from rear sensor");
    	}
        
        setTimeout(maxTimeSeconds);

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
    	Robot.DriveTrain.setSetpoint(ultrasoundTarget);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    	if (Robot.DriveTrain.onTarget() || isTimedOut()) {
    		return true;
    	}
    	else {
    		return false;
    	}
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
