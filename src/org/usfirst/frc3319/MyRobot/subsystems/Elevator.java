package org.usfirst.frc3319.MyRobot.subsystems;

import org.usfirst.frc3319.MyRobot.RobotMap;
import org.usfirst.frc3319.MyRobot.commands.ControlElevator;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SendableBase;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Elevator extends PIDSubsystem {


    private final SpeedController elevator = RobotMap.elevator;
    private final Encoder elevatorEncoder = RobotMap.elevatorEncoder;
    private final DigitalInput limitSwitchUpper = RobotMap.limitSwitchUpper;
    private final DigitalInput limitSwitchLower = RobotMap.limitSwitchLower;
    
    public Elevator() {
    	super("Elevator", 0.5,0.0,2.0);
    	setAbsoluteTolerance(100);
    	getPIDController().setContinuous(false);
    	setOutputRange(-0.8, 0.8);
    	zeroEncoders();
    }
    
   

    @Override
    public void initDefaultCommand() {
       
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ControlElevator());
    }

    @Override
    public void periodic() {
    	
    }


	@Override
	protected double returnPIDInput() {
		return elevatorEncoder.get();
	}


	@Override
	protected void usePIDOutput(double output) {
		//For output, positive is down, negative is up
		
		if (output > 0 && limitSwitchLower.get()) {} //If the output is trying to go down, and the lower limit switch is depressed, do not move
		else if (output < 0 && limitSwitchUpper.get()) {} //If the output is trying to go up, and the upper limit switch is depressed, do not move
		else { //If neither of those is true, write the output to the motor
			elevator.pidWrite(output);
		}
	}
	
	public void zeroEncoders() {
		elevatorEncoder.reset();
	}
	
	public void setSpeed(double speed)
    {
    	usePIDOutput(speed);
    }
	
	public boolean getLimitSwitchUpper() {
		return limitSwitchUpper.get();
	}
	
	public boolean getLimitSwitchLower() {
		return limitSwitchLower.get();
	}
	
	public void setPID(double p, double i, double d) {
		this.getPIDController().setPID(p, i, d);
	}
	
}

