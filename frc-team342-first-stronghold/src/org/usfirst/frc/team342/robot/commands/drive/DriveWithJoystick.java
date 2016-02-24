package org.usfirst.frc.team342.robot.commands.drive;

import org.usfirst.frc.team342.robot.subsystems.DriveSystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class DriveWithJoystick extends Command {
	private static final int LEFT_STICK = Joystick.AxisType.kY.value;
	private static final int RIGHT_STICK = Joystick.AxisType.kTwist.value;

	private static final int JOY_PAD_NUMBER = 0;

	private static final double JOYSTICK_DEADZONE = 0.25;

	private DriveSystem drive;
	/** Used to access joystick position. */
	private Joystick joystick;

	/**
	 * Drive updates speed based on the joystick values
	 * 
	 * @param joy
	 *            The joystick event from the joystick monitor class. Used to
	 *            update the value of the joystick.
	 */
	public DriveWithJoystick() {
		drive = DriveSystem.getInstance();
		joystick = new Joystick(JOY_PAD_NUMBER);

		requires(drive);
	}

	protected void initialize() {
	}

	protected void execute() {
		double left = joystick.getRawAxis(LEFT_STICK);
		double right = joystick.getRawAxis(RIGHT_STICK);

		// Detect the deadzone.
		if (Math.abs(left) + Math.abs(right) > JOYSTICK_DEADZONE) {
			drive.tankDrive(left, right);
		} else {
			drive.tankDrive(0, 0);
		}
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
	}

	protected void interrupted() {

	}
}
