package org.usfirst.frc.team342.robot;

import org.usfirst.frc.team342.robot.commandgroups.DebugInfo;
import org.usfirst.frc.team342.robot.commands.drive.ReverseRobotOrientation;
import org.usfirst.frc.team342.robot.commands.shootersystem.arm.ArmForceForward;
import org.usfirst.frc.team342.robot.commands.shootersystem.arm.ArmMovement;
import org.usfirst.frc.team342.robot.commands.shootersystem.arm.ArmMovement.ArmPosition;
import org.usfirst.frc.team342.robot.commands.shootersystem.arm.MoveBackward;
import org.usfirst.frc.team342.robot.commands.shootersystem.arm.StopArm;
import org.usfirst.frc.team342.robot.commands.shootersystem.collector.CollectBall;
import org.usfirst.frc.team342.robot.commands.shootersystem.collector.CollectorOut;
import org.usfirst.frc.team342.robot.commands.shootersystem.collector.StopCollector;
import org.usfirst.frc.team342.robot.commands.shootersystem.shoot.ShootHighPower;
import org.usfirst.frc.team342.robot.commands.shootersystem.shoot.ShootLowPower;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	/**
	 * This is a somewhat awkward class. It is a wrapper for passing commands to
	 * the scheduler using Triggers, so it's code should only be one once or
	 * else the scheduler would have two copies of the buttons.
	 */
	private static final OI instance = new OI();

	/** Create joystick from port 0 and 1. */
	// TODO Document our controller here
	private final Joystick driveLeft;
	private final Joystick driveRight;

	private final Joystick joypad;

	// Map Joypad Buttons
	// TODO Finish Mapping Buttons
	// private static final int JOYSTICK_TRIGGER = 1;
	private static final int JOYSTICK_SWITCH_CAM = 2;

	private static final int X_BUTTON = 1;
	private static final int A_BUTTON = 2;
	private static final int B_BUTTON = 3;
	private static final int Y_BUTTON = 4;

	private static final int LEFT_BUMPER = 5;
	private static final int RIGHT_BUMPER = 6;
	private static final int LEFT_TRIGGER = 7;
	private static final int RIGHT_TRIGGER = 8;

	private static final int START_BUTTON = 10;
	// private static final int BACK_BUTTON = 9;

	private static final int LEFT_STICK_BUTTON = 11;
	private static final int RIGHT_STICK_BUTTON = 12;

	private OI() {
		driveLeft = new Joystick(0);
		driveRight = new Joystick(1);
		joypad = new Joystick(2);

		// Drive
		mapCommand(JOYSTICK_SWITCH_CAM, new ReverseRobotOrientation(), false, driveLeft);
		mapCommand(JOYSTICK_SWITCH_CAM, new ReverseRobotOrientation(), false, driveRight);

		// Arm
		mapCommand(LEFT_TRIGGER, new ArmMovement(ArmPosition.FULL_IN, true), true, joypad);
		mapCommand(LEFT_BUMPER, new ArmMovement(ArmPosition.FULL_DOWN, true), true, joypad);
		mapCommand(LEFT_STICK_BUTTON, new MoveBackward(), true, joypad);
		mapCommand(START_BUTTON, new ArmForceForward(), true, joypad);
		mapCommand(Y_BUTTON, new StopArm(false), true, joypad);
		// Collector
		mapCommand(X_BUTTON, new CollectorOut(), true, joypad);
		mapCommand(A_BUTTON, new CollectBall(), false, joypad);
		mapCommand(B_BUTTON, new StopCollector(), false, joypad);

		// Shooter
		mapCommand(RIGHT_TRIGGER, new ShootHighPower(), true, joypad);
		mapCommand(RIGHT_BUMPER, new ShootLowPower(), true, joypad);

		// Debug
		mapCommand(RIGHT_STICK_BUTTON, new DebugInfo(), true, joypad);
	}

	/** Makes sure the instance has been created. */
	public static void initInstance() {
		// Does nothing other than initializing instance if it isn't
		// already initialized.
		instance.toString();
	}

	/**
	 * Simplifies mapping commands to buttons.
	 * 
	 * @param buttonNumber
	 *            The number of the button on the gamepad.
	 * @param command
	 *            The command to be run when the button is pressed.
	 */
	private static void mapCommand(int buttonNumber, Command command, boolean stopWhenReleased, Joystick joypad) {
		JoystickButton button = new JoystickButton(joypad, buttonNumber);
		if (stopWhenReleased) {
			button.whileHeld(command);
		} else {
			button.whenPressed(command);
		}
	}
}
