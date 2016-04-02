package org.usfirst.frc.team342.robot.commands.drive;

import org.usfirst.frc.team342.robot.subsystems.DriveSystem;

import edu.wpi.first.wpilibj.command.Command;

public class DriveSlow extends Command {
	private static final double SPEED = -0.4;
	private static final double DELAY = 2.0;

	private DriveSystem drive;

	private double startTime;

	public DriveSlow() {
		drive = DriveSystem.getInstance();
	}

	@Override
	protected void initialize() {
		startTime = timeSinceInitialized();
	}

	@Override
	protected void execute() {
		drive.tankDrive(SPEED, SPEED);
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
