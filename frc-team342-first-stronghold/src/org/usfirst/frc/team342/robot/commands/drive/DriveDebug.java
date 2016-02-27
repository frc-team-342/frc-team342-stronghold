package org.usfirst.frc.team342.robot.commands.drive;

import org.usfirst.frc.team342.robot.subsystems.DriveSystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.communication.FRCNetworkCommunicationsLibrary;

public class DriveDebug extends Command {
	
	DriveSystem drive;

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		drive = DriveSystem.getInstance();
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		
		FRCNetworkCommunicationsLibrary.HALSetErrorData("\nDrive Back Left" + drive.getBackLeftCurrent());
		FRCNetworkCommunicationsLibrary.HALSetErrorData("\nDrive Back Right" + drive.getBackRightCurrent());
		FRCNetworkCommunicationsLibrary.HALSetErrorData("\nDrive Front Left" + drive.getFrontLeftCurrent());
		FRCNetworkCommunicationsLibrary.HALSetErrorData("\nDrive Front Right" + drive.getFrontRightCurrent());
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
