package org.usfirst.frc.team5519.systems;

import org.usfirst.frc.team5519.util.Hardware;

import edu.wpi.first.wpilibj.VictorSP;

public class Shooter extends RobotSystem
{
	// fields
	private final VictorSP shooterMotor;		// shooter motor controller
	
	// constructor initializes button listener
	public Shooter(ButtonListener listener)
	{
		// call super constructor to initialize listener
		super(listener);
		
		// initialize shooter
		this.shooterMotor = Hardware.shooter;
	} 
	
	@Override
	public void update()
	{	
		// if button 1 is pressed, run the shooter motor backward at 20% the speed
		if (listener.isButtonPressed(ButtonListener.BUTTON1))
			motorValue = -.2;
		// if button 2 is pressed, run the shooter motor forward at 20% the speed
		else if (listener.isButtonPressed(ButtonListener.BUTTON2))
			motorValue = .2;
		// if button 5 is pressed, run the shooter motor forward full speed
		else if (listener.isButtonPressed(ButtonListener.BUTTON5))
			motorValue = 1;
		// if button 6 is pressed, run the shooter motor backwards full speed
		else if (listener.isButtonPressed(ButtonListener.BUTTON6))
			motorValue = -1;
		// if no button is pressed, stop the shooter motor
		else
			motorValue = 0;			
	}
	
	@Override
	public void setValues()
	{
		// set the shooter motor value based on the motor value
		shooterMotor.set(motorValue);
	}
}