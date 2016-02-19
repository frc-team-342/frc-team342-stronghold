package org.usfirst.frc.team342.robot;

/** The RobotMap is a mapping from the ports sensors and actuators are wired
 * into to a variable name. This provides flexibility changing wiring, makes
 * checking the wiring easier and significantly reduces the number of magic
 * numbers floating around. */
public class RobotMap {
	/* The default value for motors which are not currently on the robot.
	 * This should throw an exception if the motor is used in code but not
	 * wired. */
	public static final int UNINITIALIZED_MOTOR = 63;


	//Drive Board \/
		// Drive Motors
		public static final int FRONT_RIGHT_WHEEL_CAN_TALON = 30;
		public static final int FRONT_LEFT_WHEEL_CAN_TALON = 31;
		public static final int BACK_RIGHT_WHEEL_CAN_TALON = 32;
		public static final int BACK_LEFT_WHEEL_CAN_TALON = 33;
	
		// TODO May Become Unused 
		public static final int TURNING_WHEEL_CAN_TALON = 27;

		// Shooter motors
		public static final int COLLECTOR_MOTOR_CAN_TALON = 29;
		public static final int SHOOTER_MOTOR_CAN_TALON = 28;
		public static final int ARM_CAN_TALON = UNINITIALIZED_MOTOR;

		// Used on relay to only turn full speed or full negative speed.
		public static final int SPIKE_MOTOR_RELAY = UNINITIALIZED_MOTOR;

		// Ultrisonic Sensor
		public static final int ULTRASONIC_ANALOG = 1;
	
		/*
	//Test Board \/
		// Drive Motors
		public static final int FRONT_RIGHT_WHEEL_CAN_TALON = 26;
		public static final int FRONT_LEFT_WHEEL_CAN_TALON = 28;
		public static final int BACK_RIGHT_WHEEL_CAN_TALON = 27;
		public static final int BACK_LEFT_WHEEL_CAN_TALON = 25;
		
		// TODO May Become Unused 
		public static final int TURNING_WHEEL_CAN_TALON = 1;
		
		// Shooter motors
		public static final int COLLECTOR_MOTOR_CAN_TALON = 29;
		public static final int SHOOTER_MOTOR_CAN_TALON = 1;
		public static final int ARM_CAN_TALON = UNINITIALIZED_MOTOR;

		// Used on relay to only turn full speed or full negative speed.
		public static final int SPIKE_MOTOR_RELAY = UNINITIALIZED_MOTOR;

		// Ultrisonic Sensor
		public static final int ULTRASONIC_ANALOG = 1;
	*/
}
