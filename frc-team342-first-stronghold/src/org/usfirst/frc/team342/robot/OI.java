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
	//Declare Joypad
	private static Joystick joypad;
	private static final OI INSTANCE = new OI();
	
	//Map Joypad
	private final int DRIVE_JOYPAD = 0;
	
	//Map Joypad Buttons
	private final int JOYPAD_BUTTON_1 = 1;
	private final int JOYPAD_BUTTON_2 = 2;
	private final int JOYPAD_BUTTON_3 = 3;
	private final int JOYPAD_BUTTON_4 = 4;
	private final int JOYPAD_BUTTON_5 = 5;
	private final int JOYPAD_BUTTON_6 = 6;
	private final int JOYPAD_BUTTON_7 = 7;
	private final int JOYPAD_BUTTON_8 = 8;
	private final int JOYPAD_BUTTON_9 = 9;
	private final int JOYPAD_BUTTON_10 = 10;		
	
	private OI(){
		//Declare Joysticks
		joypad = new Joystick(DRIVE_JOYPAD);
		
		//OuputText
		JoystickButton TEXT_OUTPUT = new JoystickButton(joypad, JOYPAD_BUTTON_1);
		
		TEXT_OUTPUT.whileHeld(new TextOuput());
		
		//Change Camera
		JoystickButton Change_Camera = new JoystickButton(joypad, JOYPAD_BUTTON_2);
		
		Change_Camera.whenPressed(new ChangeCameraOld());
		
		//Collector Commands
		JoystickButton CollectorIn = new JoystickButton(joypad, JOYPAD_BUTTON_6);
		
		CollectorIn.whenPressed(new CollectorInOld());
		
		JoystickButton CollectorOut = new JoystickButton(joypad, JOYPAD_BUTTON_5);
		
		CollectorOut.whenPressed(new CollectorOutOld());
		
		//Shooter Commands
		JoystickButton ShooterFull = new JoystickButton(joypad, JOYPAD_BUTTON_8);
		
		ShooterFull.whileHeld(new ShootFullPowerOld());
		
		JoystickButton ShooterHalf = new JoystickButton(joypad, JOYPAD_BUTTON_7);
		
		ShooterHalf.whileHeld(new ShootHalfPowerOld());
	}
	
	public static OI getInstance(){
		return INSTANCE;
	}
	public static Joystick getJoypad(){
		return joypad;
	}
}

