package org.usfirst.frc3319.MyRobot.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc3319.MyRobot.OI;
import org.usfirst.frc3319.MyRobot.Robot;

/**
 *
 */
public class ControlElevator extends Command {
	//TODO Ramp function

	public static int setPoint = -1;	//0 means default, 1 means switch, 2 means scale
	private static int oldSetPoint = -1;
	private double[] setPoints = {OI.DEFAULT_HEIGHT, OI.SWITCH_HEIGHT, OI.SCALE_HEIGHT}; //Default, Switch, Scale. Add more to the list in descending order.
    private int waiting = 0;
    private SetElevatorSetpoint set;
    
    public ControlElevator() {
        requires(Robot.Elevator);
        setInterruptible(true);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	//POV Setting Setpoints
    	
    	if (Robot.oi.getPOV() == -1.0) {
       		setPoint += waiting;
       		waiting = 0;
       	}
    	else if(Robot.oi.getPOV() == 0.0 && setPoint < 2) {
    		waiting = 1;
       	}
        else if(Robot.oi.getPOV() == 180.0 && setPoint > 0) {
        	waiting = -1;
        }
    	
    	if (setPoint != oldSetPoint) {
        	set = new SetElevatorSetpoint(setPoints[setPoint]);
        	set.start();
    	}
    	
    		
    	oldSetPoint = setPoint;
    	Robot.Elevator.enable();
    	
    	//Manual Control with AnalogStick
    	if (Robot.oi.getAxis(5) > 0.1) {  //down
    		Robot.Elevator.disable();
    		Robot.Elevator.setSpeed((Robot.oi.getAxis(5)/3)-0.12);
    	}
    	else if (Robot.oi.getAxis(5) < 0.1) {  //up
    		Robot.Elevator.disable();
    		Robot.Elevator.setSpeed((Robot.oi.getAxis(5)/1.5)-0.12);
    	}
    	else if (set.isFinished()){
    		Robot.Elevator.disable();
    		Robot.Elevator.stop();
    	}
    	
    	//Limit Switch Logic
    	if (Robot.Elevator.getLimitSwitchUpper() && Robot.oi.getAxis(5) < 0.1) {
    		Robot.Elevator.stop();
    	}
    	else if (Robot.Elevator.getLimitSwitchLower() && Robot.oi.getAxis(5) > -0.1) {
    		Robot.Elevator.off();
    	}
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    	Robot.Elevator.setSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    	Robot.Elevator.setSpeed(0);
    }
    
}
