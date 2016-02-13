
package org.usfirst.frc.team342.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import org.usfirst.frc.team342.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.communication.FRCNetworkCommunicationsLibrary;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveSystem extends Subsystem {
	private static final double Kp = 0.5; // https://wpilib.screenstepslive.com/s/3120/m/7912/l/85772-gyros-to-control-robot-driving-direction
	public static final DriveSystem INSTANCE = new DriveSystem();
	private final AnalogInput ultrasonic;
	private final CANTalon frontRightWheel;
	private final CANTalon frontLeftWheel;
	private final CANTalon backRightWheel;
	private final CANTalon backLeftWheel;
	private final CANTalon fifthWheel;
	/** Creates Robot Drive From Motors */
	private final RobotDrive drive;

	// gyro stuff
	private final AHRS navx;

	private DriveSystem() {
		super();
		frontRightWheel = new CANTalon(RobotMap.TALON_FRONT_RIGHT_WHEEL_CAN);
		frontLeftWheel = new CANTalon(RobotMap.TALON_FRONT_LEFT_WHEEL_CAN);
		backRightWheel = new CANTalon(RobotMap.TALON_BACK_RIGHT_WHEEL_CAN);
		backLeftWheel = new CANTalon(RobotMap.TALON_BACK_LEFT_WHEEL_CAN);
		fifthWheel = new CANTalon(RobotMap.TALON_TURNING_WHEEL_CAN);
		ultrasonic = new AnalogInput(RobotMap.ULTRASONIC_ANALOG);

		drive = new RobotDrive(frontLeftWheel, backLeftWheel, frontRightWheel, backRightWheel);
		drive.setSafetyEnabled(false);
		navx = new AHRS(SerialPort.Port.kMXP);

	}

	public static DriveSystem getInstance() {
		return INSTANCE;
	}

	public void DriveWithJoypad(Joystick joypad) {
		double x = joypad.getRawAxis(0);
		double y = joypad.getRawAxis(1);
		double z = joypad.getRawAxis(2);
		double zrot = joypad.getRawAxis(3);
		double combined = x + y + z + zrot;
		double deadzone = 0.15;
		double driveMultiplier = 0.80;
		double fifth_wheel = 1.0;
		SmartDashboard.putData("Gyro", navx);
		double total = Math.abs(y) + Math.abs(zrot);
		int diz = ultrasonic.getValue();
		FRCNetworkCommunicationsLibrary.HALSetErrorData("\n" + diz);
		

		if (total > deadzone || total < deadzone * -1) {
			if (y * zrot < deadzone) {
				fifthWheel.set(fifth_wheel);
				drive.tankDrive(y, zrot);

			} else {
				drive.tankDrive(y * driveMultiplier, zrot * driveMultiplier);
				fifthWheel.set(fifth_wheel * -1);
			}
		} else {
			drive.tankDrive(0.0, 0.0);
		}

	}

	/**
	 * Drive with correction. As long as the robot continues to go straight, the
	 * heading will be zero. This example uses the gyro to keep the robot on
	 * course by modifying the turn parameter of the Drive method from website
	 * see Kp above.
	 */
	public void driv3straight(double speed, double angle) {

		drive.drive(speed, -angle * Kp);

	}

	public void resetGyro() {
		navx.reset();
	}

	public double GetGyro() {
		return navx.getAngle();
	}

	public String[] anglesnstuff() {
		String[] text = new String[5];
		text[0] = "" + navx.getAngle();
		text[1] = "Alt" + navx.getAltitude();
		text[2] = "Gyr x" + navx.getRawGyroX();
		text[3] = "Gyr y" + navx.getRawGyroY();
		text[4] = "Gyr z" + navx.getRawGyroZ();
		return text;
	}

	public void Stop() {
		drive.stopMotor();
	}

	@Override
	protected void initDefaultCommand() {

	}

}
