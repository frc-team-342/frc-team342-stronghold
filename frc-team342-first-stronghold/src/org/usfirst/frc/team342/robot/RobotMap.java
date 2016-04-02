package org.usfirst.frc.team342.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	/*
	 * The default value for motors which are not currently on the robot. This
	 * should throw an exception if the motor is used in code but not wired.
	 */

	// Test Board \/ // Drive Motors
	public static final int FRONT_LEFT_WHEEL_CAN_TALON = 1;
	public static final int FRONT_RIGHT_WHEEL_CAN_TALON = 2;
	public static final int BACK_LEFT_WHEEL_CAN_TALON = 3;
	public static final int BACK_RIGHT_WHEEL_CAN_TALON = 4;

	// Shooter motors
	public static final int COLLECTOR_MOTOR_CAN_TALON = 6;
	public static final int SHOOTER_MOTOR_CAN_TALON = 7;
	public static final int ARM_CAN_TALON = 5;

	public static final int ARM_LIMIT_DIO = 1;
	public static final int ARM_LIMIT_DOWN = 2;

}
