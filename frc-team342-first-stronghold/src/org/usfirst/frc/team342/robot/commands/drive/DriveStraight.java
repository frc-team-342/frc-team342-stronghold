package org.usfirst.frc.team342.robot.commands.drive;

import org.usfirst.frc.team342.robot.subsystems.DriveSystem;

import edu.wpi.first.wpilibj.command.Command;

public class DriveStraight extends Command {
	/**
	 * Time (in seconds) the robot should drive straight during autonomous.
	 */
	private static final double RUN_TIME = 6;
	/**
	 * Proportional correction constant. Higher values give faster correction,
	 * but may cause over-steering.
	 */
	private static final double KP = 0.5;
	private static final double SPEED = 0.7;

	private DriveSystem drive;

	/**
	 * Drive with correction, useful for rough terrain. As long as the robot
	 * continues to go straight, the heading will be zero. Uses the gyro to keep
	 * the robot on course by modifying the turn parameter of the drive method
	 * based on a constant of proportionality. <br>
	 * <a href=https://wpilib.screenstepslive.com/s/3120/m/7912/l/85772-
	 * gyros-to- control-robot-driving-direction> Adapted from sample code</a>
	 */
	public DriveStraight() {
		drive = DriveSystem.getInstance();
		requires(drive);
	}

	@Override
	protected void initialize() {
		// The gyro must be initialized to zero for correction to work.
		drive.resetGyro();
	}

	@Override
	protected void execute() {
		double angle = drive.getGyro(); // get current heading
		drive.drive(SPEED, -angle * KP); // drive towards heading 0
	}

	/** Runs on a timer. */
	@Override
	protected boolean isFinished() {
		return timeSinceInitialized() <= RUN_TIME;
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
