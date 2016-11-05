package org.usfirst.frc.team5519.systems;

public abstract class RobotSystem
{
	// fields
	
	// object of ButtonListener
	protected final ButtonListener listener;
	
	// variable to store motor value
	protected double motorValue;
	
	// constructor initializes fields
	public RobotSystem(ButtonListener listener)
	{
		// set the listener
		this.listener = listener;
		// set the motor value
		this.motorValue = 0;
	}
	
	// constructor with no parameter 
	public RobotSystem()
	{
		// set the listener to null
		this.listener = null;
	}
	
	// update method for every system
	public abstract void update();
	
	// setValues method for every system
	public abstract void setValues();
}
