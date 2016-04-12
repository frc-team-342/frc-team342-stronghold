<<<<<<< HEAD
package org.usfirst.frc.team342.robot.commands.drive;

import org.usfirst.frc.team342.robot.subsystems.DriveSystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Reduces risk of missing a defense by moving forward a little to avoid
 * breaking.
 */
public class DriveSlow extends Command {
	private static final double SPEED = -0.4;
	/** Amount of time to drive. */
	private static final double DELAY = 4.0;

	private boolean direction;

	private DriveSystem drive;

	private double startTime;

	public DriveSlow(boolean awayFromArm) {
		drive = DriveSystem.getInstance();

		direction = awayFromArm;
	}

	@Override
	protected void initialize() {
		startTime = timeSinceInitialized();
	}

	@Override
	protected void execute() {
		double speed = direction ? SPEED : -1.0 * SPEED;
		drive.tankDrive(speed, speed);
	}

	@Override
	protected boolean isFinished() {
		return timeSinceInitialized() > startTime + DELAY;
	}

	@Override
	protected void end() {
		drive.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
=======
package org.usfirst.frc.team342.robot.commands.drive;

import org.usfirst.frc.team342.robot.subsystems.DriveSystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Reduces risk of missing a defense by moving forward a little to avoid
 * breaking.
 */
public class DriveSlow extends Command {
	private static final double SPEED = -0.4;
	/** Amount of time to drive. */
	private static final double DELAY = 4.0;

	private boolean direction;

	private DriveSystem drive;

	private double startTime;

	public DriveSlow(boolean awayFromArm) {
		drive = DriveSystem.getInstance();

		direction = awayFromArm;
	}

	@Override
	protected void initialize() {
		startTime = timeSinceInitialized();
	}

	@Override
	protected void execute() {
		double speed = direction ? SPEED : -1.0 * SPEED;
		drive.tankDrive(speed, speed);
	}

	@Override
	protected boolean isFinished() {
		return timeSinceInitialized() > startTime + DELAY;
	}

	@Override
	protected void end() {
		drive.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
>>>>>>> origin/master
