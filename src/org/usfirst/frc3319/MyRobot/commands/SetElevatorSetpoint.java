package org.usfirst.frc3319.MyRobot.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc3319.MyRobot.Robot;
import org.usfirst.frc3319.MyRobot.subsystems.Elevator;

/**
 *
 */
public class SetElevatorSetpoint extends Command {
	
	private double setpoint;

    
    public SetElevatorSetpoint(double setpoint) {
        requires(Robot.Elevator);
        this.setpoint = setpoint;
        setInterruptible(true);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	//Use different PID values depending on if the elevator is going up or down
    	if (setpoint < Robot.Elevator.getPosition()) { //Elevator is going up
    		Robot.Elevator.setPID(SmartDashboard.getNumber("Elevator Proportional", 0.4), SmartDashboard.getNumber("Elevator Integral", 0.0), SmartDashboard.getNumber("Elevator Differential", 0.75), SmartDashboard.getNumber("Elevator Feed Forward", -0.125));
    		System.out.println("Going up");
    	} else { //Going down
    		Robot.Elevator.setPID(SmartDashboard.getNumber("Elevator Proportional", 0.4), SmartDashboard.getNumber("Elevator Integral", 0.0), SmartDashboard.getNumber("Elevator Differential", 0.75), SmartDashboard.getNumber("Elevator Feed Forward", -0.125));
    		System.out.println("Going down");
    	}
    	 
    	Robot.Elevator.enable();
    	Robot.Elevator.setSetpoint(setpoint);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    	//if (Robot.Elevator.onTarget() && setpoint == 0) {
    	//	return true;
    	//}
       return Robot.Elevator.onTarget();
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    	Robot.Elevator.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    	Robot.Elevator.disable();
    }
}
