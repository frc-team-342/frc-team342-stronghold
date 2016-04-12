package org.usfirst.frc.team342.robot.commands.drive;

import org.usfirst.frc.team342.robot.subsystems.DriveSystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class DriveWithJoystick extends Command {
	private static final int LEFT_STICK = Joystick.AxisType.kY.value;
	private static final int RIGHT_STICK = Joystick.AxisType.kY.value;

	private static final double DEAD_ZONE = 0.3;

	private static final int JOY_STICK_1 = 0;
	private static final int JOY_STICK_2 = 1;

	private DriveSystem drive;
	/** Used to access joystick position. */
	private Joystick joystick1;
	private Joystick joystick2;

	/**
	 * Drive updates speed based on the joystick values
	 * 
	 * @param joy
	 *            The joystick event from the joystick monitor class. Used to
	 *            update the value of the joystick.
	 */
	public DriveWithJoystick() {
		drive = DriveSystem.getInstance();
		joystick1 = new Joystick(JOY_STICK_1);
		joystick2 = new Joystick(JOY_STICK_2);

		requires(drive);
	}

	protected void initialize() {
	}

	protected void execute() {
		double left = joystick1.getRawAxis(LEFT_STICK);
		double right = joystick2.getRawAxis(RIGHT_STICK);

		if (Math.abs(left) < DEAD_ZONE) {
			left = 0;
		}

		if (Math.abs(right) < DEAD_ZONE) {
			right = 0;
		}

		drive.tankDrive(left, right);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
	}

	protected void interrupted() {

	}
}
