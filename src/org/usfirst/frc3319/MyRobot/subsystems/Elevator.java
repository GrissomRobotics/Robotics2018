package org.usfirst.frc3319.MyRobot.subsystems;

import org.usfirst.frc3319.MyRobot.RobotMap;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
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
    
    public Elevator() {
    	super("Elevator", 0.5, 0,2.0);
    	setAbsoluteTolerance(100);
    	getPIDController().setContinuous(false);
    	setOutputRange(-0.8, 0.8);
    	zeroEncoders();
    	((SendableBase) this).setName("Elevator", "PID Controller");
    }
   

    @Override
    public void initDefaultCommand() {
       
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
    	SmartDashboard.putString("Elevator Encoder", Double.toString(returnPIDInput()));
    }


	@Override
	protected double returnPIDInput() {
		return elevatorEncoder.get();
	}


	@Override
	protected void usePIDOutput(double output) {
		elevator.pidWrite(output);
		System.out.println(output);
	}
	
	public void zeroEncoders() {
		elevatorEncoder.reset();
	}
	
	public void setSpeed(double speed)
    {
    	elevator.set(speed);
    }
}

