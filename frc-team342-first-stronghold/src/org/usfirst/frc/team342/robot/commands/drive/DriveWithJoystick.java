package org.usfirst.frc.team342.robot.commands.drive;

import org.usfirst.frc.team342.robot.subsystems.DriveSystem;
import org.usfirst.frc.team342.robot.util.JoystickMonitor.JoystickEvent;

import edu.wpi.first.wpilibj.command.Command;

public class DriveWithJoystick extends Command {
	private DriveSystem drive;
	/** Used to access joystick position. */
	private JoystickEvent event;

	/** Drive updates speed based on the joystick values
	 * 
	 * @param e
	 *            The joystick event from the joystick monitor class. Used
	 *            to update the value of the joystick. */
	public DriveWithJoystick(JoystickEvent e) {
		drive = DriveSystem.getInstance();
		event = e;

		requires(drive);
	}

	protected void initialize() {
		event.updateValue();

		double left = event.left();
		double right = event.right();

		drive.tankDrive(left, right);
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
