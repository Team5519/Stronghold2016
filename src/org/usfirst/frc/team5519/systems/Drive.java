package org.usfirst.frc.team5519.systems;

import org.usfirst.frc.team5519.helpers.ArcadeDrive;
import org.usfirst.frc.team5519.util.Hardware;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;

public class Drive extends RobotSystem
{
	// Motor Controllers
	private final Talon frontLeftMotor = Hardware.dFrontLeft;			// controller for front left motor
	private final Talon rearLeftMotor = Hardware.dRearLeft;				// controller for rear left motor
	private final Talon frontRightMotor = Hardware.dFrontRight;			// controller for front right motor
	private final Talon rearRightMotor = Hardware.dRearRight;			// controller for rear right motor

	// Joystick
	private final Joystick driveStick = Hardware.driver;				// object to get joystick input
	
	// Drive System
	private final ArcadeDrive driveTrain;								// arcadeDrive object for drive train

	// constructor initializes drive train
	public Drive()
	{	
		driveTrain = new ArcadeDrive(driveStick, frontLeftMotor, frontRightMotor, 
				rearLeftMotor, rearRightMotor);
	}

	@Override
	public void update()
	{
	}
	
	@Override
	public void setValues()
	{
		// when update method is called, call drive method from the tankDrive to drive the robot
		driveTrain.drive();
	}
}
