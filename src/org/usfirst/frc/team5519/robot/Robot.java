
package org.usfirst.frc.team5519.robot;

import org.usfirst.frc.team5519.helpers.Updater;
import org.usfirst.frc.team5519.systems.ButtonListener;
import org.usfirst.frc.team5519.systems.Drive;
import org.usfirst.frc.team5519.systems.Shooter;
import org.usfirst.frc.team5519.systems.Winch;
import org.usfirst.frc.team5519.util.Hardware;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;

public class Robot extends IterativeRobot 
{
	// fields and global variables

	// main
	private State robotState;				// stores the state of the robot
	private Updater updater;				// updates the systems on the robot
	private Joystick driveCont;				// joystick used to drive the robot
	private Joystick otherCont;				// joystick used to control other tasks of the robot
	private ButtonListener contBtnList;		// button listener for controller joystick
	private ButtonListener driveBtnList;	// button listener for drive joystick

	// systems
	private Drive drive;					// drive system controls the driving of the robot
	private Shooter shooter;				// shooter system controls the shooter on the robot
	private Winch winch;					// winch system controls the winch on the robot
	
	// time
	private long desiredTime;				// desired time for the winch to not start

	public void robotInit()
	{
		// set the current state of the robot to be disabled
		setRobotState(State.DISABLED);
		
		// set the desired time to 5 seconds
		this.desiredTime = secToNano(5);

		// initialize fields
		updater = new Updater(desiredTime);				// initialize updater
		driveCont = Hardware.driver;					// initialize drive joystick
		otherCont = Hardware.controller;				// initialize control joystick
		contBtnList = new ButtonListener(otherCont);	// initialize button listener for control joystick
		driveBtnList = new ButtonListener(driveCont);	// initialize button listener for drive joystick
		drive = new Drive();							// initialize drive system
		shooter = new Shooter(contBtnList);				// initialize shooter system
		winch = new Winch(contBtnList, driveBtnList);	// initialize winch system
	}

	// secToNano method converts seconds into nanoSeconds
	private long secToNano(long time)
	{
		// convert into nanoSeconds and return the time
		return time * 1000000000;
	}

	
	public void autonomousInit() 
	{
		// set the robot state to autonomous
		setRobotState(State.AUTONOMOUS);
	}

	public void autonomousPeriodic() 
	{
		// if this is not the autonomous state, return and do not do anything
		if (getRobotState() != State.AUTONOMOUS) return;

		// perform set of commands in autonomous
		updater.performSetCommands();
	}

	public void teleopInit() 
	{
		// set the robot state to teleop
		setRobotState(State.TELEOP);

		// clear the updater
		updater.clear();

		// add systems to the updater
		updater.addSystem(drive);		// add drive system
		updater.addSystem(shooter);		// add shooter system
		updater.addSystem(winch);		// add winch system
		
		updater.setWinch(winch);		// provide updater witch the winch object
		updater.startTimer();			// start the timer in the updater
	}

	public void teleopPeriodic() 
	{
		// if this is not the teleop state, return and do not do anything
		if (getRobotState() != State.TELEOP) return;
		
		
		// listen to buttons using button listeners
		contBtnList.listen();
		driveBtnList.listen();

		// update all the systems using updater
		updater.update();
		
		// set the values for the motor
		updater.setValues();
	}

	public void testPeriodic() 
	{
		drive.update();
	}

	// getRobotState method returns the state of the robot
	public State getRobotState()
	{
		return this.robotState;
	}

	// setRobotState method sets the state of the robot
	public void setRobotState(State robotState)
	{
		this.robotState = robotState;
	}

}
