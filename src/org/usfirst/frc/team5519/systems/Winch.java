package org.usfirst.frc.team5519.systems;

import org.usfirst.frc.team5519.util.Hardware;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;

public class Winch extends RobotSystem
{
	// fields and global variables
	private final Talon winchContMotor;			// motor controller for winch control motor
	private final Victor winchReleaseMotor;		// motor controller for winch release motor

	// limit switch to control the movement of the winch
	private final DigitalInput limitSwitch;

	// booleans
	private boolean canControlWinch;			// boolean to decide if the winch motor can be controlled or not	
	private boolean isRightTime;				// boolean to decide if the winch can be released or not

	// motor value for the winch release motor
	private int releaseMotorValue;
	
	// Button Listener for the driver joystick
	private final ButtonListener secondaryListener; 

	// constructor initializes the system
	public Winch(ButtonListener primaryListener, ButtonListener secondaryListener)
	{
		// call the parent constructor and provide it with primary listener
		super(primaryListener);
		
		// set the secondary listener
		this.secondaryListener = secondaryListener;

		// initialize winch release motor
		winchReleaseMotor = Hardware.winchRel;

		// initialize winch control motor
		winchContMotor = Hardware.winchCont;

		// initialize limit switch
		limitSwitch = Hardware.limitSwitch;

		// set canControlWinch boolean false so that winch cannot be controlled
		canControlWinch = false;
		
		// set isRightTime to false because its not the right time to release the winch
		isRightTime = false;

		// release motor will not move that's why its value is 0
		releaseMotorValue = 0;
	}
	
	@Override
	public void update()
	{
		// if it is not the right time, do no do anything and return
		if (!isRightTime) return;
		
		// if winch release button is pressed
		if (secondaryListener.isButtonPressed(ButtonListener.BUTTON11))
		{
			// set the release motor value to 1
			releaseMotorValue = 1;

			// make canControlWinch motor true so that winch can be controlled
			canControlWinch = true;
		}
		// if button 11 is not pressed, stop the release motor value
		else
			releaseMotorValue = 0;

		// only control the winch if it is releases
		if (canControlWinch)
		{
			// if button 1 is pressed, run the winch motor forward full speed
			if (listener.isButtonPressed(ButtonListener.BUTTON1))
				motorValue = 1;
			// if button 4 is pressed, run the winch motor backwards full speed
			else if (listener.isButtonPressed(ButtonListener.BUTTON4))
				motorValue = -1;
			// if no button is pressed, stop the winch motor
			else
				motorValue = 0;

			// check if limit switch is pressed or not
			if (motorValue == 1 && limitSwitch.get())
				motorValue = 0;
		}

	}

	@Override
	public void setValues()
	{
		// if it is not the right time, return and do not set any value
		if (!isRightTime) return;
		
		// set the value of winch release motor
		winchReleaseMotor.set(releaseMotorValue);
		// set the value of winch control motor
		winchContMotor.set(motorValue);
	}
	
	// setRightTime method sets the rightTime boolena true or false
	public void setRightTime(boolean isRightTime)
	{
		// set the boolena value
		this.isRightTime = isRightTime;
	}

}
