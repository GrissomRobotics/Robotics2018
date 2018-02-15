// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc3319.MyRobot;

import org.usfirst.frc3319.custom.ADIS16448_IMU;
import org.usfirst.frc3319.custom.Adis;
import org.usfirst.frc3319.custom.MecanumPIDGyro;
import org.usfirst.frc3319.custom.UltrasonicWrapper;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalSource;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SendableBase;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.Solenoid;

import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    public static SpeedController driveTrainLeftFront;
    public static SpeedController driveTrainRightFront;
    public static SpeedController driveTrainLeftRear;
    public static SpeedController driveTrainRightRear;
    public static MecanumDrive driveTrainMecanumDrive;
	public static Solenoid grab;
	public static Solenoid release;
	public static Solenoid gripUp;
	public static Solenoid gripDown;
	public static Compressor compressor;
	public static SpeedController elevator;
	public static Encoder elevatorEncoder;
	public static Ultrasonic ultraSonicFront;
	public static Ultrasonic ultraSonicBack;
	public static UltrasonicWrapper ultraSonic;
	public static DigitalInput limitSwitchUpper;
	public static DigitalInput limitSwitchLower;
	public static Adis  gyro;
	public static PIDController gyroController;
	public static SpeedController winch;
	public static SpeedController hook;
	

    public static void init() {
        
        driveTrainLeftFront = new Talon(0);
        ((SendableBase) driveTrainLeftFront).setName("DriveTrain", "leftFront");
        driveTrainLeftFront.setInverted(true);
        
        driveTrainRightFront = new Talon(1);
        ((SendableBase) driveTrainRightFront).setName("DriveTrain", "rightFront");
        driveTrainRightFront.setInverted(false);
        
        driveTrainLeftRear = new Talon(2);
        ((SendableBase) driveTrainLeftRear).setName("DriveTrain", "leftRear");
        driveTrainLeftRear.setInverted(true);
        
        driveTrainRightRear = new Talon(3);
        ((SendableBase) driveTrainRightRear).setName("DriveTrain", "rightRear");
        driveTrainRightRear.setInverted(false);
        
        driveTrainMecanumDrive = new MecanumDrive(driveTrainLeftFront, driveTrainLeftRear,
              driveTrainRightFront, driveTrainRightRear);
        
        ((SendableBase) driveTrainMecanumDrive).setName("DriveTrain", "Mecanum Drive");
        driveTrainMecanumDrive.setSafetyEnabled(true);
        driveTrainMecanumDrive.setExpiration(0.1);
        driveTrainMecanumDrive.setMaxOutput(1.0);
                
        ultraSonicFront = new Ultrasonic(6, 7);
        ultraSonicBack = new Ultrasonic(8, 9);
        ultraSonic = new UltrasonicWrapper(ultraSonicFront, ultraSonicBack);
        
        gyro = new Adis();
        ((SendableBase) gyro).setName("DriveTrain","Gyro");
        
        gyroController = new PIDController(0.5, 0, 2.0, gyro, new MecanumPIDGyro(driveTrainMecanumDrive));
        ((SendableBase) gyroController).setName("DriveTrain", "gyroController");
        
        elevator = new Talon (4);
        ((SendableBase) elevator).setName("Elevator" , "elevator");
        elevator.setInverted(false);
        
        elevatorEncoder = new Encoder(new DigitalInput(0), new DigitalInput(1));
        ((SendableBase) elevatorEncoder).setName("Elevator", "elevatorEncoder");
        
        limitSwitchUpper = new DigitalInput(2);
        ((SendableBase) limitSwitchUpper).setName("Elevator", "limitSwitchUpper");
        
        limitSwitchLower = new DigitalInput(3);
        ((SendableBase) limitSwitchLower).setName("Elevator", "limitSwitchLower");
                
        
        compressor = new Compressor(0);
        compressor.setClosedLoopControl(true);
		
		grab = new Solenoid(0);
		((SendableBase) grab).setName("Gripper", "grab");
		grab.set(false);
		
		release = new Solenoid(1);
		((SendableBase) release).setName("Gripper", "release");
		release.set(false);
		
		gripUp = new Solenoid(2);
		((SendableBase) gripUp).setName("Gripper", "gripUp");
		gripUp.set(false);
		
		gripDown = new Solenoid(3);
		((SendableBase) gripDown).setName("Gripper", "gripDown");
		gripDown.set(false);
		
		
		//Note: You may wish to invert these depending on which direction they go
		winch = new Talon(5);
		((SendableBase) winch).setName("Climber", "winch");
		winch.setInverted(false);
		
		hook = new Talon(6);
		((SendableBase) winch).setName("Climber", "hook");
		hook.setInverted(false);
    }
}
