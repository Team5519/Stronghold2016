package org.usfirst.frc.team5519.helpers;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

public class ArcadeDrive
{
	// fields and global variables

	// joystick related
	private final Joystick stick;

	// robot drive
	private final RobotDrive drive;

	// speed controllers related
	private final SpeedController SFL;		// front left
	private final SpeedController SFR;		// front right
	private final SpeedController SRL;		// rear left
	private final SpeedController SRR;		// rear right

	// default constants
	private static final int DF_JOYSTICK_PORT = 0;
	private static final int DF_SFL_CHANNEL = 0;
	private static final int DF_SFR_CHANNEL = 1;
	private static final int DF_SRL_CHANNEL = 2;
	private static final int DF_SRR_CHANNEL = 3;

	private final static int STOP_SPEED = 0;
	private final static int MAX_SPEED = 1;
	private final static int MIN_SPEED = -1;

	// others
	private boolean isInverted;

	// constructors
	public ArcadeDrive()
	{
		// create Talon Speed Controllers with LF = 0, RF = 1, LR = 2 and RR = 3
		// use the joystick plugged in port 0
		this(null, null, null, null, null, DF_JOYSTICK_PORT, DF_SFL_CHANNEL, DF_SFR_CHANNEL,
				DF_SRL_CHANNEL, DF_SRR_CHANNEL);
	}

	// only joystick provided
	public ArcadeDrive(Joystick stick)
	{
		// create Talon Speed Controllers with LF = 0, RF = 1, LR = 2 and RR = 3
		// use the joystick provided
		this(stick, null, null, null, null, DF_JOYSTICK_PORT, DF_SFL_CHANNEL, DF_SFR_CHANNEL,
				DF_SRL_CHANNEL, DF_SRR_CHANNEL);
	}

	// provide joystick port
	public ArcadeDrive(int joystickPort)
	{
		// create Talon Speed Controllers with LF = 0, RF = 1, LR = 2 and RR = 3
		// use the joystick port provided
		this(null, null, null, null, null, joystickPort, DF_SFL_CHANNEL, DF_SFR_CHANNEL,
				DF_SRL_CHANNEL, DF_SRR_CHANNEL);
	}

	// provide speed controllers' channels
	public ArcadeDrive(final int frontLeftMotor, final int frontRightMotor,
			final int rearLeftMotor, final int rearRightMotor)
	{
		// create default joystick
		// use the channels to make speed controllers
		this(null, null, null, null, null, DF_JOYSTICK_PORT, frontLeftMotor, frontRightMotor,
				rearLeftMotor, rearRightMotor);
	}

	// provide speed controllers
	public ArcadeDrive(final SpeedController frontLeftMotor, final SpeedController frontRightMotor,
			final SpeedController rearLeftMotor, final SpeedController rearRightMotor)
	{
		// create default joystick
		// use speed controllers
		this(null, frontLeftMotor, frontRightMotor, rearLeftMotor, rearRightMotor, DF_JOYSTICK_PORT,
				DF_SFL_CHANNEL, DF_SFR_CHANNEL, DF_SRL_CHANNEL, DF_SRR_CHANNEL);
	}

	// provide Joystick and speed controllers
	public ArcadeDrive(final Joystick joystick, final SpeedController frontLeftMotor, 
			final SpeedController frontRightMotor, final SpeedController rearLeftMotor, 
			final SpeedController rearRightMotor)
	{
		// create default joystick
		// use speed controllers
		this(joystick, frontLeftMotor, frontRightMotor, rearLeftMotor, rearRightMotor, DF_JOYSTICK_PORT,
				DF_SFL_CHANNEL, DF_SFR_CHANNEL, DF_SRL_CHANNEL, DF_SRR_CHANNEL);
	}

	// provide all ports
	public ArcadeDrive(int joystickPort, int frontLeftMotor, int frontRightMotor,
			int rearLeftMotor, int rearRightMotor)
	{
		// call the main constructor
		this(null, null,null, null, null, joystickPort, frontLeftMotor, frontRightMotor,
				rearLeftMotor, rearRightMotor);
	}

	// main constructor
	private ArcadeDrive(Joystick joystick, SpeedController sfl, SpeedController sfr, SpeedController srl, 
			SpeedController srr, int joystickPort, int fl_cont, int fr_cont, int rl_cont, int rr_cont)
	{
		// if joystick is null, create joystick
		if (joystick == null)
			this.stick = new Joystick(joystickPort);
		else
			this.stick = joystick;

		// check if front left speed controller is null
		// if it is create a Talon Speed Controller at the provided port
		if (sfl == null)
			this.SFL = new Talon(fl_cont);
		else
			this.SFL = sfl;

		// check if front right speed controller is null
		// if it is create a Talon Speed Controller at the provided port
		if (sfr == null)
			this.SFR = new Talon(fr_cont);
		else
			this.SFR = sfr;

		// check if rear left speed controller is null
		// if it is create a Talon Speed Controller at the provided port
		if (srl == null)
			this.SRL = new Talon(rl_cont);
		else
			this.SRL = srl;

		// check if rear right speed controller is null
		// if it is create a Talon Speed Controller at the provided port
		if (srr == null)
			this.SRR = new Talon(rr_cont);
		else
			this.SRR = srr;

		// create RobotDrive to be used
		drive = new RobotDrive(SFL, SRL, SFR, SRR);

		isInverted = false;
	}

	// joystick object and channels
	public ArcadeDrive(Joystick driver, int frontLeftMotor, int frontRightMotor, 
			int rearLeftMotor, int rearRightMotor)
	{
		this(driver, null, null, null, null, DF_JOYSTICK_PORT, frontLeftMotor, 
				frontRightMotor, rearLeftMotor, rearRightMotor);
	}

	// set isInverted true or false
	public void setInverted(boolean isInverted)
	{
		this.isInverted = isInverted;
	}

	// specify forward speed and turn speed
	public void drive(double forwardSpeed, double turnSpeed)
	{
		if (isInverted)
		{
			forwardSpeed *= -1;
			turnSpeed *= -1;
		}

		drive.arcadeDrive(forwardSpeed, turnSpeed);
	}

	// stop the robot
	public void stop()
	{
		this.drive(STOP_SPEED, STOP_SPEED);
	}

	// no parameter
	public void drive()
	{
		this.drive(stick.getX(), stick.getY());
	}

	// specify the percentage the robot will run 
	public void drive(double percent)
	{
		if (percent < 0.0 || percent > 1.0)
			return;

		// drive based on that percentage
		this.drive(stick.getX() * percent, stick.getY() * percent);
	}

	// robot moves forward based on the percentage
	public void goForward(double percent)
	{
		if (percent < 0.00 || percent > 1.0)
			return;

		this.drive(MAX_SPEED * percent, STOP_SPEED);
	}

	// robot moves backwards based on the percentage
	public void goBackwards(double percent)
	{
		if (percent < 0.00 || percent > 1.0)
			return;

		this.drive(MIN_SPEED * percent, STOP_SPEED);
	}

	// robot turns right based on the percentage
	public void goRight(double percent)
	{
		if (percent < 0.00 || percent > 1.0)
			return;

		this.drive(STOP_SPEED, MIN_SPEED * percent);
	}

	// robot turns left based on the percentage
	public void goLeft(double percent)
	{
		if (percent < 0.00 || percent > 1.0)
			return;

		this.drive(STOP_SPEED, MAX_SPEED * percent);
	}
}
