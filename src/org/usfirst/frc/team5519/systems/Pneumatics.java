package org.usfirst.frc.team5519.systems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Pneumatics extends RobotSystem
{
	// field and global variables
	
	private DoubleSolenoid solenoid;		// object for controlling solenoid
	private Compressor compressor;			// object for the compressor
	
	// constants
	private static final int CHANNEL0 = 0;
	private static final int CHANNEL1 = 1;

	// constructor sets up the Pneumatics system
	public Pneumatics(ButtonListener listener)
	{
		// call the parent constructor and initialize listener
		super(listener);

		// initialize solenoid in channel 0 and 1
		this.solenoid = new DoubleSolenoid(CHANNEL0, CHANNEL1);
		
		// initialize compressor
		this.compressor = new Compressor();
		
		// start the compressor
		compressor.start();
	}

	@Override
	public void update()
	{	
		// do not update anything
		// return without doing anything
		return;
	}
	
	@Override
	public void setValues()
	{
		// if button 2 is pressed, turn on the solenoid
		if (listener.isButtonPressed(ButtonListener.BUTTON2))
			solenoid.set(Value.kForward);
		
		// if button 3 is pressed, turn off the solenoid
		else if (listener.isButtonPressed(ButtonListener.BUTTON3))
			solenoid.set(Value.kReverse);
		// if no button is pressed, turn off the solenoid
		else
			solenoid.set(Value.kOff);
	}
}
