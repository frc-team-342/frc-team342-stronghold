package org.usfirst.frc.team342.robot.commands.drive;

import org.usfirst.frc.team342.robot.subsystems.DriveSystem;

import edu.wpi.first.wpilibj.command.Command;

public class RaiseTurnWheel extends Command {
	private static final double SPEED = 0.8;

	DriveSystem drive;

	/** Raises the turn wheel back up after it has been lowered. */
	public RaiseTurnWheel() {
		drive = DriveSystem.getInstance();
		requires(drive);
	}

	protected void initialize() {
	}

	protected void execute() {
		drive.setFifthWheel(SPEED);
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
