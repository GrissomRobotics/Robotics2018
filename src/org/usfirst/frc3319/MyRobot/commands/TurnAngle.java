package org.usfirst.frc3319.MyRobot.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc3319.MyRobot.Robot;

/**
 *
 */
public class TurnAngle extends Command {
	//For direction to turn, positive is right, negative is left
	double setpoint;
	
    public TurnAngle(double degreesToTurn, double maxTimeSeconds) {
    	System.out.println("Turning " + degreesToTurn + " degrees with timeout of " + maxTimeSeconds + " seconds");
        requires(Robot.DriveTrain);
        setpoint = Robot.DriveTrain.getGyroValue() + degreesToTurn;
        
        setTimeout(maxTimeSeconds);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	Robot.DriveTrain.setGyroPID(SmartDashboard.getNumber("Gyro Proportional", 0.5), SmartDashboard.getNumber("Gyro Integral", 0.0), SmartDashboard.getNumber("Gyro Differential", 2.0));

    	
    	//Stop the drive train first, in case the motors are still on (they shouldn't be), then enable the gyro controller to turn
    	Robot.DriveTrain.stop();
    	Robot.DriveTrain.enableGyroController();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	Robot.DriveTrain.setGyroSetpoint(setpoint);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    	if (Robot.DriveTrain.isGyroControllerOnTarget() || isTimedOut()) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    	Robot.DriveTrain.disableGyroController();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    	Robot.DriveTrain.disableGyroController();
    }
}
