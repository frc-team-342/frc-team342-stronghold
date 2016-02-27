package org.usfirst.frc.team342.robot.subsystems;

import org.usfirst.frc.team342.robot.RobotMap;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveSystem extends Subsystem {
	private static final DriveSystem instance = new DriveSystem();

	/** Drive wheels. */
	private final CANTalon frontRightWheel;
	private final CANTalon frontLeftWheel;
	private final CANTalon backRightWheel;
	private final CANTalon backLeftWheel;

	/** Drive system to control the wheels. */
	private final RobotDrive drive;

	/** Navx device from kauilabs. */
	private final AHRS navx;

	private final AnalogInput ultrasonic;
	private boolean driveReversed;

	/** Controls and senses driving of the robot. */
	private DriveSystem() {
		frontRightWheel = new CANTalon(RobotMap.FRONT_RIGHT_WHEEL_CAN_TALON);
		frontLeftWheel = new CANTalon(RobotMap.FRONT_LEFT_WHEEL_CAN_TALON);
		backRightWheel = new CANTalon(RobotMap.BACK_RIGHT_WHEEL_CAN_TALON);
		backLeftWheel = new CANTalon(RobotMap.BACK_LEFT_WHEEL_CAN_TALON);

		frontRightWheel.setInverted(true);
		backLeftWheel.setInverted(true);

		drive = new RobotDrive(frontLeftWheel, backLeftWheel, frontRightWheel,
				backRightWheel);
		drive.setSafetyEnabled(false);

		navx = new AHRS(SerialPort.Port.kMXP);

		ultrasonic = new AnalogInput(RobotMap.ULTRASONIC_ANALOG);
		driveReversed = false;
	}

	public static DriveSystem getInstance() {
		return instance;
	}

	public void tankDrive(double leftSpeed, double rightSpeed) {
		// TODO Move the gryo data somewhere else
		SmartDashboard.putData("Gyro", navx);

		if (driveReversed) {
			drive.tankDrive(leftSpeed * -1, rightSpeed * -1);
		} else {
			drive.tankDrive(leftSpeed, rightSpeed);
		}
	}

	public void setDriveReverse(boolean reverse) {
		driveReversed = reverse;
	}

	public boolean getDriveReversed() {
		return driveReversed;
	}

	public void drive(double speed, double angle) {
		drive.drive(speed, angle);
	}

	public void resetGyro() {
		navx.reset();
	}

	public double getGyro() {
		return navx.getAngle();
	}

	public double getUltrasonic() {
		return ultrasonic.getVoltage();
	}

	/** Set all motor values to zero. */
	public void stop() {
		drive.stopMotor();
	}

	@Override
	protected void initDefaultCommand() {
	}

}
