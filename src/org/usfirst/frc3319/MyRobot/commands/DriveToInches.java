package org.usfirst.frc3319.MyRobot.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc3319.MyRobot.Robot;

/**
 * Use this command to drive until a certain reading is reached on the ultrasonic sensor
 * Make sure you use Robot.DriveTrain.setUltraSonicSensor(boolean front) before you call this command.
 * 
 */
public class DriveToInches extends Command {
	private double ultrasoundTarget;
	private boolean usingFront;
	
    public DriveToInches(double ultrasonicTarget, double maxTimeSeconds, boolean front) {
    	usingFront = front;
    	ultrasoundTarget = ultrasonicTarget;
    	setInterruptible(true);
        requires(Robot.DriveTrain);
        setTimeout(maxTimeSeconds);

        //Set the gyro controller's setpoint to be whatever the current reading is so that it drives straight
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	Robot.DriveTrain.getPIDController().setPID(SmartDashboard.getNumber("Drive Proportional", 0.5), SmartDashboard.getNumber("Drive Integral", 0.0), SmartDashboard.getNumber("Drive Differential", 2.0));
    	//Reset the ultraSonic sensor in case some other command has used it since instantiation of the class
    	Robot.DriveTrain.setUltrasonicSensor(usingFront);
    	Robot.DriveTrain.enable();
    	Robot.DriveTrain.setSetpoint(ultrasoundTarget);
    	System.out.println("Ultrasonic target: " + Robot.DriveTrain.getSetpoint());
    	
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	SmartDashboard.putNumber("DriveToInches PID Power", Robot.DriveTrain.getPIDController().get());
    	SmartDashboard.putNumber("DriveToInches Setpoint", Robot.DriveTrain.getSetpoint());
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    	if (Robot.DriveTrain.getUltrasonicInches() > Robot.DriveTrain.getSetpoint() && !usingFront) {
    		System.out.println("DriveInches Finished");
       		return true;
    	} else if (Robot.DriveTrain.getUltrasonicInches() < Robot.DriveTrain.getSetpoint() && usingFront) {
    		System.out.println("DriveInches Finished");
    		return true;
    	}
    	
    	if (Robot.DriveTrain.onTarget() || isTimedOut()) {
    		System.out.println("DriveToInches Finished");
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
