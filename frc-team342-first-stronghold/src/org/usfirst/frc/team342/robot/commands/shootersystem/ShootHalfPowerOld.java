package org.usfirst.frc.team342.robot.commands.shootersystem;

import org.usfirst.frc.team342.robot.OI;
import org.usfirst.frc.team342.robot.subsystems.ShooterSystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class ShootHalfPowerOld extends Command{
	
	private Joystick joypad;
	
	private ShooterSystem shooter = ShooterSystem.getInstance();


	public ShootHalfPowerOld() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		OI oi = OI.getInstance();
		joypad = oi.getJoypad();
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		double speed = 0.5;
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
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
