package org.usfirst.frc.team5519.util;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.VictorSP;

public class Hardware
{
	// Joysticks to be used for input
	public static Joystick driver = new Joystick(Constants.PORT0);			// joystick to drive
	public static Joystick controller = new Joystick(Constants.PORT1);		// joystick to control
	
	// Motor Controllers to be used to control motor speed
	public static Talon dFrontLeft = new Talon(Constants.P_CHANNEL0);			// controller for front left motor
	public static Talon dRearLeft = new Talon(Constants.P_CHANNEL1);			// controller for rear left motor
	public static Talon dFrontRight = new Talon(Constants.P_CHANNEL2);	// controller for front right motor
	public static Talon dRearRight = new Talon(Constants.P_CHANNEL3);		// controller for rear right motor
	
	// Motor Controller to be used to control the shooter
	public static VictorSP shooter = new VictorSP(Constants.P_CHANNEL6);
	
	// Motor Controller to be used to control the winch control
	public static Talon winchCont = new Talon(Constants.P_CHANNEL5);
	
	// Motor Controller to be used to control the winch release
	public static Victor winchRel = new Victor(Constants.P_CHANNEL7);
	
	// Limit Switch to be used to control the movement of winch
	public static DigitalInput limitSwitch = new DigitalInput(Constants.D_CHANNEL0);
}
