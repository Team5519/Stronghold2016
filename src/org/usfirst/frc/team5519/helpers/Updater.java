package org.usfirst.frc.team5519.helpers;

import java.util.ArrayList;

import org.usfirst.frc.team5519.systems.RobotSystem;
import org.usfirst.frc.team5519.systems.Winch;

public class Updater
{
	// fields and global variables
	
	// array list of all the systems on the robot
	private final ArrayList<RobotSystem> systems;
	
	// object of winch system on the robot
	private Winch winch;
	
	// timer for the winch system
	private long startTime;					// start timer when the game started
	private long elapsedTime;				// elapsedTime as game progresses
	private final long desiredTime;			// desired time for which winch cannot start
	
	public Updater(long desiredTime)
	{
		// initialize systems arrayList
		this.systems = new ArrayList<>();
	
		// set the desired time
		this.desiredTime = desiredTime;
	}
	
	// startTimer method finds the start time
	public void startTimer()
	{
		// find the start time using the current system timer
		startTime = System.nanoTime();
	}
	
	// setWinch method sets the winch object
	public void setWinch(Winch winch)
	{
		// store the winch object provided
		this.winch = winch;
	}
	
	// clear method clears all the systems stored in the array list
	public void clear()
	{
		// clear the array list
		systems.clear();
	}

	// add subsystem method adds systems to the array list
	public void addSystem(RobotSystem system)
	{
		// if a system is not null, add the system to the array list
		if (system != null)
			systems.add(system);
	}

	// update method update the value of motor controllers
	public void update()
	{
		// find the elapsedTime by subtracting start time from the current system time
		elapsedTime = System.nanoTime() - startTime;
		
		// if elapsed time is equal to desired time, set the right time boolean true in the winch system
		if (elapsedTime >= desiredTime)
			winch.setRightTime(true);
		
		// go through all the systems and update them
		for (int count = 0; count < systems.size(); count++)
			systems.get(count).update();
	}
	
	// setValues method sets the value on the motor controllers
	public void setValues()
	{
		// go through all the systems and set the value for each motor controller in the system
		for (int count = 0; count < systems.size(); count++)
			systems.get(count).setValues();
	}

	public void performSetCommands()
	{
	}
}
