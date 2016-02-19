package org.usfirst.frc.team342.robot.commands.drive;

import org.usfirst.frc.team342.robot.subsystems.DriveSystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ReverseRobotOrientation extends Command {

	private DriveSystem drive;

	public ReverseRobotOrientation() {
		drive = DriveSystem.getInstance();
		// requires(drive);
	}

	// Called just before this Command runs the first time
	protected void initialize() {

		boolean reverse = drive.getDriveReversed();
		drive.setDriveReverse(!reverse);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	protected void end() {
		drive.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
