package org.usfirst.frc.team342.robot.commands.drive;

import org.usfirst.frc.team342.robot.subsystems.DriveSystem;

import edu.wpi.first.wpilibj.command.Command;

public class LowerTurnWheel extends Command {
	private static final double SPEED = -0.8;
	DriveSystem drive;

	/** Lowers the wheel to allow the robot to turn better. */
	public LowerTurnWheel() {
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
