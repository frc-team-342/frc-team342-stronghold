package org.usfirst.frc.team342.robot.commands.drive;

import org.usfirst.frc.team342.robot.subsystems.DriveSystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.communication.FRCNetworkCommunicationsLibrary;

public class DriveDebug extends Command {

	DriveSystem drive;

	@Override
	protected void initialize() {
		drive = DriveSystem.getInstance();
	}

	@Override
	protected void execute() {
		String backLeftWheel = "Drive Back Left " + drive.getBackLeftCurrent()
				+ "/n";
		String backRightWheel = ("Drive Back Right"
				+ drive.getBackRightCurrent() + "/n");
		String frontLeftWheel = "Drive Front Left" + drive.getFrontLeftCurrent()
				+ "/n";
		String frontRightWheel = "Drive Front Right"
				+ drive.getFrontRightCurrent() + "/n";

		FRCNetworkCommunicationsLibrary.HALSetErrorData(backLeftWheel);
		System.out.println(backLeftWheel);

		FRCNetworkCommunicationsLibrary.HALSetErrorData(backRightWheel);
		System.out.println(backRightWheel);

		FRCNetworkCommunicationsLibrary.HALSetErrorData(frontLeftWheel);
		System.out.println(frontLeftWheel);

		FRCNetworkCommunicationsLibrary.HALSetErrorData(frontRightWheel);
		System.out.println(frontRightWheel);
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}

}
