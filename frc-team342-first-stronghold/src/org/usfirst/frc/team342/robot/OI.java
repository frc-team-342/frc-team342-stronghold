package org.usfirst.frc.team342.robot;

import org.usfirst.frc.team342.robot.commands.camera.ChangeCamera;
import org.usfirst.frc.team342.robot.commands.drive.DriveStop;
import org.usfirst.frc.team342.robot.commands.drive.DriveWithJoystick;
import org.usfirst.frc.team342.robot.commands.drive.LowerTurnWheel;
import org.usfirst.frc.team342.robot.commands.drive.RaiseTurnWheel;
import org.usfirst.frc.team342.robot.commands.shootersystem.arm.ArmIn;
import org.usfirst.frc.team342.robot.commands.shootersystem.arm.ArmOut;
import org.usfirst.frc.team342.robot.commands.shootersystem.arm.StopArm;
import org.usfirst.frc.team342.robot.commands.shootersystem.collector.CollectBall;
import org.usfirst.frc.team342.robot.commands.shootersystem.collector.CollectorIn;
import org.usfirst.frc.team342.robot.commands.shootersystem.collector.CollectorOut;
import org.usfirst.frc.team342.robot.commands.shootersystem.collector.PushBall;
import org.usfirst.frc.team342.robot.commands.shootersystem.collector.StopCollector;
import org.usfirst.frc.team342.robot.commands.shootersystem.shoot.ShootHighPower;
import org.usfirst.frc.team342.robot.commands.shootersystem.shoot.ShootLowPower;
import org.usfirst.frc.team342.robot.commands.shootersystem.shoot.StopShooter;
import org.usfirst.frc.team342.robot.util.JoystickMonitor;

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

	/** Create joystick from port 0. */
	// TODO Document our controller here
	private static final Joystick joypad = new Joystick(0);

	// Map Joypad Buttons
	// TODO Finish Mapping Buttons
	private static final int X_BUTTON = 1;
	private static final int A_BUTTON = 2;
	private static final int B_BUTTON = 3;
	private static final int Y_BUTTON = 4;

	private static final int LEFT_BUMPER = 5;
	private static final int RIGHT_BUMPER = 6;
	private static final int LEFT_TRIGGER = 7;
	private static final int RIGHT_TRIGGER = 8;

	private static final int NO_BUTTON = -1;

	private OI() {
		// Start the drive joystick
		enableJoystickDetection();

		// TODO Decide which buttons to map to what commands

		// Camera
		mapCommand(X_BUTTON, new ChangeCamera());

		// Drive
		mapCommand(A_BUTTON, new LowerTurnWheel(), new DriveStop());
		mapCommand(B_BUTTON, new RaiseTurnWheel(), new DriveStop());

		// Arm
		mapCommand(Y_BUTTON, new ArmIn(), new StopArm());
		mapCommand(RIGHT_BUMPER, new ArmOut(), new StopArm());

		// Collector
		mapCommand(LEFT_BUMPER, new CollectorIn(), new StopCollector());
		mapCommand(RIGHT_TRIGGER, new CollectorOut(), new StopCollector());
		mapCommand(LEFT_TRIGGER, new CollectBall());
		mapCommand(NO_BUTTON, new PushBall());

		// Shooter
		mapCommand(NO_BUTTON, new ShootHighPower(), new StopShooter());
		mapCommand(NO_BUTTON, new ShootLowPower(), new StopShooter());
	}

	/** Makes sure the instance has been created. */
	public static void initInstance() {
		// Does nothing other than initializing instance if it isn't
		// already initialized. */
		instance.notifyAll();
	}

	/** Enables teleop driving with joystick using a home-made Trigger for
	 * joystick detection. */
	private void enableJoystickDetection() {
		// Create monitor to send commands when the joystick is used.
		JoystickMonitor mon = new JoystickMonitor(joypad, false);

		// Drive with joystick values when the joystick is in use.
		DriveWithJoystick driveStartCommand = new DriveWithJoystick(
				mon.getLastEvent());
		mon.whileActive(driveStartCommand);

		// Stop when the joystick is in the deadzone.
		DriveStop driveStopCommand = new DriveStop();
		mon.whenInactive(driveStopCommand);
	}

	/** Simplifies mapping commands to buttons.
	 * 
	 * @param buttonNumber
	 *            The number of the button on the gamepad
	 * @param command
	 *            The command to be run when the button is pressed. */
	private void mapCommand(int buttonNumber, Command command) {
		JoystickButton button = new JoystickButton(joypad, buttonNumber);
		button.whenPressed(command);
	}

	/** Simplifies of the mapping to buttons.
	 * 
	 * @param buttonNumber
	 *            The number of the button on the gamepad.
	 * @param whenPressed
	 *            The command to be run when the button is pressed.
	 * @param WhenReleased
	 *            The command to be run when the button is released. */
	private void mapCommand(int buttonNumber, Command whenPressed,
			Command WhenReleased) {
		JoystickButton button = new JoystickButton(joypad, buttonNumber);
		button.whenPressed(whenPressed);
		button.whenReleased(WhenReleased);
	}
}
