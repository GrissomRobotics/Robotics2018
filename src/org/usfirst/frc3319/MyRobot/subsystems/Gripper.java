// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc3319.MyRobot.subsystems;

import org.usfirst.frc3319.MyRobot.RobotMap;
import org.usfirst.frc3319.MyRobot.commands.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 */
public class Gripper extends Subsystem {

    private final Solenoid release = RobotMap.release;
	private final Solenoid grab = RobotMap.grab;
	private final Solenoid gripUp = RobotMap.gripUp;
	private final Solenoid gripDown = RobotMap.gripDown;
	private final Compressor compressor = RobotMap.compressor;
	


    @Override
    public void initDefaultCommand() {
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop
    	release.set(false);
    	grab.set(false);
    	gripUp.set(false);
    	gripDown.set(false);
    	if(SmartDashboard.getBoolean("Compressor On", false)) {
    		compressor.start();
    	} else {
    		compressor.stop();
    	}
    }
    
    public void close() {
    	release.set(false);
    	grab.set(true);
    }
	
	public void open() {
		grab.set(false);
		release.set(true);
	}
	
	public void raise() {
		gripDown.set(false);
		gripUp.set(true);
	}
	
	public void lower() {
		gripUp.set(false);
		gripDown.set(true);
	}
	
	public boolean isLowered() {
		return (gripDown.get());
	}
	
	public boolean isClosed() {
		return (grab.get());
	}
}

