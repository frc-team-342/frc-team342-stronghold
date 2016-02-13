package org.usfirst.frc.team342.robot.commands.drive;

import org.usfirst.frc.team342.robot.OI;
import org.usfirst.frc.team342.robot.subsystems.DriveSystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class DriveWithJoystick extends Command {
	private DriveSystem drive;
	private Joystick joypad;

	public DriveWithJoystick() {
		drive = DriveSystem.getInstance();
		joypad = OI.getInstance().getJoypad();
	}

	@Override
	protected void initialize() {
		requires(drive);
	}

	@Override
	protected void execute() {
		// TODO Determine if past dead zone

		// TODO Determine if turning

		// TODO Set Speed

		// TODO Act on turn
	}

	@Override
	protected boolean isFinished() {
		// Is already run in teleop
		return false;
	}

	@Override
	protected void end() {
		drive.Stop();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
