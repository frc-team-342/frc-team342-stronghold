package org.usfirst.frc.team342.robot.commands.drive;

import org.usfirst.frc.team342.robot.subsystems.DriveSystem;

import edu.wpi.first.wpilibj.command.Command;

public class DriveStop extends Command {
	DriveSystem drive;

	public DriveStop() {
		drive = DriveSystem.getInstance();
		requires(drive);
	}

	protected void initialize() {
		drive.stop();
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
	}

	protected void interrupted() {
		drive.stop();
	}
}
