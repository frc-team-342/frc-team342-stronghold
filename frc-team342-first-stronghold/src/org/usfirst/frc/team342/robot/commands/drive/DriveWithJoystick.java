package org.usfirst.frc.team342.robot.commands.drive;

import org.usfirst.frc.team342.robot.OI;
import org.usfirst.frc.team342.robot.subsystems.DriveSystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class DriveWithJoystick extends Command{

	private Joystick joypad;
	
	private DriveSystem drive = DriveSystem.getInstance();
	
	public DriveWithJoystick() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		OI oi = OI.getInstance();
		joypad = OI.getJoypad();
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		drive.DriveWithJoypad(joypad);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		drive.Stop();
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		end();
	}

}
