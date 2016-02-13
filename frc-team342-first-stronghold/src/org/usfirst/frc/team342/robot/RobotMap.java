package org.usfirst.frc.team342.robot;

import edu.wpi.first.wpilibj.CANTalon;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	/**
	 * The default value for motors which are not currently on the robot. This
	 * should throw an exception if the motor is used in code but not wired.
	 */
	public static final int UNINITIALIZED_MOTOR = 99;
	// Drive Motors
	public static final int TALON_FRONT_RIGHT_WHEEL_CAN = 30;
	public static final int TALON_FRONT_LEFT_WHEEL_CAN = 31;
	public static final int TALON_BACK_RIGHT_WHEEL_CAN = 32;
	public static final int TALON_BACK_LEFT_WHEEL_CAN = 33;
	// May Become Unused \/
	public static final int TALON_TURNING_WHEEL_CAN = 27;

	// Shooter motors
	public static final int TALON_COLLOECTOR_MOTOR_CAN = 29;
	public static final int TALON_SHOOTER_MOTOR_CAN = 28;
	public static final int TALON_ARM_CAN = UNINITIALIZED_MOTOR;

	// Used on relay to only turn full speed or full negative speed.
	public static final int SPIKE_MOTOR_RELAY = UNINITIALIZED_MOTOR;

	// Ultrisonic Sensor
	public static final int ULTRASONIC_ANALOG = 1;

}
