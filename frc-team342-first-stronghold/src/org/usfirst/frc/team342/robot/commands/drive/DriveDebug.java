package org.usfirst.frc.team342.robot.commands.drive;

import org.usfirst.frc.team342.robot.subsystems.DriveSystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.communication.FRCNetworkCommunicationsLibrary;

public class DriveDebug extends Command {
	private DriveSystem drive;

	private double prevTime;

	@Override
	protected void initialize() {
		drive = DriveSystem.getInstance();

		prevTime = timeSinceInitialized();
	}

	@Override
	protected void execute() {
		// Only update at most 5 times a second
		if (timeSinceInitialized() > prevTime + 0.1) {
			String backLeftWheel = "Drive Back Left " + drive.getBackLeftCurrent() + "\n";
			String backRightWheel = ("Drive Back Right " + drive.getBackRightCurrent() + "\n");
			String frontLeftWheel = "Drive Front Left " + drive.getFrontLeftCurrent() + "\n";
			String frontRightWheel = "Drive Front Right " + drive.getFrontRightCurrent() + "\n";

			// String gyro_z = "Z Gyro " + drive.getHeight();

			FRCNetworkCommunicationsLibrary.HALSetErrorData(backLeftWheel);
			System.out.print(backLeftWheel);
			FRCNetworkCommunicationsLibrary.HALSetErrorData(backRightWheel);
			System.out.print(backRightWheel);
			FRCNetworkCommunicationsLibrary.HALSetErrorData(frontLeftWheel);
			System.out.print(frontLeftWheel);
			FRCNetworkCommunicationsLibrary.HALSetErrorData(frontRightWheel);
			System.out.print(frontRightWheel);

			// FRCNetworkCommunicationsLibrary.HALSetErrorData(gyro_z);
			// System.out.print(gyro_z);
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}
}
