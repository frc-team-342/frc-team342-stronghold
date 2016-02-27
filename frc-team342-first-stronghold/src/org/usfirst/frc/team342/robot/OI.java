package org.usfirst.frc.team342.robot;

import org.usfirst.frc.team342.robot.commands.camera.ChangeCamera;
import org.usfirst.frc.team342.robot.commands.drive.DriveChange_Reverse;
import org.usfirst.frc.team342.robot.commands.shootersystem.arm.ArmIn;
import org.usfirst.frc.team342.robot.commands.shootersystem.arm.ArmOut;
import org.usfirst.frc.team342.robot.commands.shootersystem.collector.CollectBall;
import org.usfirst.frc.team342.robot.commands.shootersystem.collector.CollectorOut;
import org.usfirst.frc.team342.robot.commands.shootersystem.collector.StopCollector;
import org.usfirst.frc.team342.robot.commands.shootersystem.shoot.ShootHighPower;
import org.usfirst.frc.team342.robot.commands.shootersystem.shoot.ShootLowPower;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

/** This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the
 * robot. */
public class OI {
	/** This is a somewhat awkward class. It is a wrapper for passing
	 * commands to the scheduler using Triggers, so it's code should only be
	 * one once or else the scheduler would have two copies of the
	 * buttons. */
	private static final OI instance = new OI();

	/** Create joystick from port 0 and 1. */
	// TODO Document our controller here
	private final Joystick drive;
	private final Joystick alternate;

	// Map Joypad Buttons
	// TODO Finish Mapping Buttons
	private static final int X_BUTTON = 1;
	private static final int A_BUTTON = 2;
	private static final int B_BUTTON = 3;
	// private static final int Y_BUTTON = 4;

	private static final int LEFT_BUMPER = 5;
	private static final int RIGHT_BUMPER = 6;
	private static final int LEFT_TRIGGER = 7;
	private static final int RIGHT_TRIGGER = 8;

	private static final int START_BUTTON = 10;
	private static final int BACK_BUTTON = 9;

	// private static final int NO_BUTTON = 100;

	private OI() {
		drive = new Joystick(0);
		alternate = new Joystick(1);

		// TODO Decide which buttons to map to what commands

		// Camera
		mapCommand(START_BUTTON, new ChangeCamera(), false, drive);

		// Drive
		mapCommand(BACK_BUTTON, new DriveChange_Reverse(), false, drive);

		// Arm
		mapCommand(LEFT_TRIGGER, new ArmIn(false), true, alternate);
		mapCommand(LEFT_BUMPER, new ArmOut(false), true, alternate);

		// Collector
		mapCommand(X_BUTTON, new CollectorOut(), true, alternate);
		mapCommand(A_BUTTON, new CollectBall(), false, alternate);
		mapCommand(B_BUTTON, new StopCollector(), false, alternate);

		// Shooter
		mapCommand(RIGHT_TRIGGER, new ShootHighPower(), true, alternate);
		mapCommand(RIGHT_BUMPER, new ShootLowPower(), true, alternate);
	}

	/** Makes sure the instance has been created. */
	public static void initInstance() {
		// Does nothing other than initializing instance if it isn't
		// already initialized.
		instance.toString();
	}

	/** Simplifies mapping commands to buttons.
	 * 
	 * @param buttonNumber
	 *            The number of the button on the gamepad
	 * @param command
	 *            The command to be run when the button is pressed. */
	private void mapCommand(int buttonNumber, Command command,
			boolean stopWhenReleased, Joystick joypad) {

		JoystickButton button = new JoystickButton(joypad, buttonNumber);
		if (stopWhenReleased) {
			button.whileHeld(command);
		} else {
			button.whenPressed(command);
		}
	}

}
