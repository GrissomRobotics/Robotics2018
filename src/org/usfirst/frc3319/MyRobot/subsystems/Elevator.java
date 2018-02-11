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
    private final DigitalInput limitSwitch = RobotMap.limitSwitch;
    
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
    	SmartDashboard.putString("Elevator Encoder", Double.toString(returnPIDInput()));
    	SmartDashboard.putBoolean("Limit Switch", limitSwitch.get());
    	if (!limitSwitch.get()) {
    		//If the limit switch is not depressed, the elevator is about to go off of the tracks, so disable
    		disable();
    	}
    }


	@Override
	protected double returnPIDInput() {
		return elevatorEncoder.get();
	}


	@Override
	protected void usePIDOutput(double output) {
		elevator.pidWrite(output);
	}
	
	public void zeroEncoders() {
		elevatorEncoder.reset();
	}
	
	public void setSpeed(double speed)
    {
    	elevator.set(speed);
    }
	
	public boolean getLimitSwitch() {
		return limitSwitch.get();
	}
	
	public void setPID(double p, double i, double d) {
		this.getPIDController().setPID(p, i, d);
	}
	
}

