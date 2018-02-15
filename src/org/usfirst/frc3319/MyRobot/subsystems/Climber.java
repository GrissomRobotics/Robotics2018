package org.usfirst.frc3319.MyRobot.subsystems;

import org.usfirst.frc3319.MyRobot.RobotMap;
import org.usfirst.frc3319.MyRobot.commands.ControlElevator;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SendableBase;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Climber extends Subsystem {


    private final SpeedController winch = RobotMap.winch;
    private final SpeedController hook = RobotMap.hook;
    
   

    @Override
    public void initDefaultCommand() {
       
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
    }
	
	public void engageWinch(double speed)
    {
    	winch.set(speed);
    }
	
	public void engageHook(double speed) {
		hook.set(speed);
	}
	
	public void stop() {
		engageWinch(0);
		engageHook(0);
	}
}

