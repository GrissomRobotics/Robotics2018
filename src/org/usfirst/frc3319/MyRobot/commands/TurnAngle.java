package org.usfirst.frc3319.MyRobot.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc3319.MyRobot.Robot;

/**
 *
 */
public class TurnAngle extends Command {
	//For direction to turn, positive is right, negative is left
	
    public TurnAngle(double degreesToTurn) {
        requires(Robot.DriveTrain);
        double setpoint = Robot.DriveTrain.getGyroValue()+degreesToTurn;
        Robot.DriveTrain.setGyroSetpoint(setpoint);
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
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return Robot.DriveTrain.isGyroControllerOnTarget();
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
