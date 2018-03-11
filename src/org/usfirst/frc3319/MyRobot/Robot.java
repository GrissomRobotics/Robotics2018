package org.usfirst.frc3319.MyRobot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc3319.MyRobot.commands.*;
import org.usfirst.frc3319.MyRobot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in 
 * the project.
 */
public class Robot extends TimedRobot {

    Command autonomousCommand;
    SendableChooser<Integer> chooser = new SendableChooser<>();

    public static OI oi;
    public static DriveTrain DriveTrain;
    public static Gripper Gripper;
    public static Elevator Elevator;
    public static Climber Climber;
    
    public String gameSpecificData;
    
    //private final Ultrasonic ultraSonic = RobotMap.ultraSonicFront;


    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
        RobotMap.init();
        DriveTrain = new DriveTrain();
        Gripper = new Gripper();
        Elevator = new Elevator();
        Climber = new Climber();
        
        //Send the commands and subsystems to the dashboard
        SmartDashboard.putData(DriveTrain);
        SmartDashboard.putData(Gripper);
        SmartDashboard.putData(Elevator);
        SmartDashboard.putData(Climber);
        
        SmartDashboard.putData(Scheduler.getInstance());
        

        // OI must be constructed after subsystems. If the OI creates Commands
        //(which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        oi = new OI();
        
        Robot.DriveTrain.resetGyro();

        // Add commands to Autonomous Sendable Chooser
        
        chooser.addDefault("Autonomous Command Outer Right", 1);
        chooser.addObject("Autonomous Command Center", 3);
        chooser.addObject("Autonomous Command Outer Left", 2);
        chooser.addObject("Autonomous Command Inner Right",4);
        chooser.addObject("Autonomous Command Inner Left", 5);
        
        //The scale autonomous command just goes forward a greater distance
        chooser.addObject("Autonomous Command Scale", 6);
        


        SmartDashboard.putData("Auto mode", chooser);
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    @Override
    public void disabledInit(){

    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override        
    public void autonomousInit() {
        if (chooser.getSelected() == null) {} //Shouldn't happen, this would happen in the event that SmartDashboard was not initialized
        else { //we received a request from dashboard, now create the command to match
<<<<<<< HEAD
            autonomousCommand = new AutonomousCommand(DriverStation.getInstance().getGameSpecificMessage(), (int) chooser.getSelected());
=======
            autonomousCommand = new AutonomousCommand(DriverStation.getInstance().getGameSpecificMessage(), (int) chooser.getSelected())
>>>>>>> 7031d98bb2437153c3f905e9cf8e9de2e4e181d4
        }
    	DriveTrain.resetGyro();
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        RobotMap.gyroController.disable();
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        
        //System.out.println("test");
        //System.out.println(ultraSonic.getRangeInches());
    }
}