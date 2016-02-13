package org.usfirst.frc.team342.robot;

import org.usfirst.frc.team342.robot.commands.camera.ChangeCameraOld;
import org.usfirst.frc.team342.robot.commands.drive.TextOuput;
//import org.usfirst.frc.team342.robot.commands.shootersystem.Collector;
import org.usfirst.frc.team342.robot.commands.shootersystem.CollectorInOld;
import org.usfirst.frc.team342.robot.commands.shootersystem.CollectorOutOld;
import org.usfirst.frc.team342.robot.commands.shootersystem.ShootFullPowerOld;
import org.usfirst.frc.team342.robot.commands.shootersystem.ShootHalfPowerOld;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	private static final OI INSTANCE = new OI();

	// Map Joypad
	private final int DRIVE_JOYPAD = 0;

	// Map Joypad Buttons
	private static final int X_BUTTON = 1;
	private static final int A_BUTTON = 2;
	private static final int B_BUTTON = 3;
	private static final int Y_BUTTON = 4;

	private static final int LEFT_BUMPER = 5;
	private static final int RIGHT_BUMPER = 6;
	private static final int LEFT_TRIGGER = 7;
	private static final int RIGHT_TRIGGER = 8;

	// Declare Joypad
	private Joystick joypad;

	private OI() {
		// Declare Joysticks
		joypad = new Joystick(DRIVE_JOYPAD);

		// OuputText
		JoystickButton TEXT_OUTPUT = new JoystickButton(joypad, X_BUTTON);

		// Change Camera
		JoystickButton Change_Camera = new JoystickButton(joypad, A_BUTTON);

		Change_Camera.whenPressed(new ChangeCameraOld());

		// Collector Commands
		JoystickButton CollectorIn = new JoystickButton(joypad, RIGHT_TRIGGER);

		CollectorIn.whenPressed(new CollectorInOld());

		JoystickButton CollectorOut = new JoystickButton(joypad, RIGHT_BUMPER);

		CollectorOut.whenPressed(new CollectorOutOld());

		// Shooter Commands
		JoystickButton ShooterFull = new JoystickButton(joypad, LEFT_TRIGGER);

		ShooterFull.whileHeld(new ShootFullPowerOld());

		JoystickButton ShooterHalf = new JoystickButton(joypad, LEFT_BUMPER);

		ShooterHalf.whileHeld(new ShootHalfPowerOld());
	}

	public static OI getInstance() {
		return INSTANCE;
	}

	public Joystick getJoypad() {
		return joypad;
	}
}
