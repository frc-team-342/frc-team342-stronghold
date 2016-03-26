package org.usfirst.frc.team342.robot.commands.drive;

import org.usfirst.frc.team342.robot.subsystems.DriveSystem;

import edu.wpi.first.wpilibj.command.Command;

public class DriveStraight extends Command {
	/**
	 * Proportional correction constant. Higher values give faster correction,
	 * but may cause over-steering.
	 */
	private static final double KP = 0.7;
	private static final double SPEED = 0.8;

	private static final double PEAK_VALUE = -7.0;
	private static final double LEVEL_ZONE = 1.5;

	private DriveSystem drive;

	private int listCounter;
	private double[] gyroValues;
	private double initialAverage;

	private boolean pastPeak;
	private boolean pastDefense;

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

		gyroValues = new double[20];
	}

	@Override
	protected void initialize() {
		// The gyro must be initialized to zero for correction to work.
		drive.resetGyro();

		gyroValues = new double[20];

		initialAverage = 0;
		for (int i = 0; i < 20; i++) {
			gyroValues[i] = drive.getHeight();
			initialAverage += gyroValues[i];
		}
		initialAverage /= 20;

		listCounter = 0;

		pastPeak = false;
		pastDefense = false;
	}

	@Override
	protected void execute() {
		double angle = drive.getGyro(); // get current heading
		drive.drive(SPEED, -angle * KP); // drive towards heading 0

		gyroValues[listCounter++] = drive.getHeight();
		listCounter %= 20;

		double average = 0;
		for (double val : gyroValues) {
			average += val;
		}
		average /= 20;

		if (!pastPeak) {
			pastPeak = average - PEAK_VALUE > initialAverage;
			System.out.println("Past Peak");
		} else if (pastPeak) {
			pastDefense = average < Math.abs(initialAverage - LEVEL_ZONE);

			System.out.println("Defense passed");
		}
	}

	/** Runs on a timer. */
	@Override
	protected boolean isFinished() {
		return pastDefense;
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
