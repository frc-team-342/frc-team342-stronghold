package org.usfirst.frc.team342.robot.subsystems;

import org.usfirst.frc.team342.robot.RobotMap;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveSystem extends Subsystem {
	/** Smooths stopping quickly */
	private static final double VOLTAGE_RAMP = 0.0;
	private static final double DRIVE_SCALE = 1.0;

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

	private boolean driveReversed;

	/** Controls and senses driving of the robot. */
	private DriveSystem() {
		frontRightWheel = new CANTalon(RobotMap.FRONT_RIGHT_WHEEL_CAN_TALON);
		frontLeftWheel = new CANTalon(RobotMap.FRONT_LEFT_WHEEL_CAN_TALON);
		backRightWheel = new CANTalon(RobotMap.BACK_RIGHT_WHEEL_CAN_TALON);
		backLeftWheel = new CANTalon(RobotMap.BACK_LEFT_WHEEL_CAN_TALON);

		// Ramping to make stopping smoother
		frontLeftWheel.setVoltageRampRate(VOLTAGE_RAMP);
		backLeftWheel.setVoltageRampRate(VOLTAGE_RAMP);
		frontRightWheel.setVoltageRampRate(VOLTAGE_RAMP);
		backRightWheel.setVoltageRampRate(VOLTAGE_RAMP);

		frontRightWheel.setInverted(true);
		backLeftWheel.setInverted(true);

		drive = new RobotDrive(frontLeftWheel, backLeftWheel, frontRightWheel, backRightWheel);
		drive.setSafetyEnabled(false);

		navx = new AHRS(SerialPort.Port.kMXP);
		navx.startLiveWindowMode();

		driveReversed = false;
	}

	public static DriveSystem getInstance() {
		return instance;
	}

	public void tankDrive(double leftSpeed, double rightSpeed) {
		if (driveReversed) {
			// Swaps left and right on purpose. Raise your right hand in a
			// mirror and you will see why.
			drive.tankDrive(rightSpeed * -1 * DRIVE_SCALE, leftSpeed * -1 * DRIVE_SCALE);
		} else {
			drive.tankDrive(leftSpeed * DRIVE_SCALE, rightSpeed * DRIVE_SCALE);
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

	public double getHeight() {
		return navx.getPitch();
	}

	public double getFrontRightCurrent() {
		return frontRightWheel.getOutputCurrent();
	}

	public double getFrontLeftCurrent() {
		return frontLeftWheel.getOutputCurrent();
	}

	public double getBackRightCurrent() {
		return backRightWheel.getOutputCurrent();
	}

	public double getBackLeftCurrent() {
		return backLeftWheel.getOutputCurrent();
	}

	/** Set all motor values to zero. */
	public void stop() {
		drive.stopMotor();
	}

	@Override
	protected void initDefaultCommand() {
	}
}
