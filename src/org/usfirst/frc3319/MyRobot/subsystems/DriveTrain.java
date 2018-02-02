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
import edu.wpi.first.wpilibj.AnalogInput;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class DriveTrain extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS


    private final SpeedController leftFront = RobotMap.driveTrainLeftFront;
    private final SpeedController rightFront = RobotMap.driveTrainRightFront;
    private final SpeedController leftRear = RobotMap.driveTrainLeftRear;
    private final SpeedController rightRear = RobotMap.driveTrainRightRear;
    private final MecanumDrive mecanumDrive = RobotMap.driveTrainMecanumDrive;
    private final AnalogInput ultraSonic = RobotMap.ultraSonic;


    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new DriveWithJoystick());

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }
    
    public void cartesianDrive(double xValue, double yValue, double rotationValue, double gyroCorrection) {
    	//Negate xValue to resolve strafing direction issue
    	mecanumDrive.driveCartesian(-xValue, yValue, rotationValue, gyroCorrection);
    }
    
    public void stop() {
    	leftFront.set(0);
    	rightFront.set(0);
    	leftRear.set(0);
    	rightRear.set(0);

    }
    
    public double getUltraSonicInches() {
    	//Multiply by this number to convert the voltage to inches
    	return ultraSonic.getVoltage()*25997.9529;
    }
    
}

