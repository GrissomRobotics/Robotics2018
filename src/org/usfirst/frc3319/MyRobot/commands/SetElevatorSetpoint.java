package org.usfirst.frc3319.MyRobot.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.ArrayList;

import org.usfirst.frc3319.MyRobot.Robot;
import org.usfirst.frc3319.MyRobot.subsystems.Elevator;

/**
 *
 */
public class SetElevatorSetpoint extends Command {
	
	private double setpoint;

    
    public SetElevatorSetpoint(double setpoint) {
    	System.out.println(setpoint);
        requires(Robot.Elevator);
        this.setpoint = setpoint;
        setInterruptible(true);
        System.out.println("Setting Setpoint");
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	//Use different PID values depending on if the elevator is going up or down
    	System.out.println("Initializing PID Control...");
    	if (setpoint < Robot.Elevator.getPosition()) { //Elevator is going up
    		Robot.Elevator.setPID(0.46, 0.0, 0.75, -0.12);
    		System.out.println("Going up");
    	} else { //Going down
    		Robot.Elevator.setPID(SmartDashboard.getNumber("Elevator Proportional", 0.46), SmartDashboard.getNumber("Elevator Integral", 0.0), SmartDashboard.getNumber("Elevator Differential", 0.75), SmartDashboard.getNumber("Elevator Feed Forward", -0.12));
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
    public boolean isFinished() {
    	//if (Robot.Elevator.onTarget() && setpoint == 0) {
    	//	return true;
    	//}
    	//System.out.println("isFinished called in SetElevator");
       return Robot.Elevator.onTarget();
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    	//System.out.println("end called in SetElevator");
    	Robot.Elevator.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    	//System.out.println("interrupted called in SetElevator");
    	Robot.Elevator.disable();
    }
}
