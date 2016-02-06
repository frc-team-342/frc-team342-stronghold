package org.usfirst.frc.team342.robot.commands.shootersystem;

import org.usfirst.frc.team342.robot.OI;
import org.usfirst.frc.team342.robot.subsystems.ShooterSystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.communication.FRCNetworkCommunicationsLibrary;

public class Shoot extends Command {
	
//WE need code names
	private Joystick joypad;
	
	private ShooterSystem shooter = ShooterSystem.getInstance();

	public Shoot() {
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
		boolean fullPower = joypad.getRawButton(8);
		boolean powerModifier = joypad.getRawButton(7);
		
		double speed = 0;
		
		
		if(fullPower == true && powerModifier == false){
				speed = 1.0;
				FRCNetworkCommunicationsLibrary.HALSetErrorData("Full Power");
			}
		else if(fullPower == true && powerModifier == true){
				speed = 0.5;
				FRCNetworkCommunicationsLibrary.HALSetErrorData("Half Power");

		}
		shooter.shooter(speed);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		shooter.shooterStop();
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
