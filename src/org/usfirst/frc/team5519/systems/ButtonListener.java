package org.usfirst.frc.team5519.systems;

import edu.wpi.first.wpilibj.Joystick;

public class ButtonListener
{
	// field and global variables
	
	// object of joystick
	private final Joystick stick;
	
	// array for buttons pressed
	private final boolean [] buttonsPressed;
	
	// constants for joystick buttons
	private static final int TOTAL_BUTTONS = 12;
	public static final int BUTTON1 = 1;
	public static final int BUTTON2 = 2;
	public static final int BUTTON3 = 3;
	public static final int BUTTON4 = 4;
	public static final int BUTTON5 = 5;
	public static final int BUTTON6 = 6;
	public static final int BUTTON7 = 7;
	public static final int BUTTON8 = 8;
	public static final int BUTTON9 = 9;
	public static final int BUTTON10 = 10;
	public static final int BUTTON11 = 11;
	
	
	// constructor initializes Joystick
	public ButtonListener(Joystick stick)
	{
		// initialize the stick field
		this.stick = stick;
		
		// initialize buttonPressed array
		buttonsPressed = new boolean[TOTAL_BUTTONS];
	}
	
	// listen method check if button is pressed or not
	public void listen()
	{
		// go through the array and check which button is pressed
		for (int btn = 1; btn < buttonsPressed.length; btn++)
			buttonsPressed[btn] = stick.getRawButton(btn);
	}
	
	// isButtonPressed method returns true or false of a button is pressed
	public boolean isButtonPressed(int button)
	{
		return buttonsPressed[button];
	}
}
